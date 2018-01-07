package pl.ciszemar.androidfirstapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import pl.ciszemar.androidfirstapp.dao.TaskRepository;
import pl.ciszemar.androidfirstapp.entity.Task;
import pl.ciszemar.androidfirstapp.ui.TaskAdapter;

public class ClosedTasksActivity extends AppCompatActivity {

    private static final int CLOSED_STATUS_TASK_ID = 2;
    private ArrayList<Task> tasks;
    private TaskRepository taskRepository = new TaskRepository();
    private TaskAdapter adapter;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_tasks);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tasks = new ArrayList<>();
        getTasksByStatusId(CLOSED_STATUS_TASK_ID);
        adapter = new TaskAdapter(this, new TaskAdapter.OnTaskInteractionListener() {

            @Override
            public void onTaskClicked(Task task) {
            }

            @Override
            public void onTaskLongClicked(Task task) {
            }
        });

        adapter.setData(tasks);
        recycler = findViewById(R.id.list);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getTasksByStatusId(int newStatusTaskId) {
        try {
            new ClosedTasksActivity.ListTaskAsync(newStatusTaskId).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private class ListTaskAsync extends AsyncTask<Void, Void, List<Task>> {

        private int statusId;

        public ListTaskAsync(int statusId) {
            this.statusId = statusId;
        }

        @Override
        protected List<Task> doInBackground(Void... voids) {
            ArrayList<Task> tempList = (ArrayList<Task>) taskRepository.getByStatus(statusId);
            for (Task item : tempList) {
                tasks.add(item);
            }
            return tasks;
        }
    }
}
