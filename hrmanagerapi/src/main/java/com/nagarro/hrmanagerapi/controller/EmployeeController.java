package com.nagarro.hrmanagerapi.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.hrmanagerapi.service.EmployeePDFExporter;
import com.nagarro.hrmanagerapi.model.Employee;
import com.nagarro.hrmanagerapi.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/*** Method to get all Employees list ***/
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> list = this.employeeService.getAllEmployees();

		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	/*** Method to get a Employee by Employee code ***/
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int empCode) {
		Employee employee = this.employeeService.getEmployee(empCode);
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.of(Optional.of(employee));
	}

	/*** Method to add an employee ***/
	@PostMapping("/employees")
	public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
		try {
			this.employeeService.addEmployee(employee);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	/*** Method to update an employee using employee code ***/
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") int empCode) {
		try {
		  this.employeeService.updateEmployee(employee);
			return ResponseEntity.ok().body(employee);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/*** Method to delete an employee using employee code ***/
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int empCode) {
		try {
			this.employeeService.deleteEmployee(empCode);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/*** Method to export pdf ***/
	@GetMapping("/employees/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=employees_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		List<Employee> employeeList = employeeService.getAllEmployees();
		EmployeePDFExporter exporter = new EmployeePDFExporter(employeeList);
		exporter.export(response);

	}
}
