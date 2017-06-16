package com.knkcorp.tms.mobile.web.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knkcorp.security.hash.salt.DigestUtils;
import com.knkcorp.tms.mobile.common.code.CommonCode;
import com.knkcorp.tms.mobile.web.model.CommonObject;
import com.knkcorp.tms.mobile.web.model.ResponseMessage;
import com.knkcorp.tms.mobile.web.service.ExecutorSendMailService;
import com.knkcorp.tms.mobile.web.service.ExecutorSendMailService.ThreadSendEnum;
import com.knkcorp.tms.web.jpa.dto.QUserDto;
import com.knkcorp.tms.web.jpa.dto.UserDto;
import com.knkcorp.tms.web.jpa.repository.UserJpaRepository;

/**
 * Description : 사용자 컨트롤러 
 *
 * @author yh.lee
 * @since 2016. 9. 12.
 * @version 
 *
 * Copyright (C) 2016 by KNK Corp. All right reserved.
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractCommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired private UserJpaRepository userJpaRepository;
	
	@Autowired private ExecutorSendMailService emailSendService;
	
		
	/**
	 * 사용자 아이디/비밀번호 찾기 
	 * @param request
	 * @return
	 */
	@RequestMapping("/userIdPasswordfind")
	@Transactional(transactionManager="transactionManager")
	public ResponseEntity<?> userIdPasswordfind (
			@RequestParam(value = "email", required = false, defaultValue = "") String email,
			@RequestParam(value = "userName", required = false, defaultValue = "") String userName,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			ModelMap modelMap)  throws Exception {
		
		QUserDto qUserDto = QUserDto.userDto;
		
		HttpStatus httpStatus;	
		
		CommonObject<String> commonObject = new ResponseMessage<>();

		System.out.println(userId);
		try {
		if(userId.equals("findMyIdPlease")){
			//아이디 찾기
			UserDto userDto =userJpaRepository.findOne(qUserDto.userName.eq(userName).and(qUserDto.email.eq(email)));
			if(userDto != null){

				System.out.println("아이디 검색 회원정보 검색");
				System.out.println(userDto.getEmail());
				
				emailSendService.sendEmail("knkcorp@knksoft.co.kr", userDto.getEmail(), "노원문화예술회관", "귀하의 아이디는 [" + userDto.getUserId() + "] 입니다", ThreadSendEnum.RUNNABLE);
				
				commonObject.setStateCode(CommonCode.SUCCESS);
				httpStatus = HttpStatus.OK;
			}else{
				System.out.println("아이디 검색 회원정보 틀림");
				commonObject.setResponseMessage("회원 정보가 틀립니다.");
				throw new Exception("회원 정보가 틀립니다.");
			}
		}else{

			System.out.println("비밀번호");
			//비밀번호 찾기
			UserDto userDto =userJpaRepository.findOne(qUserDto.userName.eq(userName).and(qUserDto.email.eq(email)).and(qUserDto.userId.eq(userId)));
			if(userDto != null){

				System.out.println("비밀번호 검색");
				String password="";
				StringBuffer buffer = new StringBuffer();
				Random random = new Random();
				String chars[] = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
				 
				for (int i=0 ; i<8 ; i++)
				{
					buffer.append(chars[random.nextInt(chars.length)]);
					password = buffer.toString();
				}
				
				
				
				emailSendService.sendEmail("knkcorp@knksoft.co.kr", userDto.getEmail(), "노원문화예술회관", "귀하의 비밀번호는 [" + password + "] 로 수정되었습니다.", ThreadSendEnum.RUNNABLE);
				
				userDto.setPassword(DigestUtils.encodePassword(password));
				userJpaRepository.save(userDto);
				
				commonObject.setStateCode(CommonCode.SUCCESS);
				httpStatus = HttpStatus.OK;
				
			}else{

				System.out.println("비밀번호 틈림");
				commonObject.setResponseMessage("회원 정보가 틀립니다.");
				throw new Exception("회원 정보가 틀립니다.");
			}
			
		}
		httpStatus = HttpStatus.OK;
		}catch (Exception e) {

			System.out.println("오류");
			commonObject.setStateCode(CommonCode.FAIL);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<CommonObject<String>>(commonObject,httpStatus);
	}
	
	
}
