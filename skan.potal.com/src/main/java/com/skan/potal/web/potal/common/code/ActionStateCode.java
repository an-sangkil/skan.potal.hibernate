/**
 * <pre>
 * Class Name  : ActionStateCode.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 7.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 1. 7.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.common.code;

/**
 * @author ahn
 *
 */
public enum ActionStateCode implements CommonCode {
	MESSAGE("0","화면 메세지 Property"),
	SUCCESS("100","Submit Action 성공"),
	FAIL("200","Submit Action 실패"),
	INFO("300","Submit Action 실패"),
	WARNING("400","Submit Action 실패");
	
	
	public String code;
	public String message;
	
	private ActionStateCode(String code, String message) {
		this.code=code;
		this.message=message;
	}
}
