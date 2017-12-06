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

import com.skan.config.Application;
import com.skan.config.DataSourceJpaConfig;
import com.skan.config.DataSourceMybatisConfig;
import com.skan.potal.web.potal.application.dao.CmtbCodeRepository;
import com.skan.potal.web.potal.application.model.CmtbCode;

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
