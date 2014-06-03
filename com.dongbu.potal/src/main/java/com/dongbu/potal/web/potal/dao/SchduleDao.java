package com.dongbu.potal.web.potal.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class SchduleDao extends SqlSessionDaoSupport {
	
	public List<String> testSelect () {
		return super.getSqlSession().selectList("select");
	}
}
