package com.skan.potal.hibernate.application.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Team {
	
	@Id
	@GeneratedValue
	@Column(name = "team_id", nullable = false)
	private Long teamId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(mappedBy="team" , fetch=FetchType.LAZY)
	private List<User> userList;
	
	
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", name=" + name + "]";
	}
}
