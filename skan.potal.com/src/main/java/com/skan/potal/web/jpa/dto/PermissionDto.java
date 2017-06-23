/**
 * 
 */
package com.skan.potal.web.jpa.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * <pre>
 * Class Name  : PermissionRoleDto.java
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
@Table(name="permission")
public class PermissionDto {
	
	public PermissionDto() {
		super();
	}
	
	public PermissionDto(String permissionId, String permissionName, String permissionComment) {
		super();
		this.permissionId = permissionId;
		this.permissionName = permissionName;
		this.permissionComment = permissionComment;
	}
	
	@Id
	@Column(name="permission_id", length=48)
	private String permissionId;
	
	@Column(name="permission_name", length=32)
	private String permissionName;
	
	@Column(name="permission_comment", length=128)
	private String permissionComment;	
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy = "permissions")
	private List<RoleDto> roles;
	

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
	
	/**
	 * 권한명
	 * @param permissionName
	 */
	public String getPermissionName() {
		return permissionName;
	}
	
	/**
	 * 권한 설명
	 * @param roleComment
	 */
	
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionComment() {
		return permissionComment;
	}
	public void setPermissionComment(String permissionComment) {
		this.permissionComment = permissionComment;
	}
	public List<RoleDto> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}
	
	
}
