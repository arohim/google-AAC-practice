package com.example.aad1.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.PagedList;
import androidx.preference.PreferenceManager;

import com.example.aad1.R;
import com.example.aad1.repository.TodoTaskRepository;

import static com.example.aad1.model.TodoTask.SORTED_BY_DUE_DATE;
import static com.example.aad1.model.TodoTask.SORTED_BY_PRIORITY;

public class TodoTaskViewModel extends AndroidViewModel {

    private final SharedPreferences sharedPreference;

    private final Application context;

    private MutableLiveData<String> sortedBy = new MutableLiveData<String>();

    private TodoTaskRepository repository;

    private LiveData<LiveData<PagedList<TodoTask>>> result = Transformations.map(sortedBy, new Function<String, LiveData<PagedList<TodoTask>>>() {
        @Override
        public LiveData<PagedList<TodoTask>> apply(String input) {
            String sorting = getSorting(context);
            return repository.getTodoTasks(sorting);
        }
    });

    private LiveData<PagedList<TodoTask>> todoTasks = Transformations.switchMap(result, new Function<LiveData<PagedList<TodoTask>>, LiveData<PagedList<TodoTask>>>() {
        @Override
        public LiveData<PagedList<TodoTask>> apply(LiveData<PagedList<TodoTask>> input) {
            return input;
        }
    });

    public TodoTaskViewModel(@NonNull Application application) {
        super(application);
        context = application;
        sharedPreference = PreferenceManager.getDefaultSharedPreferences(application);
        repository = new TodoTaskRepository(application);
        String sorting = sharedPreference.getString(context.getString(R.string.sorting), SORTED_BY_PRIORITY);
        configurationChanged(sorting);
    }

    public LiveData<PagedList<TodoTask>> getTodoTasks() {
        return todoTasks;
    }

    public void configurationChanged(String sortBy) {
        sortedBy.postValue(sortBy);
    }

    private String getSorting(Context context) {
        String sorting = sharedPreference.getString(context.getString(R.string.sorting), SORTED_BY_PRIORITY);
        if (sorting.equals(context.getResources().getStringArray(R.array.sorting_values)[0])) {
            return SORTED_BY_DUE_DATE;
        } else {
            return SORTED_BY_PRIORITY;
        }
    }

}
