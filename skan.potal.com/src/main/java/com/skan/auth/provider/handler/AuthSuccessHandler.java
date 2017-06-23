package com.skan.auth.provider.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.skan.auth.bean.SKANSession;
import com.skan.potal.web.jpa.dto.UserDto;
import com.skan.potal.web.jpa.repository.UserJpaRepository;

@Component
public class AuthSuccessHandler
	extends SavedRequestAwareAuthenticationSuccessHandler
	implements AuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private UserJpaRepository userJpaRepository;
	
	public AuthSuccessHandler() {
		super();
		setUseReferer(true);
		setDefaultTargetUrl("/concert/concertList");
	}


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		logger.debug("SUCCESS Login.............");
		
		
		request.getSession().setMaxInactiveInterval(30*60);
		// User principal = (User) authentication.getPrincipal();
		// WebAuthenticationDetails details = (WebAuthenticationDetails)
		// authentication.getDetails();
		// String username = authentication.getName();
		// String password = (String) authentication.getCredentials();
		SKANSession knkSession = (SKANSession) authentication.getDetails();
		UserDto user = knkSession.getUserDto();
		
		
		logger.debug(" \n\n\n ");
		logger.debug("///////////////////////////////////////");
		logger.debug("// LOGIN								 ");
		logger.debug("///////////////////////////////////////");
		logger.info("사용자 {}({}) 로그인 성공 ", user.getUserName(), user.getEmail());
		
		
		// 마지막 접속 시간 기록
		userJpaRepository.updateLoginTime(new Date().getTime(), user.getEmail());
		
		
		// logger.debug("authentication : " + authentication.toString());
		// logger.debug("principal : " + principal);
		// logger.debug("details : " + details.toString());
		// logger.debug("username : " + username);
		// logger.debug("password : " + password);
		logger.debug(" \n\n\n ");
		

		//RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		//redirectStrategy.sendRedirect(request, response, "/admin/reservitionHistory/reservationHistoryList?searchReservationState=RESERVATION_IN_COMPLATE");
		
		// header 의 referer URL 의 정보를 보고 있는경우 이전  화면으로 바로 이동.
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
