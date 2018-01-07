package pl.ciszemar.androidfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import pl.ciszemar.androidfirstapp.dao.TaskRepository;
import pl.ciszemar.androidfirstapp.entity.Task;
import pl.ciszemar.androidfirstapp.ui.TaskAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_STATUS_TASK_ID = 0;
    private static final int ON_PROGRESS_STATUS_TASK_ID = 1;

    private SharedPreferences prefs = null;
    private TaskRepository taskRepository = new TaskRepository();
    private TaskAdapter adapter;
    private RecyclerView recycler;
    private ActionMode actionMode;
    ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefs = getSharedPreferences("pl.ciszemar.androidfirstapp", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tasks = new ArrayList<>();
        if (prefs.getBoolean("firstrun", true)) {
            Toast.makeText(this, "Pierwsze uruchomienie", Toast.LENGTH_LONG).show();
            Task task = new Task();
            task.setId(1);
            task.setTheme("Temat");
            task.setDetails("Szczegóły");
            task.setStatusId(1);
            task.setPriorityId(1);
            task.setRemaindDate("2017-12-17");
            new NewTaskAsync(task).execute();
            prefs.edit().putBoolean("firstrun", false).commit();
        }

        getTasksByStatusId(NEW_STATUS_TASK_ID);
        getTasksByStatusId(ON_PROGRESS_STATUS_TASK_ID);

        adapter = new TaskAdapter(this, new TaskAdapter.OnTaskInteractionListener() {

            @Override
            public void onTaskClicked(Task task) {
                Toast.makeText(MainActivity.this, task.getTheme(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTaskLongClicked(Task task) {
                stattActionMode(task);
            }
        });
        adapter.setData(tasks);
        recycler = findViewById(R.id.list);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info: {
                Toast.makeText(this, "Pierwsza aplikacja dla systemu Andriod.", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.newTask: {
                Intent intent = new Intent(this, NewTaskActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.listClosedTask: {
                Intent intent = new Intent(this, ClosedTasksActivity.class);
                startActivity(intent);
                return true;
            }
        }
//
//        if (item.getItemId() == R.id.info) {
//            Toast.makeText(this, "Pierwsza aplikacja dla systemu Andriod.", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        if (item.getItemId() == R.id.newTask) {
//            Intent intent = new Intent(this, NewTaskActivity.class);
//            startActivity(intent);
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    private void stattActionMode(Task task) {
        if (actionMode != null) {
            actionMode.finish();
        }
        actionMode = recycler.startActionMode(
                new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        actionMode.getMenuInflater().inflate(R.menu.action_mode_menu, menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.editTask) {
                            Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
                            intent.putExtra("task_item", task);
                            startActivity(intent);
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode actionMode) {

                    }
                }
        );
    }

    private void getTasksByStatusId(int newStatusTaskId) {
        try {
            new ListTaskAsync(newStatusTaskId).execute().get();
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

    private class NewTaskAsync extends AsyncTask<Void, Void, Void> {

        private Task task;

        public NewTaskAsync(Task task) {
            this.task = task;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskRepository.insert(task);
            return null;
        }
    }
}
