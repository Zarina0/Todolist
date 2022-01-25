package com.example.todolist.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.todolist.models.TaskModel;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(TaskModel taskModel);

    @Query("SELECT * FROM task_model")
    LiveData<List<TaskModel>> getData();

    @Delete
    void delete(TaskModel model);

}
