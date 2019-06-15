package com.mrlonewolfer.example69;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity implements TaskViewCustomeAdapter.OnTasKClickListner {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    TaskViewCustomeAdapter taskViewCustomeAdapter;
  List<TaskInfoBean> taskBeanArrayList;
    TaskDao taskDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        context=this;
        AppDataBaseCon appDatabaseCon= Room.databaseBuilder(this,AppDataBaseCon.class,"taskInfo_db")
                .allowMainThreadQueries()
                .build();
        taskDao=appDatabaseCon.taskDao();


        recyclerView=findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        viewMyAllTask();


    }

    private void viewMyAllTask() {
        taskBeanArrayList=taskDao.getAllTaskInfoBeans();
        taskViewCustomeAdapter = new TaskViewCustomeAdapter(this,taskBeanArrayList);
        recyclerView.setAdapter(taskViewCustomeAdapter);

    }

    @Override
    public void onTaskClick(TaskInfoBean taskInfoBean, String myAction) {
        if(myAction.equals("Delete")){
            taskDao.removeTaskInfo(taskInfoBean);
           viewMyAllTask();

        }
        if(myAction.equals("Edit")){
            Intent intent= new Intent(ViewActivity.this,EditActivity.class);
            intent.putExtra("TaskInfoData",taskInfoBean);
            startActivity(intent);
        }
    }
}
