/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 7.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.repository.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dongbu.farm.common.repository.RepositoryException;
import com.dongbu.farm.common.repository.dao.IRepositoryDao;
import com.dongbu.farm.common.repository.manager.IRepositoryManager;
import com.dongbu.farm.common.repository.model.UploadFile;

public class RepositoryManagerImpl implements IRepositoryManager {
	private IRepositoryDao repositoryDao;

	/**
	 * @return the repositoryDao
	 */
	public IRepositoryDao getRepositoryDao() {
		return repositoryDao;
	}

	/**
	 * @param repositoryDao the repositoryDao to set
	 */
	public void setRepositoryDao(IRepositoryDao repositoryDao) {
		this.repositoryDao = repositoryDao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dongbu.farm.common.repository.manager.IRepositoryManager#saveFile(java.util.List)
	 */
	public void saveFile(List<UploadFile>  listFile) throws RepositoryException {
		repositoryDao.saveFile(listFile);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dongbu.farm.common.repository.manager.IRepositoryManager#saveFile(java.util.List, java.util.List)
	 */
	@Override
	public void saveFile(List<UploadFile> listFile, List<UploadFile> delList) throws RepositoryException {

		Map<String , Object> deleteMap = new  HashMap<String , Object>();
		
		if(delList.size() > 0){
			for (UploadFile uploadFile : delList) {
				//deleteMap.put("groupid", uploadFile.getGroup_id());
				//deleteMap.put("deleteType", "group");
				deleteMap.put("fileid", uploadFile.getFile_id());
				deleteMap.put("deleteType", "file");
				this.deleteFile(deleteMap);
			}
		}
		
		repositoryDao.saveFile(listFile);
	}
	
	public UploadFile getFileByFileId(String fileId) throws RepositoryException {
		
		return this.repositoryDao.getFileByFileId(fileId);
	}

	@Override
	public List<UploadFile> getFileGroupList(String groupId)	throws RepositoryException {
		return this.repositoryDao.getFileGroupList(groupId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dongbu.farm.common.repository.manager.IRepositoryManager#deleteFile(java.util.Map)
	 */
	@Override
	public int deleteFile(Map<String, Object> deleteMap) throws RepositoryException {
		
		return this.repositoryDao.deleteFile(deleteMap);
	}
	
}
