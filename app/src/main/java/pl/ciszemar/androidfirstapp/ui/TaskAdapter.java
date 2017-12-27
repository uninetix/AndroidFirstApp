package pl.ciszemar.androidfirstapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import pl.ciszemar.androidfirstapp.R;
import pl.ciszemar.androidfirstapp.entity.Task;

/**
 * Created by uninetix on 29.11.17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder>{

    List<Task> data = Collections.emptyList();
    private Context context;

    public TaskAdapter() {
    }

    public TaskAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Task> data) {
        this.data = data;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =
                LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(
                R.layout.item_task,
                viewGroup,
                false
        );
        return new TaskViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder viewHolder, int position) {
        Task task = data.get(position);
        viewHolder.setTask(task);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
