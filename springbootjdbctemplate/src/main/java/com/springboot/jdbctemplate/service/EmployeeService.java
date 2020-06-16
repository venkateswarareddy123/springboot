package com.springboot.jdbctemplate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springboot.jdbctemplate.model.Employee;
import com.springboot.jdbctemplate.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	public List<Employee> getAllEmployees() {


		List<Employee> employList = repository.getAllEmployees();
		return employList;
	}

	public String createEmployee(Employee employee) {
		String createEmployee=repository.createEmployee(employee);
		return createEmployee;
	}

	public String updateEmployee(Employee employee) {
		String updateEmployee=repository.updateEmployee(employee);
		return updateEmployee;
	}

	public String deleteEmployee(Integer id) {
		String deleteEmployee=repository.deleteEmployee(id);
		return deleteEmployee;
	}

}
