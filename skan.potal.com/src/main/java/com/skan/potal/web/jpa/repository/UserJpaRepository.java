package com.skan.potal.web.jpa.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.skan.potal.web.jpa.dto.UserDto;

/**
 * <pre>
 * Class Name  : UserRepository.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 5. 25.          skan               최초생성
 * </pre>
 *
 * @author skan
 * @since 2016. 5. 25.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public interface UserJpaRepository extends JpaRepository<UserDto, String> ,QueryDslPredicateExecutor<UserDto>{
	
	public UserDto findByEmail(String email) throws Exception;
	public Page<UserDto> findByuserNameContaining(String userName   , Pageable pageable) throws Exception;
	public int findByUserName(@Param("userName")String userName) throws Exception;
	
	@Modifying
	@Transactional
	@Query(value="update users set last_connected_time =:time where email =:userEmail ", nativeQuery=true)
	public void updateLoginTime(@Param(value = "time") long time,@Param("userEmail") String email);
	

}
