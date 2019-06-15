package com.mrlonewolfer.example69;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaskInfoBean implements Parcelable {
    String name,description,date,time,priority,taskStatus="Pending";

    @PrimaryKey(autoGenerate = true)
    int id;

    protected TaskInfoBean(Parcel in) {
        name = in.readString();
        description = in.readString();
        date = in.readString();
        time = in.readString();
        priority = in.readString();
        taskStatus = in.readString();
        id = in.readInt();
    }

    public static final Creator<TaskInfoBean> CREATOR = new Creator<TaskInfoBean>() {
        @Override
        public TaskInfoBean createFromParcel(Parcel in) {
            return new TaskInfoBean(in);
        }

        @Override
        public TaskInfoBean[] newArray(int size) {
            return new TaskInfoBean[size];
        }
    };

    public TaskInfoBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(priority);
        dest.writeString(taskStatus);
        dest.writeInt(id);
    }
}
