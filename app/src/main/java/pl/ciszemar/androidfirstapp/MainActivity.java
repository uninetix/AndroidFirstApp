package pl.ciszemar.androidfirstapp;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import pl.ciszemar.androidfirstapp.dao.ContactRepository;
import pl.ciszemar.androidfirstapp.entity.Contact;
import pl.ciszemar.androidfirstapp.ui.ContactAdapter;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs = null;
    private ContactRepository contactRepository = new ContactRepository();
    List<Contact> contacts = Collections.EMPTY_LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            new ListContactAsync().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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
        if (prefs.getBoolean("firstrun", true)) {
            Toast.makeText(this, "Pierwsze uruchomienie", Toast.LENGTH_LONG).show();
            Contact contact = new Contact();
            contact.setId(1);
            contact.setFirstName("Jan");
            contact.setLastName("Kowalski");
            contact.setEmail("jan.kowalski@gmail.com");
            contact.setPhoneNumber("71345678987");
            new NewContactAsync(contact).execute();
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

    private class ListContactAsync extends AsyncTask<Void, Void, List<Contact>> {

        @Override
        protected List<Contact> doInBackground(Void... voids) {
            contacts = contactRepository.getAll();
            return contacts;
        }
    }

    private class NewContactAsync extends AsyncTask<Void, Void, Void> {

        private Contact contact;

        public NewContactAsync(Contact contact) {
            this.contact = contact;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            contactRepository.insert(contact);
            return null;
        }
    }
}
