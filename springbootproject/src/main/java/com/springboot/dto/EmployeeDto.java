package com.springboot.dto;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeDto {

	private Integer empId;
	private String firstName;
	private String lastName;
	private List<AddressDto> address;

}
