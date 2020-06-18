package com.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;

import com.HU.HibernateUtility;
import com.bean.Employee;

/**
 * EmployeeService class manages the database transactions <p>
 * per user request.
 * Contains methods to view, insert, update and delete <p>
 * Employee data.
 * @author sumitsingh
 *
 */
public class EmployeeService {
	
	private Session session  = HibernateUtility.sessionFactoryInstance.openSession();

	/**
	 * Returns list of employees from the database table
	 * @return List<Employee>
	 */
	public List<Employee> getALLEmployees() {
		session.beginTransaction();
		List<Employee> list = session.createQuery("FROM Employee", Employee.class).list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	/**
	 * Inserts the given Employee instance into the database
	 * @param emp : Employee instance
	 * @return : Employee instance
	 */
	public Employee addEmployee(Employee emp) {
		session.beginTransaction();
		session.save(emp);
		session.getTransaction().commit();
		session.close();
		return getEmployeeById(emp.getEmpId());
	}
	
	/**
	 * Updates the details of <p>
	 * given Employee instance into the database
	 * @param emp : Employee instance
	 * @return : Employee instance
	 */
	public Employee updateEmployee(Employee emp) {
		session.beginTransaction();
		session.update(emp);
		session.getTransaction().commit();
		session.close();
		return getEmployeeById(emp.getEmpId());
	}

	/**
	 * Returns Employee data from <p>
	 * Empployee ID
	 * @param id : Type Integer
	 * @return Employee instance
	 */
	public Employee getEmployeeById(int id) {
		Employee object = null;
		session.beginTransaction();
		try {
		object = session.createQuery("from Employee emp where emp.id = :i ", Employee.class)
				.setParameter("i", id)
				.getSingleResult();
		} catch (NoResultException nre) {
			System.out.println("Not found");
		}
		session.getTransaction().commit();
		session.close();
		return object;
	}

	/**
	 * Deletes from database table <p>
	 * based on Employee Id
	 * @param id : Type Integer (Employee Id)
	 */
	public void deleteEmployee(int id) {
		Employee emp = getEmployeeById(id);
		session = HibernateUtility.sessionFactoryInstance.openSession();
		session.beginTransaction();
		session.delete(emp);
		session.getTransaction().commit();
		session.close();
	}

}
