package com.dongbu.potal.web.potal.application.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.potal.config.AppConfig;
import com.skan.potal.config.PersistenceJPAConfig;
import com.skan.potal.config.WebMvcConfig;
import com.skan.potal.web.potal.application.controller.ScheduleController;
import com.skan.potal.web.potal.application.dao.CmtbGroupRepository;
import com.skan.potal.web.potal.application.dao.CmtbScheduleRepository;
import com.skan.potal.web.potal.application.dao.CmtbUserRepository;
import com.skan.potal.web.potal.application.dao.ScheduleRepository;

//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = {WebMvcConfig.class,AppConfig.class, PersistenceJPAConfig.class }
//					)
public class CmtbScheduleRepositoryTest {
	
	@Autowired CmtbUserRepository cmtbUserRepository;
	@Autowired CmtbScheduleRepository cmtbScheduleRepository;
	@Autowired CmtbGroupRepository cmtbGroupRepository;
	@PersistenceContext EntityManager em;
	@Autowired SessionFactory sessionFactory;
	
	@Test
	public void schduleListTest() {
		
		
	}
}
