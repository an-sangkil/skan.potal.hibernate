package com.skan.tms.mobile.common.code;

public enum SystemConfigrationCode {
	MAIL_SMTP_INFORMATION(""), 
		MAIL_USERNAME("사용 자아이디"),
		MAIL_PASSWORD("패스 워드"),
		MAIL_SMTP_HOST("호스트 "),
		MAIL_SMTP_PROTOCOL("프로토콜"),
		MAIL_SMTP_PORT("포트"),
		MAIL_SMTP_SSL_ENABLE("SSL 사용여부"),
		MAIL_SMTP_AUTH(""),
		MAIL_SMTP_STARTTLS_ENABLE(""),
		MAIL_SMTPS_SSL_CHECKSERVERIDENTITY(""),
		MAIL_SMTPS_SSL_TRUST("")
	;

	private String codeName;

	SystemConfigrationCode (String codeName) {
		this.codeName = codeName;
	}
	
	/**
	 * 역할 코드 이름
	 * @return
	 */
	public String getCodeName() {
		return codeName;
	}
	
	public static SystemConfigrationCode[] MAIL_GROUPS = {MAIL_USERNAME, MAIL_PASSWORD, MAIL_SMTP_HOST, MAIL_SMTP_PROTOCOL, MAIL_SMTP_PORT, MAIL_SMTP_SSL_ENABLE, MAIL_SMTP_AUTH,MAIL_SMTP_STARTTLS_ENABLE,MAIL_SMTPS_SSL_CHECKSERVERIDENTITY,MAIL_SMTPS_SSL_TRUST}; 
	
	
}
