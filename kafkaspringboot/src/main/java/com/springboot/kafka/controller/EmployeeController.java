package com.springboot.kafka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.kafka.model.Employee;
import com.springboot.kafka.service.EmployeeService;
import com.springboot.kafka.service.Producer;
import com.springboot.kafka.util.ResponseData;

@RestController
@RequestMapping(value = "kafka/employee")
public class EmployeeController {

	//private final Producer producer;

	@Autowired
	private EmployeeService employeeService;
	
	/*
	 * @Autowired public EmployeeController(Producer producer) { this.producer =
	 * producer; }
	 */
	
	/*
	 * @GetMapping public void sendMessageToKafkaTopic(@RequestParam("message")
	 * String message) { this.producer.sendMessage(message); }
	 */
	  
	  @PostMapping(value="/publish")
		public @ResponseBody ResponseEntity<ResponseData<String>> publishAllEmployees() {
			ResponseData<String> response = new ResponseData<>();
			response.setBody(employeeService.publishAllEmployees());
			return new ResponseEntity(response, HttpStatus.OK);
		}
	
}
