package pl.ciszemar.androidfirstapp;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import pl.ciszemar.androidfirstapp.db.AppDatabase;

/**
 * Created by Rodzice on 16.12.2017.
 */

public class App extends Application {

    private static Context appContext;
    private static AppDatabase db;

    public static synchronized AppDatabase getDb() {
        if (db == null) {
            db = Room.databaseBuilder(appContext, AppDatabase.class, "todo-db").build();
        }
        return db;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
