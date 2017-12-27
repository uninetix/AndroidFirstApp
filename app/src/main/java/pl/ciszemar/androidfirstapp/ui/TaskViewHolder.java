package pl.ciszemar.androidfirstapp.ui;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import pl.ciszemar.androidfirstapp.App;
import pl.ciszemar.androidfirstapp.MainActivity;
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

    private Context context;
    private ArrayAdapter statusAdapter;

    public TaskViewHolder(View itemTask, Context context) {
        super(itemTask);
        this.context = context;
        theme = itemTask.findViewById(R.id.theme);
        details = itemTask.findViewById(R.id.details);
        priorityId = itemTask.findViewById(R.id.statusId);
        statusId = itemTask.findViewById(R.id.priorityId);
        remaindDate = itemTask.findViewById(R.id.remaindDate);
    }

    public void setTask(Task task) {
        this.task = task;
        String[] statusList = context.getResources().getStringArray(R.array.status);
        String[] priorityList = context.getResources().getStringArray(R.array.priority);
        theme.setText(task.getTheme());
        details.setText(task.getDetails());
        priorityId.setText(priorityList[task.getPriorityId()]);
        statusId.setText(statusList[task.getStatusId()]);
        remaindDate.setText(task.getRemaindDate());
    }

}
