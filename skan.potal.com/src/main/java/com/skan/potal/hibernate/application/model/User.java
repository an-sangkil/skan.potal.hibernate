package com.skan.potal.hibernate.application.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name="testuser")
//public class User extends AbstractPersistable<Long> implements Serializable {
//@NamedNativeQueries(value={@NamedNativeQuery(name="", query = "")})
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "name", nullable = true)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="team_id")
	private Team team;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name
				+ ", team=" + team + "]";
	}

}
