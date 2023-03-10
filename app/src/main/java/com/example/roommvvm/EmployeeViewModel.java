package com.example.roommvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.roommvvm.database.AppDatabase;
import com.example.roommvvm.database.DatabaseClient;
import com.example.roommvvm.database.EmployeeEntity;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {
    private MutableLiveData<List<EmployeeEntity>> employees;
    private AppDatabase appDatabase;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        employees = new MutableLiveData<>();
        appDatabase = DatabaseClient.getInstance(getApplication()).getAppDatabase();
    }

    public LiveData<List<EmployeeEntity>> getEmployees() {
        return employees;
    }


    public void addEmployee(String name, Integer salary) {
        //DB access must be done from background thread
        AsyncTask.execute(() -> {
            appDatabase.employeeDao().insertEmployee(new EmployeeEntity(name, salary));
            List<EmployeeEntity> updatedList = appDatabase.employeeDao().getAll();
            employees.postValue(updatedList);
        });
    }


    public void readEmployees() {
        AsyncTask.execute(() -> {
            List<EmployeeEntity> currentList = appDatabase.employeeDao().getAll();
            employees.postValue(currentList);
        });
    }
}