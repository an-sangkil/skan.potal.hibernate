package com.skan.potal.web.potal.accountbook;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class DomesticAccountBookControllerTest {
	
	@Autowired private WebApplicationContext wac;
	
	@PersistenceContext EntityManager entityManager;
	private MockMvc mockMvc;
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
	}
	
	@Test
	@Ignore
	public void domesticAccountBookList() throws Exception {
		mockMvc.perform(post("/domestic_account_book/list"));
	}
	
	@Test
	@Ignore
	public void domesticAccountBookInsert() throws Exception {
		
		for(int i=0 ; i < 100; i++) {
			mockMvc.perform(post("/domestic_account_book/insert")
					//.param("dabMngNo", "String")
					.param("typeCode", "00001")
					.param("detailTypeCode", "00002")
					.param("businessDay", "2016-01-30")
					.param("amount", "20000000")
					.param("breakdown", "특정 이익금"+i)
					.param("detailContents", "사이트 정량 비용"+i)
					);
			
		}
	}
	
	@Test
	public void domesticAccouentBookForm(){
		try {
			mockMvc.perform(get("/domestic_account_book/form"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	@Ignore
	public void domesticAccountBookDelete() throws Exception {
		mockMvc.perform(post("/domestic_account_book/delete")
				.param("dabMngNo", "cd55bf33-2402-452c-81b1-52e92cf29507")
				);
	}
	
	
}
