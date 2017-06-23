package com.skan.tms.web.jpa.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Description : 게시판 관리 
 * @author skan
 * @since 2016. 10. 6.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */

@Entity
@Table(name="boardmanagement")
public class BoardManagementDto {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="board_id")
	private Long boardId;
	
	/**
	 * 게시판 타입 (이미지, 동영상, 게시글)
	 * 기본 타입설정. 
	 */
	@Column(name="board_type", length=5)
	private String boardType;
	
	/**
	 * 게시판 이름
	 */
	@NotEmpty
	@Column(name="board_name", length=256)
	private String boardName;
	
	/**
	 * 게시판 설명
	 */
	@Column(name="board_comments", length=512)
	private String boardComments;
	
	/**
	 * 파일업로드 사용여부 
	 */
	@Column(name="fileUpload_permit")
	private boolean fileUploadPermit;
	
	/**
	 * 답글 사용여부
	 */
	@Column(name="reply_permit")
	private boolean replyPermit;
	
	/**
	 * 비밀글 사용여부
	 */
	@Column(name="secret_permit")
	private boolean secretPermit;
	
	/**
	 * 공지사항 사용여부
	 */
	@Column(name="notice_permit")
	private boolean noticePermit;
	
	@Column(length=32, updatable=false)
	private String creator;
	
	@Column(name="creation_time" , updatable=false)
	private Date creationTime;
	
	/////////////////////////////////////
	// Association
	////////////////////////////////////
	@OneToMany(mappedBy="boardManagement", fetch=FetchType.LAZY ,orphanRemoval=true)
	private List<BoardDto> boardDtos;

	/////////////////////////////////////
	// Getter,Setter
	////////////////////////////////////
	public String getBoardComments() {
		return boardComments;
	}

	public void setBoardComments(String boardComments) {
		this.boardComments = boardComments;
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public boolean isFileUploadPermit() {
		return fileUploadPermit;
	}

	public void setFileUploadPermit(boolean fileUploadPermit) {
		this.fileUploadPermit = fileUploadPermit;
	}

	public boolean isReplyPermit() {
		return replyPermit;
	}

	public void setReplyPermit(boolean replyPermit) {
		this.replyPermit = replyPermit;
	}
	
	public boolean isSecretPermit() {
		return secretPermit;
	}

	public void setSecretPermit(boolean secretPermit) {
		this.secretPermit = secretPermit;
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

	public List<BoardDto> getBoardDtos() {
		return boardDtos;
	}

	public void setBoardDtos(List<BoardDto> boardDtos) {
		boardDtos.clear();
		this.boardDtos = boardDtos;
	}

	public boolean isNoticePermit() {
		return noticePermit;
	}

	public void setNoticePermit(boolean noticePermit) {
		this.noticePermit = noticePermit;
	}
}
