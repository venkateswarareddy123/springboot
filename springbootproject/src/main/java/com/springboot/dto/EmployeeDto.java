package com.springboot.dto;

import java.util.List;

import com.googlecode.jmapper.annotations.JMap;


public class EmployeeDto {

	private @JMap Integer empId;
	private @JMap String firstName;
	private @JMap String lastName;
	private List<AddressDto> address;
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<AddressDto> getAddress() {
		return address;
	}
	public void setAddress(List<AddressDto> address) {
		this.address = address;
	}

	
}
