package com.skan.tms.mobile.web.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQuery;
import com.skan.tms.web.jpa.dto.QCommonCodeDto;

/**
 * Description : 코드 관리에서 사용하기위한 유틸성 서비스 레이어   
 * @author skan
 * @since 2016. 9. 22.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Service
public class CommonCodeService {

	@PersistenceContext EntityManager entityManager;
	
	/**
	 * 코드를 정하기 위해 저장된 코드 값의 최대 값을 가져온다.
	 * @return
	 * @throws Exception
	 */
	public String lpadMaxCodeValue () throws Exception {

		JPAQuery<QCommonCodeDto> query = new JPAQuery<QCommonCodeDto>(entityManager);
		QCommonCodeDto qcmtbCode = QCommonCodeDto.commonCodeDto;
		 
		String maxCodeValue = query.from(qcmtbCode).select(qcmtbCode.code.max()).fetchOne();
		
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
		
		JPAQuery<QCommonCodeDto> query = new JPAQuery<>(entityManager);
		QCommonCodeDto qcmtbCode = QCommonCodeDto.commonCodeDto;
		query.from(qcmtbCode);
		if(upperCode != null) {
			query.where(qcmtbCode.upperCode.eq(upperCode));
		}
		
		//coalesce > sql isnull 기능
		int maxSeq = query.from(qcmtbCode).select(qcmtbCode.codeSeq.max().coalesce(0)).fetchOne();

		if(maxSeq == 0 ) {
			maxSeq = 1;
		} else  {
			maxSeq = maxSeq+1;
		}
		
		return maxSeq;
	}
}
