package pl.ciszemar.androidfirstapp.service;

import java.util.List;

import pl.ciszemar.androidfirstapp.model.Contact;

/**
 * Created by uninetix on 23.11.17.
 */

public interface ContactService {
    public boolean newContact(Contact contact);
    public List<Contact> findAllContacts();
    public Contact getContactById(int id);

}
