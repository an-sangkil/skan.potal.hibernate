package com.dongbu.potal.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.mysema.query.types.Expression;
import com.mysema.query.types.template.SimpleTemplate;
import com.skan.potal.config.AppConfig;
import com.skan.potal.config.PersistenceJPAConfig;
import com.skan.potal.hibernate.application.model.QUser;
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
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired UserService userServiceImpl;
	@Autowired UserDAO userDao;
	@Autowired TeamDAO teamDao;
	
	@Test
	@Ignore
	public void testInsert() {
		User user = new User();
		Team team = new Team();
		user.setName("cc");
		user.setUsername("ijen");
		team.setTeamId(3L);
//		team.setName("개발 1팀");
		user.setTeam(team);

//		teamDao.save(team);
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
	public void testFindAllQueryDsl(){
		
		
		List<User> notfilterUser = (List<User>) userDao.findAll();
		System.out.println(" ======================================");
		logger.debug("*notfilterUser ");
		for (User user : notfilterUser) {
			logger.debug("notfilterUser : = {} " , user.toString());
		}
		
		List<User> filterUser = (List<User>) userDao.findAll(QUser.user.name.like("cc"));
		System.out.println(" ======================================");
		logger.debug("*filterUser ");
		for (User user : filterUser) {
			logger.debug("filterUser : = {} " , user.toString());
		}
		
		// page
		// Expressions.booleanTemplate("function('myfunction', {0}, {1})", arg1, arg2)")
		Expression<String> expressions = SimpleTemplate.create(String.class, "DECIDE({0},{1},{2})", "bb", "11","22");
		Page<User> paging = userDao.findAll(QUser.user.name.eq("aa"), new PageRequest(1, 10, Direction.DESC,"id", "name"));
		System.out.println(" ======================================");
		logger.debug("*pagingUser  ");
		List<User> pagingUserList = paging.getContent();
		for (User user : pagingUserList) {
			logger.debug("pagingUser : = {} " , user.toString());
		}
		logger.info("getNumber ={}" , paging.getNumber());
		logger.info("getNumberOfElements ={}" , paging.getNumberOfElements());
		logger.info("getSize ={}" , paging.getSize());
		logger.info("getSort ={}" , paging.getSort());
		logger.info("getTotalElements ={}" , paging.getTotalElements());
		logger.info("getTotalPages ={}" , paging.getTotalPages());
	}
	
	
	@Test 
	@Ignore
	public void testFind(){
		
		User user = userServiceImpl.findUser(1L);
		System.out.println("\n\n\n\n ======================================");
		System.out.println(user.toString());
	}
	
	@Test
	@Ignore
	public void testFindAll() {
		List<User> userList = userServiceImpl.findAllUsers();
		
		System.out.println("\n\n\n\n ======================================");

		for (User user : userList) {
			System.out.println(user.toString() + " : " +  user.getTeam().toString());
		}
	}
}


