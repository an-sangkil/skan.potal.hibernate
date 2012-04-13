/*
 * $Id: ICodeManager.java ,v 1.1 2011. 3. 25. 오후 4:43:39 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2011. 3. 25.
 * =========================================================
 * Copyright (c) 2011 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.system.code.manager;

import java.util.List;
import java.util.Map;

import com.dongbu.farm.common.tags.CodeSearchCondition;
import com.dongbu.farm.system.code.CodeException;
import com.dongbu.farm.system.code.model.Code;

public interface ICodeManager {

	public abstract Code getCodeDetail(Map<String,String> searchMap) throws CodeException;

	public abstract void writeCodeAJR(Code code)throws CodeException;
	
	public abstract int modifyCodeAJR(Code code)throws CodeException;
	
	/**
	 * 공통 그룹의 코드 리스트 검색 테그라이브러리에서 사용중
	 * @param cCondition
	 * @return
	 * @throws CodeException
	 */
	public abstract List<Code> getCodeList(CodeSearchCondition cCondition) throws CodeException;

}
