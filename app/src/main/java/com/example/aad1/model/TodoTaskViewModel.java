package com.example.aad1.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.aad1.repository.TodoTaskRepository;

public class TodoTaskViewModel extends AndroidViewModel {

    private TodoTaskRepository repository;

    private LiveData<PagedList<TodoTask>> todoTasks;

    public TodoTaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TodoTaskRepository(application);
        todoTasks = repository.getTodoTasks();
    }

    public LiveData<PagedList<TodoTask>> getTodoTasks() {
        return todoTasks;
    }

}
