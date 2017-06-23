/**
 * 
 */
package com.skan.potal.web.jpa.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skan.potal.web.jpa.dto.RoleDto;
import com.skan.potal.web.jpa.dto.UserDto;

@Entity
@Table(name="userrole")
public class UserRoleDto {
	
	
	@Embeddable
	public static class UserRolePK implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -3685033110367527731L;
		
		@Column(name="email", length=32)
		private String email;
		
		@Column(name="role_id", length=24)
		private String roleId;
		
		
		/**
		 * 사용자 아이디 
		 * @return
		 */
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UserRolePK other = (UserRolePK) obj;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (roleId == null) {
				if (other.roleId != null)
					return false;
			} else if (!roleId.equals(other.roleId))
				return false;
			return true;
		}
	}
	
	
	@EmbeddedId
	private UserRolePK userRolePK;
	
	@Column(name="role_name", length=32)
	private String roleName;
	
	@Column(name="role_comment", length=128 )
	private String roleComment;
	
	///////////////////////////////////////////
	//Association 설정 
	///////////////////////////////////////////
	@ManyToOne(fetch = FetchType.LAZY  )
	@JoinColumn(name="email" , insertable=false, updatable=false)
	private UserDto userDto;
	
	
	@ManyToOne(fetch = FetchType.LAZY  )
	@JoinColumn(name="role_id" , insertable=false, updatable=false)
	private RoleDto roleDto;
	
	
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
	 * 역할 설명
	 * @param roleComment
	 */
	public String getRoleComment() {
		return roleComment;
	}
	public void setRoleComment(String roleComment) {
		this.roleComment = roleComment;
	}
	public UserRolePK getUserRolePK() {
		return userRolePK;
	}
	public void setUserRolePK(UserRolePK userRolePK) {
		this.userRolePK = userRolePK;
	}
}
