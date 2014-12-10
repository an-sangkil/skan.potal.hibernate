package com.dongbu.potal.hibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongbu.potal.hibernate.model.User;

/**
 * <pre>
 * Class Name  : UserDao.java
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
public interface UserDAO extends JpaRepository<User, Long> {
	 
}
