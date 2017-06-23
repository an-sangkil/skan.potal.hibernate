package com.skan.tms.web.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.skan.tms.web.jpa.dto.CommonCodeDto;

/**
 * Description : 코드 조회 Repository 
 * @author skan
 * @since 2016. 9. 22.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public interface CommonCodeJpaRepository extends JpaRepository<CommonCodeDto, String>, QueryDslPredicateExecutor<CommonCodeDto>{
	
	public CommonCodeDto findByCode(String code) throws Exception;
	public List<CommonCodeDto> findByUpperCode(String upperCode) throws Exception;
	
	/**
	 * 코드명으로 조회
	 * @param codeName
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Page<CommonCodeDto> findByCodeNameContaining(String codeName   , Pageable pageable) throws Exception;
	/**
	 * 상위코드값을 조회
	 * @param upperCode
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Page<CommonCodeDto> findByUpperCode(String upperCode , Pageable pageable) throws Exception;
	
	@Query( value="select c.* from common_code c where c.code = :upperCode", nativeQuery=true)
	public CommonCodeDto findByCodeInfo(@Param("upperCode") String upperCode);
	
	/**
	 * 중복된 값이 있는지 확인 하기 위한 count
	 * @param codeName
	 * @return
	 * @throws Exception
	 */
	@Query( value="select count(c.code) from common_code c where c.code_name = :codeName" , nativeQuery=true)
	public int findByCodeName(@Param("codeName")String codeName) throws Exception;
}
