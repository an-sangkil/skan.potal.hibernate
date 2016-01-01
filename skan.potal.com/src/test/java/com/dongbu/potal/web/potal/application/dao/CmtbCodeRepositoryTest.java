package com.dongbu.potal.web.potal.application.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.potal.config.AppConfig;
import com.skan.potal.config.PersistenceJPAConfig;
import com.skan.potal.config.WebMvcConfig;
import com.skan.potal.web.potal.application.dao.CmtbCodeRepository;
import com.skan.potal.web.potal.application.model.CmtbCode;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfig.class,AppConfig.class, PersistenceJPAConfig.class }
					)
public class CmtbCodeRepositoryTest {
	
	@Autowired private CmtbCodeRepository cmtbCodeRepository;
	
	@Test
	public void findAllTest() {
		
		CmtbCode cmtbCode = new CmtbCode();
		
		cmtbCode.setCodeMgtNo("");
		cmtbCode.setUpperCode("");
		cmtbCode.setCode("");
		cmtbCode.setCodeSeq(1L);
		cmtbCodeRepository.save(cmtbCode);

	}
	
	
}
