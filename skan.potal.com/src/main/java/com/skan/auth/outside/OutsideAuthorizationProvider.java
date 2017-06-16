package com.skan.auth.outside;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skan.auth.social.signin.SignInUtils;
import com.skan.auth.utils.SessionUtils;
import com.skan.security.hash.salt.DigestUtils;
import com.skan.tms.mobile.common.code.CommonCode;
import com.skan.tms.mobile.web.controller.AbstractCommonController;
import com.skan.tms.mobile.web.model.CommonObject;
import com.skan.tms.mobile.web.model.ResponseMessage;
import com.skan.tms.web.jpa.dto.QUserDto;
import com.skan.tms.web.jpa.dto.UserDto;
import com.skan.tms.web.jpa.repository.ApiKeyManagementJpaRepository;
import com.skan.tms.web.jpa.repository.UserJpaRepository;

/**
 * 인증
 * 
 * Description : 외부 권한 인증 프로바이더.
 *  				외부 혹은 레거시로 부터 로그인 요청이 들어오는 경우 해당 컨트롤러를 통해 접근한다.
 *    
 * @author skan
 * @since  2016. 11. 30.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Controller
@RequestMapping("/outside")
public class OutsideAuthorizationProvider extends AbstractCommonController {
	
	private Logger logger = LoggerFactory.getLogger(OutsideAuthorizationProvider.class);
	
	@Autowired private UserJpaRepository userJpaRepository;
	@Autowired ApiKeyManagementJpaRepository apiKeyManagementJpaRepository;
	
	/**
	 * CASE ONE. 레거시 권한 인증 
	 * 			  단순 통과, 비밀번호 등 벨리데이션 체크 없음
	 * @param knkSecureKey	: SKAN Corporation 에서 발급한 키값
	 * 								Client Server 의 IP + Client Server 의 도메인정보
	 * @throws ServletRequestBindingException 
	 */
	@RequestMapping(value="/legacyAuthorzation" , method=RequestMethod.GET )
	public void legacyAuthorzation (
									  HttpServletRequest request 
									, @RequestHeader(value="Accept" , required=false) String accept
									, @RequestHeader(value="Accept-Language", required=false) String acceptLanguage
									, @RequestHeader(value="User-Agent", defaultValue="foo")  String userAgent
									//, @RequestHeader(value="SKAN_SECURE_KEY",required=true) String knkSecureKey
									//, @RequestHeader(value="SITE_KEY",required=true) String siteKey
									, HttpServletResponse response
									) throws Exception {
		
		String userId      = ServletRequestUtils.getStringParameter(request, "userId");
		String userEmail   = ServletRequestUtils.getStringParameter(request, "userEmail");
		String redirectUrl = ServletRequestUtils.getStringParameter(request, "redirectUrl");
		
		if(userId.equalsIgnoreCase("null")){
			userId = null;
		}
		if(userEmail.equalsIgnoreCase("null")){
			userEmail = null;
		}
		
		logger.trace("accept = {} ",accept);
		logger.trace("userAgent = {} ",userAgent);
		//logger.trace("knkSecureKey = {} ",knkSecureKey);
		//logger.trace("siteKey = {} ",siteKey);
		
		try {
			
//			ApiKeyManagement apiKeyManagement = apiKeyManagementJpaRepository.findOne(QApiKeyManagement.apiKeyManagement.siteKey.eq(siteKey));
//			
//			String serverSecureKey = CryptoStringUtils.decrypt(apiKeyManagement.getSecureKey());
//			System.out.println(new String(serverSecureKey));
//			
//			String clientSecureKey = CryptoStringUtils.decrypt(knkSecureKey);
//			
//			if(!serverSecureKey.equals(clientSecureKey)) {
//				throw new AccessDeniedException("잘못된 사용자 입니다. 관리자에게 문의 바랍니다.");
//			}
			
			String searchUserId = !StringUtils.isEmpty(userId) ? userId : userEmail;
			logger.info("outside legacyAuthorzation 접속 시도 = {}", searchUserId);
			SignInUtils.signin(searchUserId, userJpaRepository);

			if(!StringUtils.isEmpty(redirectUrl)){
				response.sendRedirect(redirectUrl);
			} else {
				response.sendRedirect(request.getContextPath());
			}
		} catch (Exception e) {
			logger.error("legacyAuthorzation = {}", e);
			// WAS - ERROR Catch , ErrorConfig.java 파일 참조.    
			//response.sendError(HttpStatus.SC_UNAUTHORIZED , "USER NOT FOUND...!! EXCEPTION!!!!");
			
			// @ControllerAdvice Error Catch
			throw new AccessDeniedException("잘못된 사용자 입니다. 관리자에게 문의 바랍니다." + userId + userEmail);
		}
	}
	
	/**
	 * CASE TWO. 레거시도메인  로그인 기능 추가 JSONP 
	 * @param request
	 * @param password
	 * @param accept
	 * @param acceptLanguage
	 * @param userAgent
	 * @param knkSecureKey
	 * @param siteKey
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin(allowCredentials="true")
	@ResponseBody
	@Transactional(transactionManager="transactionManager")
	@RequestMapping(value="/sign/legacy/legacyUserLogin", produces=MediaType.APPLICATION_JSON_VALUE)
	public CommonObject<?> legacyUserLogin(
								  HttpServletRequest request 
								, @RequestParam(value="password", required=false, defaultValue="") String password
								, @RequestHeader(value="Accept" , required=false) String accept
								, @RequestHeader(value="Accept-Language", required=false) String acceptLanguage
								, @RequestHeader(value="User-Agent", defaultValue="foo")  String userAgent
								, @RequestHeader(value="SKAN_SECURE_KEY",required=false) String knkSecureKey
								, @RequestHeader(value="SITE_KEY",required=false) String siteKey
								, HttpServletResponse response) throws Exception{
		
		String userId      = ServletRequestUtils.getStringParameter(request, "userId");
		String userEmail   = ServletRequestUtils.getStringParameter(request, "userEmail");
		String redirectUrl = ServletRequestUtils.getStringParameter(request, "redirectUrl");
		
		logger.trace("accept = {} ",accept);
		logger.trace("userAgent = {} ",userAgent);
		logger.trace("knkSecureKey = {} ",knkSecureKey);
		logger.trace("siteKey = {} ",siteKey);
		
		CommonObject<UserDto> commonObject = new ResponseMessage<>();
		
		try {
			//////////////////////////////////////////////////////
			// API KEY 확인 
			//////////////////////////////////////////////////////
//			ApiKeyManagement apiKeyManagement = apiKeyManagementJpaRepository.findOne(QApiKeyManagement.apiKeyManagement.siteKey.eq(siteKey));
//			String serverSecureKey = CryptoStringUtils.decrypt(apiKeyManagement.getSecureKey());
//			System.out.println(new String(serverSecureKey));
//			String clientSecureKey = CryptoStringUtils.decrypt(knkSecureKey);
//			if(!serverSecureKey.equals(clientSecureKey)) {
//				throw new AccessDeniedException("잘못된 사용자 입니다. 관리자에게 문의 바랍니다.");
//			}
			
			String searchUserId = !StringUtils.isEmpty(userId) ? userId : userEmail;
			logger.info("outside legacyUserLogin 로그인 시도 = {}", searchUserId);
			
			QUserDto qUserDto = QUserDto.userDto;
			UserDto userDto = userJpaRepository.findOne(qUserDto.email.eq(searchUserId).or(qUserDto.userId.eq(searchUserId)));
			
			// 비밀번호 체크
			if( !DigestUtils.isPasswordValid(userDto.getPassword(), password)) {
				throw new Exception("legacyUserLogin 로그인에 실패 하였습니다. 비밀번호 틀림.");
			} 
			// 활성화 체크 
			if(!userDto.isEmailAuthentication()) {
				throw new Exception("legacyUserLogin 로그인에 실패 하였습니다. 이메일 인증 하지 않은 사용자 입니다.");
			} 
			
			SignInUtils.signin(searchUserId, userJpaRepository);
			commonObject.setResponseObject(userDto);
			commonObject.setStateCode(CommonCode.SUCCESS);
			commonObject.setResponseMessage("로그인 성공.");
			
			Date toDate = new Date();
			//userDto.setLastConnectedTime(toDate.getTime());
			//userDto = userJpaRepository.save(userDto);
			
			long effectRow = super.getJPAUpdateClauseInstance(qUserDto).set(qUserDto.lastConnectedTime, toDate.getTime()).where(qUserDto.email.eq(userDto.getEmail())).execute();
			logger.debug("effectRow = {}" , effectRow);
			
			
		} catch (Exception e) {
			commonObject.setStateCode(CommonCode.FAIL);
			commonObject.setResponseMessage("로그인에 실패 하였습니다.");
			logger.error("외부 로그인 실패 = {} , 사용자 아이디  = {} / {} ",e ,  userId , userEmail);
		}
		
		return commonObject;
	}
	
	
	/**
	 * 레거시에서 로그인 세션 체크하기 JSONP 
	 * @param request
	 * @param response
	 * @return
	 */
	@CrossOrigin
	@ResponseBody
	@RequestMapping(value="/legacy/legacySessionCheck", produces=MediaType.APPLICATION_JSON_VALUE)
	public CommonObject<?> legacySessionCheck(HttpServletRequest request , HttpServletResponse response) {
		CommonObject<UserDto> commonObject = new ResponseMessage<>();
		try {
			if( SessionUtils.getSessionUserEmail() != null) {
				commonObject.setStateCode(CommonCode.SUCCESS);
			} else {
				commonObject.setStateCode(CommonCode.FAIL);
			}
			
		} catch (Exception e) {
			logger.error("legacySessionCheck = {}" ,e);
			commonObject.setStateCode(CommonCode.FAIL);
		}
		
		
		return commonObject;
	}

}
