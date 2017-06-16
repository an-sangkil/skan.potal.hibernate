/**
 * <pre>
 * Class Name  : HmMngAddress.java
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
package com.dongbu.potal.web.potal.address.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.potal.web.potal.address.model.HmEmailInfo;
import com.skan.potal.web.potal.address.model.HmEmailInfoId;
import com.skan.potal.web.potal.address.model.HmMngAddress;
import com.skan.potal.web.potal.address.repository.HmEmailInfoRepository;
import com.skan.potal.web.potal.address.repository.HmMngAddressRepository;
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
public class HmMngAddressReositoryTest {

	@Autowired private HmMngAddressRepository hmMngAddressRepository;
	@Autowired private HmEmailInfoRepository hmEmailInfoRepository;
	
	@Test
	@Ignore
	public void saveTest() {
		
		for (int i = 0; i < 800; i++) {
			HmMngAddress hmMngAddress = new HmMngAddress();
			HmEmailInfo hmEmailInfo = new HmEmailInfo();
			
			hmMngAddress.setHmMemo("메모 등록");
			hmMngAddress.setName("김연아"+i);
			hmMngAddress.setHomepageUrl("http://mycup.tistory.com");
			hmMngAddressRepository.save(hmMngAddress);
			
			HmEmailInfoId hmEmailInfoId = new HmEmailInfoId();
			hmEmailInfoId.setHmMngAddress(hmMngAddress);
			hmEmailInfoId.setHmEmNo(1L);
			hmEmailInfo.setHmEmailInfoId(hmEmailInfoId);
			hmEmailInfo.setHmEmail("mycup@nate.com");
			
			hmEmailInfoRepository.save(hmEmailInfo);
		}
	}
}
