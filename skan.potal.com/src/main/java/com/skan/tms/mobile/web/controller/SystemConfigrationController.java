package com.knkcorp.tms.mobile.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knkcorp.security.semmetric.CryptoStringUtils;
import com.knkcorp.tms.mobile.common.code.CommonCode;
import com.knkcorp.tms.mobile.common.code.SystemConfigrationCode;
import com.knkcorp.tms.web.jpa.dto.QSystemConfig;
import com.knkcorp.tms.web.jpa.dto.SystemConfig;
import com.knkcorp.tms.web.jpa.repository.SystemConfigJpaRepository;

/**
 * 
 * Description : 환경 설정 컨트롤러 
 * @author skan
 * @since  2016. 12. 30.
 * @version 
 *
 * Copyright (C) 2016 by KNK Corp. All right reserved.
 */
@Controller
public class SystemConfigrationController extends AbstractCommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemConfigrationController.class);
	
	@Autowired private SystemConfigJpaRepository systemConfigJpaRepository;
	
	/**
	 * 이메일 환경설정 폼
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/systemConfigration/emailConfigrationForm")
	public String emailConfigrationForm(HttpServletRequest request , HttpServletResponse response) {
		
		List<String> emailKeys = new ArrayList<>();
		for(SystemConfigrationCode scc : SystemConfigrationCode.MAIL_GROUPS){
			emailKeys.add(scc.name());
		}
		
		List<SystemConfig> systemConfigs = (List<SystemConfig>) systemConfigJpaRepository.findAll(QSystemConfig.systemConfig.configId.in(emailKeys));
		request.setAttribute("systemConfigs", systemConfigs);

		return "admin.configration.emailConfigrationForm";
	}
	
	/**
	 * 이메일 환경설정 저장 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/systemConfigration/emailConfigrationSave")
	public String emailConfigrationSave(HttpServletRequest request , 
			
			@RequestParam String MAIL_USERNAME,
			@RequestParam String MAIL_PASSWORD,
			@RequestParam String MAIL_SMTP_HOST,
			@RequestParam String MAIL_SMTP_PROTOCOL,
			@RequestParam String MAIL_SMTP_PORT,
			@RequestParam String MAIL_SMTP_SSL_ENABLE, 
			
			HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		
		try {
			List<SystemConfig> saveSystemConfigs = new ArrayList<>();
			SystemConfig systemConfig = new SystemConfig();
			systemConfig.setConfigId(SystemConfigrationCode.MAIL_USERNAME.name());
			systemConfig.setConfigValue(MAIL_USERNAME);
			saveSystemConfigs.add(systemConfig);
			
			systemConfig = new SystemConfig();
			systemConfig.setConfigId(SystemConfigrationCode.MAIL_PASSWORD.name());
			systemConfig.setConfigValue(CryptoStringUtils.encrypt(MAIL_PASSWORD));
			saveSystemConfigs.add(systemConfig);
			
			systemConfig = new SystemConfig();
			systemConfig.setConfigId(SystemConfigrationCode.MAIL_SMTP_HOST.name());
			systemConfig.setConfigValue(MAIL_SMTP_HOST);
			saveSystemConfigs.add(systemConfig);
			
			systemConfig = new SystemConfig();
			systemConfig.setConfigId(SystemConfigrationCode.MAIL_SMTP_PROTOCOL.name());
			systemConfig.setConfigValue(MAIL_SMTP_PROTOCOL);
			saveSystemConfigs.add(systemConfig);
			
			systemConfig = new SystemConfig();
			systemConfig.setConfigId(SystemConfigrationCode.MAIL_SMTP_PORT.name());
			systemConfig.setConfigValue(MAIL_SMTP_PORT);
			saveSystemConfigs.add(systemConfig);
			
			systemConfig = new SystemConfig();
			systemConfig.setConfigId(SystemConfigrationCode.MAIL_SMTP_SSL_ENABLE.name());
			systemConfig.setConfigValue(MAIL_SMTP_SSL_ENABLE);
			saveSystemConfigs.add(systemConfig);
			
			this.systemConfigJpaRepository.save(saveSystemConfigs);
			redirectAttributes.addFlashAttribute("stateCode", CommonCode.SUCCESS.name());
			
		} catch (Exception e) {
			logger.error("emailConfigrationSave = {} " , e);
			redirectAttributes.addFlashAttribute("stateCode", CommonCode.FAIL.name());
			return "redirect:/systemConfigration/emailConfigrationForm";
		}
		
		return "redirect:/systemConfigration/emailConfigrationForm";
	}
}
