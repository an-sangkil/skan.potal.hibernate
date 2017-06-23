/**
 * 
 */
package com.skan.tms.web.jpa.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <pre>
 * Class Name  : PaymentInfomationDto.java
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
@Table(name="paymentinfomation")
public class PaymentInfomationDto {
	
	@Embeddable
	public static class PaymentInformationPK implements Serializable{
		private static final long serialVersionUID = 149008762097961692L;
		@Column(name="email", length=32)
		private String email;
		
		@Column(name="order_num")
		private int orderNum;
		
		/**
		 * 사용자 이메일
		 * @return
		 */
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		/**
		 * 정렬 순서 
		 */
		public int getOrderNum() {
			return orderNum;
		}

		public void setOrderNum(int orderNum) {
			this.orderNum = orderNum;
		}
	}
	
	@EmbeddedId
	private PaymentInformationPK paymentInformationPK;
	
	
	@Column(name="credit_card_number", length=256)
	private String cardNumber;	
	
	@Column(name="credit_card_comment", length=16)
	private String creditCardComment;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	private UserDto userDto;
	
	
	
	/**
	 * 256bit 암호화된 카드 정보
	 * @param cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	/**
	 * 카드 설명
	 * @param cardComment
	 */
	public String getCreditCardComment() {
		return creditCardComment;
	}

	public void setCreditCardComment(String creditCardComment) {
		this.creditCardComment = creditCardComment;
	}
	public PaymentInformationPK getPaymentInformationPK() {
		return paymentInformationPK;
	}
	public void setPaymentInformationPK(PaymentInformationPK paymentInformationPK) {
		this.paymentInformationPK = paymentInformationPK;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	
}
