package com.dongbu.potal.web.potal.schedule.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.potal.config.AppConfig;
import com.skan.potal.config.PersistenceJPAConfig;
import com.skan.potal.config.WebMvcConfig;
import com.skan.potal.web.potal.schedule.controller.ScheduleController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfig.class,AppConfig.class, PersistenceJPAConfig.class }
					)
public class ScheduleServiceTest {
	
	@Autowired
	private ScheduleController scheduleController;
	
	@Test
	public void schduleListTest() {
		//TODO Mock Controller Test
		//ModelMap model = new ModelMap();
		//scheduleController.schduleList(schdule, bindingResult,  modelMap);
	}
}
