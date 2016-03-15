package com.skan.potal.web.potal.accountbook.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skan.potal.web.potal.accountbook.repository.DomesticAccountBookRepository;

@Service
public class DomesticAccountBookService {

	@Autowired DomesticAccountBookRepository domesticAccountBookRepository;
	
	/**
	 * 수입 비용계산
	 * @return 
	 */
	public BigDecimal calculatorIncome() {
		BigDecimal bigDecimal = new BigDecimal("1");
		
		return bigDecimal;
		
	}
	
	/**
	 * 수출비용 계산
	 * @return 
	 */
	public BigDecimal calculatorExpense() {
		BigDecimal bigDecimal = new BigDecimal("1");
		
		return bigDecimal;
	}
	
}
