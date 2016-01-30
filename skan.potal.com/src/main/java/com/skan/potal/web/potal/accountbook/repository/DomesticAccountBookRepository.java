package com.skan.potal.web.potal.accountbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.potal.web.potal.accountbook.dto.DomesticAccountBook;

public interface DomesticAccountBookRepository extends JpaRepository<DomesticAccountBook, String>,QueryDslPredicateExecutor<DomesticAccountBook> {

}
