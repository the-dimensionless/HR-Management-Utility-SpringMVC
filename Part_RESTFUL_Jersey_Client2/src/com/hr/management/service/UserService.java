package com.hr.management.service;

import java.util.List;

import com.hr.management.model.User;

/**
 * Provides a Service layer<p>
 * for dependecy Injection.
 * @author sumitsingh
 *
 */
public interface UserService {
	
	/**
	 * Saves the current User instance into the database.
	 * @param user
	 */
	 void save(User user);
	 
	 /**
	  * Retrieves all the User data as a list.
	  * @return List<User> 
	  */
	 List<User> list();
	 
	 /**
	  * Retrieves password from the<p>
	  * database for the given username.
	  * @param username : String
	  * @return String
	  */
	 String getPass(String username);
}
