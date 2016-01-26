package com.skan.potal.web.potal.application.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.potal.web.potal.application.model.CmtbCode;
import com.skan.potal.web.potal.application.model.CmtbCodeId;

public interface CmtbCodeRepository extends JpaRepository<CmtbCode, CmtbCodeId>, QueryDslPredicateExecutor<CmtbCode>{
	
	public CmtbCode findByCmtbCodeId(CmtbCodeId cmtbCodeId) throws Exception;
	public List<CmtbCode> findByUpperCode(String upperCode) throws Exception;
	public Page<CmtbCode> findByUpperCode(String upperCode , Pageable pageable) throws Exception;
	
}
