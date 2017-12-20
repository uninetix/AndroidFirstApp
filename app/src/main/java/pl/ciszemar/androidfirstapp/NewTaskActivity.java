package pl.ciszemar.androidfirstapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import pl.ciszemar.androidfirstapp.entity.Task;


public class NewTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Task task;
    Spinner statusSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        task = new Task();
        task.setStatusId(0);
        task.setPriorityId(0);
        statusSpinner = (Spinner) findViewById(R.id.statusId);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        statusSpinner.setAdapter(adapter);
        statusSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView item = (TextView) view;
        Log.d("NewTaskAvtivity", "Wybrałeś: " + item.getText() + " i=" + i + " l=" + l);
        Log.d("NewTaskAvtivity", "AdapterView: " + adapterView.getId());
        Toast.makeText(this, "Wybrałeś: " + item.getId() + " i=" + i + " l=" + l, Toast.LENGTH_LONG).show();
        task.setStatusId(item.getId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
