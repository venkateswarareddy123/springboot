package com.springboot;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.mock.web.MockMultipartFile;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.controller.EmployeeController;
import com.springboot.dto.EmployeeDto;
import com.springboot.service.EmployeeService;

import static org.mockito.ArgumentMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest

class EmployeeControllerTest {
	
	@InjectMocks
	EmployeeController empcontroller;
	
	@Mock
	EmployeeService empService;
	
	@BeforeEach
	public void setUp() throws Exception {		
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void getAllEmployeesTest() {
		when(empService.getAllEmployees())
				.thenReturn(Stream.of(new EmployeeDto(101, "sehwag", "Sachin"), new EmployeeDto(102, "Rohith", "Kohli"))
						.collect(Collectors.toList()));
		assertEquals(2, empcontroller.getAllEmployees().getBody().getBody().size());

	}
	@Test
	public void createEmployeeTest() {
		 String message ="Successfully Created";
		EmployeeDto emp = new EmployeeDto(1, "firstname1", "last1");
		when(empService.createEmployee(emp))
		.thenReturn(message);
		assertEquals(message, empcontroller.createEmployee(emp).getBody().getBody());
	}
	@Test
	public void updateEmployeeTest() {
		String message ="Successfully Updated";
		EmployeeDto emp = new EmployeeDto(1, "firstname2", "last2");
		when(empService.updateEmployee(emp))
		.thenReturn(message);
		assertEquals(message, empcontroller.updateEmployee(emp).getBody().getBody());
	}
	
	@Test
	public void getEmployeesByPincodeTest() {
		when(empService.getEmployeesByPincode(anyInt()))
				.thenReturn(Stream.of(new EmployeeDto(101, "sehwag", "Sachin"), new EmployeeDto(102, "Rohith", "Kohli"))
						.collect(Collectors.toList()));
		assertEquals(2, empcontroller.getEmployeesByPincode(544444).getBody().getBody().size());

	}
	@Test
	public void getEmployeesByCityTest() {
		when(empService.getEmployeesByCity(anyString()))
				.thenReturn(Stream.of(new EmployeeDto(101, "sehwag", "Sachin"), new EmployeeDto(102, "Rohith", "Kohli"))
						.collect(Collectors.toList()));
		assertEquals(2, empcontroller.getEmployeesByCity("HYD").getBody().getBody().size());

	}
	@Test
	public void deleteEmployeeTest() {
		 String message ="Employee Successfully Deleted";	
			when(empService.deleteEmployee(anyInt())).thenReturn(message);
			assertEquals(message, empcontroller.deleteEmployee(1999990).getBody().getBody());
		}
		 
	
}
	