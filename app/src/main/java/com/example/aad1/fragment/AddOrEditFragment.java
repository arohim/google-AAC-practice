package com.example.aad1.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;

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
import com.example.aad1.util.TodoDateUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.Calendar;

import static com.example.aad1.model.TodoTask.TASK_NOT_COMPLETED;

public class AddOrEditFragment extends Fragment {

    private AddOrEditViewModel viewModel;

    private FragmentAddOrEditBinding binding;

    private int priority = 0;

    private long dueDate;

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

        binding.btnPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPriorityDialog();
            }
        });


        binding.btnDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDueDateDialog();
            }
        });

    }

    public void showDueDateDialog() {

        if (getActivity() == null)
            return;

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        // Get Current Time
        final int mHour = c.get(Calendar.HOUR_OF_DAY);
        final int mMinute = c.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {

                        // Launch Time Picker Dialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // 2018-06-10T08:25:05.964047+07:00
                                String dateTimeStr = makeDateTimeString(hourOfDay, minute, year, monthOfYear, dayOfMonth);
                                DateTime dateTime = DateTime.parse(dateTimeStr);
                                dueDate = new Timestamp(dateTime.getMillis()).getTime();
                                binding.btnDueDate.setText(TodoDateUtils.formatDueDate(getActivity(), dueDate));
                                binding.btnDueDate.setSelected(true);
                            }
                        }, mHour, mMinute, false);
                        timePickerDialog.show();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @SuppressLint("DefaultLocale")
    @NotNull
    private String makeDateTimeString(int hourOfDay, int minute, int year, int monthOfYear, int dayOfMonth) {
        return String.format("%04d-%02d-%02dT%02d:%02d:00+07:00",
                year,
                monthOfYear, dayOfMonth, hourOfDay, minute);
    }

    private void showPriorityDialog() {
        final String[] priorities = getResources().getStringArray(R.array.priority_entries);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, priorities);
        new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .setTitle(getResources().getString(R.string.priority))
                .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        priority = which;
                        String priorityString = priorities[which];
                        binding.btnPriority.setText(priorityString);
                        binding.btnPriority.setSelected(true);
                    }
                }).create().show();
    }

    private void insertToDB() {
        String title = binding.addTaskTitle.getText().toString();
        String description = binding.addTaskDescription.getText().toString();
        TodoTask todoTask = new TodoTask(
                title,
                description,
                priority,
                dueDate,
                TASK_NOT_COMPLETED);
        binding.addTaskTitle.clearFocus();
        binding.addTaskDescription.clearFocus();
        viewModel.insert(todoTask);
    }

    private void redirectToTodoList() {
        NavDirections action = AddOrEditFragmentDirections.Companion.nextAction();
        NavHostFragment.findNavController(AddOrEditFragment.this).navigate(action);
    }

}
