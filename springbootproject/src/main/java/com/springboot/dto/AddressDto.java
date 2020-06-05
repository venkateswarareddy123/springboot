package com.springboot.dto;

import lombok.Data;

@Data
public class AddressDto {
	private Integer addressId;
	private String city;
	private Long pincode;
	private Long phoneNumber;
	private Integer empId;

}
