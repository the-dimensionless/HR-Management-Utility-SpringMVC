package com.HU;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {

	public static SessionFactory sessionFactoryInstance =  new Configuration()
			.configure().buildSessionFactory(); 
}
