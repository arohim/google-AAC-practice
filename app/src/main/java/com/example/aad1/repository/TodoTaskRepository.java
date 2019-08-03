package com.example.aad1.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.aad1.helper.TodoTaskRoomDatabase;
import com.example.aad1.model.TodoTask;
import com.example.aad1.model.TodoTaskDAO;

public class TodoTaskRepository {

    public static int DATABASE_PAGE_SIZE = 20;

    private final TodoTaskDAO todoTaskDAO;

    public TodoTaskRepository(Context context) {
        TodoTaskRoomDatabase database = TodoTaskRoomDatabase.getDatabase(context);
        todoTaskDAO = database.todoTaskDAO();
    }

    public LiveData<PagedList<TodoTask>> getTodoTasks() {
        LiveData<PagedList<TodoTask>> data = new LivePagedListBuilder(todoTaskDAO.loadTodoTasks(), DATABASE_PAGE_SIZE)
                .setBoundaryCallback(callback)
                .build();
        return data;
    }

    private PagedList.BoundaryCallback callback = new PagedList.BoundaryCallback<TodoTask>() {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
        }

        @Override
        public void onItemAtFrontLoaded(@NonNull TodoTask itemAtFront) {
            super.onItemAtFrontLoaded(itemAtFront);
        }

        @Override
        public void onItemAtEndLoaded(@NonNull TodoTask itemAtEnd) {
            super.onItemAtEndLoaded(itemAtEnd);
        }
    };

}
