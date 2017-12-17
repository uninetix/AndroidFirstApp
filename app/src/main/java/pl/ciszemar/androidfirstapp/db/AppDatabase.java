package pl.ciszemar.androidfirstapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import pl.ciszemar.androidfirstapp.entity.Contact;

/**
 * Created by Rodzice on 16.12.2017.
 */

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDbDao getContactDbDao();
}
