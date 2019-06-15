package com.mrlonewolfer.example69;

import android.graphics.Color;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewCustomeAdapter extends  RecyclerView.Adapter<TaskViewCustomeAdapter.taskViewHolder>  {


    private  OnTasKClickListner listner;
    private List<TaskInfoBean> taskBeanArrayList;

    public TaskViewCustomeAdapter(OnTasKClickListner listner, List<TaskInfoBean> taskBeanArrayList) {
        this.listner = listner;
        this.taskBeanArrayList = taskBeanArrayList;
    }

    public interface OnTasKClickListner {
        void onTaskClick(TaskInfoBean taskInfoBean,String myAction);
    }


    @NonNull
    @Override
    public taskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_view_row_item,viewGroup,false);
        taskViewHolder viewHolder = new taskViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull taskViewHolder holder, int position) {
        final TaskInfoBean taskInfo=taskBeanArrayList.get(position);
        holder.txtId.setText(taskInfo.getId()+"");
        holder.txtName.setText(taskInfo.getName());
        holder.txtDescription.setText(taskInfo.getDescription());
        holder.txtDate.setText(taskInfo.getDate());
        holder.txtTime.setText(taskInfo.getTime());
        holder.txtPriority.setText(taskInfo.getPriority());
        holder.txtStatus.setText(taskInfo.getTaskStatus());
        String taskStatus=taskInfo.getTaskStatus();
        if(taskStatus.equals("Completed")){
            holder.cardView.setCardBackgroundColor(Color.GRAY);
        }
        else {
            if(holder.txtPriority.getText().equals("High")){
                holder.cardView.setCardBackgroundColor(Color.RED);
            }
            if(holder.txtPriority.getText().equals("Average")){
                holder.cardView.setCardBackgroundColor(Color.BLUE);
            }
            if(holder.txtPriority.getText().equals("Low")){
                holder.cardView.setCardBackgroundColor(Color.GREEN);
            }

        }


        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onTaskClick(taskInfo,"Delete");
                taskBeanArrayList.remove(taskInfo);
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onTaskClick(taskInfo,"Edit");
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskBeanArrayList.size();
    }

    public class taskViewHolder extends RecyclerView.ViewHolder {
        TextView txtId,txtName,txtDescription,txtDate,txtTime,txtPriority,txtStatus;
        ImageView imgDelete,imgEdit;

        CardView cardView;

        public taskViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId=itemView.findViewById(R.id.txtId);
            txtName=itemView.findViewById(R.id.txtName);
            txtDescription=itemView.findViewById(R.id.txtDescription);
            txtDescription.setMovementMethod(new ScrollingMovementMethod());
            txtDate=itemView.findViewById(R.id.txtDate);
            txtTime=itemView.findViewById(R.id.txtTime);
            txtPriority=itemView.findViewById(R.id.txtPriority);
            txtStatus=itemView.findViewById(R.id.txtStatus);
            imgEdit=itemView.findViewById(R.id.imgEdit);
            imgDelete=itemView.findViewById(R.id.imgDelete);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }
}
