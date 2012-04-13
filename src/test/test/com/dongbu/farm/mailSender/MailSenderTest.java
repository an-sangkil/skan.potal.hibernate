/*
 * $Id: mailSender.java ,v 1.1 2010. 9. 8. 오후 3:33:43 smrscvs1 Exp $
 * created by    : ahn
 * creation-date : 2010. 9. 8.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package test.com.dongbu.farm.mailSender;

import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import com.dongbu.farm.common.mail.MimeMailSender;
/**
 * ClassPathXmlApplicationContext  : classPath 에 있는 XML	 빈 설정 파일을 이용할때 사용. import org.springframework.context.support.ClassPathXmlApplicationContext;
 * FileSystemXmlApplicationContext : 파일시스템에 있는 XML 빈 설정을 이용할때 사용한다.   import org.springframework.context.support.FileSystemXmlApplicationContext;
 * 
 * SendMail Test Class
 * @author ahn
 *
 */
public class MailSenderTest extends AbstractDependencyInjectionSpringContextTests {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				new String[] { "/WebContent/WEB-INF/applicationContext/applicationContext-mail.xml" });
		MimeMailSender sender = (MimeMailSender) ctx.getBean("mimeMailSender");

		try {
			sender.sendMail("mycup@nate.com", "an.sangkil@gmail.com",
					"Mail Send Test", "Hello Mail Send Test, That's OK?");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
