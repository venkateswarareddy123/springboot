package com.springboot.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.googlecode.jmapper.JMapper;
import com.springboot.domain.Address;
import com.springboot.domain.Employee;
import com.springboot.dto.AddressDto;
import com.springboot.dto.EmployeeDto;
import com.springboot.repository.EmployeeRepository;
import com.springboot.utill.CustomException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Cacheable(value="employeeCache")
	public List<EmployeeDto> getAllEmployees() {

		List<EmployeeDto> dtoList = new ArrayList<>();

		List<Employee> employList = repository.findAll();
		if(employList != null && !employList.isEmpty()) {
		convertObjectToDto(dtoList, employList);
		}
		return dtoList;
	}

	private void convertObjectToDto(List<EmployeeDto> dtoList, List<Employee> employList) {
		ModelMapper modelMapper = new ModelMapper();
		employList.forEach(e -> {
			EmployeeDto dto = modelMapper.map(e, EmployeeDto.class);
			List<AddressDto> addressList = new ArrayList<>();
			e.getAddress().forEach(ad -> {
				AddressDto addDto = modelMapper.map(ad, AddressDto.class);
				addDto.setEmpId(e.getEmpId());
				addressList.add(addDto);
				dto.setAddress(addressList);
			});
			dtoList.add(dto);
		});
	}
	
	@CacheEvict(value="employeeCache",allEntries = true,key = "#id")
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
	@CacheEvict(value="employeeCache",allEntries = true,key = "#id")
	public String deleteEmployee(Integer id) {

		repository.deleteEmployee(id);
		
		return "Employee Successfully Deleted";
	}

	public List<EmployeeDto> getEmployeesByCity(String city) {
		List<EmployeeDto> employDtoList = new ArrayList<>();

		List<Employee> employList = repository.findEmployeeByCity(city);
		if(employList != null && !employList.isEmpty()) {
			convertObjectToDto(employDtoList, employList);	
		}
		return employDtoList;
	}
	
	@Async
	public String saveBulkEmployees(MultipartFile file) throws CustomException{
		List<Employee> employees=parseFile(file);
		employees = repository.saveAll(employees);
		return "Bulk Employees created successfully";
		
	}

	private List<Employee> parseFile(MultipartFile file) throws CustomException{
		try {		
			  List<Employee> employeeList = new ArrayList<Employee>();
			    XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			    XSSFSheet worksheet = workbook.getSheetAt(0);

			    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
			    	Employee empployee = new Employee();

			        XSSFRow row = worksheet.getRow(i);

			        empployee.setFirstName(row.getCell(0).getStringCellValue());
			        empployee.setLastName(row.getCell(1).getStringCellValue());
			        employeeList.add(empployee); 
			        
			    }
			    return employeeList;		
		}catch (Exception e) {
			throw new CustomException("Error Occured while File Parsing",e);
		}
	}
	@CacheEvict(value="employeeCache",allEntries = true,key = "#id")
	public String updateEmployee(EmployeeDto employeeDto) {
		Employee emp = new Employee();
		emp.setEmpId(employeeDto.getEmpId());
		emp.setFirstName(employeeDto.getFirstName());
		emp.setLastName(employeeDto.getLastName());
		List<Address> addressList = new ArrayList<>();
		employeeDto.getAddress().forEach(addressDto -> {
			Address address = new Address();
			address.setAddressId(addressDto.getAddressId());
			address.setCity(addressDto.getCity());
			address.setPhoneNumber(addressDto.getPhoneNumber());
			address.setPincode(addressDto.getPincode());
			addressList.add(address);
		});
		emp.setAddress(addressList);
		repository.saveAndFlush(emp);
		return "Successfully Updated";
	}

}
