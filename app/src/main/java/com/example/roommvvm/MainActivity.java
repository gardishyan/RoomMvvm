package com.example.roommvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roommvvm.database.EmployeeEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView init to display the data
        RecyclerView rv = findViewById(R.id.item_list);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new EmployeeAdapter();
        rv.setAdapter(adapter);

        //Retrieving data from ViewModel and passing to RecyclerView
        EmployeeViewModel employeeViewModel = new EmployeeViewModel(getApplication());
        employeeViewModel.getEmployees().observe(this, employees -> adapter.setEmployees(employees));

        //Add button, to insert records into database and update ViewModel
        TextView addButton = findViewById(R.id.add_item);
        addButton.setOnClickListener(v -> {
            employeeViewModel.addEmployee("Armen Garnikyan", 2344555);
        });

        //Initial read from database
        employeeViewModel.readEmployees();
    }
}