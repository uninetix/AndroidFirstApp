package pl.ciszemar.androidfirstapp.dao;

import java.util.List;

import pl.ciszemar.androidfirstapp.App;
import pl.ciszemar.androidfirstapp.db.TaskDbDao;
import pl.ciszemar.androidfirstapp.entity.Task;

/**
 * Created by Rodzice on 17.12.2017.
 */

public class TaskRepository {
    private TaskDbDao taskDbDao;

    public TaskRepository() {
        taskDbDao = App.getDb().getTaskDbDao();
    }

    public List<Task> getAll(){
        return taskDbDao.getAll();
    }

    public List<Task> getByStatus(int statusId) {
        return taskDbDao.getByStatus(statusId);
    }

    public void insert(Task task){
        taskDbDao.insert(task);
    }

    public void update(Task task) {
        taskDbDao.update(task);
    }
}
