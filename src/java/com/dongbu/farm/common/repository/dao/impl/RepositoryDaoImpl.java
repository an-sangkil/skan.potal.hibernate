/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 7.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.repository.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.dongbu.farm.common.repository.dao.IRepositoryDao;
import com.dongbu.farm.common.repository.model.UploadFile;

public class RepositoryDaoImpl extends SqlMapClientTemplate implements IRepositoryDao {
	
	/*
	 * (non-Javadoc)
	 * @see com.dongbu.farm.common.repository.dao.IRepositoryDao#getFileByFileId(java.lang.String)
	 */
	public UploadFile getFileByFileId(String fileId) throws DataAccessException {
		
		Map<String, String> searchMap = new HashMap<String,String>();
		searchMap.put("fileId", fileId);
		return (UploadFile)queryForObject("com.dongbu.farm.common.repository.dao.xml.Repository.getFileByFileId", searchMap);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dongbu.farm.common.repository.dao.IRepositoryDao#saveFile(java.util.List)
	 */
	public void saveFile(List<UploadFile>  listFile) throws DataAccessException {
		for(UploadFile uploadFile : listFile){
			insert("com.dongbu.farm.common.repository.dao.xml.Repository.saveFile", uploadFile);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UploadFile> getFileGroupList(String groupId) throws DataAccessException {
		
		return queryForList("com.dongbu.farm.common.repository.dao.xml.Repository.getFileGroupList", groupId);
	}

	@Override
	public int deleteFile(Map<String, Object> deleteMap) throws DataAccessException {
		return delete("com.dongbu.farm.common.repository.dao.xml.Repository.deleteFile" , deleteMap);
	}

}
