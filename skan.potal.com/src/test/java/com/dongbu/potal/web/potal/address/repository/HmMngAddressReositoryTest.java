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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skan.potal.config.AppConfig;
import com.skan.potal.config.PersistenceJPAConfig;
import com.skan.potal.config.WebMvcConfig;
import com.skan.potal.web.potal.address.model.HmEmailInfo;
import com.skan.potal.web.potal.address.model.HmEmailInfoId;
import com.skan.potal.web.potal.address.model.HmMngAddress;
import com.skan.potal.web.potal.address.repository.HmEmailInfoRepository;
import com.skan.potal.web.potal.address.repository.HmMngAddressRepository;

/**
 * @author ahn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfig.class,AppConfig.class, PersistenceJPAConfig.class }
					)
public class HmMngAddressReositoryTest {

	@Autowired private HmMngAddressRepository hmMngAddressRepository;
	@Autowired private HmEmailInfoRepository hmEmailInfoRepository;
	
	@Test
	public void saveTest() {
		HmMngAddress hmMngAddress = new HmMngAddress();
		HmEmailInfo hmEmailInfo = new HmEmailInfo();
		
		hmMngAddress.setHmMemo("메모 등록");
		hmMngAddress.setName("홍길동");
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
