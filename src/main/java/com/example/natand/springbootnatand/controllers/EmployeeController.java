package com.example.natand.springbootnatand.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.natand.springbootnatand.models.Employee;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/employees"})
public class EmployeeController {

    private List<Employee> employees = createList();

    @RequestMapping(produces = "application/json")
    public List<Employee> firstPage() {
        return employees;
    }

    @DeleteMapping(path = { "/{id}" })
    public Employee delete(@PathVariable("id") int id) {
        Employee deletedEmp = null;
        for (Employee emp : employees) {
            if (emp.getEmpId().equals(id)) {
                employees.remove(emp);
                deletedEmp = emp;
                break;
            }
        }
        return deletedEmp;
    }

    @PostMapping
    public Employee create(@RequestBody Employee user) {
        employees.add(user);
        System.out.println(employees);
        return user;
    }

    private static List<Employee> createList() {
        List<Employee> tempEmployees = new ArrayList<>();
        Employee emp1 = new Employee();
        emp1.setName("Natan");
        emp1.setDesignation("DevOps Engineer");
        emp1.setEmpId("1");
        emp1.setSalary(3000);

        Employee emp2 = new Employee();
        emp2.setName("Marc");
        emp2.setDesignation("Developer");
        emp2.setEmpId("2");
        emp2.setSalary(2100);

        tempEmployees.add(emp1);
        tempEmployees.add(emp2);
        return tempEmployees;
    }

}