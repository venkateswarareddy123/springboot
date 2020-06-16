package com.springboot.jdbctemplate.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



public class EmployeeRowMapper implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp= new Employee();
		emp.setEmpId(rs.getInt("PK_EMP_ID"));
		emp.setFirstName(rs.getString("FIRST_NAME"));
		emp.setLastName(rs.getString("LAST_NAME"));
		return emp;
	}
}
