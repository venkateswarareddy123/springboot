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

import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "EMPLOYEE")
@Data
public class Employee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@NonNull
	@Column(name = "PK_EMP_ID")
	private Integer empId;
	@Column(name = "FIRST_NAME")
	@NonNull
	private String firstName;
	@Column(name = "LAST_NAME")
	@NonNull
	private String lastName;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "FK_EMP_ID")
	private List<Address> address;

}
