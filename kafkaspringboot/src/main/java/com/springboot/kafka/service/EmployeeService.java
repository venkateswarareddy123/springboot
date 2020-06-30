package com.springboot.kafka.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.springboot.kafka.model.Employee;
import com.springboot.kafka.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private Gson gson;

	private final Producer producer;

	@Autowired
	public EmployeeService(Producer producer) {
		this.producer = producer;
	}

	public String publishAllEmployees() {
		
		List<Employee> employList = repository.getAllEmployees();
		
		employList.parallelStream().forEach(emp->{
		    	  this.producer.sendMessage(String.valueOf(emp.getEmpId()), gson.toJson(emp));
		});
		return "Successfully Published";
	}
}
