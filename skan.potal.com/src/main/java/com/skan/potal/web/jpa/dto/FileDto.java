package com.skan.tms.web.jpa.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Description : 
 * @author skan
 * @since 2016. 10. 11.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table(name="files")
public class FileDto {
	
	@Id
	@Column(name="file_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long fileSeq;
	
	@Column(name="file_group_id" ,length=36)
	private String fileGroupId;
	
	String fileUUID;
	String groupCode;
	String realFileName;
	String aliasFileName;
	String prefix;
	Long   fileSize;
	String saveFilePath;
	String fileContentType;
	String creator;
	Date   creationTime;

	
	public String getFileUUID() {
		return fileUUID;
	}

	public void setFileUUID(String fileUUID) {
		this.fileUUID = fileUUID;
	}

	public String getFileGroupId() {
		return fileGroupId;
	}

	public void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}

	public Long getFileSeq() {
		return fileSeq;
	}

	public void setFileSeq(Long fileSeq) {
		this.fileSeq = fileSeq;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getRealFileName() {
		return realFileName;
	}

	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}

	public String getAliasFileName() {
		return aliasFileName;
	}

	public void setAliasFileName(String aliasFileName) {
		this.aliasFileName = aliasFileName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getSaveFilePath() {
		return saveFilePath;
	}

	public void setSaveFilePath(String saveFilePath) {
		this.saveFilePath = saveFilePath;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	} 
	
	
	/**
	 * 파일 업로드  초기화리스트에 사용되는 json 객체 
	 * @param fileDtos
	 * @return
	 */
	public String initialFileConvertJson (List<FileDto> fileDtos){
		
		@SuppressWarnings("unused")
		@JsonIgnoreProperties(ignoreUnknown=true)
		class FileJson{
			// Reference : http://docs.fineuploader.com/branch/master/features/session.html
			// *name: String - Name of the file.
			// *uuid: String - UUID of the file.
			//  size: Number - Size of the file, in bytes.
			//  deleteFileEndpoint: String - Endpoint for the associated delete file request. If omitted, the deleteFile.endpoint is used.
			//  deleteFileParams: Object - Parameters to send along with the associated delete file request. If omitted, deleteFile.params is used.
			//  thumbnailUrl: String - URL of an image to display next to the file.
			// *s3Key: String - Key of the file in your S3 bucket. Only required if using Fine Uploader S3.
			// *s3Bucket: String - Name of the bucket where the file is stored in S3. Only required if using Fine Uploader S3 and if the bucket cannot be determined by examining the endpoint URL (such as when routing through a CDN).
			// *blobName: String - Name of the file in your Azure Blob Storage container. Only required if using Fine Uploader Azure.
			String name,uuid, size , deleteFileEndPoint; 
			String thumbnailUrl,s3Key, blobName;
			Object deleteFileParams;
			
			/**
			 * 
			 * @param name
			 * @param uuid
			 * @param size
			 * @param deleteFileEndPoint
			 * @param deleteFileParams
			 * @param thumbnailUrl
			 */
			
			public FileJson(String name, String uuid, String size , String deleteFileEndPoint, Object deleteFileParams, String thumbnailUrl
					,String s3Key, String blobName){
			}

			public FileJson(String name, String uuid, String size, String deleteFileEndPoint, Object deleteFileParams, String thumbnailUrl) {
				super();
				this.name = name;
				this.uuid = uuid;
				this.size = size;
				this.deleteFileEndPoint = deleteFileEndPoint;
				this.deleteFileParams = deleteFileParams;
				this.thumbnailUrl = thumbnailUrl;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getUuid() {
				return uuid;
			}

			public void setUuid(String uuid) {
				this.uuid = uuid;
			}

			public String getSize() {
				return size;
			}

			public void setSize(String size) {
				this.size = size;
			}

			public String getDeleteFileEndPoint() {
				return deleteFileEndPoint;
			}

			public void setDeleteFileEndPoint(String deleteFileEndPoint) {
				this.deleteFileEndPoint = deleteFileEndPoint;
			}

			public String getThumbnailUrl() {
				return thumbnailUrl;
			}

			public void setThumbnailUrl(String thumbnailUrl) {
				this.thumbnailUrl = thumbnailUrl;
			}

			public String getS3Key() {
				return s3Key;
			}

			public void setS3Key(String s3Key) {
				this.s3Key = s3Key;
			}

			public String getBlobName() {
				return blobName;
			}

			public void setBlobName(String blobName) {
				this.blobName = blobName;
			}

			public Object getDeleteFileParams() {
				return deleteFileParams;
			}

			public void setDeleteFileParams(Object deleteFileParams) {
				this.deleteFileParams = deleteFileParams;
			}
		}
		
		List<FileJson> filejsons = new ArrayList<>();
		fileDtos.forEach(fileDto ->{
			
			Map<String, Object> deleteFileParams = new HashMap<>();
			deleteFileParams.put("fileGroupId", fileDto.getFileGroupId());
			deleteFileParams.put("saveFilePath", fileDto.getSaveFilePath());
			deleteFileParams.put("aliasFileName", fileDto.getAliasFileName());
			
			filejsons.add(new FileJson(
					fileDto.getRealFileName()
					,fileDto.getFileUUID()
					,fileDto.getFileSize().toString()
					,null
					,deleteFileParams
					,""));
		});
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		try {
			return mapper.writeValueAsString(filejsons);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	
}
