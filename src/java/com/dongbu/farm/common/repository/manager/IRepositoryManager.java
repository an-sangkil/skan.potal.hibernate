/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 7.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.repository.manager;

import java.util.List;
import java.util.Map;

import com.dongbu.farm.common.repository.RepositoryException;
import com.dongbu.farm.common.repository.model.UploadFile;

public interface IRepositoryManager {
	
	/**
	 * 파일정보 DB 저장
	 * @param listFile
	 * @throws RepositoryException
	 */
	public abstract void saveFile(List<UploadFile> listFile) throws RepositoryException;
	
	/**
	 * 파일정보 DB 저장 및 선택된 파일 삭제
	 * @param listFile
	 * @param delList
	 * @throws RepositoryException
	 */
	public abstract void saveFile(List<UploadFile> listFile, List<UploadFile> delList)throws RepositoryException;
	
	/**
	 * 파일 ID 검색
	 * @param fileId
	 * @return
	 * @throws RepositoryException
	 */
	public abstract UploadFile getFileByFileId(String fileId) throws RepositoryException;
	
	/**
	 * 파일 그룹 검색
	 * @param groupId
	 * @return
	 * @throws RepositoryException
	 */
	public abstract List<UploadFile> getFileGroupList(String groupId) throws RepositoryException;
	
	/**
	 * 파일삭제 
	 * @param deleteMap
	 * @returns
	 * @throws RepositoryException
	 */
	public abstract int deleteFile(Map<String,Object> deleteMap) throws RepositoryException;

	

}
