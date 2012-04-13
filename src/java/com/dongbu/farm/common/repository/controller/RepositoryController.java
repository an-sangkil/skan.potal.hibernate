/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 6.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.repository.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.dongbu.farm.common.repository.RepositoryException;
import com.dongbu.farm.common.repository.manager.IRepositoryManager;
import com.dongbu.farm.common.repository.model.UploadFile;
import com.dongbu.farm.common.utils.RepositoryUtils;
import com.dongbu.farm.common.utils.SessionUtils;
import com.dongbu.farm.common.utils.StringUtils;
import com.dongbu.farm.common.utils.Utils;

@Component
public abstract class RepositoryController extends MultiActionController {
	
	@Autowired
	private IRepositoryManager repositoryManager;
	@Autowired
	protected CommonsMultipartResolver multipartResolver;
	
	//파일이 저장될 디폴트 디렉토리
	private String filePath;
	
	//파일이 삭제되며 이동될 디렉토리
	private String wastebasketPath;
	
	/**
	 * 파일 첨부 메소드
	 * @param request
	 * @return
	 */
	public String fileUpload(HttpServletRequest request){
		return fileUpload(request, null);
	}
	
	/**
	 * 파일 첨부 메소드
	 * @param request
	 * @param response
	 * @param groupId
	 * @return
	 */
	public String fileUpload(HttpServletRequest request, String groupId){
		return multiFileUpload(request, groupId , null , null);
	}
	
	/**
	 * multiFileUpload
	 * @param request
	 * @param response
	 */
	public String multiFileUpload(HttpServletRequest request, String groupId, String[] deleteFileList , String folderName) throws RepositoryException{
		
		
		groupId = (StringUtils.isNull(groupId, "")).equals("") ? Utils.getRandomUUID() : groupId;
		List<UploadFile> listFile = new ArrayList<UploadFile>();
		
		
		if(!this.multipartResolver.isMultipart(request)){					//Multipart로 작성성된게 아니면 Null Retuen
			return null;
		}
		
		
		
		try{
			MultipartHttpServletRequest multiPartRequest = (MultipartHttpServletRequest)request;
			Iterator<String> iFileList  = (Iterator) multiPartRequest.getFileNames();
			
			while(iFileList.hasNext()){
				String inputFileName = iFileList.next();
				MultipartFile multipartFile = multiPartRequest.getFile(inputFileName);
				if(!multipartFile.isEmpty()){
					UploadFile uploadFile = new UploadFile();
					uploadFile.setGroup_id(groupId);
					uploadFile.setFile_id(Utils.getRandomUUID());
					uploadFile.setFile_input_name(inputFileName);
					uploadFile.setOriginal_file_name(multipartFile.getOriginalFilename());			  //Real File Name
					uploadFile.setFile_path((this.getFilePath()+Utils.strValue(folderName)).trim());  //Real File is Save Path	
					uploadFile.setFile_size(multipartFile.getSize());								  //File Size
					uploadFile.setContent_type(multipartFile.getContentType());						  //File ContentType
					uploadFile.setInputStream(multipartFile.getInputStream());						  //InputStream
					uploadFile.setCreater(SessionUtils.getUserID(request));
					uploadFile.setCreater_name(SessionUtils.getUserName(request));
					uploadFile.setCreatedtime(new Date());											  //Save Date
					listFile.add(uploadFile);
				}
			}
			
			List<UploadFile> delList = new ArrayList<UploadFile>();

			// 삭제할 파일 Get , 파라미터로 받는 변수 무시함.(Yeo)
			//deleteFileList = request.getParameterValues("deleteFileId");

			if (deleteFileList != null) {
				for (int i = 0; i < deleteFileList.length; i++) {
					delList.add(this.repositoryManager.getFileByFileId(deleteFileList[i]));
				}
			}
			
			
			
			// DB에 파일 내용 저장
			//repositoryManager().saveFile(listFile, delFileList);
			this.repositoryManager.saveFile(listFile , delList);
			
			
			
			// 파일 시스템에 파일 작성
			RepositoryUtils.fileWrite(listFile);
			
			
			
			//파일스스템에서 휴지통폴더로 파일이동
			for (UploadFile uploadFile : delList) {
				RepositoryUtils.moveFile(uploadFile.getFile_path(), uploadFile.getFile_id(), uploadFile.getOriginal_file_name() , wastebasketPath);
			}
			
		} catch (MultipartException e) {
			logger.error(e);
			throw new RepositoryException(e);
		} catch (IOException e) {
			logger.error(e);
			throw new RepositoryException(e);
		} 
		
		
		
		return groupId; 
		
	}
	
	
	/**
	 * Image Upload
	 * @param request
	 * @return
	 * @throws RepositoryException
	 */
	@SuppressWarnings("unchecked")
	public String imageUpload(HttpServletRequest request) throws RepositoryException {
		List<UploadFile> listFile = new ArrayList<UploadFile>();
		String fileId = UUID.randomUUID().toString();												//GroupId 생성을 위해 UUID 생성
		
		if (!multipartResolver.isMultipart(request)){
			return null;//throw new FileUploadException();
		}
		
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  //request Casting 
			Iterator<String> iFileList = (Iterator) multipartRequest.getFileNames();				

			while (iFileList.hasNext()) {
				String inputFileName = (String) iFileList.next();
				MultipartFile multiFile = multipartRequest.getFile(inputFileName);
				if (!multiFile.isEmpty()) {
					UploadFile uploadFile = new UploadFile();
					uploadFile.setGroup_id("IMG" + fileId);										  //GroupId ('IMG' + UUID) 
					uploadFile.setFile_id(fileId);												  //FILE ID (UUID)
					uploadFile.setFile_input_name(inputFileName);									  
					uploadFile.setOriginal_file_name(multiFile.getOriginalFilename());				  //Real File Name
					uploadFile.setFile_path(this.getFilePath());									  //Real File is Save Path	
					uploadFile.setFile_size(multiFile.getSize());								  //File Size
					uploadFile.setContent_type(multiFile.getContentType());						  //File ContentType
					uploadFile.setInputStream(multiFile.getInputStream());						  //InputStream
					uploadFile.setCreater(SessionUtils.getUserID(request));
					uploadFile.setCreater_name(SessionUtils.getUserName(request));
					uploadFile.setCreatedtime(new Date());		  //Save Date
					listFile.add(uploadFile);
				}
			}

			// DB에 파일 내용 저장
			this.repositoryManager.saveFile(listFile);
			// 파일을 시스템에 쓰기,
			RepositoryUtils.fileWrite(listFile);
			
		} catch (MultipartException e) {
			logger.error(e);
			throw new RepositoryException(e);
		} catch (IOException e) {
			logger.error(e);
			throw new RepositoryException(e);
		} 

		return fileId;
	}
	
	

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}


	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the wastebasketPath
	 */
	public String getWastebasketPath() {
		return wastebasketPath;
	}

	/**
	 * @param wastebasketPath the wastebasketPath to set
	 */
	public void setWastebasketPath(String wastebasketPath) {
		this.wastebasketPath = wastebasketPath;
	}
	
}
