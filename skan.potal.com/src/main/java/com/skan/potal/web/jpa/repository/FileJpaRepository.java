package com.skan.potal.web.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.potal.web.jpa.dto.FileDto;

public interface FileJpaRepository extends JpaRepository<FileDto, Long>, QueryDslPredicateExecutor<FileDto> {

	FileDto findByfileUUID(String mainImageFileId);

}
