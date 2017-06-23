package com.skan.potal.web.jpa.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.skan.potal.web.jpa.dto.UserRoleDto;
import com.skan.potal.web.jpa.dto.UserRoleDto.UserRolePK;

/**
 * 사용자 롤 레파지토리 
 * <pre>
 * Class Name  : UserRoleJpaRepository.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 8. 18.          skan               최초생성
 * </pre>
 *
 * @author skan
 * @since 2016. 8. 18.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public interface UserRoleJpaRepository extends JpaRepository<UserRoleDto, UserRolePK>,QueryDslPredicateExecutor<UserRoleDto>{
	
	@Transactional
	@Modifying
	@Query(value="delete from userrole where email =:email", nativeQuery=true )
	public void deleteUserRoleByEmail(@Param(value = "email") String email);
}
