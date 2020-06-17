package com.springboot.jdbctemplate.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springboot.jdbctemplate.model.Employee;
import com.springboot.jdbctemplate.model.EmployeeRowMapper;


@Repository
@PropertySource(value= {"classpath:queries.properties"})
public class EmployeeRepository {

	@Autowired
	private Environment env;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	 public List<Employee> getAllEmployees() {
		  RowMapper<Employee> rowMapper = new EmployeeRowMapper();
		  List<Employee> list = jdbcTemplate.query(env.getProperty("FETCH_ALL_EMPLOYEES"), rowMapper);		  
		  return list;
	 }
	 public String createEmployee(Employee employee) {
		  jdbcTemplate.update(env.getProperty("CREATE_EMPLOYEE"), employee.getEmpId(), employee.getFirstName(), employee.getLastName());
		return "Employee Created Succesfully";
		  
	 }
	 
	 public String updateEmployee(Employee employee) {
		  jdbcTemplate.update(env.getProperty("UPDATE_EMPLOYEE"), employee.getFirstName(), employee.getLastName(), employee.getEmpId());
		  return "Employee Updated Successfully ";
	 }

	 public String deleteEmployee(Integer id) {
		  jdbcTemplate.update(env.getProperty("DELETE_EMPLOYEE"), id);
		  return "Employee Deleted Successfully";
	 }
	 public void insertMultipleRecords(List<Employee> employeeList) {
			jdbcTemplate.batchUpdate(env.getProperty("CREATE_EMPLOYEE"), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Employee emp = employeeList.get(i);
					ps.setInt(1, emp.getEmpId());
					ps.setString(2, emp.getFirstName());
					ps.setString(3, emp.getLastName());					
				}
				
				@Override
				public int getBatchSize() {
					return employeeList.size();
				}
			});
		}
	 
}
