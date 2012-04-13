/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 18.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.dongbu.farm.common.repository.model.UploadFile;

public class RepositoryUtils {
	
	private static Logger logger  = Logger.getLogger(RepositoryUtils.class);
	
	/**
	 * Server in file write
	 * @param listFile		: 파일 정보<list>
	 * @throws IOException
	 */
	public static void fileWrite(List<UploadFile> listFile) throws IOException{
		// 파일 시스템에 파일 작성
		for (UploadFile uploadFile : listFile) {
			//RepositoryUtil.writeFile(uploadFile);
			InputStream is  = null;
			OutputStream os = null;
			try{
				File uploadDir = new File(uploadFile.getFile_path());
				if(!uploadDir.exists()){														//폴더가없으면
					uploadDir.mkdir();															//새로생성
				}
				
				is = new BufferedInputStream(uploadFile.getInputStream());						//Stream 값을 읽어온다
				os = new BufferedOutputStream(new FileOutputStream(uploadFile.getFile_path() + "/" + uploadFile.getFile_id())); //저장될 위치 선정
				
				int redyBytes = 0;
				//byte[] buffer = new byte[8192];
				//while((redyBytes = is.read(buffer,0,8192)) != -1){							
				while((redyBytes = is.read()) != -1){								
					os.write(redyBytes);														//해당폴더에 getFileId로 파일을 생성한다.
				}
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				is.close();																		//InputStream  종료
				os.close();																		//OutputStream 종료
			}
		}
	}
	
	/**
	 * 시스템 메뉴 별로 파일이 저장되는 공간을 따로 구성하고자 할경우 사용
	 * @param listFile
	 * @param fileType 기본폴더에 하위 폴더이름
	 * @throws IOException
	 */
	public static void fileWrite(List<UploadFile> listFile , String folderName) throws IOException{
		// 파일 시스템에 파일 작성
		for (UploadFile uploadFile : listFile) {
			//RepositoryUtil.writeFile(uploadFile);
			InputStream is  = null;
			OutputStream os = null;
			
			String saveDirectory= (uploadFile.getFile_path()+folderName).trim();
			
			try{
				File uploadDir = new File(saveDirectory);
				if(!uploadDir.exists()){														//폴더가없으면
					uploadDir.mkdir();															//새로생성
				}
				
				is = new BufferedInputStream(uploadFile.getInputStream());						//Stream 값을 읽어온다
				os = new BufferedOutputStream(new FileOutputStream(saveDirectory + "/" + uploadFile.getFile_id())); //저장될 위치 선정
				
				int redyBytes = 0;
				//byte[] buffer = new byte[8192];
				//while((redyBytes = is.read(buffer,0,8192)) != -1){							
				while((redyBytes = is.read()) != -1){								
					os.write(redyBytes);														//해당폴더에 getFileId로 파일을 생성한다.
				}
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				is.close();																		//InputStream  종료
				os.close();																		//OutputStream 종료
			}
		}
	}
	
	/**
	 * 파일시스템에서 실제 파일삭제
	 * @param file_path 파일패스
	 * @param file_id	파일 uuid 값
	 * @throws IOException
	 */
	public static void deleteFile(String file_path, String file_id) throws IOException{
		try {
			File delFile = new File(file_path + "/" + file_id);
			
			logger.info("\n\n\n 파일디렉토리 : " + file_path + "/" + file_id + "\n\n\n");
			
			if(delFile.delete()){
				logger.info("\n\n\n 삭제완료 \n\n\n");
			}else{
				logger.info("\n\n\n 삭제실패 \n\n\n");
			}
			
		} catch (SecurityException  e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 파일 이동
	 * @param file_path 실제 파일패스
	 * @param file_id		  :
	 * @param wastebasketPath : 
	 * @throws IOException 
	 */
	public static void moveFile(String file_path, String file_id, String originalFileName, String copyDirectoryPath) throws IOException{
		InputStream  is = null;
		OutputStream os = null;

		
		
		try {
			
			//복사될 디렉토리명
			File copyDirectory = new File(copyDirectoryPath);
			
			
			//복사할 디렉토리가 없는경우 디렉토리 생성.
			if(!copyDirectory.exists()){
				copyDirectory.mkdirs();
			}
			
			
			//실제파일을 스트림형태로 읽어온다.
			is = new BufferedInputStream(new FileInputStream(new File(file_path  + "/" + file_id)));
			
			
			//1. 복사된 파일이 저장될 공간과 파일이름을 설정 
			//2. 파일명은 실제 파일명으로 변경한다. 중복되는걸 방지하기 위해 파일앞에 년도 설정
			os = new BufferedOutputStream(new FileOutputStream(new File(copyDirectoryPath + "/" + DateUtils.getDateTimeString(new Date()) + originalFileName)));
			
			int b;
			while((b=is.read()) != -1){
				os.write(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			is.close();
			os.close();
		}
		
		//원본 파일삭제
		deleteFile(file_path, file_id);
	}
}
