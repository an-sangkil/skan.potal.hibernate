package com.skan.tms.mobile.common.code;

/**
 * 
 * Description : 공통 코드 
 *  
 * @author skan
 * @since 2016. 9. 8.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public enum CommonCode {
	//
	BOARD_TYPE("게시판 타입"),
		IMAGE_BOARD("이미지 게시판  타입"),
		MOVIE_BOARD("동영상 타입 게시판"),
		LETTER_BOARD("글자 문자 타입 게시판"),
	
	
	// Controller 성공 실패 응답 코드 
	RESPONSE_CODE("응답에 대한 성공 실패 코드"),
		SUCCESS("성공 하였습니다."),
		FAIL("실패 하였습니다.");
	

	private String codeName;
	private CommonCode (String codeName) {
		this.codeName = codeName;
	}
	
	/**
	 * 역할 코드 이름
	 * @return
	 */
	public String getCodeName() {
		return codeName;
	}
}
