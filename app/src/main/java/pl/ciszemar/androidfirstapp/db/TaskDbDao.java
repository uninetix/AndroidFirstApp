package pl.ciszemar.androidfirstapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pl.ciszemar.androidfirstapp.entity.Task;

/**
 * Created by Rodzice on 17.12.2017.
 */
@Dao
public interface TaskDbDao {
    @Query("SELECT * FROM tasks")
    List<Task> getAll();

    @Insert
    void insert(Task task);
}
