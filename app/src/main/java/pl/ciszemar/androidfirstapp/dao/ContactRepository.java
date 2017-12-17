package pl.ciszemar.androidfirstapp.dao;

import java.util.List;

import pl.ciszemar.androidfirstapp.App;
import pl.ciszemar.androidfirstapp.db.AppDatabase;
import pl.ciszemar.androidfirstapp.db.ContactDbDao;
import pl.ciszemar.androidfirstapp.entity.Contact;

/**
 * Created by Rodzice on 17.12.2017.
 */

public class ContactRepository {

    private ContactDbDao contactDbDao;

    public ContactRepository(){
        contactDbDao =  App.getDb().getContactDbDao();
    }

    public List<Contact> getAll(){
        return contactDbDao.getAll();
    }

    public void insert(Contact contact){
        contactDbDao.insert(contact);
    }
}
