package com.example.natand.springbootnatand.dao;

import com.example.natand.springbootnatand.models.Employee;

import java.util.List;

public interface EmployeeDao {
    Employee insertEmployee(Employee employee);
    void insertEmployees(List<Employee> employees);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int empId);
    void deleteEmployee(int empId);
}
