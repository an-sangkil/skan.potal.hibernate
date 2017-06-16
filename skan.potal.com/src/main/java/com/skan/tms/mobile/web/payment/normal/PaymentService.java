package com.skan.tms.mobile.web.payment.normal;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skan.tms.mobile.web.controller.AbstractCommonController;
import com.skan.tms.mobile.web.model.ResponsePaymentTransaction;

/**
 * 
 * Description : 
 * @author skan
 * @since  2016. 12. 2.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */


@Component
public abstract class PaymentService extends AbstractCommonController{
	
	
	/**
	 * 결제 진행 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public abstract ResponsePaymentTransaction payment(HttpServletRequest request, BigDecimal totalPrice) throws Exception;
	
	/**
	 * 결제 취소 자동 취소 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	abstract ResponsePaymentTransaction autoPaymentCancle(String tno, String custIp, String ordrId, String resonMessage) throws Exception;
	
	/**
	 * 결제 취소 수동
	 * @param request
	 * @param transactionNo
	 * @param reason
	 * @return
	 * @throws Exception
	 */
	public abstract ResponsePaymentTransaction paymentCancle(HttpServletRequest request, String transactionNo, String reason) throws Exception;


	/**
	 * 가상계좌 공통 통보 페이지  
	 * @param request
	 */
	@Transactional(transactionManager="transactionManager")
	@Modifying
	public void virtual(HttpServletRequest request, HttpServletResponse response) {
		/* ============================================================================== */
	    /* =   01. 공통 통보 페이지 설명(필독!!)                                        = */
	    /* = -------------------------------------------------------------------------- = */
	    /* =   공통 통보 페이지에서는,                                                  = */
	    /* =   가상계좌 입금 통보 데이터를 KCP를 통해 실시간으로 통보 받을 수 있습니다. = */
	    /* =                                                                            = */
	    /* =   common_return 페이지는 이러한 통보 데이터를 받기 위한 샘플 페이지        = */
	    /* =   입니다. 현재의 페이지를 업체에 맞게 수정하신 후, 아래 사항을 참고하셔서  = */
	    /* =   KCP 관리자 페이지에 등록해 주시기 바랍니다.                              = */
	    /* =                                                                            = */
	    /* =   등록 방법은 다음과 같습니다.                                             = */
	    /* =  - KCP 관리자페이지(admin.kcp.co.kr)에 로그인 합니다.                      = */
	    /* =  - [쇼핑몰 관리] -> [정보변경] -> [공통 URL 정보] -> 공통 URL 변경 후에    = */
	    /* =    가맹점 URL을 입력합니다.                                                = */
	    /* ============================================================================== */


	    /* ============================================================================== */
	    /* =   02. 공통 통보 데이터 받기                                                = */
	    /* = -------------------------------------------------------------------------- = */
	    String site_cd      = StringUtils.defaultString( request.getParameter( "site_cd"      ) );  // 사이트 코드
	    String tno          = StringUtils.defaultString( request.getParameter( "tno"          ) );  // KCP 거래번호
	    String order_no     = StringUtils.defaultString( request.getParameter( "order_no"     ) );  // 주문번호
	    String tx_cd        = StringUtils.defaultString( request.getParameter( "tx_cd"        ) );  // 업무처리 구분 코드
	    String tx_tm        = StringUtils.defaultString( request.getParameter( "tx_tm"        ) );  // 업무처리 완료 시간
	    /* = -------------------------------------------------------------------------- = */
	    String ipgm_name    = "";                                                    // 주문자명
	    String remitter     = "";                                                    // 입금자명
	    String ipgm_mnyx    = "";                                                    // 입금 금액
	    String bank_code    = "";                                                    // 은행코드
	    String account      = "";                                                    // 가상계좌 입금계좌번호
	    String op_cd        = "";                                                    // 처리구분 코드
	    String noti_id      = "";                                                    // 통보 아이디
	    String cash_a_no    = "";                                                    // 현금영수증 승인번호
	    String cash_a_dt    = "";                                                    // 현금영수증 승인시간
	    /* = -------------------------------------------------------------------------- = */

	    /* = -------------------------------------------------------------------------- = */
	    /* =   02-1. 가상계좌 입금 통보 데이터 받기                                     = */
	    /* = -------------------------------------------------------------------------- = */
	    if ( tx_cd.equals("TX00") ) {
	        ipgm_name = StringUtils.defaultString( request.getParameter( "ipgm_name" ) );           // 주문자명
	        remitter  = StringUtils.defaultString( request.getParameter( "remitter"  ) );           // 입금자명
	        ipgm_mnyx = StringUtils.defaultString( request.getParameter( "ipgm_mnyx" ) );           // 입금 금액
	        bank_code = StringUtils.defaultString( request.getParameter( "bank_code" ) );           // 은행코드
	        account   = StringUtils.defaultString( request.getParameter( "account"   ) );           // 가상계좌 입금계좌번호
	        op_cd     = StringUtils.defaultString( request.getParameter( "op_cd"     ) );           // 처리구분 코드
	        noti_id   = StringUtils.defaultString( request.getParameter( "noti_id"   ) );           // 통보 아이디
	        cash_a_no = StringUtils.defaultString( request.getParameter( "cash_a_no" ) );           // 현금영수증 승인번호
	        cash_a_dt = StringUtils.defaultString( request.getParameter( "cash_a_dt" ) );           // 현금영수증 승인시간
	    }

	    /* ============================================================================== */
	    /* =   03. 공통 통보 결과를 업체 자체적으로 DB 처리 작업하시는 부분입니다.      = */
	    /* = -------------------------------------------------------------------------- = */
	    /* =   통보 결과를 DB 작업 하는 과정에서 정상적으로 통보된 건에 대해 DB 작업에  = */
	    /* =   실패하여 DB update 가 완료되지 않은 경우, 결과를 재통보 받을 수 있는     = */
	    /* =   프로세스가 구성되어 있습니다.                                            = */
	    /* =                                                                            = */
	    /* =   * DB update가 정상적으로 완료된 경우                                     = */
	    /* =   하단의 [04. result 값 세팅 하기] 에서 result 값의 value값을 0000으로     = */
	    /* =   설정해 주시기 바랍니다.                                                  = */
	    /* =                                                                            = */
	    /* =   * DB update가 실패한 경우                                                = */
	    /* =   하단의 [04. result 값 세팅 하기] 에서 result 값의 value값을 0000이외의   = */
	    /* =   값으로 설정해 주시기 바랍니다.                                           = */
	    /* = -------------------------------------------------------------------------- = */
	    
	    /* = -------------------------------------------------------------------------- = */
	    /* =   03-1. 가상계좌 입금 통보 데이터 DB 처리 작업 부분                        = */
	    /* = -------------------------------------------------------------------------- = */
	    long effcetCount = 0;
	    if ( tx_cd.equals("TX00") ) {
	    	// TODO 가상계좌 처리 완료 처리 .
	    }


	    /* = -------------------------------------------------------------------------- = */
	    /* =   03-2. 모바일계좌이체 통보 데이터 DB 처리 작업 부분                       = */
	    /* = -------------------------------------------------------------------------- = */
	    else if ( tx_cd.equals("TX08") ) {
	    	
	    }
	    /* ============================================================================== */


	    /* ============================================================================== */
	    /* =   04. result 값 세팅 하기                                                  = */
	    /* = -------------------------------------------------------------------------- = */
	    /* =   정상적으로 처리된 경우 value값을 0000으로 설정하여 주시기 바랍니다.      = */
	    /* ============================================================================== */
	    if(effcetCount > 0) {
	    	try (PrintWriter printWriter = response.getWriter();){
	    		
	    		printWriter.println("<html><body><form><input type='hidden' name='result' value='0000'></form></body></html>");
	    		printWriter.flush();
	    		
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    }
	}
}
