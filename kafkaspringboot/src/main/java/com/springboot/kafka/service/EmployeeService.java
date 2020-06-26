package com.springboot.kafka.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.kafka.model.Employee;
import com.springboot.kafka.repository.EmployeeRepository;


@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	private final Producer producer;
	@Autowired
	public EmployeeService(Producer producer) {
	this.producer = producer;
	}
	
	public String publishAllEmployees() {
		
		List<Employee> employList = repository.getAllEmployees();
		
		employList.parallelStream().forEach(emp->{
			ObjectMapper mapper = new ObjectMapper();	 
		      //Converting the Employee Object to JSONString
		      try { 
		    	  
		    	  String jsonString = mapper.writeValueAsString(emp); 
		    	  this.producer.sendMessage(jsonString);
		        } 
		        catch (IOException e) { 
		            e.printStackTrace(); 
		        } 
		});
		return "Successfully Published";
	}
}
