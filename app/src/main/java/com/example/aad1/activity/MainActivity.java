package com.example.aad1.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aad1.R;
import com.example.aad1.adapter.TodoListAdapter;
import com.example.aad1.databinding.ActivityTodoListBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    ActivityTodoListBinding activityTodoListBinding;
    RecyclerView recyclerView;
    TodoListAdapter todoListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTodoListBinding = DataBindingUtil.setContentView(this, R.layout.activity_todo_list);
        setSupportActionBar(activityTodoListBinding.toolbar);

        recyclerView = activityTodoListBinding.rvTodoList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        todoListAdapter = new TodoListAdapter();
        recyclerView.setAdapter(todoListAdapter);
        DividerItemDecoration decorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decorator);

        activityTodoListBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
