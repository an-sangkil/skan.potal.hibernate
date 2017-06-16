/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.skan.auth.social.signup;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.skan.auth.social.signin.SignInUtils;
import com.skan.security.hash.salt.DigestUtils;
import com.skan.security.semmetric.CryptoStringUtils;
import com.skan.tms.mobile.web.service.ExecutorSendMailService;
import com.skan.tms.mobile.web.service.ExecutorSendMailService.ThreadSendEnum;
import com.skan.tms.web.jpa.dto.QUserDto;
import com.skan.tms.web.jpa.dto.UserDto;
import com.skan.tms.web.jpa.repository.UserJpaRepository;

/**
 * 
 * Description : 페이스북 회원 가입 및 로그인 
 * @author skan
 * @since  2016. 09. 14.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Controller
public class SocialSignupController {
	
	private final ProviderSignInUtils providerSignInUtils;
	
	@Autowired private ExecutorSendMailService emailSendService;
	private UserJpaRepository userJpaRepository;
	@SuppressWarnings("unused")
	private ConnectionRepository connectionRepository;
	
	@Value("${custom.server.domain}")
	private String hostName;
	
	
	
	@Inject
	public SocialSignupController(UserJpaRepository userJpaRepository, 
		                    ConnectionFactoryLocator connectionFactoryLocator,
		                    UsersConnectionRepository connectionRepository) {
		this.userJpaRepository = userJpaRepository;
		this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
	}
	
	// 회원 가입 화면으로 이동
	@RequestMapping(value="signup", method=RequestMethod.GET)
	public String singupForm(WebRequest request, ModelMap modelMap) {
		
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
		if(connection != null ){
			request.setAttribute("message","please sign in...", WebRequest.SCOPE_REQUEST);
			modelMap.put("userDto", UserDto.fromProviderUser(connection));
		}
		
		return "signup/signupForm";
	}
	
	/**
	 * 신규 회원 가입 
	 * @param form
	 * @param formBinding
	 * @param request
	 * @return
	 */
	@Transactional(value = "transactionManager",propagation=Propagation.REQUIRED)
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(@Valid UserDto form, BindingResult formBinding, WebRequest request) {
		
		request.setAttribute("userDto", form, 1);
		if (formBinding.hasErrors()) {
			return "signup/signupForm";
		}
		
		// 비밀번호 체크  및 Validation 확인.
		if(!form.getPassword().equals(form.getConfirmPassword())) {
			
			formBinding.rejectValue("confirmPassword", "user.password.check", "입력하신 비밀번호가 올바르지 않습니다.");
			
			return "signup/signupForm";
		}
		
		
		UserDto account = createAccount(form, formBinding);
		
		// 신규회원 가입 성공시
		if (account != null) {
			
			// 세션 생성 및 권한 생성  
			SignInUtils.signin(account.getEmail(),userJpaRepository);
			providerSignInUtils.doPostSignUp(account.getEmail(), request);
			
			// 메일 발송
			//EMAIL 발송  Thread 처리 , Template 생성
			try {
				String message = CryptoStringUtils.encrypt(account.getEmail());
				String template = "<a href='http://"+hostName+"/tms/outside/emailAuthentication?message="+ message +"'>여기</a>";
				emailSendService.sendEmail("knkcorp@knksoft.co.kr", account.getEmail(), "회원가입 인증 메일", template+ " 를 눌러 인증 하세요.", ThreadSendEnum.RUNNABLE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "redirect:/sign/signupAuthenticationEmailSendView?email="+account.getEmail();
		} 
		
		return "signup/signupForm";
	}
	
	/*
	 * 사용자 정보 저장 
	 */
	private UserDto createAccount(UserDto account, BindingResult formBinding) {
		try {
			
			// 신규 가입자 사용자 정보 저장 
			// account = jpaUserConnectionRepository.save(account);
			if(userJpaRepository.findOne(account.getEmail()) != null){
				formBinding.rejectValue("email", "user.duplicateUsername", "이미 사용중인 E-mail 입니다.");
				return null;
			}
			
			if(userJpaRepository.count(QUserDto.userDto.userId.eq(account.getUserId())) > 0){
				formBinding.rejectValue("userId", "signup.email.duplication","이미 등록된 아이디 입니다.");
				return null;
			}
			
			account.setPassword(DigestUtils.encodePassword(account.getPassword()));
			account = userJpaRepository.save(account);
			return account;
		} catch (Exception e) {
			formBinding.rejectValue("email", "user.failSave", "failed to save.");
			return null;
		}
	}
	
}
