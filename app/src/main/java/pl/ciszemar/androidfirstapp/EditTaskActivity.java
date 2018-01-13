package pl.ciszemar.androidfirstapp;

import android.app.DatePickerDialog;
import android.content.Intent;
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

public class EditTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Task task;
    private EditText themeET, detailsET, remaindDateET;
    private Spinner prioritySpinner, statusSpinner;
    private Button saveBtn;
    Calendar myCalendar = Calendar.getInstance();

    private TaskRepository taskRepository = new TaskRepository();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        task = (Task) intent.getSerializableExtra("task_item");

        themeET = findViewById(R.id.theme);
        detailsET = findViewById(R.id.details);
        remaindDateET = findViewById(R.id.remaindDate);

        prioritySpinner = findViewById(R.id.priorityId);
        ArrayAdapter priorityAdapter = ArrayAdapter.createFromResource(this, R.array.priority, android.R.layout.simple_spinner_item);
        prioritySpinner.setAdapter(priorityAdapter);
        prioritySpinner.setOnItemSelectedListener(this);

        statusSpinner = findViewById(R.id.statusId);
        ArrayAdapter statusAdapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        statusSpinner.setAdapter(statusAdapter);
        statusSpinner.setOnItemSelectedListener(this);

        themeET.setText(task.getTheme());
        detailsET.setText(task.getDetails());
        remaindDateET.setText(task.getRemaindDate());
        prioritySpinner.setSelection(task.getPriorityId());
        statusSpinner.setSelection(task.getStatusId());

        saveBtn = findViewById(R.id.save_task_button);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setTheme(themeET.getText().toString());
                task.setDetails(detailsET.getText().toString());
                task.setRemaindDate(remaindDateET.getText().toString());
                new EditTaskActivity.EditTaskAsync().execute();
                onBackPressed();
            }
        });

        DatePickerDialog.OnDateSetListener date = (datePicker, year, monthofYear, dayOfMounth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthofYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMounth);
            updateLabel();
        };

        remaindDateET.setOnFocusChangeListener((view, b) -> new DatePickerDialog(EditTaskActivity.this, date,
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

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void updateLabel() {
        String myFormatDate = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormatDate);
        remaindDateET.setText(sdf.format(myCalendar.getTime()));
    }

    private class EditTaskAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            taskRepository.update(task);
            return null;
        }
    }
}
