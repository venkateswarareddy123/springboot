package com.springboot.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.kafka.service.Producer;

@RestController
@RequestMapping(value = "kafka/employee")
public class EmployeeController {

	private final Producer producer;
	@Autowired
	public EmployeeController(Producer producer) {
	this.producer = producer;
	}
	
	  @GetMapping
	    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
	    this.producer.sendMessage(message);
	    }

	
}
