/**
 * <pre>
 * Class Name  : HibernateSessionFactory.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 9.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 1. 9.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.common.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ahn
 *
 */
@Component
public class HibernateSessionFactory {
	
	@Autowired SessionFactory sessionFactory;
	private Session session;
	
	@PostConstruct
	private void init() {
		session = sessionFactory.openSession();
	}
	
	public Session getSession () {
		return this.session;
	}
	
	@PreDestroy
	private void endPoint() {
		session.close();
	}
}
