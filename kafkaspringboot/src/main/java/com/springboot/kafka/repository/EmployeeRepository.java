package com.springboot.kafka.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springboot.kafka.model.Employee;
import com.springboot.kafka.model.EmployeeRowMapper;


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
		  jdbcTemplate.update(env.getProperty("CREATE_EMPLOYEE"), employee.getEmpId(), employee.getEmpName(), employee.getDesignation(),
				  employee.getJoblevel(),employee.getSalary(),employee.getRating());
		return "Employee Created Succesfully";
		  
	 }
}
