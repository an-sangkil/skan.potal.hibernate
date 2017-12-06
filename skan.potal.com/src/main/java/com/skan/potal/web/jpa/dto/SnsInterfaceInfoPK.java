package com.skan.potal.web.jpa.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
 * 복합키 연관구성을 위한 PK CLASS
 */
@Embeddable
public class SnsInterfaceInfoPK implements Serializable{

	private static final long serialVersionUID = -4227242859418660534L;

	@Column(name="email", length=32)
	private String email;
	
	@Column(name="service_name", length=32)
	private String serviceName;

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
	 * 서비스명
	 * @param serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
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
		SnsInterfaceInfoPK other = (SnsInterfaceInfoPK) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (serviceName == null) {
			if (other.serviceName != null)
				return false;
		} else if (!serviceName.equals(other.serviceName))
			return false;
		return true;
	}
	
}
