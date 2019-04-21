package com.example.natand.springbootnatand.services.impl;

import com.example.natand.springbootnatand.dao.EmployeeDao;
import com.example.natand.springbootnatand.models.Employee;
import com.example.natand.springbootnatand.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Transactional
    @Override
    public Employee insertEmployee(Employee employee) {
        employeeDao.insertEmployee(employee);
        return employee;
    }

    @Transactional
    @Override
    public void insertEmployees(List<Employee> employees) {
        employeeDao.insertEmployees(employees);
    }

    @Transactional
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeDao.getAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
        return employees;
    }

    @Transactional
    @Override
    public Employee getEmployeeById(int empId) {
        Employee employee = employeeDao.getEmployeeById(empId);
        System.out.println(employee);
        return employee;
    }

    @Override
    public void deleteEmployee(int empId) {
        employeeDao.deleteEmployee(empId);
    }
}
