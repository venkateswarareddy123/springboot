package com.springboot.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.jmapper.annotations.JMap;
import com.springboot.dto.AddressDto;
import com.springboot.dto.EmployeeDto;

import lombok.Data;


@Entity
@Table(name = "ADDRESS")
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PK_ADDRESS_ID")
	private @JMap Integer addressId;
	@Column(name = "CITY")
	private @JMap String city;
	@Column(name = "PINCODE")
	private @JMap Integer pincode;
	@Column(name = "PHONE_NUMBER")
	private @JMap Long phoneNumber;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_EMP_ID", referencedColumnName = "PK_EMP_ID")
	@JsonIgnore
	private Employee empId;
	
}
