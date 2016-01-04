/**
 * <pre>
 * Class Name  : UUIDUtils.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2015. 12. 30.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2015. 12. 30.
 * @version 
 *
 * Copyright (C) 2015 by SKAN.COMPANY All right reserved.
 */
package com.skan.potal.web.potal.common.util;

import java.util.UUID;

public class UUIDUtils {
	
	public String createUUID() {
		return UUID.randomUUID().toString();
	}
}
