package com.skan.potal.hibernate.application.model.test;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author Kang
 *
 * CJ오쇼핑 -> 올댓 외부 API 전문 전송용 외부클래스
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtAllthatMallDto extends ExtAllthatCommonDto {

	private String alth_mll_id; 	// 올댓몰ID 문자열(5) 
	private String alth_cag_id; 	//  올댓카테고리ID 문자열(8) 
	private String alth_cag_ccd; 	//  올댓카테고리구분코드(1: 카테고리상품, 2: 모바일상품 전용, 3: 패밀리몰상품 전용) 문자열(1) 
	private String cag_nm; 		 	//  카테고리명 문자열(100) 
	private String cag_lev_vl; 		//  카테고리레벨값 문자열(1) 
	private String cag_desc_tt; 	//  카테고리설명내용 문자열(2000) 
	private String qee_rk; 			// 정렬순위 정수 
	private String se_f; 			// 사용여부(Y/N) 문자열(1)

	public String getAlth_mll_id() {
		return alth_mll_id;
	}

	public void setAlth_mll_id(String alth_mll_id) {
		this.alth_mll_id = alth_mll_id;
	}

	public String getAlth_cag_id() {
		return alth_cag_id;
	}

	public void setAlth_cag_id(String alth_cag_id) {
		this.alth_cag_id = alth_cag_id;
	}

	public String getAlth_cag_ccd() {
		return alth_cag_ccd;
	}

	public void setAlth_cag_ccd(String alth_cag_ccd) {
		this.alth_cag_ccd = alth_cag_ccd;
	}

	public String getCag_nm() {
		return cag_nm;
	}

	public void setCag_nm(String cag_nm) {
		this.cag_nm = cag_nm;
	}

	public String getCag_lev_vl() {
		return cag_lev_vl;
	}

	public void setCag_lev_vl(String cag_lev_vl) {
		this.cag_lev_vl = cag_lev_vl;
	}

	public String getCag_desc_tt() {
		return cag_desc_tt;
	}

	public void setCag_desc_tt(String cag_desc_tt) {
		this.cag_desc_tt = cag_desc_tt;
	}

	public String getQee_rk() {
		return qee_rk;
	}

	public void setQee_rk(String qee_rk) {
		this.qee_rk = qee_rk;
	}

	public String getSe_f() {
		return se_f;
	}

	public void setSe_f(String se_f) {
		this.se_f = se_f;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(getClass(), ToStringStyle.MULTI_LINE_STYLE);
	}

}
