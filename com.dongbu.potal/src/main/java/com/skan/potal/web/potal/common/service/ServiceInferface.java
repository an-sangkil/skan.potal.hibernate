package com.skan.potal.web.potal.common.service;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class Name  : Service.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2014. 8. 7.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2014. 8. 7.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
public interface ServiceInferface {
	
	public int insert(Object obj , Map<String, Object> dataMap ) throws Exception;
	public int update(Object obj , Map<String,Object> dataMap ) throws Exception;
	public int delete(Object obj , Map<String,Object> dataMap ) throws Exception;
	
	public List<?> select(Object obj , Map<String,Object> dataMap ) throws Exception;
	public List<?> selectList(Object obj , Map<String,Object> dataMap ) throws Exception;
	
}
