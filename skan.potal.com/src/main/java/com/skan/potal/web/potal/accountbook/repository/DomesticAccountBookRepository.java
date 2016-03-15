package com.skan.potal.web.potal.accountbook.repository;

import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.potal.web.potal.accountbook.dto.DomesticAccountBook;

public interface DomesticAccountBookRepository extends JpaRepository<DomesticAccountBook, String>,QueryDslPredicateExecutor<DomesticAccountBook> {
	
	public List<DomesticAccountBook> findByBusinessDayBetweenOrderByBusinessDayDesc(@Temporal(value=TemporalType.DATE)java.util.Date from,@Temporal(value=TemporalType.DATE) java.util.Date to);
}
