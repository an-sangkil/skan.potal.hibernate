package com.skan.potal.web.potal.accountbook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.skan.potal.config.AppConfig;
import com.skan.potal.config.PersistenceJPAConfig;
import com.skan.potal.config.WebMvcConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
/**
 * @author ahn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebMvcConfig.class, AppConfig.class, PersistenceJPAConfig.class })
public class DomesticAccountBookControllerTest {
	
	@Autowired private WebApplicationContext wac;
	
	@PersistenceContext EntityManager entityManager;
	private MockMvc mockMvc;
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
	}
	
	@Test
	public void domesticAccountBookList() throws Exception {
		mockMvc.perform(post("/domestic_account_book/list"));
	}
	
	@Test
	public void domesticAccountBookInsert() throws Exception {
		mockMvc.perform(post("/domestic_account_book/insert")
				//.param("dabMngNo", "String")
				.param("typeCode", "00001")
				.param("detailTypeCode", "00002")
				.param("businessDay", "2016-01-30")
				.param("amount", "20000000")
				.param("breakdown", "특정 이익금")
				.param("detailContents", "사이트 정량 비용")
				);
	}
	
	@Test
	public void domesticAccountBookDelete() throws Exception {
		mockMvc.perform(post("/domestic_account_book/delete")
				.param("dabMngNo", "cd55bf33-2402-452c-81b1-52e92cf29507")
				);
	}
	
	
}
