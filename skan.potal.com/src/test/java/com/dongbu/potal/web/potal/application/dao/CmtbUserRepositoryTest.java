package com.dongbu.potal.web.potal.application.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.potal.config.AppConfig;
import com.skan.potal.config.PersistenceJPAConfig;
import com.skan.potal.config.WebMvcConfig;
import com.skan.potal.web.potal.application.dao.CmtbScheduleRepository;
import com.skan.potal.web.potal.application.dao.CmtbUserRepository;
import com.skan.potal.web.potal.application.model.CmtbSchedule;
import com.skan.potal.web.potal.application.model.CmtbUser;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfig.class,AppConfig.class, PersistenceJPAConfig.class }
					)
public class CmtbUserRepositoryTest  {
	
	@Autowired CmtbUserRepository cmtbUserRepository;
	@Autowired CmtbScheduleRepository cmtbScheduleRepository;
	
	@Test
	public void userSaveTest(){
		CmtbUser cmtbUser = new CmtbUser();
		cmtbUser.setUserId("testUser2");
		cmtbUser.setUserName("userNameTest1");
		
		CmtbSchedule cmtbSchedule = new CmtbSchedule();
		cmtbSchedule.setSchMgtNo(1L);
		cmtbSchedule.setSchSeq(1L);
		cmtbSchedule.setSchSubject("제목");
		cmtbSchedule.setSchContent("내용");
		
		cmtbUserRepository.save(cmtbUser);
		//cmtbScheduleRepository.save(cmtbSchedule);
	}
}
