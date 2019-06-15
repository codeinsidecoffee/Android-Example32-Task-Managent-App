package com.mrlonewolfer.example69;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName,edtDescription,edtDate,edtTime;
    Spinner spinnerPriority;
    Context context;
    TaskDao taskDao;
    TaskInfoBean taskInfoBean;
    Button btnSubmit,btnCancle;
    String priority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        context=this;


        AppDataBaseCon appDatabaseCon= Room.databaseBuilder(this,AppDataBaseCon.class,"taskInfo_db")
                .allowMainThreadQueries()
                .build();
        taskDao=appDatabaseCon.taskDao();

        edtName=findViewById(R.id.edtName);
        edtDescription=findViewById(R.id.edtDesctiption);
        edtDate=findViewById(R.id.edtDate);
        edtTime=findViewById(R.id.edtTime);
        btnSubmit=findViewById(R.id.btnSumbit);
        btnCancle=findViewById(R.id.btnCancel);
        spinnerPriority=findViewById(R.id.spinnerTaskPriority);
            
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

        edtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    showDatePickerDailog();

                }
            }

            private void showDatePickerDailog() {
                final Calendar calendar=Calendar.getInstance();
                final int Year=calendar.get(Calendar.YEAR);
                final int Month=calendar.get(Calendar.MONTH);
                final int Day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                edtDate.setText(dayOfMonth+"/"+month+"/"+year);

                            }
                        },Year,Month,Day);

                datePickerDialog.show();
            }
        });
        
        edtTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                showTimePickerDailog();
            }

            private void showTimePickerDailog() {
                Calendar calendar=Calendar.getInstance();
                int Hour=calendar.get(Calendar.HOUR);
                int Minute=calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {



                            edtTime.setText("Time is : "+hourOfDay+" : "+minute);

                    }
                },Hour,Minute,false);
                timePickerDialog.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnSumbit){
            insertTaskInfo();
        }
        if(v.getId()==R.id.btnCancel){
            Intent intent= new Intent(AddActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void insertTaskInfo() {
        if(taskInfoBean==null){
            taskInfoBean= new TaskInfoBean();
            taskInfoBean.setName(edtName.getText().toString());
            taskInfoBean.setDescription(edtDescription.getText().toString());
            taskInfoBean.setTime(edtTime.getText().toString());
            taskInfoBean.setDate(edtDate.getText().toString());
            taskInfoBean.setPriority(priority);
            taskDao.insertTaskInfo(taskInfoBean);

        }
       
        resetAllData();
        Toast.makeText(context, "Data Succefully Inserted", Toast.LENGTH_SHORT).show();
    }

    private void resetAllData() {
        edtName.setText("");
        edtDescription.setText("");
        edtDate.setText("");
        edtTime.setText("");

        taskInfoBean=null;
    }
}
