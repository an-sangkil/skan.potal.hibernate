/**
 * <pre>
 * Class Name  : CattleRepositoryTest.java
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
package com.skan.potal.web.potal.cattle.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.potal.config.AppConfig;
import com.skan.potal.config.PersistenceJPAConfig;
import com.skan.potal.config.WebMvcConfig;
import com.skan.potal.web.potal.cattle.model.HmCattleRegister;

/**
 * @author ahn
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebMvcConfig.class, AppConfig.class, PersistenceJPAConfig.class })
public class CattleRepositoryTest {
	
	@Autowired CattleRepository cattleRepository;
	
	@Test
	public void selectAllTest() {
		cattleRepository.findAll();
		HmCattleRegister cattleRegister = new HmCattleRegister();
		cattleRegister.setEntityDiscernNo("43-598-0908");
		cattleRepository.save(cattleRegister);
	}
	
	
}
