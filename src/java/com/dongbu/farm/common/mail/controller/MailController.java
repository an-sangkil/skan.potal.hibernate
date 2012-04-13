/*
 * $Id: MailController.java ,v 1.1 2010. 9. 8. 오후 3:34:49 smrscvs1 Exp $
 * created by    : ahn
 * creation-date : 2010. 9. 8.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.mail.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.dongbu.farm.common.mail.MimeMailSender;
import com.dongbu.farm.common.mail.model.MailContents;
import com.dongbu.farm.common.repository.controller.RepositoryController;

public class MailController extends RepositoryController{
	
	private MimeMailSender mimeMailSender;
	private MailContents mailContents;
	
	
	/**
	 * 메일 작성 화면으로 이동.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView goMailWritePage (HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		this.mailContents.getCommonMailContents("common");
		
		System.out.println("test");
		System.out.println(this.mailContents.getCommonMailContents("commonCase1"));
		return new ModelAndView("");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void sendMail(HttpServletRequest request, HttpServletResponse response){
		
		//this.mimeMailSender.sendMail(to, from, subject, text)
		
	}

	
	
	/**
	 * @return the mimeMailSender
	 */
	public MimeMailSender getMimeMailSender() {
		return mimeMailSender;
	}

	/**
	 * @param mimeMailSender the mimeMailSender to set
	 */
	public void setMimeMailSender(MimeMailSender mimeMailSender) {
		this.mimeMailSender = mimeMailSender;
	}

	/**
	 * @return the mailContents
	 */
	public MailContents getMailContents() {
		return mailContents;
	}

	/**
	 * @param mailContents the mailContents to set
	 */
	public void setMailContents(MailContents mailContents) {
		this.mailContents = mailContents;
	}
	
}
