package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.domain.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value= "SELECT emp FROM Employee emp join emp.address a WHERE a.pincode =:pincode")
    public List<Employee> findEmployeeByPincode(@Param("pincode") Integer pincode);
	

}
