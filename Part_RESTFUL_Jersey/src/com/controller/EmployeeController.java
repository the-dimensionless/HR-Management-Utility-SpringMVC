package com.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bean.Employee;
import com.service.EmployeeService;

@Path("/employees")
public class EmployeeController {

	EmployeeService es = new EmployeeService();
	
	@GET
	@Produces ({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Employee> getEmployees() {
		List<Employee> listOfEmployees = es.getALLEmployees();
		for (Employee e : listOfEmployees) {
			System.out.println(e);
		}
		return listOfEmployees;
	}
	
	@GET
	@Path ("/{id}")
	@Produces ({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Employee getEmployeeById(@PathParam("id") int id) {
		return es.getEmployeeById(id);
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces ({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void addEmployee (Employee emp) {
		es.addEmployee(emp);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces ({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Employee updateEmployee (Employee emp) {
		System.out.println(emp.toString());
		return es.updateEmployee (emp);
	}
	
	@DELETE
	@Path("/delete/{id}")
	public void deleteEmployee (@PathParam("id") int id) {
		System.out.println("On deletion");
		es.deleteEmployee(id);
	}
	
}
