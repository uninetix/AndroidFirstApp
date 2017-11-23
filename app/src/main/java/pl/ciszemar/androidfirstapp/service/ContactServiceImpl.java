package pl.ciszemar.androidfirstapp.service;

import java.util.List;

import pl.ciszemar.androidfirstapp.dao.ContactDao;
import pl.ciszemar.androidfirstapp.model.Contact;

/**
 * Created by uninetix on 23.11.17.
 */

public class ContactServiceImpl implements ContactService {

    private ContactDao contactDao;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public boolean newContact(Contact contact) {
        return contactDao.newContact(contact);
    }

    @Override
    public List<Contact> findAllContacts() {
        return contactDao.findAllContacts();
    }

    @Override
    public Contact getContactById(int id) {
        return contactDao.getContactById(id);
    }
}
