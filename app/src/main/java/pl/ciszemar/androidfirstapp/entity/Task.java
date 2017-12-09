package pl.ciszemar.androidfirstapp.entity;

/**
 * Created by Rodzice on 08.12.2017.
 */

public class Task {

    private int id;
    private String theme;
    private String details;
    private int priorityId;
    private int statusId;
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
