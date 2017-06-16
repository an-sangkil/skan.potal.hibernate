package com.skan.tms.web.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.tms.web.jpa.dto.FileDto;

public interface FileJpaRepository extends JpaRepository<FileDto, Long>, QueryDslPredicateExecutor<FileDto> {

	FileDto findByfileUUID(String mainImageFileId);

}
