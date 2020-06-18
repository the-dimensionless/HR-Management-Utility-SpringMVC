package com.hr.management.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Model Class for Employee Type.
 * @author sumitsingh
 *
 */
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
	
	int empId;
	String empName;
	String empLocation;
	String empEmail;
	LocalDate empDOB;

	public Employee (int id, String name, String location, String email, LocalDate dob) {
		this.empId = id;
		this.empName = name;
		this.empLocation = location;
		this.empEmail = email;
		this.empDOB = dob;
	}
	
	public Employee() {
		
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
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

	public String getEmpDOB() {
		return empDOB.toString();
	}

//	public void setEmpDOB(LocalDate empDOB) {
//		this.empDOB = empDOB;
//	}
	public void setEmpDOB(String empDOB) {
		this.empDOB = LocalDate.parse(empDOB,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empLocation=" + empLocation + ", empEmail="
				+ empEmail + ", empDOB=" + empDOB + "]";
	}
	
	
	
}
