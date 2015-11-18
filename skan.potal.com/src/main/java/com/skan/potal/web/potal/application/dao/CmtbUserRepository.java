package com.skan.potal.web.potal.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skan.potal.web.potal.application.model.CmtbUser;

public interface CmtbUserRepository extends JpaRepository<CmtbUser, String> {

}
