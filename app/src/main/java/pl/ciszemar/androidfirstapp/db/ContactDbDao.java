package pl.ciszemar.androidfirstapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import pl.ciszemar.androidfirstapp.entity.Contact;

/**
 * Created by Rodzice on 17.12.2017.
 */
@Dao
public interface ContactDbDao {

    @Query("SELECT * FROM contacts")
    List<Contact> getAll();

    @Insert
    void insert(Contact contact);
}
