/*
 * $Id: CodeManagerImpl.java ,v 1.1 2011. 3. 25. 오후 4:43:59 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 25.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.system.code.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongbu.farm.common.tags.CodeSearchCondition;
import com.dongbu.farm.system.code.CodeException;
import com.dongbu.farm.system.code.dao.ICodeDao;
import com.dongbu.farm.system.code.manager.ICodeManager;
import com.dongbu.farm.system.code.model.Code;

/**
 * 코드관리 메니저
 * @author skan
 *
 */
@Service
public class CodeManagerImpl implements ICodeManager{

	@Autowired
	private ICodeDao codeDaoImpl;
	
	@Override
	public Code getCodeDetail(Map<String,String> searchMap) throws CodeException {
		
		return this.codeDaoImpl.getCodeDetail(searchMap);
	}

	@Override
	public void writeCodeAJR(Code code) throws CodeException {
		this.codeDaoImpl.writeCodeAJR(code);
	}

	@Override
	public int modifyCodeAJR(Code code) throws CodeException {
		return this.codeDaoImpl.modifyCodeAJR(code);
	}

	@Override
	public List<Code> getCodeList(CodeSearchCondition cCondition)
			throws CodeException {
		
		return this.codeDaoImpl.getCodeList(cCondition);
	}

}
