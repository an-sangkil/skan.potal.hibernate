package com.skan.potal.mybatis.repository;

import java.util.List;

import com.skan.potal.hibernate.application.model.User;

public interface UserMapper {
	
	public List<User> findAllUser() throws Exception;
}
