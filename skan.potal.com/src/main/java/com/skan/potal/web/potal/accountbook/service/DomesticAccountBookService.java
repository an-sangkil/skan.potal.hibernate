package com.skan.potal.web.potal.accountbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skan.potal.web.potal.accountbook.repository.DomesticAccountBookRepository;

@Service
public class DomesticAccountBookService {

	@Autowired DomesticAccountBookRepository domesticAccountBookRepository;
	
}
