package com.skan.tms.mobile.common.mail;

import java.util.List;

import javax.mail.Message;

import org.springframework.stereotype.Component;

@Component
public interface BaseMailSender {
	
	/**
	 * 메일 발송
	 * @param from
	 * @param to
	 * @param subject
	 * @param text
	 */
	public void send(String from, String to, String subject, String text);
	
	public void send(String from, String[] to , String subject , String textHTML);
	
	public void send(String from, String[] to , String[] cc , String[] bcc ,String subject , String textHTML);

	void send(List<Message> messages);
}
