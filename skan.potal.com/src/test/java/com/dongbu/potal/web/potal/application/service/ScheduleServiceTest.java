package com.dongbu.potal.web.potal.application.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.tms.mobile.config.ApplicationTMSMobile;
import com.skan.tms.mobile.config.DataSourceJpaConfig;
import com.skan.tms.mobile.config.DataSourceMybatisConfig;

@RunWith(SpringRunner.class)
@Rollback
@SpringBootTest(classes = { ApplicationTMSMobile.class, DataSourceJpaConfig.class,
		DataSourceMybatisConfig.class })
@WebAppConfiguration
@AutoConfigureMockMvc
public class ScheduleServiceTest {
	
	@Test
	public void schduleListTest() {
		//scheduleRepository.save(cmtbSchedule);
	}
}
