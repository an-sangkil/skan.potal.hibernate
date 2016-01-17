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

import static org.junit.Assert.assertSame;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mysema.query.jpa.impl.JPAQuery;
import com.skan.potal.config.AppConfig;
import com.skan.potal.config.PersistenceJPAConfig;
import com.skan.potal.config.WebMvcConfig;
import com.skan.potal.web.potal.cattle.model.HmCattleBuyInfo;
import com.skan.potal.web.potal.cattle.model.HmCattleChildbirthRecode;
import com.skan.potal.web.potal.cattle.model.HmCattleChildbirthRecodeId;
import com.skan.potal.web.potal.cattle.model.HmCattleRegister;
import com.skan.potal.web.potal.cattle.model.QHmCattleBuyInfo;
import com.skan.potal.web.potal.cattle.model.QHmCattleRegister;

/**
 * @author ahn
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebMvcConfig.class, AppConfig.class, PersistenceJPAConfig.class })
public class CattleRepositoryTest {
	
	@Autowired CattleRegisterRepository cattleRepository;
	@Autowired CattleBuyInfoRepository cattleBuyInfoRepository;
	@Autowired CattleChildbirthRecodeRepository cattleChildbirthRecodeRepository;
	@Autowired EntityManager em;
	
	
	@Test
	@Ignore
	public void selectJoinQueryDsl() {

		QHmCattleRegister hmCattleRegister = QHmCattleRegister.hmCattleRegister;
		JPAQuery query = new JPAQuery(em);
		query.from(hmCattleRegister)
			.leftJoin(QHmCattleRegister.hmCattleRegister.hmCattleBuyInfoSet, QHmCattleBuyInfo.hmCattleBuyInfo)
			.where(QHmCattleRegister.hmCattleRegister.gender.eq("황소").or(QHmCattleRegister.hmCattleRegister.gender.eq("암소"))
					,QHmCattleBuyInfo.hmCattleBuyInfo.buyStoreName.eq("AAA") 
					);
			//.list(hmCattleRegister)
			//.forEach(item->{System.out.println(item.getEntityDiscernNo());});
		
		Page<HmCattleRegister> hmCattleRegisterPage = cattleRepository.buildPage(query, query, new PageRequest(0, 10));
		for (HmCattleRegister obj : hmCattleRegisterPage.getContent()) {
			System.out.println(obj.getEntityDiscernNo());
		}
//		assertSame(1, hmCattleRegisterPage.getContent().size());
	}
	
	@Test
	@Rollback(value=true)
	public void selectAllTest() {
		HmCattleRegister cattleRegister = new HmCattleRegister();
		HmCattleBuyInfo hmCattleBuyInfo = new HmCattleBuyInfo();
		for (int i = 0; i < 100; i++) {
			cattleRegister.setEntityDiscernNo("43-598-0908-"+i);
			cattleRegister.setGender("황소");
			cattleRepository.save(cattleRegister);
			hmCattleBuyInfo.setEntityDiscernNo(cattleRegister.getEntityDiscernNo());
			hmCattleBuyInfo.setBuyStoreName("AAA");
			cattleBuyInfoRepository.save(hmCattleBuyInfo);
			
			HmCattleChildbirthRecodeId hmCattleChildbirthRecodeId = new HmCattleChildbirthRecodeId();
			hmCattleChildbirthRecodeId.setThNo(1L);
			hmCattleChildbirthRecodeId.setHmCattleRegister(cattleRegister);
			HmCattleChildbirthRecode cattleChildbirthRecode = new HmCattleChildbirthRecode();
			cattleChildbirthRecode.setHmCattleChildbirthRecodeId(hmCattleChildbirthRecodeId);
			cattleChildbirthRecode.setExpectedDateConfinement(new Date());
			cattleChildbirthRecodeRepository.save(cattleChildbirthRecode);
		}
	}
	
	
	@Test
	@Ignore
	public void selectQueryDsl() {
		Page<HmCattleRegister> paging = cattleRepository.findAll(QHmCattleRegister.hmCattleRegister.entityDiscernNo.eq("43-598-0908"), new PageRequest(0, 10, Direction.DESC,"birthDay", "entityDiscernNo"));
		paging.getContent().forEach(item -> System.out.println(item.getEntityDiscernNo()));
		assertSame(1, paging.getContent().size());
	}
	
}
