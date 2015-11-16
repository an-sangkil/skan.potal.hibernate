package com.skan.potal.hibernate.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skan.potal.hibernate.application.model.Team;

public interface TeamDAO extends JpaRepository<Team, Long> {

}
