package com.springboot.kafka.model;


import lombok.Data;

@Data
public class Employee {

	private Integer empId;
	private String empName;
	private String designation;
	private Integer joblevel;
	private Integer salary;
	private Integer rating;
	
}
