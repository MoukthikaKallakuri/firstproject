package com.example.firstproject.controller;
import com.example.firstproject.model.Employee;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.firstproject.service.EmployeeService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@CrossOrigin(origins="http://localhost:5173")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }
    @GetMapping("/getData")
    public List<Employee> getData() throws Exception{
        return employeeService.getAllEmployees();
    }

}

