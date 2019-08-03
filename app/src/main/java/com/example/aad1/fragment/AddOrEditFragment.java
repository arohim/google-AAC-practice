package com.example.aad1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.example.aad1.R;
import com.example.aad1.databinding.FragmentAddOrEditBinding;
import com.example.aad1.helper.Utils;
import com.example.aad1.model.AddOrEditViewModel;
import com.example.aad1.model.TodoTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.aad1.model.TodoTask.TASK_NOT_COMPLETED;

public class AddOrEditFragment extends Fragment {

    private AddOrEditViewModel viewModel;

    private FragmentAddOrEditBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_or_edit, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(AddOrEditViewModel.class);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_done_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.hideKeyboard(getActivity());
                insertToDB();
                redirectToTodoList();
            }
        });
    }

    private void insertToDB() {
        String title = binding.addTaskTitle.getText().toString();
        String description = binding.addTaskDescription.getText().toString();
        TodoTask todoTask = new TodoTask(title, description, TodoTask.LOW_PRIORITY, TodoTask.NO_DUE_DATE, TASK_NOT_COMPLETED);
        binding.addTaskTitle.clearFocus();
        binding.addTaskDescription.clearFocus();
        viewModel.insert(todoTask);
    }

    private void redirectToTodoList() {
        NavDirections action = AddOrEditFragmentDirections.Companion.nextAction();
        NavHostFragment.findNavController(AddOrEditFragment.this).navigate(action);
    }

}
