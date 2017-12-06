package com.skan.potal.web.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * Description : 결제 요청에 대한 응답 정보 정보 
 * 
 * @author skan
 * @since 2016. 12. 6.
 * @version
 *
 * 			Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class ResponsePaymentTransaction {
	
	/**
	 * KCP 결제 성공여부 기본 FALSE
	 */
	private boolean checkWhether = false; // KCP 성공여부
	private String  orderIdx; // 상품 주문번호
	
	private String	g_conf_site_cd   ;//    <!-- 사이트 코드 -->
	private String	req_tx           ;//    <!-- 요청 구분 -->
	private String	usePayMethod   ;//    <!-- 사용한 결제 수단 -->
	private String	bSucc            ;//    <!-- 쇼핑몰 DB 처리 성공 여부 -->
	private String	amount           ;//    <!-- 금액 -->
	private String	responseCode	 ;//    <!-- 결과 코드 -->
	private String	responseMessage  ;//    <!-- 결과 메세지 -->
	private String	ordr_idxx        ;//    <!-- 주문번호 -->
	private String	tno              ;//    <!-- KCP 거래번호 -->
	private String	good_mny         ;//    <!-- 결제금액 -->
	private String	good_name        ;//    <!-- 상품명 -->
	private String	buyr_name        ;//    <!-- 주문자명 -->
	private String	buyr_tel1        ;//    <!-- 주문자 전화번호 -->
	private String	buyr_tel2        ;//    <!-- 주문자 휴대폰번호 -->
	private String	buyr_mail        ;//    <!-- 주문자 E-mail -->
	private String	app_time         ;//    <!-- 승인시간 -->
	
	///////////////////////////////////////////
	//	<!--카드 정보 -->
	///////////////////////////////////////////
	private String	cardCode         ;//    <!-- 카드코드 -->
	private String	cardName         ;//    <!-- 카드이름 -->
	private String  cardNo			 ;//    <!-- 카드번호 보안정책에 의해  3번째는 **** 표시 -->
	private String	aprovalNo        ;//    <!-- 승인번호 -->
	private String	noinf            ;//    <!-- 무이자여부 -->
	private String	quota            ;//    <!-- 할부개월 -->
	private String	partCalcelYN     ;//    <!-- 부분취소가능유무 -->
	//private String	card_bin_type_01 ;//   <!-- 카드구분1 -->
	//private String	card_bin_type_02 ;//   <!-- 카드구분2 -->

	/////////////////////////////////////////////
	//<!--계좌이체 정보 -->
	///////////////////////////////////////////
	private String	bank_name        ;//    <!-- 은행명 -->
	private String	bank_code        ;//    <!-- 은행코드 -->

	/////////////////////////////////////////////
	//<!-- 가상계좌 계좌정보 -->
	///////////////////////////////////////////
	private String	bankName         ;//    <!-- 입금 은행 -->
	private String	depositor        ;//    <!-- 입금계좌 예금주 -->
	private String	account          ;//    <!-- 입금계좌 번호 -->
	private String	virtualEndDate   ;//    <!-- 가상계좌 입금마감시간 -->
	//private String	pnt_issue        ;//    <!-- 포인트 서비스사 -->
	
	///////////////////////////////////////////
	//<!-- 포인트 정보 -->
	///////////////////////////////////////////
	private String	pnt_app_time     ;//    <!-- 승인시간 -->
	private String	pnt_app_no       ;//    <!-- 승인번호 -->
	private String	pnt_amount       ;//    <!-- 적립금액 or 사용금액 -->
	private String	add_pnt          ;//    <!-- 발생 포인트 -->
	private String	use_pnt          ;//    <!-- 사용가능 포인트 -->
	private String	rsv_pnt          ;//    <!-- 총 누적 포인트 -->
	
	///////////////////////////////////////////
	//<!-- 휴대폰 정보 -->
	///////////////////////////////////////////
	private String	commid           ;//    <!-- 통신사 코드 -->
	private String	mobile_no        ;//    <!-- 휴대폰 번호 -->
	

	///////////////////////////////////////////
	//<!-- 상품권 정보 -->
	///////////////////////////////////////////
	private String	tk_van_code      ;//    <!-- 발급사 코드 -->
	private String	tk_app_no        ;//    <!-- 승인 번호 -->

	///////////////////////////////////////////
	//<!-- 현금영수증 정보 -->
	///////////////////////////////////////////
	private String	cashYN           ;//    <!-- 현금영수증 등록 여부 -->
	private String	cashApprovalNumber;//  <!-- 현금 영수증 승인 번호 -->
	private String	cashTRCode       ;//    <!-- 현금 영수증 발행 구분 -->
	private String	cashIDInfo       ;//    <!-- 현금 영수증 등록 번호 -->
	
	/**
	 * KCP 결제 성공여부 기본 FALSE  성공시 TRUE
	 * @return
	 */
	public boolean isCheckWhether() {
		return checkWhether;
	}
	/**
	 * KCP 결제 성공여부 기본 FALSE  성공시 TRUE
	 * @return
	 */
	public void setCheckWhether(boolean checkWhether) {
		this.checkWhether = checkWhether;
	}
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getOrderIdx() {
		return orderIdx;
	}
	public void setOrderIdx(String orderIdx) {
		this.orderIdx = orderIdx;
	}
	public String getG_conf_site_cd() {
		return g_conf_site_cd;
	}
	public void setG_conf_site_cd(String g_conf_site_cd) {
		this.g_conf_site_cd = g_conf_site_cd;
	}
	public String getReq_tx() {
		return req_tx;
	}
	public void setReq_tx(String req_tx) {
		this.req_tx = req_tx;
	}
	public String getUsePayMethod() {
		return usePayMethod;
	}
	public void setUsePayMethod(String usePayMethod) {
		this.usePayMethod = usePayMethod;
	}
	public String getbSucc() {
		return bSucc;
	}
	public void setbSucc(String bSucc) {
		this.bSucc = bSucc;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getOrdr_idxx() {
		return ordr_idxx;
	}
	public void setOrdr_idxx(String ordr_idxx) {
		this.ordr_idxx = ordr_idxx;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public String getGood_mny() {
		return good_mny;
	}
	public void setGood_mny(String good_mny) {
		this.good_mny = good_mny;
	}
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	public String getBuyr_name() {
		return buyr_name;
	}
	public void setBuyr_name(String buyr_name) {
		this.buyr_name = buyr_name;
	}
	public String getBuyr_tel1() {
		return buyr_tel1;
	}
	public void setBuyr_tel1(String buyr_tel1) {
		this.buyr_tel1 = buyr_tel1;
	}
	public String getBuyr_tel2() {
		return buyr_tel2;
	}
	public void setBuyr_tel2(String buyr_tel2) {
		this.buyr_tel2 = buyr_tel2;
	}
	public String getBuyr_mail() {
		return buyr_mail;
	}
	public void setBuyr_mail(String buyr_mail) {
		this.buyr_mail = buyr_mail;
	}
	public String getApp_time() {
		return app_time;
	}
	public void setApp_time(String app_time) {
		this.app_time = app_time;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getAprovalNo() {
		return aprovalNo;
	}
	public void setAprovalNo(String aprovalNo) {
		this.aprovalNo = aprovalNo;
	}
	public String getNoinf() {
		return noinf;
	}
	public void setNoinf(String noinf) {
		this.noinf = noinf;
	}
	public String getQuota() {
		return quota;
	}
	public void setQuota(String quota) {
		this.quota = quota;
	}
	public String getPartCalcelYN() {
		return partCalcelYN;
	}
	public void setPartCalcelYN(String partCalcelYN) {
		this.partCalcelYN = partCalcelYN;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getDepositor() {
		return depositor;
	}
	public void setDepositor(String depositor) {
		this.depositor = depositor;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getVirtualEndDate() {
		return virtualEndDate;
	}
	public void setVirtualEndDate(String virtualEndDate) {
		this.virtualEndDate = virtualEndDate;
	}
	public String getPnt_app_time() {
		return pnt_app_time;
	}
	public void setPnt_app_time(String pnt_app_time) {
		this.pnt_app_time = pnt_app_time;
	}
	public String getPnt_app_no() {
		return pnt_app_no;
	}
	public void setPnt_app_no(String pnt_app_no) {
		this.pnt_app_no = pnt_app_no;
	}
	public String getPnt_amount() {
		return pnt_amount;
	}
	public void setPnt_amount(String pnt_amount) {
		this.pnt_amount = pnt_amount;
	}
	public String getAdd_pnt() {
		return add_pnt;
	}
	public void setAdd_pnt(String add_pnt) {
		this.add_pnt = add_pnt;
	}
	public String getUse_pnt() {
		return use_pnt;
	}
	public void setUse_pnt(String use_pnt) {
		this.use_pnt = use_pnt;
	}
	public String getRsv_pnt() {
		return rsv_pnt;
	}
	public void setRsv_pnt(String rsv_pnt) {
		this.rsv_pnt = rsv_pnt;
	}
	public String getCommid() {
		return commid;
	}
	public void setCommid(String commid) {
		this.commid = commid;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getTk_van_code() {
		return tk_van_code;
	}
	public void setTk_van_code(String tk_van_code) {
		this.tk_van_code = tk_van_code;
	}
	public String getTk_app_no() {
		return tk_app_no;
	}
	public void setTk_app_no(String tk_app_no) {
		this.tk_app_no = tk_app_no;
	}
	public String getCashYN() {
		return cashYN;
	}
	public void setCashYN(String cashYN) {
		this.cashYN = cashYN;
	}
	public String getCashApprovalNumber() {
		return cashApprovalNumber;
	}
	public void setCashApprovalNumber(String cashApprovalNumber) {
		this.cashApprovalNumber = cashApprovalNumber;
	}
	public String getCashTRCode() {
		return cashTRCode;
	}
	public void setCashTRCode(String cashTRCode) {
		this.cashTRCode = cashTRCode;
	}
	public String getCashIDInfo() {
		return cashIDInfo;
	}
	public void setCashIDInfo(String cashIDInfo) {
		this.cashIDInfo = cashIDInfo;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,  ToStringStyle.MULTI_LINE_STYLE);
	}
}
