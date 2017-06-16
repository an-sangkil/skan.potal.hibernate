package com.skan.potal.web.potal.application.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQuery;
import com.skan.potal.web.potal.accountbook.dto.QPersonalCode;

@Service
public class PersonalCodeService {
	
	@PersistenceContext EntityManager entityManager;
	
	/**
	 * 코드를 정하기 위해 저장된 코드 값의 최대 값을 가져온다.
	 * @return
	 * @throws Exception
	 */
	public String lpadMaxCodeValue () throws Exception {

		JPAQuery query = new JPAQuery(entityManager);
		QPersonalCode qcmtbCode = QPersonalCode.personalCode;
		String maxCodeValue = (String) query.select(qcmtbCode.code.max()).from(qcmtbCode).fetchOne();
				//.where(qcmtbCode.cmtbCodeId.code.eq(code))
				
		
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
	public Integer maxCodeSeq(String upperCode){
		
		JPAQuery query = new JPAQuery(entityManager);
		QPersonalCode qcmtbCode = QPersonalCode.personalCode;
		query.from(qcmtbCode);
		if(upperCode != null) {
			query.where(qcmtbCode.upperCode.eq(upperCode));
			
		}
		
		Integer maxSeq = (Integer) query.select(qcmtbCode.codeSeq.max()).from(qcmtbCode).fetchOne();

		if(maxSeq == null ) {
			maxSeq = 1;
		} else  {
			maxSeq = maxSeq+1;
		}
		
		return maxSeq;
	}
}
