package com.hr.management.dao;

import java.util.List;

import com.hr.management.model.User;

/**
 * Handles actual database operations.
 * @author sumitsingh
 *
 */
public interface UserDao {
	
	/**
	 * Saves the user instance into the database
	 * @param user
	 */
	void save(User user);
	
	/**
	 * Retrieves a list of User from the database
	 * @return List<User>
	 */
	List<User> list();
	
	/**
	 * Retrieves the password of the<p>
	 * current user
	 * @param username : Type String
	 * @return password : Type String
	 */
	String getPassword(String username);

}
