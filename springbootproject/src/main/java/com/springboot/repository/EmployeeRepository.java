package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.domain.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
