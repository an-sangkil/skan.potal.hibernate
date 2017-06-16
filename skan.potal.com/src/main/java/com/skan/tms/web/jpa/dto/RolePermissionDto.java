/**
 * 
 */
package com.skan.tms.web.jpa.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <pre>
 * Class Name  : RolePermissionDto.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 8. 18.        yh.Lee               최초생성
 * </pre>
 *
 * @author yh.Lee
 * @since 2016. 8. 18.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table(name="rolepermission")
public class RolePermissionDto {
	
	@Id
	@Column(name="role_id", length=24)
	private String roleId;	
	
	@Column(name="permission_id", length=24)
	private String permissionId;	
	
	
	/**
	 * 롤 아이디
	 * @param roleId
	 */
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * 권한 아이디
	 * @param permissionId
	 */
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	
}
