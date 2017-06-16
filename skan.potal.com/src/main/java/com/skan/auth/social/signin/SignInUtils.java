package com.skan.auth.social.signin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.skan.auth.bean.SKANSession;
import com.skan.com.util.utils.LocalDateUtils;
import com.skan.tms.mobile.common.code.AuthorizationCode;
import com.skan.tms.mobile.common.utils.ApplicationContextProvider;
import com.skan.tms.web.jpa.dto.Membership;
import com.skan.tms.web.jpa.dto.QMembership;
import com.skan.tms.web.jpa.dto.QRoleDto;
import com.skan.tms.web.jpa.dto.QUserDto;
import com.skan.tms.web.jpa.dto.QUserRoleDto;
import com.skan.tms.web.jpa.dto.UserDto;
import com.skan.tms.web.jpa.repository.MembershipJpaRepository;
import com.skan.tms.web.jpa.repository.RoleJpaRepository;
import com.skan.tms.web.jpa.repository.UserJpaRepository;
import com.skan.tms.web.jpa.repository.UserRoleJpaRepository;

/**
 * Description : 소셜 로그인 유틸로 
 * 			           스프링 스큐리티 에 함께 세션을 부여해 준다.
 *  
 * @author skan
 * @since 2016. 9. 8.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class SignInUtils {
	
	private static final String ROLE_ = "ROLE_";
	
	private static final Logger logger = LoggerFactory.getLogger(SignInUtils.class);
	
	
	/**
	 * Programmatically signs in the user with the given the user ID.
	 */
	public static void signin(String userId) {
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, null));	
	}
	
	/**
	 * Programmatically signs in the user with the given the user ID.
	 * 
	 * 사용자 정보 포함.
	 */
	public static void signin(String userEmail, UserJpaRepository userJpaRepository) {
		
		QUserDto qUserDto = QUserDto.userDto;
		UserDto userDto = userJpaRepository.findOne(qUserDto.email.eq(userEmail).or(qUserDto.userId.eq(userEmail)));
		
		// 소셜 로그인으로 로그인 했을경우 권할 설정
		// ROLE 및 PERMISSION CHECK
		
		// GET BEAN
		UserRoleJpaRepository userRoleJpaRepository = ApplicationContextProvider.getBean("userRoleJpaRepository", UserRoleJpaRepository.class);
		RoleJpaRepository roleJpaRepository = ApplicationContextProvider.getBean("roleJpaRepository", RoleJpaRepository.class);
		MembershipJpaRepository membershipJpaRepository = ApplicationContextProvider.getBean("membershipJpaRepository", MembershipJpaRepository.class);
		
		////////////////////////////////////////////
		// ROLES 할당
		////////////////////////////////////////////
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.clear();
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.and(QUserRoleDto.userRoleDto.userRolePK.email.eq(userDto.getEmail()));
		
		List<String> roleIds = new ArrayList<>();
		userRoleJpaRepository.findAll(booleanBuilder).forEach(userRole -> {
			roleIds.add(userRole.getUserRolePK().getRoleId());
		});
		
		// 조건 설정
		booleanBuilder = new BooleanBuilder();
		if(!userDto.isAdmin()) {
			booleanBuilder.and(QRoleDto.roleDto.roleId.in(roleIds));
		}
		
		// ROLE 추가
		roleJpaRepository.findAll(booleanBuilder).forEach(role -> {
			roles.add(
				// ROLE first fix
				new SimpleGrantedAuthority(role.getRoleId())
			);
		});
		
		
		DateTemplate from = Expressions.dateTemplate(
			    Date.class, "to_date({0},'{1s}')", "2017-04-07", ConstantImpl.create("YYYY-MM-DD"));

		
		// 유료 사용자 확인  , XXX : 오늘 날짜보다 같거나 높은 날짜로 조회하는 내역 추가 필요함. 
		List<Membership> memberships = (List<Membership>) membershipJpaRepository
					.findAll(QMembership.membership.userDto.email.eq(userDto.getEmail())
							.and(
									QMembership.membership.endDate
									.goe(LocalDateUtils.localDateConvertToDate(LocalDate.now()))
							), new Sort(new Order("startDate")));
		
		;
		
		if(memberships.size() > 0) {
			roles.add(new SimpleGrantedAuthority(ROLE_ + AuthorizationCode.PAID_MEMBER.name()));
			
			switch (memberships.get(0).getMembershipType().getMembershipTypeCode()) {
			case "00550001":
			case "00550004":
				//개인
				roles.add(new SimpleGrantedAuthority(ROLE_ + AuthorizationCode.INDIVIDUAL_MEMBER.name()));
				break;
			case "00550002":
			case "00550005":
				//가족
				roles.add(new SimpleGrantedAuthority(ROLE_ + AuthorizationCode.FAMILY_MEMBER.name()));
				break;
			case "00550003":
			case "00550006":
				//단체 
				roles.add(new SimpleGrantedAuthority(ROLE_ + AuthorizationCode.GROUP_MEMBER.name()));
				break;
			case "00550007":
			case "00550008":
				//어린이 청소년
				roles.add(new SimpleGrantedAuthority(ROLE_ + AuthorizationCode.TEENAGER_MEMBER.name()));
				break;
			}
		}
		
		roles.add(new SimpleGrantedAuthority(ROLE_ + AuthorizationCode.DEFAULT_USER.name()));
		
		// SKANSession.class Object에 add on 하여 세션 생성
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( userDto.getEmail(), null , roles );
		
		//////////////////////////////////////////
		// 세션 생성 
		//////////////////////////////////////////
		SKANSession knkSession = new SKANSession();
		knkSession.setUserDto(userDto);
		//knkSession.setRemoteAddress(remoteIp);
		authenticationToken.setDetails( knkSession );
		
		
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);	
	}
	
}