/**
 * <pre>
 * Class Name  : DomesticAccountBookConvert.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 2. 13.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 2. 13.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.accountbook.converter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.skan.potal.web.potal.accountbook.dto.DomesticAccountBook;
import com.skan.potal.web.potal.accountbook.dto.PersonalCode;
import com.skan.potal.web.potal.accountbook.model.DomesticAccountBookModel;

/**
 * @author ahn
 *
 */
@Component
public class DomesticAccountBookConvert extends ConvertObject<DomesticAccountBookModel, DomesticAccountBook> {
	
//	private DomesticAccountBookModel  domesticAccountBookModel;
//	private DomesticAccountBook domesticAccountBook;
//	
//	public DomesticAccountBookConvert(@Qualifier DomesticAccountBookModel  domesticAccountBookModel ,@Qualifier DomesticAccountBook domesticAccountBook) {
//		this.domesticAccountBookModel = domesticAccountBookModel;
//		this.domesticAccountBook = domesticAccountBook;
//	}

	/* (non-Javadoc)
	 * @see com.skan.potal.web.potal.accountbook.converter.ConvertObject#ConvertToModel(java.lang.Object, java.lang.Object)
	 */
	@Override
	public DomesticAccountBookModel ConvertToModel(DomesticAccountBookModel model, DomesticAccountBook dto) {
		
		model.setAmount(dto.getAmount());
		model.setBreakdown(dto.getBreakdown());
		model.setBusinessDay(dto.getBusinessDay());
		model.setDabMngNo(dto.getDabMngNo());
		model.setDetailContents(dto.getDetailContents());
		model.setTypeCode(dto.getTypePersonalCode().getCode());

		return model;
	}

	/* (non-Javadoc)
	 * @see com.skan.potal.web.potal.accountbook.converter.ConvertObject#ConvertToDTO(java.lang.Object, java.lang.Object)
	 */
	@Override
	public DomesticAccountBook ConvertToDTO(DomesticAccountBookModel model, DomesticAccountBook dto) {
		
		dto.setAmount(model.getAmount());
		dto.setBreakdown(model.getBreakdown());
		dto.setBusinessDay(model.getBusinessDay());
		dto.setDabMngNo(model.getDabMngNo());
		dto.setDetailContents(model.getDetailContents());
		
		PersonalCode personalCode = new PersonalCode();
		personalCode.setCode(model.getTypeCode());
		dto.setTypePersonalCode(personalCode); 

		return dto;
	}



}
