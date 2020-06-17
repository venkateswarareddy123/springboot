package com.springboot.jdbctemplate.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.jdbctemplate.model.Employee;
import com.springboot.jdbctemplate.repository.EmployeeRepository;
import com.springboot.jdbctemplate.util.CustomException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	public List<Employee> getAllEmployees() {


		List<Employee> employList = repository.getAllEmployees();
		return employList;
	}

	public String createEmployee(Employee employee) {
		String createEmployee=repository.createEmployee(employee);
		return createEmployee;
	}

	public String updateEmployee(Employee employee) {
		String updateEmployee=repository.updateEmployee(employee);
		return updateEmployee;
	}

	public String deleteEmployee(Integer id) {
		String deleteEmployee=repository.deleteEmployee(id);
		return deleteEmployee;
	}

	public String saveBulkEmployees(MultipartFile file)throws CustomException {
		List<Employee> employees=parseFile(file);
		repository.insertMultipleRecords(employees);
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
			        empployee.setEmpId(new Double(row.getCell(0).getNumericCellValue()).intValue());
			        empployee.setFirstName(row.getCell(1).getStringCellValue());
			        empployee.setLastName(row.getCell(2).getStringCellValue());
			        employeeList.add(empployee); 
			        
			    }
			    return employeeList;		
		}catch (Exception e) {
			throw new CustomException("Error Occured while File Parsing",e);
		}
	}


}
