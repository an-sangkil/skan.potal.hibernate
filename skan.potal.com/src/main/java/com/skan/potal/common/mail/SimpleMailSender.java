package com.skan.potal.common.mail;

import java.util.List;

import javax.mail.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;

/**
 * 
 * <pre>
 * Class Name  : MailSender.java
 * Description : 스프링 컨터이너에서 관리하는 메일 센더 사용,  
 *  
 * @author skan
 * @since 2016. 8. 22.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Component
public class SimpleMailSender extends AbstractMailConfig implements BaseMailSender{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Subscribe
	public void send(String from, String to, String subject, String textHTML){
		this.send(from, new String[]{to}, subject, textHTML);
	}

	@Override
	public void send(String from, String[] to, String subject, String textHTML) {
		this.send(from, to, null, null, subject, textHTML);
	}

	@Override
	public void send(String from, String[] to, String[] cc, String[] bcc, String subject, String textHTML) {
		
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(from);
			msg.setTo(to);
			msg.setText(textHTML);
			super.mailSender.send(msg);
			
			logger.debug("Mail send request = {} " , to.toString());
			
		} catch (MailException ex) {
			
			// simply log it and go on...
			logger.error("{} 메일발송에 실패하였습니다.",to.toString());
			logger.error("SEND_MAIL_FAIL = {}" ,ex.getMessage());
		}
	}
	
	/*
	 * 심플메일에서는 대용량 메일 발송 없음, 
	 * (non-Javadoc)
	 * @see com.knkcorp.tms.common.mail.BaseMailSender#send(java.util.List)
	 */
	@Override
	public void send(List<Message> messages) {}

}
