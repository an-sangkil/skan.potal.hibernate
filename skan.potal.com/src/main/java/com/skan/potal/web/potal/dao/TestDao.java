package com.skan.potal.web.potal.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * SAMPLE
 * @author ask
 *
 */
//@Repository
@Deprecated
public class TestDao extends SqlSessionDaoSupport {
	
	public List<String> testSelect () {
		return super.getSqlSession().selectList("select");
	}
}
