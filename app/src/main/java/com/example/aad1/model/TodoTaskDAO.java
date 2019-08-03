package com.example.aad1.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoTaskDAO {

    @Insert
    void insert(TodoTask todoTask);

    @Delete
    void delete(TodoTask todoTask);

    @Update
    void update(TodoTask... todoTask);

    @Query("SELECT * FROM TodoTask")
    List<TodoTask> loadTodoTasks();

    @Query("SELECT * FROM TodoTask WHERE completed = :completed")
    List<TodoTask> loadTodoTasksByCompletedStatus(int completed);


    @Query("SELECT * FROM TodoTask WHERE priority = :priority")
    List<TodoTask> loadTodoTasksByPriority(int priority);
    
}
