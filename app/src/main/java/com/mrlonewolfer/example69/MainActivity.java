package com.mrlonewolfer.example69;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/*
*
* To create task management application for
* adding, updating, deleting the task and
*
* show the tasks in the list view or gridview.
*
* Task have name, description, date, time, priority.

•    To sort the task by the date and time wise.
•    if task is due then automatically show as blue color.
•    It will search the tasks by date wise.

•    If high priority then show as red color ,
    average priority as blue color,
      low priority as  green  color.

•    Select specified item an open context menu to
select “Complete the Task” then
        this task  is modified as gray color and

 stripe line over the all the statements (showable labels).

 •    Search task by name in the listview.

*
* */
public class MainActivity extends AppCompatActivity implements TaskGridViewAdapter.OnTaskClickListner{

    GridView gridView;
    ArrayList<TaskGridViewBean> taskGridViewArray;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_main);
        gridView=findViewById(R.id.gridView);

        preparedGridView();

        TaskGridViewAdapter taskGridViewAdapter=new TaskGridViewAdapter(taskGridViewArray, context,this);
        gridView.setAdapter(taskGridViewAdapter);

    }

    private void preparedGridView() {
        taskGridViewArray= new ArrayList<>();
        taskGridViewArray.add(new TaskGridViewBean(R.drawable.add,"Add"));
        taskGridViewArray.add(new TaskGridViewBean(R.drawable.edit,"Edit"));
        taskGridViewArray.add(new TaskGridViewBean(R.drawable.view,"View"));
        taskGridViewArray.add(new TaskGridViewBean(R.drawable.report,"Report"));
        taskGridViewArray.add(new TaskGridViewBean(R.drawable.setting,"Setting"));
        taskGridViewArray.add(new TaskGridViewBean(R.drawable.search,"Search"));


    }


    @Override
    public void onTaskClick(String myAction) {

        if(myAction.equals("Add")){
            Toast.makeText(context, "You Click On"+myAction, Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(MainActivity.this,AddActivity.class);
            startActivity(intent);
        }
        if(myAction.equals("Edit")){
            Toast.makeText(context, "You Click On"+myAction, Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(MainActivity.this,EditActivity.class);
            startActivity(intent);

        }
        if(myAction.equals("View")){
            Toast.makeText(context, "You Click On"+myAction, Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(MainActivity.this,ViewActivity.class);
            startActivity(intent);
        }
        if(myAction.equals("Report")){
            Toast.makeText(context, "You Click On"+myAction, Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(MainActivity.this,AddActivity.class);
            startActivity(intent);
        }
        if(myAction.equals("Setting")){
            Toast.makeText(context, "You Click On"+myAction, Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(MainActivity.this,AddActivity.class);
            startActivity(intent);
        }
        if(myAction.equals("Search")){
            Toast.makeText(context, "You Click On"+myAction, Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(MainActivity.this,AddActivity.class);
            startActivity(intent);
        }
    }
}
