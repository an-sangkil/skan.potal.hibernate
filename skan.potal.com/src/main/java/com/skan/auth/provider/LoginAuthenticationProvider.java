package com.skan.auth.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.Expressions;
import com.skan.auth.bean.SKANSession;
import com.skan.security.hash.salt.DigestUtils;
import com.skan.tms.mobile.common.code.AuthorizationCode;
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
 * Class Name  : 인증 처리 
 * Description : 

 * @author skan
 * @since 2016. 9. 8.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired UserJpaRepository userJpaRepository;
	@Autowired RoleJpaRepository roleJpaRepository;
	@Autowired UserRoleJpaRepository userRoleJpaRepository;
	@Autowired MembershipJpaRepository membershipJpaRepository;

	private static final String ROLE_ = "ROLE_";
	
	@Override
	public Authentication authenticate(Authentication authentication) {
		
		String userEmail = String.valueOf( authentication.getPrincipal() );
		String password = String.valueOf( authentication.getCredentials() );
		
		SKANSession knkSession = new SKANSession();
		WebAuthenticationDetails detail = (WebAuthenticationDetails) authentication.getDetails();
		String remoteIp = detail.getRemoteAddress();
		
		
		QUserDto qUserDto = QUserDto.userDto;
		UserDto userDto = userJpaRepository.findOne(qUserDto.email.eq(userEmail).or(qUserDto.userId.eq(userEmail)));
		
		if(userDto == null) {
			logger.error("사용자 정보가 없습니다.");
			throw new UsernameNotFoundException("사용자 정보가 없습니다.. " + remoteIp);
		} else {
			if(!DigestUtils.isPasswordValid(userDto.getPassword(), password)) {
				throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
			}
			
			// 이메일 인증 사용자 여부 
			if(!userDto.isEmailAuthentication()) {
				throw new BadCredentialsException("미인증 사용자 입니다.");
			}
			
			// 활성화 비활성화 (삭제)
			
			// 비밀번호 틀린 횟수
			
		}
		
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
		
		// 유료 사용자 확인  , XXX : 오늘 날짜보다 같거나 높은 날짜로 조회하는 내역 추가 필요함. 
		List<Membership> memberships = (List<Membership>) membershipJpaRepository
					.findAll(QMembership.membership.userDto.email.eq(userDto.getEmail())
							.and(
									Expressions.dateTimeOperation(Date.class, Ops.DateTimeOps.DATE, QMembership.membership.endDate).goe(new Date())
							).and(
									QMembership.membership.paymentState.eq("PAYMENT_IN_COMPLATE")
							), new Sort(new Order("startDate")));
		
		// 유료 사용자 기간 확인 (오늘날짜 time < 종료날짜 )
		//Calendar calendar = Calendar.getInstance();
		//long count = memberships.stream().filter(
		//		p -> 	DateUtils.dateFormatSecond( DateUtils.dateConvertCalendar(p.getEndDate()).get(Calendar.YEAR), 
		//											DateUtils.dateConvertCalendar(p.getEndDate()).get(Calendar.MONTH)+1,
		//											DateUtils.dateConvertCalendar(p.getEndDate()).get(Calendar.DATE))   
		//					> DateUtils.dateFormatSecond(calendar.get(Calendar.YEAR), 
		//												 calendar.get(Calendar.MONTH)+1,
		//												 calendar.get(Calendar.DATE))).count();
		
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
		// TODO PERMISION  추가 해서 함께 관리
		// SKANSession.class Object에 add on 하여 세션 생성
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( userDto.getEmail(), password, roles );
		
		//////////////////////////////////////////
		// 세션 생성 
		//////////////////////////////////////////
		//knkSession.setRoles(roles.toArray());
		knkSession.setUserDto(userDto);
		knkSession.setRemoteAddress(remoteIp);
		authenticationToken.setDetails( knkSession );
		
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals( UsernamePasswordAuthenticationToken.class );
	}
	
}
