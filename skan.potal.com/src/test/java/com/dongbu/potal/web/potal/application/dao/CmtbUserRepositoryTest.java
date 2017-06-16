package com.dongbu.potal.web.potal.application.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.potal.web.potal.application.dao.CmtbGroupRepository;
import com.skan.potal.web.potal.application.dao.CmtbUserRepository;
import com.skan.potal.web.potal.application.model.CmtbGroup;
import com.skan.potal.web.potal.application.model.CmtbUser;
import com.skan.potal.web.potal.schedule.model.CmtbSchedule;
import com.skan.potal.web.potal.schedule.model.CmtbSchedulePK;
import com.skan.potal.web.potal.schedule.repository.CmtbScheduleRepository;
import com.skan.tms.mobile.config.ApplicationTMSMobile;
import com.skan.tms.mobile.config.DataSourceJpaConfig;
import com.skan.tms.mobile.config.DataSourceMybatisConfig;


@RunWith(SpringRunner.class)
@Rollback
@SpringBootTest(classes = { ApplicationTMSMobile.class, DataSourceJpaConfig.class,
		DataSourceMybatisConfig.class })
@WebAppConfiguration
@AutoConfigureMockMvc
public class CmtbUserRepositoryTest  {
	
	@Autowired CmtbUserRepository cmtbUserRepository;
	@Autowired CmtbScheduleRepository cmtbScheduleRepository;
	@Autowired CmtbGroupRepository cmtbGroupRepository;
	@PersistenceContext EntityManager em;
	@Autowired SessionFactory sessionFactory;
	
	@PostConstruct
	public void aaPost(){
		
	}
	
	@Test
//	@Ignore
	public void auserSaveTest(){
		CmtbUser cmtbUser = new CmtbUser();
		cmtbUser.setUserId("testUser2");
		cmtbUser.setUserName("userNameTest2");
		cmtbUserRepository.save(cmtbUser);
		
		CmtbGroup cmtbGroup = new CmtbGroup();
		cmtbGroup.setGroupNo(1L);
		cmtbGroup.setGroupCrtTime(new Date());
		cmtbGroup.setGroupDesc("Group Description");
		cmtbGroup.setGroupName("Group Name");
		cmtbGroupRepository.save(cmtbGroup);
		Assert.assertEquals(1, cmtbGroupRepository.count());
		
		CmtbSchedule cmtbSchedule = new CmtbSchedule();
		CmtbSchedulePK cmtbSchedulePK =	new CmtbSchedulePK();
		cmtbSchedulePK.setSchMgtNo(1L);
		cmtbSchedulePK.setSchSeq(1L);
		cmtbSchedule.setCmtbSchedulePK(cmtbSchedulePK);
		cmtbSchedule.setSchSubject("제목");
		cmtbSchedule.setSchContent("내용");
		cmtbSchedule.setStdDate("2015-12-31");
		cmtbSchedule.setEndDate("2015-12-31");
		cmtbSchedule.setUserId(cmtbUser.getUserId());
		cmtbSchedule.setGroupNo(cmtbGroup.getGroupNo());
		cmtbScheduleRepository.save(cmtbSchedule);
		
		List<CmtbUser> cmtbUserList =cmtbUserRepository.findAll();
		cmtbUserList.forEach(user -> System.out.println(user.getUserName()));
		
	}
	@Test
//	@Ignore
	public void bcriteriaTest() {
		Session session = sessionFactory.openSession();
		// Session session = (Session) em.getDelegate();
		// Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CmtbUser.class);
		List<CmtbUser> cats =criteria.add(Restrictions.like("userName", "user" , MatchMode.ANYWHERE)).list();
		System.out.println(cats.size());
		cats.forEach(
				user -> {
							System.out.println("aaa : "+user.toString());
							System.out.println("aaa : "+user.getCmtbGroupMember());
				
						}
				);
		//session.beginTransaction();
	}
}
