package com.skan.potal.hibernate.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.skan.potal.hibernate.application.model.User;

public interface UserDAO extends JpaRepository<User, Long> ,QueryDslPredicateExecutor<User>  {
}