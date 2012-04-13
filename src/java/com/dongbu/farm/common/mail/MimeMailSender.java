/*
 * $Id: MimeMailSender.java ,v 1.1 2010. 9. 8. 오후 3:13:54 smrscvs1 Exp $
 * created by    : ahn
 * creation-date : 2010. 9. 8.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;

public class MimeMailSender extends AbstractMailSender {

	@Override
	public void sendMail(String to, String from, String subject, String text)
			throws MessagingException {
		// TODO Auto-generated method stub
		MimeMessage msg = sender.createMimeMessage();
		//True 를 셋팅함으로써 메일 포멧의 다양화를 지원하겠다는 것을 명시적으로 알려준다.
		MimeMessageHelper msgHelper = new MimeMessageHelper(msg,true,"utf-8");
		
		msgHelper.setTo(to);
		msgHelper.setFrom(from);
		msgHelper.setSubject(subject);
		msgHelper.setText(text);
		
		sender.send(msg);
	}

}
