package com.skan.potal.web.potal.application.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.config.Application;
import com.skan.config.DataSourceJpaConfig;
import com.skan.config.DataSourceMybatisConfig;

@RunWith(SpringRunner.class)
@Rollback
@SpringBootTest(classes = { Application.class, DataSourceJpaConfig.class,
		DataSourceMybatisConfig.class })
@WebAppConfiguration
@AutoConfigureMockMvc
public class ScheduleServiceTest {
	
	@Test
	public void schduleListTest() {
		//scheduleRepository.save(cmtbSchedule);
	}
}
