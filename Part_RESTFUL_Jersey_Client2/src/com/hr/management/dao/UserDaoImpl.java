package com.hr.management.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.hr.management.hibernateUtil.HibernateUtility;
import com.hr.management.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory = HibernateUtility.sessionFactoryInstance;
	
	
	@Override
	   public void save(User user) {
	      sessionFactory.getCurrentSession().save(user);
	   }
	 
	   @SuppressWarnings("unchecked")
	@Override
	   public List<User> list() {
	      return sessionFactory.getCurrentSession().createQuery("from User").list();
	   }

	@SuppressWarnings("unchecked")
	@Override
	public String getPassword(String username) {
		List<String> pass = null;
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		pass = sessionFactory.openSession().createQuery("select u.userPassword from User u where"
				+ " u.userName = :i").setParameter("i", username).getResultList();
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println("value is "+pass.get(0));
		
		return pass.get(0);
	}

}
