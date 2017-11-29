package pl.ciszemar.androidfirstapp.dao;

import java.util.ArrayList;
import java.util.List;

import pl.ciszemar.androidfirstapp.entity.Contact;

/**
 * Created by uninetix on 23.11.17.
 */

public class ContactDaoMemoryImpl implements ContactDao {

    private List<Contact> contacts;

    public ContactDaoMemoryImpl() {
        List<Contact> contacts = new ArrayList<>();
        for(int i=0; i<10; i++){
            Contact contact = new Contact();
            contact.setId(i);
            contact.setFirstName("Imie" + (i+1));
            contact.setLastName("Nazwisko" + (i+1));
            contact.setEmail("mail" + (i+1) + "@gmail.com");
            contact.setPhoneNumber("+48 324 546 32"  + (i+1));
            contact.setFavorite(false);
            contacts.add(contact);
        }
        this.contacts = contacts;
    }

    @Override
    public boolean newContact(Contact contact) {
        contacts.add(contact);
        return true;
    }

    @Override
    public List<Contact> findAllContacts() {
        return contacts;
    }

    @Override
    public Contact getContactById(int id) {
        for(Contact item : contacts){
            if(item.getId() == id) return item;
        }
        return null;
    }
}
