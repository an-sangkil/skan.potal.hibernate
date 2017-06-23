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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.skan.tms.mobile.config.ApplicationTMSMobile;
import com.skan.tms.mobile.config.DataSourceJpaConfig;
import com.skan.tms.mobile.config.DataSourceMybatisConfig;

/**
 * @author ahn
 *
 */
@RunWith(SpringRunner.class)
@Rollback
@SpringBootTest(classes = { ApplicationTMSMobile.class, DataSourceJpaConfig.class,
		DataSourceMybatisConfig.class })
@WebAppConfiguration
@AutoConfigureMockMvc
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
	@Ignore
	public void addressPageList() throws Exception {
		this.mockMvc.perform(post("/address/address_list").accept(MediaType.TEXT_HTML_VALUE)
						.contentType(MediaType.TEXT_HTML_VALUE)
						.param("page", "1")
						.param("size", "10")
						.param("direction", "DESC"))
				.andExpect(status().isOk())
				//.andExpect(content().contentType(MediaType.TEXT_HTML_VALUE))
				.andExpect(model().attributeExists("hmMngAddressPage"));

	}

	@Test
	public void addressInsertTest() throws Exception {
		this.mockMvc.perform(post("/address/address_insert").accept(MediaType.TEXT_HTML_VALUE).param("name", "ansangkil"));
	}
	
	
}
