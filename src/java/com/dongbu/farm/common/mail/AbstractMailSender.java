/*
 * $Id: AbstractMailSender.java ,v 1.1 2010. 9. 8. �ㅼ쟾 9:50:23 smrscvs1 Exp $
 * created by    : ahn
 * creation-date : 2010. 9. 8.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.mail;

import javax.mail.MessagingException;


import org.springframework.mail.javamail.JavaMailSender;

public abstract class AbstractMailSender {
	
	protected JavaMailSender sender;
	
	public void setSender(JavaMailSender sender){
		this.sender = sender;
	}
	
	/**
	 * 
	 * @param to	   : 보낸이
	 * @param from	   : 받는이
	 * @param subject  : 제목
	 * @param text	   : 내용
	 * @throws MessagingException
	 */
	public abstract void sendMail(String to, String from, String subject, String text) throws MessagingException;
	
}
