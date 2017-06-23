package com.skan.tms.mobile.common.mail;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;

import com.skan.tms.web.jpa.repository.UserRoleJpaRepository;


public abstract class AbstractMailConfig {
	
	/*
	 * application.properties 에 정의한 값을 이용한 메일 발송 Class 
	 */
	@Autowired
	@Qualifier(value="mailSender")
	JavaMailSender mailSender;
	
	private Set<Object> mailConfigs = new HashSet<>();
	
	@Autowired
	UserRoleJpaRepository userRoleJpaRepository;
	
	@PostConstruct
	private void init(){
		//TODO EMAIL 환경 설정 정보 
		userRoleJpaRepository.findAll();
		this.mailConfigs = null; //DB.. link
	}

	public void dataLoad() {
		System.out.println("Spring Container @PostLoad");
		
	}
	
	@PreDestroy
	public void cleanUp() throws Exception {
	  System.out.println("Spring Container is destroy! Customer clean up");
	}
}
