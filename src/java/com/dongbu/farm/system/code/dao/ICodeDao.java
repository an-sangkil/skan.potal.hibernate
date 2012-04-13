/*
 * $Id: ICodeDao.java ,v 1.1 2011. 3. 25. 오후 4:42:40 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 25.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.system.code.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.dongbu.farm.common.tags.CodeSearchCondition;
import com.dongbu.farm.system.code.model.Code;

public interface ICodeDao {

	public abstract Code getCodeDetail(Map<String,String> searchMap) throws DataAccessException;

	public abstract void writeCodeAJR(Code code)throws DataAccessException;

	public abstract int modifyCodeAJR(Code code)throws DataAccessException;
	
	/**
	 * 공통 그룹의 코드 리스트 검색 테그라이브러리에서 사용중
	 * @param cCondition
	 * @return
	 * @throws DataAccessException
	 */
	public abstract List<Code> getCodeList(CodeSearchCondition cCondition)throws DataAccessException;

}
