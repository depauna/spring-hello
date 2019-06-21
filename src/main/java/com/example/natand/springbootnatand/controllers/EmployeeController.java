package com.example.natand.springbootnatand.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.natand.springbootnatand.models.Employee;

import com.example.natand.springbootnatand.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
public class EmployeeController {

    private List<Employee> employees = createList();
    @Autowired


    @RequestMapping(produces = "application/json")
    public List<Employee> firstPage() {
         return employees;
    }

    @GetMapping(produces = "application/json")
    @RequestMapping({"/{id}"})
    public Employee get(@PathVariable("id") int id) {
        Employee getEmp = null;
        for (Employee emp : employees) {
            if (emp.getEmpId() == id) {
                getEmp = emp;
                break;
            }
        }
        return getEmp;
    }

    @DeleteMapping(path = {"/{id}"})
    public void delete(@PathVariable("id") int id) {
        Employee deletedEmp = null;
        for (Employee emp : employees) {
            if (emp.getEmpId() == id) {
                employees.remove(emp);
                deletedEmp = emp;
                break;
            }
        }
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json")
    public Employee create(@RequestBody Employee user) {
         employees.add(user);
        System.out.println(employees);
        return user;
    }

    @GetMapping(produces = "application/json")
    @RequestMapping({"/validateLogin"})
    public User validateLogin() {
        return new User("User successfully authenticated");
    }

    private static List<Employee> createList() {
        List<Employee> tempEmployees = new ArrayList<>();
        Employee emp1 = new Employee();
        emp1.setName("Natan");
        emp1.setDesignation("DevOps Engineer");
        emp1.setEmpId(1);
        emp1.setSalary(3000);

        Employee emp2 = new Employee();
        emp2.setName("Marc");
        emp2.setDesignation("Developer");
        emp2.setEmpId(2);
        emp2.setSalary(2100);

        tempEmployees.add(emp1);
        tempEmployees.add(emp2);
        return tempEmployees;
    }

}