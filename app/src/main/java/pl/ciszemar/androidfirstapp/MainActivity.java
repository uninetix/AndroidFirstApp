package pl.ciszemar.androidfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import pl.ciszemar.androidfirstapp.dao.TaskRepository;
import pl.ciszemar.androidfirstapp.entity.Task;
import pl.ciszemar.androidfirstapp.ui.TaskAdapter;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs = null;
    private TaskRepository taskRepository = new TaskRepository();
    private TaskAdapter adapter;
    List<Task> tasks = Collections.EMPTY_LIST;

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
        try {
            new ListTaskAsync().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        adapter = new TaskAdapter(this, new TaskAdapter.OnTaskInteractionListener() {

            @Override
            public void onTaskClicked(Task task) {
                Toast.makeText(MainActivity.this, task.getTheme(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTaskLongClicked(Task task) {
                Toast.makeText(MainActivity.this, task.getDetails(), Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setData(tasks);
        RecyclerView recycler = findViewById(R.id.list);
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
        if (item.getItemId() == R.id.info) {
            Toast.makeText(this, "Pierwsza aplikacja dla systemu Andriod.", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId() == R.id.newTask) {
            Intent intent = new Intent(this, NewTaskActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.editTask) {
            Intent intent = new Intent(this, EditTaskActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ListTaskAsync extends AsyncTask<Void, Void, List<Task>> {

        @Override
        protected List<Task> doInBackground(Void... voids) {
            tasks = taskRepository.getAll();
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
