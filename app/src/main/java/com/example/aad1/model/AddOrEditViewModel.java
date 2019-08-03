package com.example.aad1.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.aad1.repository.TodoTaskRepository;

public class AddOrEditViewModel extends AndroidViewModel {

    private final TodoTaskRepository repository;

    public AddOrEditViewModel(@NonNull Application application) {
        super(application);
        repository = new TodoTaskRepository(application);
    }

    public void insert(TodoTask todoTask) {
        repository.insert(todoTask);
    }

}
