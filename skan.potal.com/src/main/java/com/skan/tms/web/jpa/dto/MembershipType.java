package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 회원종류 모델 클래스.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Entity
@Table
public class MembershipType implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 회원 종류 코드. */
	@Id
	private String membershipTypeCode;

	/** 회원 종류명. */
	private String membershipTypeName;

	/** 기간 (개월수). */
	private Short period;

	/** 설명. */
	private String explanation;

	/** 가격 (max 1,000,000). */
	private Double price;

	/** 활성화:Y 비활성화:N. */
	private String useAble;

	/** 등록자. */
	private String creator;

	/** 등록시간. */
	private Date creationTime;

	/** 유료회원정보 목록. */
	@OneToMany(mappedBy="membershipType" , fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Membership> membershipSet;

	/**
	 * 생성자.
	 */
	public MembershipType() {
		this.membershipSet = new HashSet<Membership>();
	}

	public MembershipType(String membershipTypeCode) {
		this.membershipTypeCode = membershipTypeCode;
	}

	/**
	 * 회원 종류 코드을 설정합니다..
	 * 
	 * @param membershipTypeCode
	 *            회원 종류 코드
	 */
	public void setMembershipTypeCode(String membershipTypeCode) {
		this.membershipTypeCode = membershipTypeCode;
	}

	/**
	 * 회원 종류 코드을 가져옵니다..
	 * 
	 * @return 회원 종류 코드
	 */
	public String getMembershipTypeCode() {
		return this.membershipTypeCode;
	}

	/**
	 * 회원 종류명을 설정합니다..
	 * 
	 * @param membershipTypeName
	 *            회원 종류명
	 */
	public void setMembershipTypeName(String membershipTypeName) {
		this.membershipTypeName = membershipTypeName;
	}

	/**
	 * 회원 종류명을 가져옵니다..
	 * 
	 * @return 회원 종류명
	 */
	public String getMembershipTypeName() {
		return this.membershipTypeName;
	}

	/**
	 * 기간 (개월수)을 설정합니다..
	 * 
	 * @param period
	 *            기간 (개월수)
	 */
	public void setPeriod(Short period) {
		this.period = period;
	}

	/**
	 * 기간 (개월수)을 가져옵니다..
	 * 
	 * @return 기간 (개월수)
	 */
	public Short getPeriod() {
		return this.period;
	}

	/**
	 * 설명을 설정합니다..
	 * 
	 * @param explanation
	 *            설명
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/**
	 * 설명을 가져옵니다..
	 * 
	 * @return 설명
	 */
	public String getExplanation() {
		return this.explanation;
	}

	/**
	 * 가격 (max 1,000,000)을 설정합니다..
	 * 
	 * @param price
	 *            가격 (max 1,000,000)
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 가격 (max 1,000,000)을 가져옵니다..
	 * 
	 * @return 가격 (max 1,000,000)
	 */
	public Double getPrice() {
		return this.price;
	}

	/**
	 * 활성화:Y 비활성화:N을 설정합니다..
	 * 
	 * @param useAble
	 *            활성화:Y 비활성화:N
	 */
	public void setUseAble(String useAble) {
		this.useAble = useAble;
	}

	/**
	 * 활성화:Y 비활성화:N을 가져옵니다..
	 * 
	 * @return 활성화:Y 비활성화:N
	 */
	public String getUseAble() {
		return this.useAble;
	}

	/**
	 * 등록자을 설정합니다..
	 * 
	 * @param creator
	 *            등록자
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 등록자을 가져옵니다..
	 * 
	 * @return 등록자
	 */
	public String getCreator() {
		return this.creator;
	}

	/**
	 * 등록시간을 설정합니다..
	 * 
	 * @param creationTime
	 *            등록시간
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * 등록시간을 가져옵니다..
	 * 
	 * @return 등록시간
	 */
	public Date getCreationTime() {
		return this.creationTime;
	}

	/**
	 * 유료회원정보 목록을 설정합니다..
	 * 
	 * @param membershipSet
	 *            유료회원정보 목록
	 */
	public void setMembershipSet(Set<Membership> membershipSet) {
		this.membershipSet = membershipSet;
	}

	/**
	 * 유료회원정보를 추가합니다..
	 * 
	 * @param membership
	 *            유료회원정보
	 */
	public void addMembership(Membership membership) {
		this.membershipSet.add(membership);
	}

	/**
	 * 유료회원정보 목록을 가져옵니다..
	 * 
	 * @return 유료회원정보 목록
	 */
	public Set<Membership> getMembershipSet() {
		return this.membershipSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((membershipTypeCode == null) ? 0 : membershipTypeCode.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MembershipType other = (MembershipType) obj;
		if (membershipTypeCode == null) {
			if (other.membershipTypeCode != null) {
				return false;
			}
		} else if (!membershipTypeCode.equals(other.membershipTypeCode)) {
			return false;
		}
		return true;
	}

}
