package com.dongbu.potal.hibernate;

import java.util.List;

import org.hibernate.Hibernate;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.potal.config.AppConfig;
import com.skan.potal.config.PersistenceJPAConfig;
import com.skan.potal.config.WebMvcConfig;
import com.skan.potal.hibernate.application.model.Team;
import com.skan.potal.hibernate.application.model.User;
import com.skan.potal.hibernate.user.dao.TeamDAO;
import com.skan.potal.hibernate.user.dao.UserDAO;
import com.skan.potal.hibernate.user.service.UserService;

/**
 * <pre>
 * Class Name  : UserJPADaoTest.java
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
 * @WebAppConfiguration : 웹 컨피그 레이션을 적용하여 사용할때.
 *  						
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, PersistenceJPAConfig.class }
						, loader = AnnotationConfigContextLoader.class
					)
public class JpaDaoTest {
	
	@Autowired UserService userServiceImpl;
	@Autowired UserDAO userDao;
	@Autowired TeamDAO teamDao;
	
	@Test
//	@Ignore
	public void testInsert() {
		User user = new User();
		Team team = new Team();
		user.setName("aa");
		user.setUsername("kkkkkk");
		team.setTeamId(1L);
		user.setTeam(team);

		//teamDao.save(team);
		userServiceImpl.insertUser(user);
	}
	
	@Test
	@Ignore
	public void testInsertTeam() {
		Team team = new Team();
		team.setName("개발1팀");
		teamDao.save(team);
	}
	
	
	@Test 
	@Ignore
	public void testFind(){
		
		User user = userServiceImpl.findUser(1L);
		System.out.println("\n\n\n\n ======================================");
		System.out.println(user.toString());
		System.out.println(" ======================================\n\n\n\n");
	}
	
	@Test
	public void testFindAll() {
		List<User> userList = userServiceImpl.findAllUsers();
		
		System.out.println("\n\n\n\n ======================================");

		for (User user : userList) {
			Hibernate.initialize(user);
			System.out.println(user.toString() + " : " +  user.getTeam().toString());
		}
		
		System.out.println(" ======================================\n\n\n\n");
	}
}


