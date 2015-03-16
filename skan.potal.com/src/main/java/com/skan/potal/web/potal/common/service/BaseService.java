package com.skan.potal.web.potal.common.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * <pre>
 * Class Name  : BaseService.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2015. 3. 16.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2015. 3. 16.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
public interface BaseService<T, ID> {
	T insert(T t);
	List<T> findAllDatas();
	List<T> findAllDatas(Iterator<ID> ids);
	public T findData(ID pk);
	Page<T> pageFindDatas(PageRequest pageRequest);
	void deleteData(T t);
}
