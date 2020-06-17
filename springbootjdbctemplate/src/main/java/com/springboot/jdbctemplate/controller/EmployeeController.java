package com.springboot.jdbctemplate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.jdbctemplate.model.Employee;
import com.springboot.jdbctemplate.service.EmployeeService;
import com.springboot.jdbctemplate.util.CustomException;
import com.springboot.jdbctemplate.util.ResponseData;



@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<Employee>>> getAllEmployees() {
		ResponseData<List<Employee>> response = new ResponseData<>();
		response.setBody(employeeService.getAllEmployees());
		return new ResponseEntity(response, HttpStatus.OK);
	}
	@PostMapping(produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseEntity<ResponseData<String>> createEmployee(
			@RequestBody Employee employee) {
		ResponseData<String> response = new ResponseData<>();
		response.setBody(employeeService.createEmployee(employee));
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@PutMapping(produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseEntity<ResponseData<String>> updateEmployee(
			@RequestBody Employee employee) {
		ResponseData<String> response = new ResponseData<>();
		response.setBody(employeeService.updateEmployee(employee));
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<ResponseData<String>> deleteEmployee(@PathVariable("id") Integer id) {
		ResponseData<String> response = new ResponseData<>();
		response.setBody(employeeService.deleteEmployee(id));
		return new ResponseEntity(response, HttpStatus.OK);
	}
	@PostMapping(value = "/bulkemployees", consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = "application/json")
	public ResponseEntity<ResponseData<String>> saveBulkEmployees(@RequestParam(value = "file") MultipartFile file)
			throws CustomException {
		ResponseData<String> response = new ResponseData<>();
		try {
			response.setBody(employeeService.saveBulkEmployees(file));
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return new ResponseEntity(response, HttpStatus.OK);

	}
}
