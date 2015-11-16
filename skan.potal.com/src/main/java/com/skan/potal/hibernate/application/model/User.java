package com.skan.potal.hibernate.application.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * <pre>
 * Class Name  : UserEntity.java
 * Description : 
 * Modification Information
 * 
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2014. 12. 4.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2014. 12. 4.
 * @version
 *
 *          Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */

@Entity
@Table(name="cmtb_user")
public class User extends AbstractPersistable<Long> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "name", nullable = true)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="team_id")
	private Team team;
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + "]";
	}
	
	
}
