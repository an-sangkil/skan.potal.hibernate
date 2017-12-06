package com.skan.potal.common.mail;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;

/**
 * 
 * <pre>
 * Class Name  : MailSender.java
 * Description : 메일 발송시 첨부파일을 보내고자 할경우
 *
 * @author skan
 * @since 2016. 8. 22.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Component
public class MimeMailSender {
	
	static {
		// EMail 정보에 대한 환경설정 값을 DB로 부터 읽어온다.
		@SuppressWarnings("unused")
		ConcurrentHashMap<String, String> mailConfigration = null;
	}
	
	
	//데이터 베이스 정보를 읽어오고 싶다면, Autowired 제거 후 Configration 에서 설정하여 사용. 
	@Autowired
	@Qualifier(value="mailSender")
	private JavaMailSender mailSender;
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @param text
	 * @param address
	 * @throws AddressException
	 * @throws MessagingException
	 */
	@Subscribe
	public void send(String from, String to, String subject, String text , List<InternetAddress> address) throws AddressException, MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		mimeMessage.setFrom(new InternetAddress(from));
		mimeMessage.setSubject(subject, "utf-8");
		mimeMessage.setText(text, "utf-8");
		mimeMessage.setRecipients(RecipientType.TO, address.toArray(new InternetAddress[address.size()]));
		mailSender.send(mimeMessage);
	}
	
	public void fileSend(String from, String to, String text , List<InternetAddress> address) throws AddressException, MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		mimeMessage.setFrom(new InternetAddress(from));
		mimeMessage.setText(text, "utf-8");
		mimeMessage.setRecipients(RecipientType.TO, address.toArray(new InternetAddress[address.size()]));
		
		mailSender.send(mimeMessage);
	}
	
	
	/**
	 * 메일 내용에 이미지나 파일을 삽입 할때
	 * @param from
	 * @param tos
	 */
	public void sendAttachment(String from, String tos[], File file){
		try {
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			// 첨부파일 사용시
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8"); 
			helper.setText("Check out this image!");

			// let's attach the infamous windows Sample file (this time copied to c:/)
			FileSystemResource fileSR = new FileSystemResource(file);
			helper.addAttachment(file.getName(), fileSR);
			mailSender.send(mimeMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 메일 내용에 특정위치 라인에 이미지나 파일을 삽입 할때
	 * 
	 * @param from
	 * @param tos
	 * @param file
	 */
	public void sendInLineAttachment(String from, String tos[], File file){
		try {
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(tos);

			// use the true flag to indicate the text included is HTML
			helper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);

			// let's include the infamous windows Sample file (this time copied to c:/)
			FileSystemResource res = new FileSystemResource(new File("c:/Sample.jpg"));
			helper.addInline("identifier1234", res);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
