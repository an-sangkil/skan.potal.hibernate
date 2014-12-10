package com.dongbu.potal.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongbu.potal.hibernate.dao.UserDAO;
import com.dongbu.potal.hibernate.model.User;

/**
 * <pre>
 * Class Name  : UserService.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2014. 12. 4.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2014. 12. 4.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;

	public void insertUser(User user) {
		userDAO.save(user);
	}

	public List<User> findAllUsers() {
		return userDAO.findAll();
	}
	
	public User findUser() {
		return userDAO.findOne(1L);
	}
	
}
