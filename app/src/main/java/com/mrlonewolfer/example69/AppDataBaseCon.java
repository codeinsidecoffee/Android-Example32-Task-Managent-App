package com.mrlonewolfer.example69;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TaskInfoBean.class},version = 1,exportSchema = false)
public abstract class AppDataBaseCon extends RoomDatabase {
    public abstract TaskDao taskDao();
}
