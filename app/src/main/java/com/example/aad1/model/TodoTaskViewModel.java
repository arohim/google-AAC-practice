package com.example.aad1.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aad1.repository.TodoTaskRepository;

import java.util.List;

public class TodoTaskViewModel extends AndroidViewModel {

    private final LiveData<List<TodoTask>> todoTasks;

    public TodoTaskViewModel(@NonNull Application application) {
        super(application);
        TodoTaskRepository repository = new TodoTaskRepository(application);
        todoTasks = repository.getTodoTasks();
    }

    public LiveData<List<TodoTask>> getTodoTasks() {
        return todoTasks;
    }

}
