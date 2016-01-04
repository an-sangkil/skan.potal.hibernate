package com.skan.potal.web.potal.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skan.potal.web.potal.application.model.CmtbCode;

public interface CmtbCodeRepository extends JpaRepository<CmtbCode, String>{
	
	public List<CmtbCode> findByUpperCode(String upperCode) throws Exception;
}
