package com.skan.potal.web.potal.application.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.config.Application;
import com.skan.config.DataSourceJpaConfig;
import com.skan.config.DataSourceMybatisConfig;
import com.skan.potal.web.potal.application.dao.CmtbGroupRepository;
import com.skan.potal.web.potal.application.dao.CmtbUserRepository;
import com.skan.potal.web.potal.schedule.model.CmtbSchedule;
import com.skan.potal.web.potal.schedule.model.CmtbSchedulePK;
import com.skan.potal.web.potal.schedule.repository.CmtbScheduleRepository;

@RunWith(SpringRunner.class)
@Rollback
@SpringBootTest(classes = { Application.class, DataSourceJpaConfig.class,
		DataSourceMybatisConfig.class })
@WebAppConfiguration
@AutoConfigureMockMvc
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
