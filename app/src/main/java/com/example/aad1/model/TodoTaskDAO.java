package com.example.aad1.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TodoTaskDAO {

    @Insert
    void insert(TodoTask todoTask);

    @Delete
    void delete(TodoTask todoTask);

    @Query("DELETE FROM todoTask")
    void deleteAll();

    @Update
    void update(TodoTask... todoTask);

    @Query("SELECT * FROM TodoTask")
    LiveData<TodoTask> loadTodoTasks();

    @Query("SELECT * FROM TodoTask WHERE completed = :completed")
    LiveData<TodoTask> loadTodoTasksByCompletedStatus(int completed);


    @Query("SELECT * FROM TodoTask WHERE priority = :priority")
    LiveData<TodoTask> loadTodoTasksByPriority(int priority);

}
