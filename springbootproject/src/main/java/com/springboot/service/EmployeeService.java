package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.googlecode.jmapper.JMapper;
import com.springboot.domain.Address;
import com.springboot.domain.Employee;
import com.springboot.dto.AddressDto;
import com.springboot.dto.EmployeeDto;
import com.springboot.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Cacheable(value="employee")
	public List<EmployeeDto> getAllEmployees() {

		List<EmployeeDto> dtoList = new ArrayList<>();

		List<Employee> employList = repository.findAll();
		if(employList != null && !employList.isEmpty()) {
		convertObjectToDto(dtoList, employList);
		}
		return dtoList;
	}

	private void convertObjectToDto(List<EmployeeDto> dtoList, List<Employee> employList) {
		JMapper<EmployeeDto, Employee> jmapper = new JMapper<>(EmployeeDto.class, Employee.class);
		JMapper<AddressDto, Address> addressMapper = new JMapper<>(AddressDto.class, Address.class);
		employList.forEach(e -> {
			EmployeeDto dto = jmapper.getDestination(e);
			List<AddressDto> addressList = new ArrayList<>();
			e.getAddress().forEach(ad -> {
				AddressDto addDto = addressMapper.getDestination(ad);
				addDto.setEmpId(e.getEmpId());
				addressList.add(addDto);
				dto.setAddress(addressList);
			});
			dtoList.add(dto);
		});
	}
	
	public String createEmployee(EmployeeDto employeeDto) {

		Employee emp = new Employee();
		emp.setFirstName(employeeDto.getFirstName());
		emp.setLastName(employeeDto.getLastName());
		List<Address> addressList = new ArrayList<>();
		employeeDto.getAddress().forEach(addressDto -> {
			Address address = new Address();
			address.setCity(addressDto.getCity());
			address.setPhoneNumber(addressDto.getPhoneNumber());
			address.setPincode(addressDto.getPincode());
			addressList.add(address);
		});
		emp.setAddress(addressList);
		repository.saveAndFlush(emp);
		return "Successfully Created";
	}

	public List<EmployeeDto> getEmployeesByPincode(Integer pincode) {

		List<EmployeeDto> employDtoList = new ArrayList<>();

		List<Employee> employList = repository.findEmployeeByPincode(pincode);
		if(employList != null && !employList.isEmpty()) {
			convertObjectToDto(employDtoList, employList);	
		}
		return employDtoList;
	}
	@CacheEvict(value="employee",allEntries = true,key = "#id")
	public String deleteEmployee(Integer id) {

		repository.deleteEmployee(id);
		
		return "Employee Successfully Deleted";
	}

	public List<EmployeeDto> getEmployeesByCity(Integer city) {
		List<EmployeeDto> employDtoList = new ArrayList<>();

		List<Employee> employList = repository.findEmployeeByCity(city);
		if(employList != null && !employList.isEmpty()) {
			convertObjectToDto(employDtoList, employList);	
		}
		return employDtoList;
	}
	
	

}
