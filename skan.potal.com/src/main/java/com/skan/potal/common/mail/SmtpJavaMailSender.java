package com.skan.tms.mobile.common.mail;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.skan.tms.web.jpa.repository.SystemConfigJpaRepository;


/**
 * DB 환경설정의 Default 파일을 읽어와서 메일 발송
 * 프로퍼티 값으로 설정된 내역을 사용하지 않고, DB에 값을 사용하기 때문에 동적인 메일 셋팅이 가능한 클래스이다.
 * 
 * @author skan
 * @since 2016. 8. 22.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Primary
@Component
public class SmtpJavaMailSender implements BaseMailSender{
	
	
	@Autowired SystemConfigJpaRepository systemConfigJpaRepository;
	@Autowired MessageSource messageSource;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String host;
	private String protocol;
	private int port;
	private String username;
	private String password;
	private String sslUnable;
	private Session session;
	
	
	public Session getSession() {
		return session;
	}
	
	/**
	 * 전처리 : DB에서 추출한  mail 기본정보
	 *       프로퍼티 값으로 설정된 내역을 사용하지 않고, DB에 값을 사용하기 때문에 동적인 메일 셋팅이 가능한 클래스이다.
	 * @throws Exception 
	 */
	@PostConstruct
	public void mailConfigration() {
		try {
			// EMail 정보에 대한 환경설정 값을 DB로 부터 읽어온다.
			//List<String> emailKeys = new ArrayList<>();
			//for(SystemConfigrationCode scc : SystemConfigrationCode.MAIL_GROUPS){
			//	emailKeys.add(scc.name());
			//}
			
			//ConcurrentHashMap<String, String> mailConfigration = new ConcurrentHashMap<>();
			//List<SystemConfig> systemConfigs = (List<SystemConfig>) systemConfigJpaRepository.findAll(QSystemConfig.systemConfig.configId.in(emailKeys));
			//systemConfigs.stream().filter(p->p.getConfigId().equals(SystemConfigrationCode.MAIL_SMTP_HOST.name())).findFirst().orElse(new SystemConfig()).getConfigValue();
			//for (SystemConfig systemConfig : systemConfigs) {
			//	mailConfigration.put(systemConfig.getConfigId(), systemConfig.getConfigValue());
			//}
					
			//messageSource.getMessage("spring.mail.protocol", null, Locale.ROOT);
			//messageSource.getMessage("spring.mail.host", null, Locale.ROOT);
			//messageSource.getMessage("spring.mail.port", null, Locale.ROOT);
			//messageSource.getMessage("spring.mail.username", null, Locale.ROOT);
			//messageSource.getMessage("spring.mail.password", null, Locale.ROOT);
			//messageSource.getMessage("spring.mail.properties.mail.smtp.auth", null, Locale.ROOT);
			
			//if(mailConfigration != null || !mailConfigration.isEmpty()) {
				//this.host = mailConfigration.get(SystemConfigrationCode.MAIL_SMTP_HOST.name());
				//this.protocol = mailConfigration.get(SystemConfigrationCode.MAIL_SMTP_PROTOCOL.name());
				//this.port = Integer.parseInt(mailConfigration.get(SystemConfigrationCode.MAIL_SMTP_PORT.name()));
				//this.username = mailConfigration.get(SystemConfigrationCode.MAIL_USERNAME.name());
				//this.password = CryptoStringUtils.encrypt(mailConfigration.get(SystemConfigrationCode.MAIL_PASSWORD.name()));
				//this.sslUnable = mailConfigration.get(SystemConfigrationCode.MAIL_SMTP_SSL_ENABLE.name()); 
				
				this.host = "smtp.worksmobile.com";
				this.protocol = "smtp";
				this.port = 465;
				this.username = "knkcorp@knksoft.co.kr";
				// 암/복호화 필요.
				this.password = "knkcorp!!23";
			//}
			
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", host);
			props.setProperty("mail.smtp.port", port + "");
	        props.setProperty("mail.host", host);
	        props.setProperty("mail.transport.protocol", protocol);
	        //props.setProperty("mail.smtp.localhost", "localhost");
			
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtps.ssl.checkserveridentity", "true");
			props.put("mail.smtps.ssl.trust", "*");
			
			Authenticator auth = null;
			if (username != null && password != null) {
				auth = new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				};
			}
			
			session = Session.getInstance(props, auth);
		} catch (Exception e) {
			logger.error(" mailConfigration = {}" , e);
		}
		
	}
	@Override
	public void send(String from, String to, String subject, String textHTML) {
		this.send(from, new String[]{to}, subject, textHTML);
	}
	
	@Override
	public void send(String from, String[] to, String subject , String textHTML ) {
		this.send(from, to, null, null, subject, textHTML);
	}

	@Override
	public void send(String from, String[] to, String[] cc, String[] bcc, String subject, String textHTML) {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setDefaultEncoding("utf-8");
		sender.setSession(session);
		
		try {
			MimeMessage message = sender.createMimeMessage();
	
			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
	
			// use the true flag to indicate the text included is HTML
			helper.setSubject(subject);
			helper.setText(textHTML, true);
	
			// let's include the infamous windows Sample file (this time copied to c:/)
			//FileSystemResource res = new FileSystemResource(new File("c:/Sample.jpg"));
			sender.send(message);
			
			logger.debug("Send Mail Success... = {}" , to.toString());
			
		} catch (Exception e) {
			logger.error("{} 님메일 발송에 실패하였습니다. 에러메세지 = {} " , to.toString() , e.getMessage());
			// TODO : 메일 발송에 실패한 사용자 내역 관리.
		}
	}
	
	/**
	 * 대량 메일 발송
	 * @param messages
	 */
	@Override
	public void send(List<Message> messages) {
		try {
			
			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			sender.setDefaultEncoding("utf-8");
			sender.setSession(session);
			MimeMessage[] mailMessageArr = new MimeMessage[messages.size()];
			messages.toArray(mailMessageArr);
			sender.send(mailMessageArr);
			logger.debug("Send Mail Success... = {}" , mailMessageArr.toString());
		} catch (Exception e) {
			logger.error("BULK 메일 발송에 실패하였습니다. 에러메세지 = {} " , e.getMessage());
			// TODO : 메일 발송에 실패한 사용자 내역 관리.
			
		}
	}
	
	
	
}
