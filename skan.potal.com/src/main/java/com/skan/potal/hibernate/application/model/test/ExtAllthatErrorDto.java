package com.skan.potal.hibernate.application.model.test;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author Kang
 *
 * CJ오쇼핑 -> 올댓 외부 API 전문용 외부클래스
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtAllthatErrorDto {

	private String message; //오류메세지
	private String type; //오류타입
	private String code; //오류코드
	private String fim_pd_n_vl; //업체 상품 번호
	private String pd_txn_id; //상품 등록 트랜잭션 ID
	private String error_code; //올댓 오류 코드

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFim_pd_n_vl() {
		return fim_pd_n_vl;
	}

	public void setFim_pd_n_vl(String fim_pd_n_vl) {
		this.fim_pd_n_vl = fim_pd_n_vl;
	}

	public String getPd_txn_id() {
		return pd_txn_id;
	}

	public void setPd_txn_id(String pd_txn_id) {
		this.pd_txn_id = pd_txn_id;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(getClass(), ToStringStyle.MULTI_LINE_STYLE);
	}
}
