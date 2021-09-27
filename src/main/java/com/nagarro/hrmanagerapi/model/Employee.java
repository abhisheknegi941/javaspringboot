package com.nagarro.hrmanagerapi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/*Creating Employee class */

@Entity
public class Employee {

	/*Properties of the Employee class*/
	@Id
	@Column(unique = true)
	private int empCode;
	private String empName;
	private String empLocation;
	private String empEmail;
	private String empDob;

	public Employee() {
		super();
	}

	public Employee(int empCode, String empName, String empLocation, String empEmail, String empDob) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.empLocation = empLocation;
		this.empEmail = empEmail;
		this.empDob = empDob;
	}

	public int getEmpCode() {
		return empCode;
	}

	public void setEmpCode(int empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpLocation() {
		return empLocation;
	}

	public void setEmpLocation(String empLocation) {
		this.empLocation = empLocation;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpDob() {
		return empDob;
	}

	public void setEmpDob(String empDob) {
		this.empDob = empDob;
	}
}
