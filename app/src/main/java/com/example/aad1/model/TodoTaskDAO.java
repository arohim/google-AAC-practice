package com.example.aad1.model;

import androidx.paging.DataSource;
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

    @Query("DELETE FROM TodoTask")
    void deleteAll();

    @Update
    void update(TodoTask... todoTask);

    @Query("SELECT * FROM TodoTask")
    DataSource.Factory<Integer, TodoTask> loadTodoTasks();

    @Query("SELECT * FROM TodoTask limit :limit offset :offset")
    DataSource.Factory<Integer, TodoTask> loadTodoTasks(int offset, int limit);

    @Query("SELECT * FROM TodoTask WHERE completed = :completed")
    DataSource.Factory<Integer, TodoTask> loadTodoTasksByCompletedStatus(int completed);


    @Query("SELECT * FROM TodoTask WHERE priority = :priority")
    DataSource.Factory<Integer, TodoTask> loadTodoTasksByPriority(int priority);

}
