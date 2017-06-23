package com.skan.potal.web.potal.code.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.skan.config.Application;
import com.skan.config.DataSourceJpaConfig;
import com.skan.config.DataSourceMybatisConfig;

/**
 * 
 * @author skan
 *
 */
@RunWith(SpringRunner.class)
@Rollback
@SpringBootTest(classes = { Application.class, DataSourceJpaConfig.class,
		DataSourceMybatisConfig.class })
@WebAppConfiguration
@AutoConfigureMockMvc
public class CodeControllerTest {
	
	@Autowired private WebApplicationContext wac;
	@PersistenceContext EntityManager entityManager;
	private MockMvc mockMvc;

	private String codeMgtNo="ab974a25-1cd5-4a96-be52-c218aeb8d1a2";
	private String upperCode;
	private Long codeSeq;
	private String code = "00001";
	private String codeName = "온도";
	private String codeComment = "온도를 나타냅니다.";
	
	
	// 기본 코드 생성
	// 온도 및 기타 내역 기본코드 생성에 필요함
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void codeListTest() throws Exception {
		mockMvc.perform( post("/code/code_list") )
		.andExpect(MockMvcResultMatchers.status().isOk() )
		.andDo(MockMvcResultHandlers.print());
	}
	
	
	@Test
	@Transactional
	@Rollback(false)
	public void codeSaveTest() throws Exception {
		mockMvc.perform(post("/code/code_insert")
//				.param("codeMgtNo", codeMgtNo)
				.param("codeName", "다다다다온도다다다다")
				.param("codeComment", codeComment));
		
		mockMvc.perform(post("/code/code_insert")
				.param("upperCode", "00001")
				.param("codeName", "영상")
				.param("codeComment", codeComment));
		
		mockMvc.perform(post("/code/code_insert")
				.param("upperCode", "00001")
				.param("codeName", "개체관리코드")
				.param("codeComment", codeComment));
		
		
		
	}
}
