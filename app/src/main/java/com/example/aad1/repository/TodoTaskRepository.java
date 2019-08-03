package com.example.aad1.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.aad1.helper.TodoTaskRoomDatabase;
import com.example.aad1.model.TodoTask;
import com.example.aad1.model.TodoTaskDAO;

public class TodoTaskRepository {

    private final LiveData<TodoTask> todoTasks;

    public TodoTaskRepository(Context context) {
        TodoTaskRoomDatabase database = TodoTaskRoomDatabase.getDatabase(context);
        TodoTaskDAO dao = database.todoTaskDAO();
        todoTasks = dao.loadTodoTasks();
    }

    public LiveData<TodoTask> getTodoTasks() {
        return todoTasks;
    }


}
