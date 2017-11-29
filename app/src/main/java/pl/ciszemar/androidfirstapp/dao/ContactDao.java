package pl.ciszemar.androidfirstapp.dao;

import java.util.List;

import pl.ciszemar.androidfirstapp.entity.Contact;

/**
 * Created by uninetix on 23.11.17.
 */

public interface ContactDao {
    public boolean newContact(Contact contact);
    public List<Contact> findAllContacts();
    public Contact getContactById(int id);
}
