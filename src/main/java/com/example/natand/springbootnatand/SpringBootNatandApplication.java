package com.example.natand.springbootnatand;

import com.example.natand.springbootnatand.models.Employee;
import com.example.natand.springbootnatand.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootNatandApplication {

	@Autowired
	EmployeeService employeeService;

	public static void main(String[] args) {
//		SpringApplication.run(SpringBootNatandApplication.class, args);
		ApplicationContext applicationContext = SpringApplication.run(SpringBootNatandApplication.class, args);
		EmployeeService employeeService = applicationContext.getBean(EmployeeService.class);

		Employee employee0 = new Employee();
		employee0.setEmpId(1);
		employee0.setName("Natan");
		employee0.setDesignation("DevOps Engineer");
		employee0.setSalary(3000);

		Employee employee1 = new Employee();
		employee1.setEmpId(2);
		employee1.setName("Charlotte");
		employee1.setDesignation("Developer");
		employee1.setSalary(2500);

		Employee employee2 = new Employee();
		employee2.setEmpId(3);
		employee2.setName("Harry");
		employee2.setDesignation("Sorcerer");
		employee2.setSalary(9000);

		employeeService.insertEmployee(employee0);

		List<Employee> employees = new ArrayList<>();
		employees.add(employee1);
		employees.add(employee2);
		employeeService.insertEmployees(employees);

		employeeService.getAllEmployees();

		employeeService.getEmployeeById(employee1.getEmpId());
	}

}
