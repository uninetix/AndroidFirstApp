package pl.ciszemar.androidfirstapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

    @Update
    void update(Task task);

    @Query("SELECT * FROM tasks WHERE statusId = :statusId")
    List<Task> getByStatus(int statusId);
}
