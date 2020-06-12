package com.springboot.dto;


import com.googlecode.jmapper.annotations.JMap;

import lombok.Data;
@Data
public class AddressDto {
	private @JMap Integer addressId;
	private @JMap String city;
	private @JMap Integer pincode;
	private @JMap Long phoneNumber;
	private Integer empId;
		
}
