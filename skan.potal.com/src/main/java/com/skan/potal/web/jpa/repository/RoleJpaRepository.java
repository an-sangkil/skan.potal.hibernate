package com.skan.potal.web.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.potal.web.jpa.dto.RoleDto;

/**
 * 
 * <pre>
 * Class Name  : RoleJpaRepository.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 8. 18.        yh.Lee               최초생성
 * </pre>
 *
 * @author yh.Lee
 * @since 2016. 8. 18.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public interface RoleJpaRepository extends JpaRepository<RoleDto, String> , QueryDslPredicateExecutor<RoleDto>{

}
