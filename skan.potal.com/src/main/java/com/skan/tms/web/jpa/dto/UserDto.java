package com.skan.tms.web.jpa.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <pre>
 * Class Name  : UserDto.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 5. 25.          skan               최초생성
 * </pre>
 *
 * @author skan
 * @since 2016. 5. 25.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Entity
@Table(name="users")
@JsonIgnoreProperties(ignoreUnknown=true,value={"snsInterfaceInfos","paymentInfomations"})
public class UserDto implements Serializable {
	
	public UserDto() {}
	
	public UserDto(String email) {
		this.email = email;
	}

	public UserDto(String email, String userName) {
		super();
		this.email = email;
		this.userName = userName;
	}
	
	public UserDto(String email, String userName, String cellPhone) {
		super();
		this.email = email;
		this.userName = userName;
		this.cellPhone = cellPhone;
	}
	
	public UserDto(String email, String userName, String cellPhone ,String membershipNames) {
		super();
		this.email = email;
		this.userName = userName;
		this.cellPhone = cellPhone;
		this.membershipNames = membershipNames;
	}
	
	public UserDto(String email, String userName, String cellPhone ,String membershipNames, Date creationTime) {
		super();
		this.email = email;
		this.userName = userName;
		this.cellPhone = cellPhone;
		this.membershipNames = membershipNames;
		this.creationTime = creationTime;
	}

	private static final long serialVersionUID = -2708635373487176045L;
	
	@Id
	@Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$" ,message="올바른 이메일을 입력하세요.")
	@Column(name="email", length=32)
	@NotBlank(message="이메일을 입력해 주세요.")
	private String email;
	
	@Size(min=5, max=12, message="사용자 아이디는 5자리 이상 12자 이하만 가능 합니다.")
	@NotBlank(message="아이디를 입력해 주세요.")
	@Column(name="user_id", length=32 , unique=true)
	private String userId;
	
	// 우편물 발송 여부 Y/N
	@Column(name="post_receive_confirm", length=1)
	private String postReceiveConfirm;
	
	// 관리자 여부 
	@Column(name="is_admin", nullable=false)
	private boolean isAdmin;
	
	@NotBlank(message="이름을 입력해 주세요.")
	@Column(name="user_name", length=32)
	private String userName;
	
	// 휴대 전화번호
	@NotBlank(message="전화번로를 입력해 주세요.")
	@Pattern(regexp="^[0-9]+$", message="0-9 사이의 숫자만 입력 가능 합니다.")
	@Column(name="cell_phone", length=11)
	private String cellPhone;	

	// 성별
	@Column(name="gender", length=1)
	private String gender;		
	
	// 우편번호
	@Column(name="zipcode", length=5)
	private String zipcode;
	
	// 주소
	@Column(name="address", length=128)
	private String address;
	
	// 상세 주소
	@Column(name="detail_address", length=64)
	private String detailAddress;
	
	// 생년월일
	@NotBlank(message="생년월일을 입력해 주세요. ")
	@Size(min=8, message="숫자만입력하세요  예) 20010125")
	@Pattern(regexp="^[0-9]+$", message="0-9 사이의 숫자만 입력 가능 합니다.")
	@Column(name="birth_date", length=8)
	private String birthDate;
	// 거주지
	private String city;		
	
	@NotBlank(message="패스워드를 입력해 주세요.")
	//	@Pattern(regexp="([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9]){8,12}" ,message="숫자 영문자 특수 문자를 포함한 8 ~ 12 자를 입력하세요. ")
	@Size(min=8, message="숫자 영문자 특수 문자를 포함한 8 ~ 16 자를 입력하세요.")
	@Column(name="password", length=256)
	private String password;
	
	@Transient private String confirmPassword;
	
	/** 이메일  인증 여부*/
	@Column(length=1, updatable=false)
	private boolean emailAuthentication;
	
	/** 이메일 수신 동의 여부 */
	@Column(name="email_confirm", length=1)
	private String emailConfirm;	
	
	/**문자 수신 동의여부 */
	@Column(name="sms_receive_confirm", length=1)
	private String smsReceiveConfirm;	
	
	/** 마지막 접속시간 */
	@Column(name="last_connected_time")
	private long lastConnectedTime;
	
	/** 로그인실패 횟수 */
	@Column(name="try_connect_fail_count")
	private int tryConnectfailCount;
	
	
	/**접속 허용여부 체크  - 계정 삭제시 FALSE로 변경 */
	private boolean allowAccessCheck;
	
	/**마지막 패스워드 변경일 */
	private Date passwordChangeTime;
	
	/** 등록 시간 */
	@Column(name="creation_time")
	private Date creationTime;
	
	/** 수정시간  */
	@Column(name="modified_time")
	private Date modifiedTime;
	
	
	/**OAuth 제공자*/ 
	@Transient private String providerId;
	
	@Transient private String membershipNames;

	/*@OneToMany(mappedBy="userRoleDto" ,fetch=FetchType.LAZY)
	@JoinColumn(name="email",insertable=false, updatable=false,nullable=true)
	private UserRoleDto userRoleInfo;
	
	
	public UserRoleDto getUserRoleInfo() {
		return userRoleInfo;
	}

	public void setUserRoleInfo(UserRoleDto userRoleInfo) {
		this.userRoleInfo = userRoleInfo;
	}*/

	//////////////////////////////////////////////////////////////
	// Object 연관관계
	//////////////////////////////////////////////////////////////
	@OneToMany(mappedBy="userDto" ,fetch = FetchType.LAZY )
	private Set<UserRoleDto> userRoles;
	
	@OneToMany(mappedBy="userDto" ,fetch = FetchType.LAZY )
	private List<SnsInterfaceInfoDto> snsInterfaceInfos;
	
	@OneToMany(mappedBy="userDto" ,fetch = FetchType.LAZY )
	private List<PaymentInfomationDto> paymentInfomations;
	
	@OneToMany(mappedBy="userDto" ,fetch = FetchType.LAZY )
	private List<Membership> membership;

	
	//////////////////////////////////////////////////////////////
	// Getter Setter
	//////////////////////////////////////////////////////////////
	
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
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPostReceiveConfirm() {
		return postReceiveConfirm;
	}

	public void setPostReceiveConfirm(String postReceiveConfirm) {
		this.postReceiveConfirm = postReceiveConfirm;
	}

	/**
	 * 사용자 이름
	 * @param userName
	 */
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 휴대전화번호
	 * @param cellPhone
	 */
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	
	/**
	 * 성별(M,F)
	 * @param gender
	 */
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * 주소
	 * @param address
	 */
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 생년월일
	 * @param birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	/**
	 * 거주지
	 * @param city
	 */
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * 패스워드
	 * @param password
	 */
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 이메일 수신동의 여부(Y/N)
	 * @param emailConfirm
	 */
	public String getEmailConfirm() {
		return emailConfirm;
	}
	public void setEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
	}
	
	/**
	 * 문자 수신동의 여부(Y/N)
	 * @param smsReceiveConfirm
	 */
	public String getSmsReceiveConfirm() {
		return smsReceiveConfirm;
	}
	public void setSmsReceiveConfirm(String smsReceiveConfirm) {
		this.smsReceiveConfirm = smsReceiveConfirm;
	}

	public Set<UserRoleDto> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoleDto> userRoles) {
		this.userRoles = userRoles;
	}

	public List<SnsInterfaceInfoDto> getSnsInterfaceInfos() {
		return snsInterfaceInfos;
	}

	public void setSnsInterfaceInfos(List<SnsInterfaceInfoDto> snsInterfaceInfos) {
		this.snsInterfaceInfos = snsInterfaceInfos;
	}
	
	public boolean isAllowAccessCheck() {
		return allowAccessCheck;
	}

	public void setAllowAccessCheck(boolean allowAccessCheck) {
		this.allowAccessCheck = allowAccessCheck;
	}

	public Date getPasswordChangeTime() {
		return passwordChangeTime;
	}

	public void setPasswordChangeTime(Date passwordChangeTime) {
		this.passwordChangeTime = passwordChangeTime;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public long getLastConnectedTime() {
		return lastConnectedTime;
	}

	public void setLastConnectedTime(long lastConnectedTime) {
		this.lastConnectedTime = lastConnectedTime;
	}

	public int getTryConnectfailCount() {
		return tryConnectfailCount;
	}

	public void setTryConnectfailCount(int tryConnectfailCount) {
		this.tryConnectfailCount = tryConnectfailCount;
	}

	public List<PaymentInfomationDto> getPaymentInfomations() {
		return paymentInfomations;
	}

	public void setPaymentInfomations(List<PaymentInfomationDto> paymentInfomations) {
		this.paymentInfomations = paymentInfomations;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public boolean isEmailAuthentication() {
		return emailAuthentication;
	}

	public void setEmailAuthentication(boolean emailAuthentication) {
		this.emailAuthentication = emailAuthentication;
	}

	public List<Membership> getMembership() {
		return membership;
	}

	public void setMembership(List<Membership> membership) {
		this.membership = membership;
	}
	
	public String getMembershipNames() {
		return membershipNames;
	}

	public void setMembershipNames(String membershipNames) {
		this.membershipNames = membershipNames;
	}

	/**
	 * 사용자 정보 컨버팅
	 * @param providerUser
	 * @return
	 */
	public static UserDto fromProviderUser(UserProfile providerUser) {
		UserDto userDto = new UserDto();
		
		userDto.setUserName(providerUser.getName());
		userDto.setEmail(providerUser.getEmail());
		return userDto;
	}

	public static UserDto fromProviderUser(Connection<?> connection) {
		UserDto userDto = new UserDto();
		
		userDto.setUserName(connection.fetchUserProfile().getName());
		userDto.setEmail(connection.fetchUserProfile().getEmail());
		userDto.setProviderId(connection.getKey().getProviderId());
		return userDto;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
