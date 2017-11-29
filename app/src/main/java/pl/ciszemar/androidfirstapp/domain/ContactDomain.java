package pl.ciszemar.androidfirstapp.domain;

import java.util.List;

import pl.ciszemar.androidfirstapp.entity.Contact;

/**
 * Created by uninetix on 23.11.17.
 */

public interface ContactDomain {
    public boolean newContact(Contact contact);
    public List<Contact> findAllContacts();
    public Contact getContactById(int id);

}
