package com.mrlonewolfer.example69;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskGridViewAdapter extends BaseAdapter {

    ArrayList<TaskGridViewBean> taskGridViewArray;
    TaskGridViewBean taskGridViewBean;
    Context context;
    ImageView imageView;
    TextView textView;
    public  interface  OnTaskClickListner{
        void  onTaskClick(String myAction);
    }

    OnTaskClickListner listner;

    public TaskGridViewAdapter(ArrayList<TaskGridViewBean> taskGridViewArray, Context context, OnTaskClickListner listner) {
        this.taskGridViewArray = taskGridViewArray;
        this.context = context;
        this.listner = listner;
    }

    @Override
    public int getCount() {
        return taskGridViewArray.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            convertView=inflater.inflate(R.layout.grid_view_row_item,parent,false);
        }
        taskGridViewBean=taskGridViewArray.get(position);
        imageView=convertView.findViewById(R.id.grid_image);
        textView=convertView.findViewById(R.id.grid_text);

        imageView.setImageResource(taskGridViewBean.getImgId());
        textView.setText(taskGridViewBean.getImgName());
        imageView.setOnClickListener(new View.OnClickListener() {

            String myaction=taskGridViewArray.get(position).getImgName();
            @Override
            public void onClick(View v) {
                if(myaction.equals("Add")){
                    listner.onTaskClick("Add");
                }
                if(myaction.equals("Edit")){
                    listner.onTaskClick("Edit");
                }
                if(myaction.equals("View")){
                    listner.onTaskClick("View");
                }
                if(myaction.equals("Report")){
                    listner.onTaskClick("Report");
                }
                if(myaction.equals("Setting")){
                    listner.onTaskClick("Setting");
                }
                if(myaction.equals("Search")){
                    listner.onTaskClick("Search");
                }
            }
        });

        return convertView;
    }
}
