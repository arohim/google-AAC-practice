package com.example.aad1.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aad1.R;
import com.example.aad1.adapter.TodoListAdapter;
import com.example.aad1.databinding.FragmentTodoListBinding;
import com.example.aad1.model.TodoTask;
import com.example.aad1.model.TodoTaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TodoListFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener, LoaderManager.LoaderCallbacks<Object> {

    FragmentTodoListBinding binding;

    RecyclerView recyclerView;

    TodoListAdapter todoListAdapter;

    private TodoTaskViewModel viewModel;

    private SharedPreferences mSharedPreferences;

    private static final int ID_TODO_LIST_LOADER = 2019;

    public TodoListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpAdapter();
        setUpViewModel();
        setUpFloatingAction();
    }

    private void setUpFloatingAction() {
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = TodoListFragmentDirections.Companion.nextAction(0);
                NavHostFragment.findNavController(TodoListFragment.this).navigate(action);
            }
        });
    }

    private void setUpViewModel() {
        viewModel = ViewModelProviders.of(this).get(TodoTaskViewModel.class);
        Observer<PagedList<TodoTask>> observer = new Observer<PagedList<TodoTask>>() {
            @Override
            public void onChanged(PagedList<TodoTask> todoTasks) {
                todoListAdapter.submitList(todoTasks);
            }
        };
        viewModel.getTodoTasks().observe(this, observer);
    }

    private void setUpAdapter() {
        recyclerView = binding.rvTodoList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        todoListAdapter = new TodoListAdapter(new TodoListAdapter.TodoListAdapterOnClickHandler() {
            @Override
            public void onClick(TodoTask todoTask, View view) {
                NavDirections action = TodoListFragmentDirections.Companion.nextAction(todoTask.getId());
                NavHostFragment.findNavController(TodoListFragment.this).navigate(action);
            }
        });
        recyclerView.setAdapter(todoListAdapter);
        DividerItemDecoration decorator = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decorator);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            NavHostFragment.findNavController(TodoListFragment.this).navigate(R.id.settings);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        viewModel.configurationChanged();
    }

    @NonNull
    @Override
    public Loader<Object> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Object> loader, Object data) {
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Object> loader) {
    }
}
