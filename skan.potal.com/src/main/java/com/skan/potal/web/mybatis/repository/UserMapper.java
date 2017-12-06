/**
 * 
 */
package com.skan.potal.web.mybatis.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.skan.potal.web.jpa.dto.UserDto;

/**
 * <pre>
 * Class Name  : UserMapper.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 5. 25.          skan               최초생성
 * </pre>
 *
 * @author skan
 * @since 2016. 5. 25.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public interface UserMapper {
	
	public List<UserDto> userList() throws DataAccessException ;

}
