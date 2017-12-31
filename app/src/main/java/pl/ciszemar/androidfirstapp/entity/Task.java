package pl.ciszemar.androidfirstapp.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Rodzice on 08.12.2017.
 */
@Entity(tableName = "tasks")
public class Task implements Serializable {

    private static final long serialVersionUID = 1;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "theme")
    private String theme;
    @ColumnInfo(name = "details")
    private String details;
    @ColumnInfo(name = "priorityId")
    private int priorityId;
    @ColumnInfo(name = "statusId")
    private int statusId;
    @ColumnInfo(name = "remaindDate")
    private String remaindDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getRemaindDate() {
        return remaindDate;
    }

    public void setRemaindDate(String remaindDate) {
        this.remaindDate = remaindDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", details='" + details + '\'' +
                ", priorityId=" + priorityId +
                ", statusId=" + statusId +
                ", remaindDate='" + remaindDate + '\'' +
                '}';
    }
}
