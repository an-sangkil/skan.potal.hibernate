/**
 * 
 */
package com.skan.potal.web.jpa.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * Class Name  : CommonCodeDto.java
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
@Table(name="commoncode")
public class CommonCodeDto {
	
	
	@Column(name="code_seq")
	private int codeSeq;
	
	@Id
	//@Pattern(regexp="[A-Z]", message="대문자 영문자만 입력 가능 합니다.")
	@Column(name="code", length=32)
	private String code;
	
	@Column(name="upper_code", length=32, nullable=true)
	private String upperCode;
	@Column(name="code_name", length=64)
	@NotEmpty
	private String codeName;
	
	@Column(name="code_comment", length=256)
	private String codeComment;
	
	@Column(name="useYN", length=1)
	private String useYn;
	
	@Column(name="creation_time")
	private Date creationTime;

	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumns(
    		{
    				@JoinColumn(name="upper_code", insertable=false, updatable=false 
						//, columnDefinition="code"
						,nullable=true)
    					//,@JoinColumn(name="codeMgtNo", insertable=false, updatable=false)
    			}
    		
    		)
    private CommonCodeDto cmtbUpperCode;
	
	/**
	 * 삭제 가능 여부 
	 */
	private Boolean deleteYn;
	
	
	public Boolean getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(Boolean deleteYn) {
		this.deleteYn = deleteYn;
	}

	/**
	 * 코드명
	 * @return
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUpperCode() {
		return upperCode;
	}
	
	/**
	 * forinKey 값이 null 이어야 하기때문에 상위 코드 값이 Empty 인경우 null로 교체 한다.
	 * @return
	 */
	public void setUpperCode(String upperCode) {
		this.upperCode = org.apache.commons.lang.StringUtils.defaultIfEmpty(upperCode, null);
	}

	public int getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(int codeSeq) {
		this.codeSeq = codeSeq;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeComment() {
		return codeComment;
	}

	public void setCodeComment(String codeComment) {
		this.codeComment = codeComment;
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

	public CommonCodeDto getCmtbUpperCode() {
		return cmtbUpperCode;
	}

	public void setCmtbUpperCode(CommonCodeDto cmtbUpperCode) {
		this.cmtbUpperCode = cmtbUpperCode;
	}
	
}
