package com.skan.potal.web.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.potal.web.jpa.dto.MembershipType;

/**
 * 
 * Description : 회원종류  레파지토리 
 * @author skan
 * @since  2016. 11. 30.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public interface MembershipTypeJpaRepository extends JpaRepository<MembershipType, String> , QueryDslPredicateExecutor<MembershipType>{

}
