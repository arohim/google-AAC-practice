package com.example.aad1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aad1.R;
import com.example.aad1.customview.PriorityStarImageView;
import com.example.aad1.model.TodoTask;
import com.example.aad1.util.TodoDateUtils;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoListAdapterViewHolder> {

    private List<TodoTask> todoTasks;

    @NonNull
    @Override
    public TodoListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo_list, parent, false);
        TodoListAdapterViewHolder viewHolder = new TodoListAdapterViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListAdapterViewHolder holder, int position) {
        TodoTask todoTask = todoTasks.get(position);
        holder.cbTodoName.setText(todoTask.getName());
        holder.cbTodoName.setChecked(todoTask.getCompleted() == 1);
        holder.ivTodoPriorityStar.setPriority(todoTask.getPriority());

        String dueDateString;
        long dueDate = todoTask.getDueDate();
        Context context = holder.itemView.getContext();
        if (dueDate == TodoTask.NO_DUE_DATE) {
            dueDateString = context.getString(R.string.no_due_date);
        } else {
            dueDateString = TodoDateUtils.formatDueDate(context, dueDate);
        }

        holder.tvTodoDueDate.setText(dueDateString);

    }

    @Override
    public int getItemCount() {
        if (todoTasks != null)
            return todoTasks.size();
        else return 0;
    }


    public class TodoListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final AppCompatCheckBox cbTodoName;
        final TextView tvTodoDueDate;
        final TextView tvTodoPriority;
        final PriorityStarImageView ivTodoPriorityStar;
        final ConstraintLayout clTodoListItem;

        public TodoListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            cbTodoName = itemView.findViewById(R.id.cb_todo_name);
            tvTodoDueDate = itemView.findViewById(R.id.tv_todo_due_date);
            tvTodoPriority = itemView.findViewById(R.id.tv_todo_priority);
            ivTodoPriorityStar = itemView.findViewById(R.id.iv_todo_priority_star);
            clTodoListItem = (ConstraintLayout) itemView;
            itemView.setOnClickListener(this);
            cbTodoName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public void setTodoTasks(List<TodoTask> todoTasks) {
        this.todoTasks = todoTasks;
        notifyDataSetChanged();
    }
}
