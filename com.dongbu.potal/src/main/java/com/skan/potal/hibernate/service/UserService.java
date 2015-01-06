package com.skan.potal.hibernate.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.skan.potal.hibernate.model.User;

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

public interface UserService  {
	
	void insertUser(User user);
	List<User> findAllUsers();
	public User findUser(Long pk);
	Page<User> findUser(PageRequest pageRequest);

}
