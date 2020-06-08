package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.domain.Employee;
import com.springboot.dto.EmployeeDto;
import com.springboot.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
	@Autowired
	private EmployeeRepository repository;
	public List<EmployeeDto> getAllEmployees() {

		List<EmployeeDto> dtoList=new ArrayList<>();
		
		List<Employee> employList=repository.findAll();
		employList.forEach(e->{
			EmployeeDto dto=new EmployeeDto();
			dto.setEmpId(e.getEmpId());
			dto.setFirstName(e.getFirstName());
			dto.setLastName(e.getLastName());
			dtoList.add(dto);
		});
	
		return dtoList;
	}

	public String createEmployee(EmployeeDto employeeDto) {
		
        Employee emp=new Employee();
        emp.setFirstName(employeeDto.getFirstName());
        emp.setLastName(employeeDto.getLastName());
		repository.saveAndFlush(emp);
		return "Successfully Created";
	}

}
