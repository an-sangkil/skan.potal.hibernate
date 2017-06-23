/**
 * 
 */
package com.skan.tms.web.jpa.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <pre>
 * Class Name  : RoleDto.java
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
@Table(name="role")
public class RoleDto {
	
	public RoleDto() {
		super();
	}
	
	public RoleDto(String roleId, String roleName, String roleDocument) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDocument = roleDocument;
	}
	@Id
	@Column(name="role_id", length=24)
	private String roleId;	
	
	@Column(name="role_name", length=32)
	private String roleName;
	
	@Column(name="role_document", length=128)
	private String roleDocument;	
	
	
	///////////////////////////////////////////
	//Association 설정 
	///////////////////////////////////////////
	@ManyToMany(fetch=FetchType.LAZY , cascade=CascadeType.ALL)
	@JoinTable(name="role_permission"
				,joinColumns = @JoinColumn(name = "role_id")
	            ,inverseJoinColumns = @JoinColumn(name = "permission_id")
			)
	private Set<PermissionDto> permissions;
	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="roleDto")
	private Set<UserRoleDto> userRoles;
	
	
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
	 * 역할명
	 * @param roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * 롤 설명
	 * @param roleDocument
	 */
	public String getRoleDocument() {
		return roleDocument;
	}
	public void setRoleDocument(String roleDocument) {
		this.roleDocument = roleDocument;
	}
	
	/**
	 * 허가(권한) 목록
	 * @return
	 */
	public Set<PermissionDto> getPermissions() {
		return permissions;
	}
	public void setPermissionRoles(Set<PermissionDto> permissions) {
		this.permissions = permissions;
	}

	public Set<UserRoleDto> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoleDto> userRoles) {
		this.userRoles = userRoles;
	}
	
	
}
