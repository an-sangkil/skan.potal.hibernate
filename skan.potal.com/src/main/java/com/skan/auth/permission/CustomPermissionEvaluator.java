package com.skan.auth.permission;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.querydsl.core.BooleanBuilder;
import com.skan.tms.web.jpa.dto.QRoleDto;
import com.skan.tms.web.jpa.dto.RoleDto;
import com.skan.tms.web.jpa.repository.RoleJpaRepository;

/**
 * Description : 
 * 					사용방법
 * 					@PreAuthorize("hasPermission('storeForm','PERM_STORE_MANAGEMENT_VIEW')")
 * 
 * 
 * @author skan
 * @since 2016. 10. 4.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {

	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired RoleJpaRepository roleJpaRepository;

	/*
	 * Permisstion Table에서 Permission 정보를 체크하기 위해 사용한다. 
	 * (non-Javadoc)
	 * @see org.springframework.security.access.PermissionEvaluator#hasPermission(org.springframework.security.core.Authentication, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

		logger.debug("ACL Permission ");
		boolean hasPermission = false;
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(QRoleDto.roleDto.roleId.in(authentication.getAuthorities().stream()
						//.filter(simpleGrantedAuthority-> !simpleGrantedAuthority.getAuthority().equals(""))
						.findAny().get().getAuthority()
				    ));
		
		List<RoleDto> roles = (List<RoleDto>) roleJpaRepository.findAll(builder);
		
		for (RoleDto roleDto : roles) {
			long count = roleDto.getPermissions().stream().filter(permissionDto -> permissionDto.getPermissionId().equals(permission)).count();
			if(count >= 1) {
				hasPermission = true;
				break;
			}
		}
		
		return hasPermission;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		// TODO Auto-generated method stub
		return false;
	}
}
