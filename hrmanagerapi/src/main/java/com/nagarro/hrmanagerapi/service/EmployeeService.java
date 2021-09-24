package com.nagarro.hrmanagerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.hrmanagerapi.dao.EmployeeRepository;
import com.nagarro.hrmanagerapi.model.Employee;

/*Service class for Employee*/

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/* Method to get employees list */
	public List<Employee> getAllEmployees() {
		List<Employee> list = (List<Employee>) this.employeeRepository.findAll();
		return list;
	}

	/* Method to get a employee */
	public Employee getEmployee(int empCode) {
		Employee emp = null;
		try {
			emp = this.employeeRepository.findById(empCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	/* Method to add a employee */
	public Employee addEmployee(Employee emp) {
		Employee result = this.employeeRepository.save(emp);
		return result;
	}

	/* Method to update a employee */
	/*public Employee updateEmployee(Employee employee, int empCode) {
		Employee emp = null;
		employee.setEmpCode(empCode);
		emp = this.employeeRepository.save(employee);
		System.out.println("Update Employee: " + emp);
		return emp;
	}*/

	public void updateEmployee(Employee emp) {
		this.employeeRepository.save(emp);
	}
	
	/* Method to delete a employee */
	public void deleteEmployee(int empCode) {
		this.employeeRepository.deleteById(empCode);
	}
}
