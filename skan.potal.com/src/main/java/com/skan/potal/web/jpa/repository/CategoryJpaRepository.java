package com.skan.potal.web.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.skan.potal.web.jpa.dto.CategoryDto;

/**
 * 
 * <pre>
 * Class Name  : CategoryJpaRepository.java
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
public interface CategoryJpaRepository extends JpaRepository<CategoryDto, String> , QueryDslPredicateExecutor<CategoryDto> {
	
	public CategoryDto findByCategoryCode(String code) throws Exception;
	public List<CategoryDto> findByCategoryUpperCode(String upperCode) throws Exception;
	
	/**
	 * 코드명으로 조회
	 * @param codeName
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Page<CategoryDto> findByCategoryNameContaining(String codeName   , Pageable pageable) throws Exception;
	/**
	 * 상위코드값을 조회
	 * @param upperCode
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Page<CategoryDto> findByCategoryUpperCode(String upperCode , Pageable pageable) throws Exception;
	
	@Query( value="select c.* from category_code c where c.category_code = :upperCode", nativeQuery=true)
	public CategoryDto findByCodeInfo(@Param("upperCode") String upperCode);
	
	/**
	 * 중복된 값이 있는지 확인 하기 위한 count
	 * @param codeName
	 * @return
	 * @throws Exception
	 */
	@Query( value="select count(c.category_code) from category_code c where c.category_name = :codeName" , nativeQuery=true)
	public int findByCodeName(@Param("codeName")String codeName) throws Exception;
}
