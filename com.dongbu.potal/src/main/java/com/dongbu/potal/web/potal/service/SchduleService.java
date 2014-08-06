package com.dongbu.potal.web.potal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongbu.potal.web.potal.common.service.ServiceInferface;
import com.dongbu.potal.web.potal.dao.SchduleDao;

/**
 * <pre>
 * Class Name  : SchduleService.java
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

@Service
public class SchduleService implements ServiceInferface{
	
	@Autowired private SchduleDao schduleDao;

	@Override
	public int insert(Object obj, Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object obj, Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object obj, Map<String, Object> dataMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<?> select(Object obj, Map<String, Object> dataMap)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> selectList(Object obj, Map<String, Object> dataMap)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
