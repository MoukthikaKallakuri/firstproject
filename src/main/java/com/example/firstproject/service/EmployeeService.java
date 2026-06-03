package com.example.firstproject.service;
import com.example.firstproject.model.Employee;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.api.core.ApiFuture;
import java.util.List;
import java.util.ArrayList;

@Service
public class EmployeeService {
    @Autowired
    private Firestore firestore;
    public List<Employee> getAllEmployees() throws Exception {
        List<Employee> employeeList=new ArrayList<>();
        ApiFuture<QuerySnapshot> future=firestore.collection("employee").get();
        List<QueryDocumentSnapshot> documents=future.get().getDocuments();
        for(DocumentSnapshot document:documents){
            Employee employee=document.toObject(Employee.class);
            employeeList.add(employee);

        }
        return employeeList;
    }
}
