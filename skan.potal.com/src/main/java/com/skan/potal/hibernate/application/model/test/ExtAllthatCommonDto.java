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
public class ExtAllthatCommonDto {

	private AllthatAPICode apiId; // api구분
	private String extGb; // 대형제휴입점 구분(EP01) 01:G마켓, 02:옥션, 03:11번가, 04:올댓
	private String itemCd; // 판매상품코드
	private String chnCd; // 채널코드
	private String extItemCd; // 대형제휴입점 상품번호
	private String cellerId; // 판매자 ID
	private String encTicket = ""; //인증키
	private String drvSeq; //드라이빙 일련번호
	private String tmpSeq; //TMP 통합일련번호

	
	private String startTime; // 시작 시간
	private String endTime; // 종료 시간
	private String externalStatus; // 외부 호출 상태
	private ExtAllthatErrorDto error; // Response Error 객체

	public String getExternalStatus() {
		return externalStatus;
	}

	public void setExternalStatus(String externalStatus) {
		this.externalStatus = externalStatus;
	}

	public ExtAllthatErrorDto getError() {
		return error;
	}

	public void setError(ExtAllthatErrorDto error) {
		this.error = error;
	}

	public AllthatAPICode getApiId() {
		return apiId;
	}

	public void setApiId(AllthatAPICode apiId) {
		this.apiId = apiId;
	}

	public String getCellerId() {
		return cellerId;
	}

	public void setCellerId(String cellerId) {
		this.cellerId = cellerId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEncTicket() {
		return encTicket;
	}

	public void setEncTicket(String encTicket) {
		this.encTicket = encTicket;
	}

	public String getExtGb() {
		return extGb;
	}

	public void setExtGb(String extGb) {
		this.extGb = extGb;
	}

	public String getItemCd() {
		return itemCd;
	}

	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	public String getChnCd() {
		return chnCd;
	}

	public void setChnCd(String chnCd) {
		this.chnCd = chnCd;
	}

	public String getExtItemCd() {
		return extItemCd;
	}

	public void setExtItemCd(String extItemCd) {
		this.extItemCd = extItemCd;
	}
	
	public String getDrvSeq() {
		return drvSeq;
	}

	public void setDrvSeq(String drvSeq) {
		this.drvSeq = drvSeq;
	}
	
	public String getTmpSeq() {
		return tmpSeq;
	}
	
	public void setTmpSeq(String tmpSeq) {
		this.tmpSeq = tmpSeq;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
