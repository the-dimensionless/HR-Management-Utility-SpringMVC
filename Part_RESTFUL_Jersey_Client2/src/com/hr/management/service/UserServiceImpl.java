package com.hr.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.management.dao.UserDao;
import com.hr.management.model.User;

@Service
public class UserServiceImpl implements  UserService {
	
	@Autowired
    private UserDao userDao;

	@Transactional
   public void save(User user) {
      userDao.save(user);
   }
 
   @Transactional(readOnly = true)
   public List<User> list() {
      return userDao.list();
   }
   
   @Transactional
   public String getPass(String username) {
	   return userDao.getPassword(username);
   }

}
