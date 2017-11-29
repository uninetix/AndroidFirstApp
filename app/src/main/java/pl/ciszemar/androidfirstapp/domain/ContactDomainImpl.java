package pl.ciszemar.androidfirstapp.domain;

import java.util.List;

import pl.ciszemar.androidfirstapp.dao.ContactDao;
import pl.ciszemar.androidfirstapp.entity.Contact;

/**
 * Created by uninetix on 23.11.17.
 */

public class ContactDomainImpl implements ContactDomain {

    private ContactDao contactDao;

    public ContactDomainImpl(ContactDao contactDao) {
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
