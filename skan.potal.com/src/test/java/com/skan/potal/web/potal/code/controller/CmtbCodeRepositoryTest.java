package com.skan.potal.web.potal.code.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.potal.web.potal.application.dao.CmtbCodeRepository;
import com.skan.potal.web.potal.application.model.CmtbCode;
import com.skan.tms.mobile.config.ApplicationTMSMobile;
import com.skan.tms.mobile.config.DataSourceJpaConfig;
import com.skan.tms.mobile.config.DataSourceMybatisConfig;

/**
 * 
 * @author skan
 *
 */
@RunWith(SpringRunner.class)
@Rollback
@SpringBootTest(classes = { ApplicationTMSMobile.class, DataSourceJpaConfig.class,
		DataSourceMybatisConfig.class })
@WebAppConfiguration
@AutoConfigureMockMvc
public class CmtbCodeRepositoryTest {
	
	@Autowired private CmtbCodeRepository cmtbCodeRepository;
	
	@Test
	public void findAllTest() throws Exception {
		List<CmtbCode> cmtbCode =  cmtbCodeRepository.findAll();
		cmtbCode.forEach(item -> {
			item.getCodeName();	
		});
	}
	
	
	
	
}
