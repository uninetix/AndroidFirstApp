package pl.ciszemar.androidfirstapp.domain;

import java.util.List;

import pl.ciszemar.androidfirstapp.entity.Task;

/**
 * Created by Rodzice on 08.12.2017.
 */

public interface TaskDomain {
    public boolean newContact(Task task);
    public List<Task> findAllTasks();
    public Task getTaskById(int id);
}
