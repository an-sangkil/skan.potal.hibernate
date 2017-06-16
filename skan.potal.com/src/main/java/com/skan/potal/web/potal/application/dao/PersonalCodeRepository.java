package com.skan.potal.web.potal.application.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.skan.potal.web.potal.accountbook.dto.PersonalCode;


public interface PersonalCodeRepository extends JpaRepository<PersonalCode, String> , QueryDslPredicateExecutor<PersonalCode> {
	public PersonalCode findByCode(String code) throws Exception;
	public List<PersonalCode> findByUpperCode(String upperCode) throws Exception;
	
	/**
	 * 코드명으로 조회
	 * @param codeName
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Page<PersonalCode> findByCodeNameContaining(String codeName   , Pageable pageable) throws Exception;
	/**
	 * 상위코드값을 조회
	 * @param upperCode
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Page<PersonalCode> findByUpperCode(String upperCode , Pageable pageable) throws Exception;
	
	@Query( value="select c.* from PersonalCode c where c.code = :upperCode", nativeQuery=true)
	public PersonalCode findByCodeInfo(@Param("upperCode") String upperCode);

}
