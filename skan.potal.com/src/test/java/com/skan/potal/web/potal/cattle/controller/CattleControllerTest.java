/**
 * <pre>
 * Class Name  : CattleController.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 11.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 1. 11.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.cattle.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.mysema.query.jpa.impl.JPAQuery;
import com.skan.potal.config.AppConfig;
import com.skan.potal.config.PersistenceJPAConfig;
import com.skan.potal.config.WebMvcConfig;
import com.skan.potal.web.potal.cattle.dto.QHmCattleCalfRecode;

/**
 * @author ahn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebMvcConfig.class, AppConfig.class, PersistenceJPAConfig.class })
public class CattleControllerTest {
	
	
	@Autowired private WebApplicationContext wac;
	
	@PersistenceContext EntityManager entityManager;
	private MockMvc mockMvc;
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
	}
	
	@Test
	@Ignore
	public void queryDslMaxTest() {
		QHmCattleCalfRecode qhmCattleCalfRecode = QHmCattleCalfRecode.hmCattleCalfRecode;
		JPAQuery query = new JPAQuery(entityManager);
		Long thno = query.from(qhmCattleCalfRecode)
				.where(QHmCattleCalfRecode.hmCattleCalfRecode.hmCattleCalfRecodeId.thNo.eq(1L))
				.singleResult(QHmCattleCalfRecode.hmCattleCalfRecode.hmCattleCalfRecodeId.thNo.max());
		System.out.println(thno);
		
	}
	
	@Test
	@Ignore
	public void cattleNativeQueryTest() {
		String testCount = (String) entityManager.createNativeQuery("select COALESCE(max(entitydiscernno), '1') from hmCattleRegister"
						+ " where entitydiscernno = '123'").getSingleResult();
		Assert.assertEquals(testCount, "1");
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void cattleSaveTest() throws Exception {
		mockMvc.perform(post("/cattle/cattle_form").param("entityDiscernNo", "2015-0118-001"));
	}	
}
