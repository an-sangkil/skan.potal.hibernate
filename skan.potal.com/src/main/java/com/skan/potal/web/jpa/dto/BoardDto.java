package com.skan.potal.web.jpa.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Description : 게시판 공용 컨텐츠 
 * @author skan
 * @since 2016. 10. 6.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table(name="board")
public class BoardDto {
	
	@Embeddable
	public static class BoardDtoPK implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -1243678608824377116L;

		public BoardDtoPK() {
		}
		public BoardDtoPK(Long boardId, Long seq) {
			super();
			this.boardId = boardId;
			this.seq = seq;
		}
		
		@Column(name="board_id")
		private Long boardId;
		
		// MAX+1 증가
		@Column
		private Long seq;

		public Long getBoardId() {
			return boardId;
		}

		public void setBoardId(Long boardId) {
			this.boardId = boardId;
		}

		public Long getSeq() {
			return seq;
		}

		public void setSeq(Long seq) {
			this.seq = seq;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (boardId ^ (boardId >>> 32));
			result = prime * result + (int) (seq ^ (seq >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BoardDtoPK other = (BoardDtoPK) obj;
			if (boardId != other.boardId)
				return false;
			if (seq != other.seq)
				return false;
			return true;
		}

	}
	
	@EmbeddedId
	private BoardDtoPK boardDtoPK;
	
	/**
	 * 제목
	 */
	@Column(length=256)
	@NotEmpty
	private String subject;
	
	/**
	 * 내용
	 */
	@Basic(fetch=FetchType.LAZY)
	@Lob
	@Column
	@NotEmpty
	private byte[] contents;
	
	/**
	 * 파일 그룹 아이디
	 */
	@Column(name="fileGroupId" ,length=36)
	private String fileGroupId;
	
	@Column
	private boolean notice;
	
	@Column
	private boolean secret;

	
	@Column(length=32, updatable=false)
	private String creator;
	
	@Column(name="creation_time" , updatable=false)
	private Date creationTime;
	
	@Column(length=32)
	private String modifier;
	
	@Column(name="modified_time")
	private Date modifiedTime;
	
	/////////////////////////////////////
	//Association
	/////////////////////////////////////
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="board_id", insertable=false, updatable=false)
	private BoardManagementDto boardManagement;
	
	
	public String getFileGroupId() {
		return fileGroupId;
	}

	public void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}

	public BoardDtoPK getBoardDtoPK() {
		return boardDtoPK;
	}

	public void setBoardDtoPK(BoardDtoPK boardDtoPK) {
		this.boardDtoPK = boardDtoPK;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContents() {
		
		if(contents != null) {
			return new String(contents);
		}
		return null;
	}

	public void setContents(byte[] contents) {
		this.contents = contents;
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

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public BoardManagementDto getBoardManagement() {
		return boardManagement;
	}

	public void setBoardManagement(BoardManagementDto boardManagement) {
		this.boardManagement = boardManagement;
	}

	public boolean isNotice() {
		return notice;
	}

	public void setNotice(boolean notice) {
		this.notice = notice;
	}

	public boolean isSecret() {
		return secret;
	}

	public void setSecret(boolean secret) {
		this.secret = secret;
	}
	
}
