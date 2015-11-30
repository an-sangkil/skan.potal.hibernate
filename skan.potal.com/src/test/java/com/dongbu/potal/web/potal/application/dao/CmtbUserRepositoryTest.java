package com.dongbu.potal.web.potal.application.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
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
import com.skan.potal.web.potal.application.model.CmtbGroup;
import com.skan.potal.web.potal.application.model.CmtbSchedule;
import com.skan.potal.web.potal.application.model.CmtbSchedulePK;
import com.skan.potal.web.potal.application.model.CmtbUser;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfig.class,AppConfig.class, PersistenceJPAConfig.class }
					)
public class CmtbUserRepositoryTest  {
	
	@Autowired CmtbUserRepository cmtbUserRepository;
	@Autowired CmtbScheduleRepository cmtbScheduleRepository;
	@Autowired CmtbGroupRepository cmtbGroupRepository;
	@PersistenceContext EntityManager em;
	
	@Test
	public void userSaveTest(){
		CmtbUser cmtbUser = new CmtbUser();
		cmtbUser.setUserId("testUser2");
		cmtbUser.setUserName("userNameTest1");
		cmtbUserRepository.save(cmtbUser);
		//cmtbScheduleRepository.save(cmtbSchedule);
		
		CmtbGroup cmtbGroup = new CmtbGroup();
		cmtbGroup.setGroupNo(1L);
		cmtbGroup.setGroupCrtTime(new Date());
		cmtbGroup.setGroupDesc("테스트 그룹 설명");
		cmtbGroup.setGroupName("테스트 그룹 명");
		cmtbGroupRepository.save(cmtbGroup);
		Assert.assertEquals(1, cmtbGroupRepository.count());
		
		CmtbSchedule cmtbSchedule = new CmtbSchedule();
		CmtbSchedulePK cmtbSchedulePK =	new CmtbSchedulePK();
		cmtbSchedulePK.setSchMgtNo(1L);
		cmtbSchedulePK.setSchSeq(1L);
		cmtbSchedule.setCmtbSchedulePK(cmtbSchedulePK);
		cmtbSchedule.setSchSubject("제목");
		cmtbSchedule.setSchContent("내용");
		cmtbSchedule.setUserId(cmtbUser.getUserId());
		cmtbSchedule.setGroupNo(cmtbGroup.getGroupNo());
		cmtbScheduleRepository.save(cmtbSchedule);
		
	}
	
	public void criteriaTest(Session session) {
		Criteria criteria = session.createCriteria(CmtbUser.class);
		criteria.add(Restrictions.ge("userId", "testUser"));
	}
}
