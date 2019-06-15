package com.mrlonewolfer.example69;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtName,edtDescription,edtDate,edtTime,edtId;
    Spinner spinnerPriority;
    Context context;
    TaskDao taskDao;
    TaskInfoBean taskInfoBean;
    Button btnSubmit,btnCancle;
    String priority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        context=this;

        Intent intent=getIntent();
        taskInfoBean=intent.getParcelableExtra("TaskInfoData");
        AppDataBaseCon appDatabaseCon= Room.databaseBuilder(this,AppDataBaseCon.class,"taskInfo_db")
                .allowMainThreadQueries()
                .build();
        taskDao=appDatabaseCon.taskDao();

        edtId=findViewById(R.id.edtId);
        edtName=findViewById(R.id.edtName);
        edtDescription=findViewById(R.id.edtDesctiption);
        edtDate=findViewById(R.id.edtDate);
        edtTime=findViewById(R.id.edtTime);
        btnSubmit=findViewById(R.id.btnSumbit);
        spinnerPriority=findViewById(R.id.spinnerTaskPriority);
        btnCancle=findViewById(R.id.btnCancel);
        spinnerPriority=findViewById(R.id.spinnerTaskPriority);

        edtId.setText(taskInfoBean.getId()+"");
        edtName.setText(taskInfoBean.getName());
        edtDescription.setText(taskInfoBean.getDescription());
        edtDate.setText(taskInfoBean.getDate());
        edtTime.setText(taskInfoBean.getTime());
        btnSubmit.setOnClickListener(this);
        btnCancle.setOnClickListener(this);

        spinnerPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                priority=spinnerPriority.getItemAtPosition(position).toString();
                Toast.makeText(context, "Selected Value is :"+priority, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnSumbit){
            taskInfoBean.setName(edtName.getText().toString());
            taskInfoBean.setDescription(edtDescription.getText().toString());
            taskInfoBean.setPriority(priority);
            taskInfoBean.setTaskStatus("Completed");
            taskDao.updateTaskInfo(taskInfoBean);
            Toast.makeText(context, "Task Update Succesfully", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(EditActivity.this,ViewActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.btnCancel){
            Intent intent=new Intent(EditActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
