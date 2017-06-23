/**
 * 
 */
package com.skan.potal.web.jpa.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * <pre>
 * Class Name  : CategoryDto.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 8. 29.        yh.Lee               최초생성
 * </pre>
 *
 * @author yh.Lee
 * @since 2016. 8. 29.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table(name="categorycode")
public class CategoryDto {
	
	@Id
	@Column(name="category_code", length=5)
	private String categoryCode;
	
	@Column(name="category_upper_code", length=5, nullable=true)
	private String categoryUpperCode;
	
	// 순번
	@Column(name="seq")
	private int seq;

	@Column(name="category_name", length=32)
	private String categoryName;
	
	@Column(name="category_comment", length=256)
	private String categoryComment;
	
	// 사용여부 
	@Column(name="useYN", length=1)
	private String useYn;
	
	// 생성일자 
	@Column(name="creation_time")
	private Date creationTime;

	
	/////////////////////////////////////////////////
	// Association Relation
	/////////////////////////////////////////////////
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_upper_code",updatable=false, insertable=false, nullable=true)
	private CategoryDto categoryUpperCodeDto;


	/////////////////////////////////////////////////
	// Getter, Setter
	/////////////////////////////////////////////////
	
	public String getCategoryCode() {
		return categoryCode;
	}

	public CategoryDto getCategoryUpperCodeDto() {
		return categoryUpperCodeDto;
	}

	public void setCategoryUpperCodeDto(CategoryDto categoryUpperCodeDto) {
		this.categoryUpperCodeDto = categoryUpperCodeDto;
	}

	public String getCategoryComment() {
		return categoryComment;
	}

	public void setCategoryComment(String categoryComment) {
		this.categoryComment = categoryComment;
	}


	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	

	public String getCategoryUpperCode() {
		return categoryUpperCode;
	}

	/**
	 * forinKey 값이 null 이어야 하기때문에 상위 코드 값이 Empty 인경우 null로 교체 한다.
	 * @return
	 */
	public void setCategoryUpperCode(String categoryUpperCode) {
		this.categoryUpperCode = org.apache.commons.lang.StringUtils.defaultIfEmpty(categoryUpperCode, null);
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getUseYn() {
		return useYn;
	}


	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}


	public Date getCreationTime() {
		return creationTime;
	}


	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
}
