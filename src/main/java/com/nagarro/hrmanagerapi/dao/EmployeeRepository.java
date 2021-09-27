package com.nagarro.hrmanagerapi.dao;

import org.springframework.data.repository.CrudRepository;

import com.nagarro.hrmanagerapi.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
   
	public Employee findById(int empCode);
}
