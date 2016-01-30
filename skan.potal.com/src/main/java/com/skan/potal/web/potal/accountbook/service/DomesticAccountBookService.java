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
		return null;
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 수출비용 계산
	 * @return 
	 */
	public BigDecimal calculatorExpense() {
		return null;
		// TODO Auto-generated method stub
	}
	
}
