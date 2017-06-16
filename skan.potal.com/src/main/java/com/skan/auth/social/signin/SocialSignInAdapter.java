package com.skan.auth.social.signin;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.context.request.NativeWebRequest;

import com.skan.tms.web.jpa.repository.UserJpaRepository;

/**
 * 
 * Description : 공통 소셜 로그인 아답터  
 *
 * @author skan
 * @since 2016. 9. 8.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class SocialSignInAdapter implements SignInAdapter {
	
	private Logger logger = LoggerFactory.getLogger(SocialSignInAdapter.class);
	
	private final RequestCache requestCache;
	
	@Autowired UserJpaRepository userJpaRepository;
	
	@Autowired UsersConnectionRepository usersConnectionRepository;
	
	@Inject
	public SocialSignInAdapter(RequestCache requestCache
			) {
		this.requestCache = requestCache;
	}
	
	@Override
	public	String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {

		// FIXME 유효시간에 대한 처리,  기본 프레임 웍에서 시간을 업데이트 해주기때문에 별도 처리 하지 않음.
		//ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(connection.fetchUserProfile().getEmail());
		//OAuth2Connection<FacebookTemplate> oldConnection = (OAuth2Connection<FacebookTemplate>) connectionRepository.getConnection(connection.getKey());
		
		//oldConnection.hasExpired();
		//logger.debug("oldConnection 정보 = {}" , oldConnection.toString());
		SignInUtils.signin(localUserId, userJpaRepository);
		
		
		return extractOriginalUrl(request);
	}

	private String extractOriginalUrl(NativeWebRequest request) {
		HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);
		SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
		if (saved == null) {
			return null;
		}
		requestCache.removeRequest(nativeReq, nativeRes);
		removeAutheticationAttributes(nativeReq.getSession(false));
		return saved.getRedirectUrl();
	}

	private void removeAutheticationAttributes(HttpSession session) {
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
