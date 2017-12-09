package pl.ciszemar.androidfirstapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import pl.ciszemar.androidfirstapp.dao.ContactDao;
import pl.ciszemar.androidfirstapp.dao.ContactDaoMemoryImpl;
import pl.ciszemar.androidfirstapp.entity.Contact;
import pl.ciszemar.androidfirstapp.domain.ContactDomain;
import pl.ciszemar.androidfirstapp.domain.ContactDomainImpl;
import pl.ciszemar.androidfirstapp.ui.ContactAdapter;

public class MainActivity extends AppCompatActivity {

    private ContactDomain contactDomain;
    private SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ContactDao contactDao = new ContactDaoMemoryImpl();
        ContactDomain contactDomain = new ContactDomainImpl(contactDao);

        List<Contact> contacts = contactDomain.findAllContacts();
        Log.d("onCreate()", String.valueOf(contacts.size()));

        ContactAdapter adapter = new ContactAdapter();
        adapter.setData(contacts);
        RecyclerView recycler = findViewById(R.id.list);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        prefs = getSharedPreferences("pl.ciszemar.androidfirstapp", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(prefs.getBoolean("firstrun", true)){
            Toast.makeText(this, "Pierwsze uruchomienie", Toast.LENGTH_LONG).show();
            prefs.edit().putBoolean("firstrun", false).commit();
        }
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
        return super.onOptionsItemSelected(item);
    }
}
