package com.springboot.kafka.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.springboot.kafka.model.Employee;
import com.springboot.kafka.repository.EmployeeRepository;

@Service
public class Consumer {

	private final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	private Gson gson;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Value("${tpd.topic-two}")
	private String topicTwo;

	@KafkaListener(topics = "${tpd.topic-one}", groupId = "group-id")
	public void consume(String employee) throws IOException {
		logger.info(String.format("$$ -> Consumed Employee from topic1 -->%s", employee));
		Employee emp = gson.fromJson(employee, Employee.class);
		Integer bonus = emp.getSalary() * 10 / 100;
		Integer salary = emp.getSalary() + bonus;
		Employee bonusEmp = new Employee();
		bonusEmp.setEmpId(emp.getEmpId());
		bonusEmp.setEmpName(emp.getEmpName());
		bonusEmp.setDesignation(emp.getDesignation());
		bonusEmp.setJoblevel(emp.getJoblevel());
		bonusEmp.setSalary(salary);
		bonusEmp.setRating(emp.getRating());
		logger.info(String.format("$$ -> Producing Employee to topic2 --> %s", bonusEmp));
		//this.kafkaTemplate.send(topicTwo, gson.toJson(bonusEmp));
		this.kafkaTemplate.send(topicTwo, String.valueOf(bonusEmp.getEmpId()), gson.toJson(bonusEmp));
	}

	@KafkaListener(topics = "${tpd.topic-two}", groupId = "group-id")
	public void consumeEmployee(String employee) throws IOException {
		logger.info(String.format("$$ -> Consumed Employee from topic2 -->%s", employee));
		Employee emp = gson.fromJson(employee, Employee.class);
		employeeRepository.createEmployee(emp);

	}
}
