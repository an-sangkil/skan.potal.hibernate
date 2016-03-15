/**
 * <pre>
 * Class Name  : ConvertObject.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 2. 13.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @since 2016. 2. 13.
 * @version 
 *
 * Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.accountbook.converter;

/**
 * 
 * @author ahn
 *
 * @param <E> Model 객체
 * @param <T> DTO 객체
 */
public abstract class ConvertObject<E,T> {

	/**
	 * @param e
	 * @param t
	 * @return
	 */
	public abstract E ConvertToModel(E model, T dto);
	
	/**
	 * Model 을 DTO로 변경
	 * @return
	 */
	public abstract T ConvertToDTO(E model, T dto);
	
}
