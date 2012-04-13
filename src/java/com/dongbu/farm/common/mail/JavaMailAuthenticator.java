/*
 * $Id: JavaMailAuthenticator.java ,v 1.1 2010. 9. 8. 오후 2:02:11 smrscvs1 Exp $
 * created by    : ahn
 * creation-date : 2010. 9. 8.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.mail;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 메일인증을 담당하고 있는 javamailauthenticator 클레스를
 * JavaMailSenderImpl 을 상속받아 사용한다.
 * @author ahn
 *
 */
public class JavaMailAuthenticator extends JavaMailSenderImpl{

	public JavaMailAuthenticator() {
		super();
		// TODO Auto-generated constructor stub
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.auth", true);
		this.setJavaMailProperties(props);
	}

}
