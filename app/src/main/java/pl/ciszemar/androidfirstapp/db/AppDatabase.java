package pl.ciszemar.androidfirstapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import pl.ciszemar.androidfirstapp.entity.Contact;
import pl.ciszemar.androidfirstapp.entity.Task;

/**
 * Created by Rodzice on 16.12.2017.
 */

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDbDao getTaskDbDao();
}
