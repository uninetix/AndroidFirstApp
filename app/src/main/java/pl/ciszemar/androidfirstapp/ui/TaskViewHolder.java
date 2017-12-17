package pl.ciszemar.androidfirstapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pl.ciszemar.androidfirstapp.R;
import pl.ciszemar.androidfirstapp.entity.Task;

/**
 * Created by uninetix on 29.11.17.
 */

class TaskViewHolder extends RecyclerView.ViewHolder {

    private Task task;

    private TextView theme;
    private TextView details;
    private TextView priorityId;
    private TextView statusId;
    private TextView remaindDate;


    public TaskViewHolder(View itemTask) {
        super(itemTask);
        theme = itemTask.findViewById(R.id.theme);
        details = itemTask.findViewById(R.id.details);
        priorityId = itemTask.findViewById(R.id.statusId);
        statusId = itemTask.findViewById(R.id.priorityId);
        remaindDate = itemTask.findViewById(R.id.remaindDate);
    }

    public void setTask(Task task) {
        this.task = task;

        theme.setText(task.getTheme());
        details.setText(task.getDetails());
        priorityId.setText(String.valueOf(task.getPriorityId()));
        statusId.setText(String.valueOf(task.getStatusId()));
        remaindDate.setText(task.getRemaindDate());
    }

}
