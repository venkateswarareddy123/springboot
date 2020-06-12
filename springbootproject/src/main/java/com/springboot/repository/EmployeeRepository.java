package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.domain.Employee;

@Transactional
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value= "SELECT emp FROM Employee emp join emp.address a WHERE a.pincode =:pincode")
    public List<Employee> findEmployeeByPincode(@Param("pincode") Integer pincode);
	
	@Query(value= "SELECT emp FROM Employee emp join emp.address a WHERE a.city =:city")
    public List<Employee> findEmployeeByCity(@Param("city") String city);

	@Modifying
	@Query(value= "DELETE Employee emp WHERE emp.empId =:id")
	public void deleteEmployee(@Param("id") Integer id);
	

}
