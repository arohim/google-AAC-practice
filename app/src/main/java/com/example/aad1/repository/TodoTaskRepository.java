package com.example.aad1.repository;

import android.content.Context;
import android.os.AsyncTask;

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

    public LiveData<PagedList<TodoTask>> loadTodoTasksOrderByPriority() {
        LiveData<PagedList<TodoTask>> data = new LivePagedListBuilder(todoTaskDAO.loadTodoTasksOrderByPriority(), DATABASE_PAGE_SIZE)
                .setBoundaryCallback(callback)
                .build();
        return data;
    }

    public LiveData<PagedList<TodoTask>> loadTodoTasksOrderByDueDate() {
        LiveData<PagedList<TodoTask>> data = new LivePagedListBuilder(todoTaskDAO.loadTodoTasksOrderByDueDate(), DATABASE_PAGE_SIZE)
                .setBoundaryCallback(callback)
                .build();
        return data;
    }

    public LiveData<TodoTask> loadTodoTaskById(int id) {
        return todoTaskDAO.loadTodoTaskById(id);
    }

    public void insert(TodoTask todoTask) {
        new InsertAsyncTask(todoTaskDAO).execute(todoTask);
    }

    public void update(TodoTask todoTask) {
        new UpdateAsyncTask(todoTaskDAO).execute(todoTask);
    }

    public static class InsertAsyncTask extends AsyncTask<TodoTask, Void, Void> {

        private TodoTaskDAO todoTaskDAO;

        InsertAsyncTask(TodoTaskDAO todoTaskDAO) {
            this.todoTaskDAO = todoTaskDAO;
        }

        @Override
        protected Void doInBackground(TodoTask... todoTasks) {
            todoTaskDAO.insert(todoTasks);
            return null;
        }
    }

    public static class UpdateAsyncTask extends AsyncTask<TodoTask, Void, Void> {

        private TodoTaskDAO todoTaskDAO;

        UpdateAsyncTask(TodoTaskDAO todoTaskDAO) {
            this.todoTaskDAO = todoTaskDAO;
        }

        @Override
        protected Void doInBackground(TodoTask... todoTasks) {
            todoTaskDAO.update(todoTasks);
            return null;
        }
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
