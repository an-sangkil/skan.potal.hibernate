/**
 * <pre>
 * Class Name  : CattleRepository.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 11.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 1. 11.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.cattle.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.skan.potal.web.potal.cattle.model.HmCattleRegister;
import com.skan.potal.web.potal.cattle.model.QHmCattleRegister;

/**
 * @author ahn
 *
 */
@Repository
public interface CattleRegisterRepository
		extends JpaRepository<HmCattleRegister, String>, QueryDslPredicateExecutor<HmCattleRegister> {

	default Page<HmCattleRegister> buildPage(JPQLQuery countQuery, JPQLQuery query, Pageable pageable) {
		
		Long total = countQuery.count();
		
		query.offset(pageable.getOffset());
		query.limit(pageable.getPageSize());
		
		List<HmCattleRegister> content = total > pageable.getOffset() ? query.list(QHmCattleRegister.hmCattleRegister) : Collections.<HmCattleRegister> emptyList();
		
		return  new PageImpl<HmCattleRegister>(content, pageable, total);
	}
}
