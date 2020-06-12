package com.springboot.dto;

import java.util.List;

import com.googlecode.jmapper.annotations.JMap;

import lombok.Data;

@Data
public class EmployeeDto {

	private @JMap Integer empId;
	private @JMap String firstName;
	private @JMap String lastName;
	private List<AddressDto> address;
	
	public EmployeeDto() {
		
	}
	public EmployeeDto(Integer empId, String firstName, String lastName) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	

	
}
