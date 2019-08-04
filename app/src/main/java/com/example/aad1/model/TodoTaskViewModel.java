package com.example.aad1.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;
import androidx.preference.PreferenceManager;

import com.example.aad1.R;
import com.example.aad1.repository.TodoTaskRepository;

import static com.example.aad1.model.TodoTask.SORTED_BY_DUE_DATE;
import static com.example.aad1.model.TodoTask.SORTED_BY_PRIORITY;

public class TodoTaskViewModel extends AndroidViewModel {

    private final SharedPreferences sharedPreference;

    private MutableLiveData<String> sortedBy = new MutableLiveData<String>();

    private TodoTaskRepository repository;

    private LiveData<PagedList<TodoTask>> todoTasks;

    public TodoTaskViewModel(@NonNull Application application) {
        super(application);
        sharedPreference = PreferenceManager.getDefaultSharedPreferences(application);
        repository = new TodoTaskRepository(application);
        String sortingBy = getSorting(application);
        todoTasks = repository.getTodoTasks(sortingBy);
    }

    public LiveData<PagedList<TodoTask>> getTodoTasks() {
        return todoTasks;
    }

    public void changeSorting(String sortBy) {
        sortedBy.postValue(sortBy);
    }

    public String getSorting(Context context) {
        String sorting = sharedPreference.getString(context.getString(R.string.sorting), SORTED_BY_PRIORITY);
        if (sorting.equals(context.getResources().getStringArray(R.array.sorting_values)[0])) {
            return SORTED_BY_DUE_DATE;
        } else {
            return SORTED_BY_PRIORITY;
        }
    }

}
