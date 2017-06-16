package com.skan.auth.email.check;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skan.security.semmetric.CryptoStringUtils;
import com.skan.tms.mobile.web.controller.AbstractCommonController;
import com.skan.tms.web.jpa.dto.QUserDto;
import com.skan.tms.web.jpa.repository.UserJpaRepository;

/**
 * 
 * Description : 이메일 인증 컨트롤러  
 * @author skan
 * @since  2016. 12. 14.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Controller
public class EmailAuthenticationController extends AbstractCommonController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired UserJpaRepository userJpaRepository;
	
	/**
	 * STEP.1  이메일 인증 
	 * 사용자가 발송 받은 이메일을 확인하여 해당 계정을 활성화 한다.
	 * @throws Exception 
	 */
	@RequestMapping("/outside/emailAuthentication")
	@Transactional(transactionManager="transactionManager")
	public void emailAuthentication(
				  @RequestHeader(required=false) String secureKey		// 키검증 키.
				
				  , @RequestParam(required=false) String authSeq			// 번호
				, @RequestParam(required=false) String message			// 키검증 키.
				, HttpServletRequest request
				, HttpServletResponse response
				
			) throws Exception {
		
		//TODO HEADER 키검증 
		
		
		try {
			// message로 암호화된 내용 복호화
			String email = CryptoStringUtils.decrypt(message); 
			
			QUserDto quserDto = QUserDto.userDto;
			super.getJPAUpdateClauseInstance(quserDto)
						.where(quserDto.email.eq(email))
						.set(quserDto.emailAuthentication, true).execute();
			
			
		} catch (Exception e) {
			logger.error("이메일 인증 실패 ", e);
		}
		
		response.sendRedirect(request.getContextPath() + "/outside/emailAuthenticationView?");
	}
	
	/**
	 * STEP .2 이메일 인증
	 * 	계정 활성화 성공 실페에 대한 화면
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/outside/emailAuthenticationView")
	public String emailAuthenticationView(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		return "/outside/email/emailAuthenticationView";
	}
	
}
