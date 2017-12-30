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

    public interface OnTaskInteractionListener {
        void onTaskClicked(Task task);

        void onTaskLongClicked(Task task);
    }

    List<Task> data = Collections.emptyList();
    private Context context;
    private OnTaskInteractionListener taskListener;

    private TaskViewHolder.OnTaskInteraction listener = new TaskViewHolder.OnTaskInteraction() {
        @Override
        public void onTaskClicked(int position) {
            Task task = data.get(position);
            if (taskListener != null) {
                taskListener.onTaskClicked(task);
            }
        }

        @Override
        public void onTaskLongClicked(int position) {
            Task task = data.get(position);
            if (taskListener != null) {
                taskListener.onTaskLongClicked(task);
            }
        }
    };

    public TaskAdapter(Context context, OnTaskInteractionListener taskListener) {
        this.context = context;
        this.taskListener = taskListener;
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
        return new TaskViewHolder(view, context, listener);
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
