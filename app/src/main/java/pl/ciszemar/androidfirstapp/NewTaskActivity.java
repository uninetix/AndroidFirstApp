package pl.ciszemar.androidfirstapp;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import pl.ciszemar.androidfirstapp.dao.TaskRepository;
import pl.ciszemar.androidfirstapp.entity.Task;


public class NewTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Task task;
    private Spinner statusSpinner, prioritySpinner;
    private Button submit;
    EditText remaindDate;
    Calendar myCalendar = Calendar.getInstance();

    private TaskRepository taskRepository = new TaskRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        submit = findViewById(R.id.add_task_button);
        submit.setOnClickListener(view -> {
            task.setTheme(((EditText) findViewById(R.id.theme)).getText().toString());
            task.setDetails(((EditText) findViewById(R.id.details)).getText().toString());
            task.setRemaindDate(((EditText) findViewById(R.id.remaindDate)).getText().toString());
            new NewTaskAsync(task).execute();
            onBackPressed();
        });
        task = new Task();
        task.setStatusId(0);
        task.setPriorityId(0);

        statusSpinner = findViewById(R.id.statusId);
        ArrayAdapter statusAdapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        statusSpinner.setAdapter(statusAdapter);
        statusSpinner.setOnItemSelectedListener(this);

        prioritySpinner = findViewById(R.id.priorityId);
        ArrayAdapter priorityAdapter = ArrayAdapter.createFromResource(this, R.array.priority, android.R.layout.simple_spinner_item);
        prioritySpinner.setAdapter(priorityAdapter);
        prioritySpinner.setOnItemSelectedListener(this);

        remaindDate = findViewById(R.id.remaindDate);
        DatePickerDialog.OnDateSetListener date = (datePicker, year, monthofYear, dayOfMounth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthofYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMounth);
            updateLabel();
        };

        remaindDate.setOnFocusChangeListener((view, b) -> new DatePickerDialog(NewTaskActivity.this, date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView item = (TextView) view;
        switch (adapterView.getId()) {
            case R.id.statusId:
                task.setStatusId(i);
                break;
            case R.id.priorityId:
                task.setPriorityId(i);
                break;
        }
    }


    private void updateLabel() {
        String myFormatDate = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormatDate);
        remaindDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
