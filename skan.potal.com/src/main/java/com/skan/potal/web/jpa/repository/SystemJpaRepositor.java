package com.skan.tms.web.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skan.tms.web.jpa.dto.SystemDto;

/**
 * 
 * <pre>
 * Class Name  : SystemJpaRepository.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 8. 30.        yh.Lee               최초생성
 * </pre>
 *
 * @author yh.Lee
 * @since 2016. 8. 30.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public interface SystemJpaRepositor extends JpaRepository<SystemDto, String>{

}
