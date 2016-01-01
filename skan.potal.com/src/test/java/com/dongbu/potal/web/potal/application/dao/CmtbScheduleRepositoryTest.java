package com.dongbu.potal.web.potal.application.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.CoreMatchers.*;
import org.hibernate.SessionFactory;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.potal.config.AppConfig;
import com.skan.potal.config.PersistenceJPAConfig;
import com.skan.potal.config.WebMvcConfig;
import com.skan.potal.web.potal.application.dao.CmtbGroupRepository;
import com.skan.potal.web.potal.application.dao.CmtbScheduleRepository;
import com.skan.potal.web.potal.application.dao.CmtbUserRepository;
import com.skan.potal.web.potal.application.model.CmtbSchedule;
import com.skan.potal.web.potal.application.model.CmtbSchedulePK;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfig.class,AppConfig.class, PersistenceJPAConfig.class }
					)
public class CmtbScheduleRepositoryTest {
	
	@Autowired CmtbUserRepository cmtbUserRepository;
	@Autowired CmtbScheduleRepository cmtbScheduleRepository;
	@Autowired CmtbGroupRepository cmtbGroupRepository;
	@PersistenceContext EntityManager em;
	@Autowired SessionFactory sessionFactory;
	
	@Test
	@Ignore
	public void schduleListTest() {
		cmtbScheduleRepository.findAll();
	}
	@Test
	public void findByCmtbScheduleAndUserIdTest() {
		try {
			List<CmtbSchedule> cmtbSchedules = cmtbScheduleRepository.findByUserId("testUser2");
			assertNotNull(cmtbSchedules);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void schduleFindOneTest() {
		CmtbSchedulePK cmtbSchedulePK = new CmtbSchedulePK();
		cmtbSchedulePK.setSchMgtNo(1L);
		cmtbSchedulePK.setSchSeq(1L);
		CmtbSchedule cmtbSchedule = cmtbScheduleRepository.findOne(cmtbSchedulePK);
		assertNotNull(cmtbSchedule);
		assertEquals(cmtbSchedule.getSchSubject(), "제목");
	}
}
