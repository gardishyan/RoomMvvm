package com.example.roommvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roommvvm.database.EmployeeEntity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeCardHolder> {
    List<EmployeeEntity> employees;

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = new ArrayList<>(employees);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new EmployeeCardHolder(inflater.inflate(R.layout.view_employee_item, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull EmployeeCardHolder holder, int position) {
        holder.id.setText(String.valueOf(employees.get(position).id));
        holder.name.setText(employees.get(position).name);
        holder.salary.setText(String.valueOf(employees.get(position).salary));
    }

    @Override
    public int getItemCount() {
        return employees != null ? employees.size() : 0;
    }
    class EmployeeCardHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView salary;

        public EmployeeCardHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.employee_id);
            name = itemView.findViewById(R.id.employee_name);
            salary = itemView.findViewById(R.id.employee_salary);
        }
    }
}
