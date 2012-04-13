/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 7.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.repository.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.dongbu.farm.common.repository.model.UploadFile;

public interface IRepositoryDao {
	
	/**
	 * 
	 * @param listFile
	 * @throws DataAccessException
	 */
	public void saveFile(List<UploadFile>  listFile) throws DataAccessException;

	/**
	 * 
	 * @param fileId
	 * @return
	 * @throws DataAccessException
	 */
	public UploadFile getFileByFileId(String fileId) throws DataAccessException;
	
	/**
	 * 
	 * @param groupId
	 * @return
	 */
	public List<UploadFile> getFileGroupList(String groupId) throws DataAccessException;
	
	
	/**
	 * 
	 * @param deleteMap
	 * @return
	 * @throws DataAccessException
	 */
	public int deleteFile(Map<String, Object> deleteMap)throws DataAccessException;

}
