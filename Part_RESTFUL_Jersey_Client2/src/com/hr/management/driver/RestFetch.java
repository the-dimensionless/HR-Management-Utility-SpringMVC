package com.hr.management.driver;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hr.management.model.Employee;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.hr.management.urls.DataRepository;

import java.io.IOException;

/**
 * RestFetch class is used to make<p>
 * REST API Calls to Part_RESTFUL_Jersey<p>
 * application.
 * Contains methods for retrieval, updation,<p>
 * insertion and removal of Employee data.
 * @author sumitsingh
 *
 */
public class RestFetch {

	/**
	 * Retrieves the details of All Employees
	 * @return Employee[] : array of employee type
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Employee[] fetchAllEmployees() throws JsonParseException, JsonMappingException, IOException {
		final String uri = DataRepository.allEmployeesData;
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    Employee[] users = new ObjectMapper().readValue(result, Employee[].class);
	    return users;
	}
	
	/**
	 * Retrieves details of Employee by Employee Id
	 * @param id : Type Integer
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static Employee fetchEmployeeById(int id) throws JsonMappingException, JsonProcessingException {
		final String uri = DataRepository.oneEmployeeData + id;
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);	    
	    return new ObjectMapper().readValue(result, Employee.class);
	}
	
	/**
	 * Updates the details of Employee instance.
	 * @param object
	 * @throws JsonProcessingException
	 */
	public static void updateEmployeeDetails(Employee object) throws JsonProcessingException {
		//final String uri = "http://localhost:8983/Part_RESTFUL_Jersey/rest/employees/";
		final String uri = DataRepository.allEmployeesData;
		ObjectMapper m = new ObjectMapper();
		String json =  m.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		
		Client client = Client.create();
		WebResource webResource = client.resource(uri);
		ClientResponse response = webResource
		    .type("application/json")
		    .put(ClientResponse.class, json);
		response.close();
	}

	/**
	 * Deletes the Employee record based on<p>
	 * the id.
	 * @param id : Type Integer
	 */
	public static void deleteEmployee(int id) {
		final String uri = DataRepository.deleteEmployeeData + id;
		Client client = Client.create();
		WebResource webResource = client.resource(uri);
		webResource.delete();
	}

	/**
	 * Inserts a record into the Employee database.
	 * @param object
	 * @throws JsonProcessingException
	 */
	public static void addEmployee(Employee object) throws JsonProcessingException {
		final String uri = DataRepository.addEmployeeData;
		
		ObjectMapper m = new ObjectMapper();
		String json =  m.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		
		Client client = Client.create();
		WebResource webResource = client.resource(uri);
		ClientResponse response = webResource
		    .type("application/json")
		    .post(ClientResponse.class, json);
		response.close();
	}

	/**
	 * Retreives the details of all Employees<p>
	 * from the database as in json form.
	 * @return String
	 * @throws IOException
	 */
	public static String fetchAllEmployeesFile() throws IOException {
		//final String uri = "http://localhost:8983/Part_RESTFUL_Jersey/rest/employees";
		final String uri = DataRepository.allEmployeesData;
	    RestTemplate restTemplate = new RestTemplate();
	    return restTemplate.getForObject(uri, String.class);
	}

}
