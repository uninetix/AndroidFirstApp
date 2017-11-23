package pl.ciszemar.androidfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import pl.ciszemar.androidfirstapp.dao.ContactDao;
import pl.ciszemar.androidfirstapp.dao.ContactDaoMemoryImpl;
import pl.ciszemar.androidfirstapp.model.Contact;
import pl.ciszemar.androidfirstapp.service.ContactService;
import pl.ciszemar.androidfirstapp.service.ContactServiceImpl;

public class MainActivity extends AppCompatActivity {

    List<Contact> contacts;
    private ContactService contactService = new ContactServiceImpl(new ContactDaoMemoryImpl());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = contactService.findAllContacts();

        Log.d("onCreate()", String.valueOf(contacts.size()));
    }
}
