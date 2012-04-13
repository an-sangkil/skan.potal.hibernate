/*
 * $Id: CodeDaoImpl.java ,v 1.1 2011. 3. 25. 오후 4:43:01 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 25.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.system.code.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.dongbu.farm.common.tags.CodeSearchCondition;
import com.dongbu.farm.system.code.dao.ICodeDao;
import com.dongbu.farm.system.code.model.Code;

/**
 * 코드관리 다오
 * @author skan
 *
 */
@Repository
public class CodeDaoImpl implements ICodeDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;  
	
	private static String CODE_STATEMENT = "system.code.";

	@Override
	public Code getCodeDetail(Map<String,String> searchMap) throws DataAccessException {
		return (Code) this.sqlMapClientTemplate.queryForObject(CODE_STATEMENT + "getCodeDetail" , searchMap);
	}

	@Override
	public void writeCodeAJR(Code code) throws DataAccessException {
		this.sqlMapClientTemplate.insert(CODE_STATEMENT + "writeCode", code);
	}

	@Override
	public int modifyCodeAJR(Code code) throws DataAccessException {
		return this.sqlMapClientTemplate.update(CODE_STATEMENT + "modifyCode", code);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Code> getCodeList(CodeSearchCondition cCondition)
			throws DataAccessException {
		return this.sqlMapClientTemplate.queryForList(CODE_STATEMENT + "getCodeList", cCondition);
	}
}
