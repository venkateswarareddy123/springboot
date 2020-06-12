package com.springboot.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.jmapper.annotations.JMap;
import com.springboot.dto.AddressDto;
import com.springboot.dto.EmployeeDto;

import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "EMPLOYEE")
@Data
public class Employee implements Serializable {

	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NonNull
	@Column(name = "PK_EMP_ID")
	private @JMap Integer empId;
	@Column(name = "FIRST_NAME")
	@NonNull
	private @JMap String firstName;
	@Column(name = "LAST_NAME")
	@NonNull
	private @JMap String lastName;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "FK_EMP_ID")
	private List<Address> address;
	public Employee() {
	}
		

}
