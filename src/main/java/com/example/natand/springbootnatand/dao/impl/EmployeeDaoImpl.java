package com.example.natand.springbootnatand.dao.impl;

import com.example.natand.springbootnatand.dao.EmployeeDao;
import com.example.natand.springbootnatand.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        String sql = "INSERT INTO employee "
                + "(empId, empName, designation, salary) VALUES (?, ?, ?, ?)";
        getJdbcTemplate().update(sql, new Object[]{
                employee.getEmpId(), employee.getName(), employee.getDesignation(), employee.getSalary()
        });
        return employee;
    }

    @Override
    public void insertEmployees(List<Employee> employees) {
        String sql = "INSERT INTO employee "
                + "(empId, empName, designation, salary) VALUES (?, ?, ?, ?)";
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pS, int i) throws SQLException {
                Employee employee = employees.get(i);
                pS.setInt(1, employee.getEmpId());
                pS.setString(2, employee.getName());
                pS.setString(3, employee.getDesignation());
                pS.setDouble(4, employee.getSalary());
            }

            @Override
            public int getBatchSize() {
                return employees.size();
            }
        });
    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employee";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Employee> result = new ArrayList<Employee>();
        for(Map<String, Object> row:rows){
            Employee employee = new Employee();
            employee.setEmpId((Integer)row.get("empId"));
            employee.setName((String)row.get("empName"));
            employee.setDesignation((String)row.get("designation"));
            employee.setSalary((Double) row.get("salary"));
            result.add(employee);
        }
        return result;
    }

    @Override
    public Employee getEmployeeById(int empId) {
        String sql = "SELECT * FROM employee WHERE empId = ?";
        return (Employee)getJdbcTemplate().queryForObject(sql, new Object[]{empId}, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Employee employee = new Employee();
                employee.setEmpId(rs.getInt("empId"));
                employee.setName(rs.getString("empName"));
                employee.setDesignation(rs.getString("designation"));
                employee.setSalary(rs.getDouble("salary"));
                return employee;
            }
        });
    }

    @Override
    public void deleteEmployee(int empId) {
        String sql = "DELETE FROM employee WHERE empId = ?";
        getJdbcTemplate().update(sql, new Object[]{empId});
    }
}
