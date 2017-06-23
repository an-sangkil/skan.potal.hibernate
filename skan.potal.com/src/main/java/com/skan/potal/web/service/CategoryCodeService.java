package com.skan.potal.web.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQuery;
import com.skan.potal.web.jpa.dto.QCategoryDto;

/**
 * Description : 카테고리 관리에서 사용하기위한 유틸성 서비스 레이어  
 * @author skan
 * @since 2016. 9. 22.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Service
public class CategoryCodeService {

	@PersistenceContext EntityManager entityManager;
	
	/**
	 * 코드를 정하기 위해 저장된 코드 값의 최대 값을 가져온다.
	 * @return
	 * @throws Exception
	 */
	public String lpadMaxCodeValue () throws Exception {

		JPAQuery<QCategoryDto> query = new JPAQuery<QCategoryDto>(entityManager);
		QCategoryDto qcmtbCode = QCategoryDto.categoryDto;
		 
		String maxCodeValue = query.from(qcmtbCode).select(qcmtbCode.categoryCode.max()).fetchOne();
		
		Long lpadMaxValue= NumberUtils.createLong(maxCodeValue == null ? "0" : maxCodeValue)+1;
		maxCodeValue = StringUtils.leftPad(lpadMaxValue.toString(), 5 , '0');
		
		return maxCodeValue;
	}
	
	
	/**
	 * 상위코드에 이어 정렬될 순서의 Max를 가져온다.  
	 * 		(추후에 순서 변경할때 사용하려고함.)
	 * @param upperCode
	 * @return
	 */
	public int maxCodeSeq(String upperCode){
		
		JPAQuery<QCategoryDto> query = new JPAQuery<>(entityManager);
		QCategoryDto qcmtbCode = QCategoryDto.categoryDto;
		query.from(qcmtbCode);
		if(upperCode != null) {
			query.where(qcmtbCode.categoryUpperCode.eq(upperCode));
			
		}
		
		int maxSeq = query.from(qcmtbCode).select(qcmtbCode.seq.max().coalesce(0)).fetchOne();

		if(maxSeq == 0 ) {
			maxSeq = 1;
		} else  {
			maxSeq = maxSeq+1;
		}
		
		return maxSeq;
	}
}
