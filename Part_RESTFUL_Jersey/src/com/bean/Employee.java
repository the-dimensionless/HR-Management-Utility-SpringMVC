package com.bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "RestEmployee")
public class Employee {

	@Id
	@Column (name = "id", unique = true)
	int empId;
	@Column (name = "name")
	String empName;
	@Column (name = "location")
	String empLocation;
	@Column (name = "email")
	String empEmail;
	@Column (name = "dob")
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

	public void setEmpDOB(String empDOB) {
		this.empDOB = LocalDate.parse(empDOB,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
}
