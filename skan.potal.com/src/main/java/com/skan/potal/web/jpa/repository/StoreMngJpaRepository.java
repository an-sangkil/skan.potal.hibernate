package com.skan.tms.web.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.skan.tms.web.jpa.dto.StoreMngDto;

/**
 * 
 * <pre>
 * Class Name  : StoreMngJpaRepository.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 8. 30.        yh.Lee               최초생성
 * </pre>
 *
 * @author yh.Lee
 * @since 2016. 8. 30.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public interface StoreMngJpaRepository extends JpaRepository<StoreMngDto, String>,QueryDslPredicateExecutor<StoreMngDto>{
	public List<StoreMngDto> findByStoreManagerId(@Param("storeManagerId")String storeManagerId);
}
