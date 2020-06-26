package com.springboot.kafka.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class EmployeeRowMapper implements RowMapper<Employee>{
	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp= new Employee();
		emp.setEmpId(rs.getInt("PK_EMP_ID"));
		emp.setEmpName(rs.getString("EMP_NAME"));
		emp.setDesignation(rs.getString("DESIGNATION"));
		emp.setJoblevel(rs.getInt("JOBLEVEL"));
		emp.setSalary(rs.getInt("SALARY"));
		emp.setRating(rs.getInt("RATING"));
		return emp;
	}
}
