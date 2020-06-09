package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.EmployeeDto;
import com.springboot.service.EmployeeService;
import com.springboot.utill.CustomException;
import com.springboot.utill.ResponseData;
@RestController
@RequestMapping(value= "/employee" )
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<EmployeeDto>>> getAllEmployees() {
		ResponseData<List<EmployeeDto>> response=new ResponseData<>();
		response.setBody(employeeService.getAllEmployees());
	  return new ResponseEntity(response,HttpStatus.OK); 
	  }
	@PostMapping(produces="application/json",consumes="application/json")
	public @ResponseBody ResponseEntity<ResponseData<String>> createEmployee(@RequestBody EmployeeDto employeeDto) {
		ResponseData<String> response=new ResponseData<>();
		response.setBody(employeeService.createEmployee(employeeDto));
	  return new ResponseEntity(response,HttpStatus.OK); 
	  }
	
	@GetMapping(value="/{pincode}")
	public @ResponseBody ResponseEntity<ResponseData<List<EmployeeDto>>> getEmployeesByPincode(
			@PathVariable ("pincode") Integer pincode) {
		ResponseData<List<EmployeeDto>> response=new ResponseData<>();
		response.setBody(employeeService.getEmployeesByPincode(pincode));
	  return new ResponseEntity(response,HttpStatus.OK); 
	  }
	
	@DeleteMapping(value="/{id}")
	public @ResponseBody ResponseEntity<ResponseData<String>> deleteEmployee(
			@PathVariable ("id") Integer id) throws CustomException{
		ResponseData<String> response=new ResponseData<>();
		//response.setBody(employeeService.deleteEmployee(id));
	  return new ResponseEntity(response,HttpStatus.OK); 
	  }
}
