package com.hr.management.hibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Class used to provide a Hibernate<p>
 * SessionFactory instance as a static field.
 * @author sumitsingh
 *
 */
public class HibernateUtility {

	public static SessionFactory sessionFactoryInstance =  new Configuration()
			.configure().buildSessionFactory(); 
}