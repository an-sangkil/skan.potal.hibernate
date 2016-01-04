/**
 * <pre>
 * Class Name  : AddressController.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 4.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 1. 4.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.dongbu.potal.web.potal.address.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.skan.potal.config.AppConfig;
import com.skan.potal.config.PersistenceJPAConfig;
import com.skan.potal.config.WebMvcConfig;

/**
 * @author ahn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfig.class,AppConfig.class, PersistenceJPAConfig.class }
					)
public class AddressControllerTest {

	@Autowired
	protected WebApplicationContext wac;
	protected MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		// Set up a mock MVC tester based on the web application context
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void addressInsert() throws Exception {
//		this.mockMvc.perform(get("/address/address_list?name=kkk").accept(MediaType.TEXT_PLAIN_VALUE))
//        .andExpect(status().isOk())
//        .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE))
//        .andExpect(model().attribute("returnName", "hahahaha~"));
		
		this.mockMvc.perform(post("/address/address_list").accept(MediaType.TEXT_PLAIN_VALUE).param("name", "testMN") );
	}
}
