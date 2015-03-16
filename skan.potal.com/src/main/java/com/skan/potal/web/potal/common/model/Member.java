package com.skan.potal.web.potal.common.model;

import javax.persistence.Entity;

/**
 * <pre>
 * Class Name  : Member.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2015. 2. 24.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2015. 2. 24.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */

@Entity
public class Member {
	private String user_id;
	private String user_name;
	private String password;			
	private String dept_code;			//부서코드
	private String dept_name;			//부서이름
	private String e_mail;				//이메일
	private String zip_code;			//우편번호
	private String address;				//주소
	private String phone_number;		//전화번호
	private String mobile_phone_number;	//휴대전화번호
	private String ssn_number;			//주민등록번호
	private String gender;				//성별(M:남자, F:여성)
	
	
}
