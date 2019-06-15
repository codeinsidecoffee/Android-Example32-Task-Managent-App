package com.mrlonewolfer.example69;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TaskDao {

    @Insert
    void insertTaskInfo(TaskInfoBean taskInfoBean);

    @Update
    void updateTaskInfo(TaskInfoBean taskInfoBean);

    @Delete
    void removeTaskInfo(TaskInfoBean taskInfoBean);

    @Query("Select * From taskInfoBean")
    List<TaskInfoBean> getAllTaskInfoBeans();

}
