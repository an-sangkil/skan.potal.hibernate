package com.skan.tms.mobile.web.payment.normal;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skan.tms.mobile.web.model.ResponsePaymentTransaction;

/**
 * 
 * Description :  지불 요청 및 결과 처리 PAGE   
 * @author skan
 * @since  2016. 12. 2.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Service
public class KcpPaymentService extends PaymentService {

	@Override
	public ResponsePaymentTransaction payment(HttpServletRequest request, BigDecimal totalPrice) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	ResponsePaymentTransaction autoPaymentCancle(String tno, String custIp, String ordrId, String resonMessage)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePaymentTransaction paymentCancle(HttpServletRequest request, String transactionNo, String reason)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//	
//	@Autowired PaymentConfig paymentConfig;
//	
//	// 환경설정 파일 
//	private String g_conf_log_level = PaymentConfig.g_conf_log_level;
//	private String g_conf_gw_port   = PaymentConfig.g_conf_gw_port;
//	private int    g_conf_tx_mode   = PaymentConfig.g_conf_tx_mode;
//
//	@Override
//	public ResponsePaymentTransaction payment(HttpServletRequest request, BigDecimal totalPrice) throws Exception {
//		
//		
//		ResponsePaymentTransaction paymentTransaction = new ResponsePaymentTransaction();
//		String message = "";
//		if (!request.getMethod().equals("POST")) {
//			message = "잘못된 경로로 접속하였습니다.";
//			// logger.info(message);
//			paymentTransaction.setCheckWhether(false);
//		    paymentTransaction.setResponseMessage(message);
//			
//			return paymentTransaction;
//		}
//		
//		request.setCharacterEncoding ( "euc-kr" ) ;
//	    /* ============================================================================== */
//	    /* =   02. 지불 요청 정보 설정                                                  = */
//	    /* = -------------------------------------------------------------------------- = */
//	    String req_tx         = StringUtils.defaultString(request.getParameter( "req_tx"         ) ); // 요청 종류
//	    String tran_cd        = StringUtils.defaultString( request.getParameter( "tran_cd"        ) ); // 처리 종류
//	    /* = -------------------------------------------------------------------------- = */
//	    String cust_ip        = StringUtils.defaultString( request.getRemoteAddr()                  ); // 요청 IP
//	    String ordr_idxx      = StringUtils.defaultString( request.getParameter( "ordr_idxx"      ) ); // 쇼핑몰 주문번호
//	    String good_name      = StringUtils.defaultString( request.getParameter( "good_name"      ) ); // 상품명
//	    String good_mny       = StringUtils.defaultString( request.getParameter( "good_mny"       ) ); // 결제 총금액
//	    /* = -------------------------------------------------------------------------- = */
//	    String res_cd         = "";                                                     // 응답코드
//	    String res_msg        = "";                                                     // 응답 메세지
//	    String tno            = StringUtils.defaultString( request.getParameter( "tno"            ) ); // KCP 거래 고유 번호
//	    /* = -------------------------------------------------------------------------- = */
//	    String buyr_name      = StringUtils.defaultString( request.getParameter( "buyr_name"      ) ); // 주문자명
//	    String buyr_tel1      = StringUtils.defaultString( request.getParameter( "buyr_tel1"      ) ); // 주문자 전화번호
//	    String buyr_tel2      = StringUtils.defaultString( request.getParameter( "buyr_tel2"      ) ); // 주문자 핸드폰 번호
//	    String buyr_mail      = StringUtils.defaultString( request.getParameter( "buyr_mail"      ) ); // 주문자 E-mail 주소
//	    /* = -------------------------------------------------------------------------- = */
//	    String use_pay_method = StringUtils.defaultString( request.getParameter( "use_pay_method" ) ); // 결제 방법
//	    String bSucc          = "";                                                     // 업체 DB 처리 성공 여부
//	    /* = -------------------------------------------------------------------------- = */
//	    String app_time       = "";                                                     // 승인시간 (모든 결제 수단 공통)
//	    String amount         = "";                                                     // KCP 실제 거래금액         
//	    String total_amount   = "0";                                                    // 복합결제시 총 거래금액
//	    String coupon_mny     = "";                                                     // 쿠폰금액
//	    /* = -------------------------------------------------------------------------- = */
//	    String card_no		  = "";														// 신용카드 번호 
//	    String card_cd        = "";                                                     // 신용카드 코드
//	    String card_name      = "";                                                     // 신용카드 명
//	    String app_no         = "";                                                     // 신용카드 승인번호
//	    String noinf          = "";                                                     // 신용카드 무이자 여부
//	    String quota          = "";                                                     // 신용카드 할부개월
//	    String partcanc_yn    = "";                                                     // 부분취소 가능유무
//	    String card_bin_type_01 = "";                                                   // 카드구분1
//	    String card_bin_type_02 = "";                                                   // 카드구분2
//	    String card_mny       = "";                                                     // 카드결제금액
//	    /* = -------------------------------------------------------------------------- = */
//	    String bank_name      = "";                                                     // 은행명
//	    String bank_code      = "";                                                     // 은행코드
//	    String bk_mny         = "";                                                     // 계좌이체결제금액
//	    /* = -------------------------------------------------------------------------- = */
//	    String bankname       = "";                                                     // 입금 은행명
//	    String depositor      = "";                                                     // 입금 계좌 예금주 성명
//	    String account        = "";                                                     // 입금 계좌 번호
//	    String va_date        = "";                                                     // 가상계좌 입금마감시간
//	    /* = -------------------------------------------------------------------------- = */
//	    String pnt_issue      = "";                                                     // 결제 포인트사 코드
//	    String pnt_amount     = "";                                                     // 적립금액 or 사용금액
//	    String pnt_app_time   = "";                                                     // 승인시간
//	    String pnt_app_no     = "";                                                     // 승인번호
//	    String add_pnt        = "";                                                     // 발생 포인트
//	    String use_pnt        = "";                                                     // 사용가능 포인트
//	    String rsv_pnt        = "";                                                     // 총 누적 포인트
//	    /* = -------------------------------------------------------------------------- = */
//	    String commid         = "";                                                     // 통신사코드
//	    String mobile_no      = "";                                                     // 휴대폰번호
//	    /* = -------------------------------------------------------------------------- = */
//	    String shop_user_id   = StringUtils.defaultString( request.getParameter( "shop_user_id"   ) ); // 가맹점 고객 아이디
//	    String tk_van_code    = "";                                                     // 발급사코드
//	    String tk_app_no      = "";                                                     // 승인번호
//	    /* = -------------------------------------------------------------------------- = */
//	    String cash_yn        = StringUtils.defaultString( request.getParameter( "cash_yn"        ) ); // 현금 영수증 등록 여부
//	    String cash_authno    = "";                                                     // 현금 영수증 승인 번호
//	    String cash_tr_code   = StringUtils.defaultString( request.getParameter( "cash_tr_code"   ) ); // 현금 영수증 발행 구분
//	    String cash_id_info   = StringUtils.defaultString( request.getParameter( "cash_id_info"   ) ); // 현금 영수증 등록 번호
//	    /* ============================================================================== */
//	    /* =   02. 지불 요청 정보 설정 END
//	    /* ============================================================================== */
//
//
//	    /* ============================================================================== */
//	    /* =   03. 인스턴스 생성 및 초기화(변경 불가)                                   = */
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =       결제에 필요한 인스턴스를 생성하고 초기화 합니다.                     = */
//	    /* = -------------------------------------------------------------------------- = */
//	    J_PP_CLI_N c_PayPlus = new J_PP_CLI_N();
//
//	    c_PayPlus.mf_init( "", paymentConfig.g_conf_gw_url, g_conf_gw_port, g_conf_tx_mode, paymentConfig.g_conf_log_dir );
//	    c_PayPlus.mf_init_set();   
//
//	    /* ============================================================================== */
//	    /* =   03. 인스턴스 생성 및 초기화 END                                          = */
//	    /* ============================================================================== */
//
//
//	    /* ============================================================================== */
//	    /* =   04. 처리 요청 정보 설정                                                  = */
//	    /* = -------------------------------------------------------------------------- = */
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   04-1. 승인 요청 정보 설정                                                = */
//	    /* = -------------------------------------------------------------------------- = */
//	    if ( req_tx.equals( "pay" ) )
//	    {
//	            c_PayPlus.mf_set_enc_data( StringUtils.defaultString( request.getParameter( "enc_data" ) ),
//	                                       StringUtils.defaultString( request.getParameter( "enc_info" ) ) );
//
//	            /* 1 원은 실제로 업체에서 결제하셔야 될 원 금액을 넣어주셔야 합니다. 결제금액 유효성 검증 */
//	            if(good_mny.trim().length() > 0)
//	            {
//	                int ordr_data_set_no;
//
//	                ordr_data_set_no = c_PayPlus.mf_add_set( "ordr_data" );
//
//	                c_PayPlus.mf_set_us( ordr_data_set_no, "ordr_mony",  totalPrice.toString());
//	            }
//	            
//	    }
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   04. 처리 요청 정보 설정 END                                              = */
//	    /* = ========================================================================== = */
//
//
//	    /* = ========================================================================== = */
//	    /* =   05. 실행                                                                 = */
//	    /* = -------------------------------------------------------------------------- = */
//	    if ( tran_cd.length() > 0 )
//	    {
//	        c_PayPlus.mf_do_tx( paymentConfig.g_conf_site_cd, paymentConfig.g_conf_site_key, tran_cd, "", ordr_idxx, g_conf_log_level, "1" );
//	    }
//	    else
//	    {
//	        c_PayPlus.m_res_cd  = "9562";
//	        c_PayPlus.m_res_msg = "연동 오류|Payplus Plugin이 설치되지 않았거나 tran_cd값이 설정되지 않았습니다.";
//	    }
//
//	        res_cd  = c_PayPlus.m_res_cd;  // 결과 코드
//	        res_msg = c_PayPlus.m_res_msg; // 결과 메시지
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   05. 실행 END                                                             = */
//	    /* ============================================================================== */
//
//
//	    /* ============================================================================== */
//	    /* =   06. 승인 결과 값 추출                                                    = */
//	    /* = -------------------------------------------------------------------------- = */
//	    if ( req_tx.equals( "pay" ) )
//	    {
//	        if ( res_cd.equals( "0000" ) )
//	        {
//	            tno       = c_PayPlus.mf_get_res( "tno"       ); // KCP 거래 고유 번호
//	            amount    = c_PayPlus.mf_get_res( "amount"    ); // KCP 실제 거래 금액
//	            pnt_issue = c_PayPlus.mf_get_res( "pnt_issue" ); // 결제 포인트사 코드
//	            coupon_mny = c_PayPlus.mf_get_res( "coupon_mny"	); // 쿠폰금액
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   06-1. 신용카드 승인 결과 처리                                            = */
//	    /* = -------------------------------------------------------------------------- = */
//	            if ( use_pay_method.equals( "100000000000" ) )
//	            {
//	                card_cd   = c_PayPlus.mf_get_res( "card_cd"   ); // 카드사 코드
//	                card_no   = c_PayPlus.mf_get_res( "card_no"   ); // 카드사 코드
//	                card_name = c_PayPlus.mf_get_res( "card_name" ); // 카드사 명
//	                app_time  = c_PayPlus.mf_get_res( "app_time"  ); // 승인시간
//	                app_no    = c_PayPlus.mf_get_res( "app_no"    ); // 승인번호
//	                noinf     = c_PayPlus.mf_get_res( "noinf"     ); // 무이자 여부
//	                quota     = c_PayPlus.mf_get_res( "quota"     ); // 할부 개월 수
//	                partcanc_yn = c_PayPlus.mf_get_res( "partcanc_yn"     ); // 부분취소 가능유무
//	                card_bin_type_01 = c_PayPlus.mf_get_res( "card_bin_type_01" ); // 카드구분1
//	                card_bin_type_02 = c_PayPlus.mf_get_res( "card_bin_type_02" ); // 카드구분2
//	                card_mny = c_PayPlus.mf_get_res( "card_mny" ); // 카드결제금액
//
//	                /* = -------------------------------------------------------------- = */
//	                /* =   06-1.1. 복합결제(포인트+신용카드) 승인 결과 처리             = */
//	                /* = -------------------------------------------------------------- = */
//	                if ( pnt_issue.equals( "SCSK" ) || pnt_issue.equals( "SCWB" ) )
//	                {
//	                    pnt_amount   = c_PayPlus.mf_get_res( "pnt_amount"   ); // 적립금액 or 사용금액
//	                    pnt_app_time = c_PayPlus.mf_get_res( "pnt_app_time" ); // 승인시간
//	                    pnt_app_no   = c_PayPlus.mf_get_res( "pnt_app_no"   ); // 승인번호
//	                    add_pnt      = c_PayPlus.mf_get_res( "add_pnt"      ); // 발생 포인트
//	                    use_pnt      = c_PayPlus.mf_get_res( "use_pnt"      ); // 사용가능 포인트
//	                    rsv_pnt      = c_PayPlus.mf_get_res( "rsv_pnt"      ); // 총 누적 포인트
//	                    total_amount = amount + pnt_amount;                    // 복합결제시 총 거래금액
//	                }
//	            }
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   06-2. 계좌이체 승인 결과 처리                                            = */
//	    /* = -------------------------------------------------------------------------- = */
//	            if ( use_pay_method.equals("010000000000") )
//	            {
//	                app_time  = c_PayPlus.mf_get_res( "app_time"  ); // 승인시간
//	                bank_name = c_PayPlus.mf_get_res( "bank_name" ); // 은행명
//	                bank_code = c_PayPlus.mf_get_res( "bank_code" ); // 은행코드
//	                bk_mny    = c_PayPlus.mf_get_res( "bk_mny"    ); // 계좌이체결제금액
//	            }
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   06-3. 가상계좌 승인 결과 처리                                            = */
//	    /* = -------------------------------------------------------------------------- = */
//	            if ( use_pay_method.equals( "001000000000" ) )
//	            {
//	                bankname  = c_PayPlus.mf_get_res( "bankname"  ); // 입금할 은행 이름
//	                depositor = c_PayPlus.mf_get_res( "depositor" ); // 입금할 계좌 예금주
//	                account   = c_PayPlus.mf_get_res( "account"   ); // 입금할 계좌 번호
//	                va_date   = c_PayPlus.mf_get_res( "va_date"   ); // 가상계좌 입금마감시간
//	                
//	                paymentTransaction.setBankName(bankname);
//	                paymentTransaction.setDepositor(depositor);
//	                paymentTransaction.setAccount(account);
//	                paymentTransaction.setVirtualEndDate(va_date);
//	                
//	            }
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   06-4. 포인트 승인 결과 처리                                              = */
//	    /* = -------------------------------------------------------------------------- = */
//	            if ( use_pay_method.equals( "000100000000" ) )
//	            {
//	                pnt_amount   = c_PayPlus.mf_get_res( "pnt_amount"   ); // 적립금액 or 사용금액
//	                pnt_app_time = c_PayPlus.mf_get_res( "pnt_app_time" ); // 승인시간
//	                pnt_app_no   = c_PayPlus.mf_get_res( "pnt_app_no"   ); // 승인번호
//	                add_pnt      = c_PayPlus.mf_get_res( "add_pnt"      ); // 발생 포인트
//	                use_pnt      = c_PayPlus.mf_get_res( "use_pnt"      ); // 사용가능 포인트
//	                rsv_pnt      = c_PayPlus.mf_get_res( "rsv_pnt"      ); // 총 누적 포인트
//	            }
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   06-5. 휴대폰 승인 결과 처리                                              = */
//	    /* = -------------------------------------------------------------------------- = */
//	            if ( use_pay_method.equals( "000010000000" ) )
//	            {
//	                app_time = c_PayPlus.mf_get_res( "hp_app_time" ); // 승인 시간
//	                commid	 = c_PayPlus.mf_get_res( "commid"	   ); // 통신사 코드
//	                mobile_no= c_PayPlus.mf_get_res( "mobile_no"   ); // 휴대폰 번호
//	            }
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   06-6. 상품권 승인 결과 처리                                              = */
//	    /* = -------------------------------------------------------------------------- = */
//	            if ( use_pay_method.equals( "000000001000" ) )
//	            {
//	                app_time    = c_PayPlus.mf_get_res( "tk_app_time" ); // 승인 시간
//	                tk_van_code = c_PayPlus.mf_get_res( "tk_van_code" ); // 발급사 코드
//	                tk_app_no   = c_PayPlus.mf_get_res( "tk_app_no"   ); // 승인 번호
//	            }
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   06-7. 현금영수증 승인 결과 처리                                          = */
//	    /* = -------------------------------------------------------------------------- = */
//	            cash_authno = c_PayPlus.mf_get_res( "cash_authno" ); // 현금영수증 승인번호
//	        }
//	    }
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   06. 승인 결과 처리 END                                                   = */
//	    /* ============================================================================== */
//
//
//	    /* = ========================================================================== = */
//	    /* =   07. 승인 및 실패 결과 DB 처리                                            = */
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =      결과를 업체 자체적으로 DB 처리 작업하시는 부분입니다.                 = */
//	    /* = -------------------------------------------------------------------------- = */
//
//	    if ( req_tx.equals( "pay" ) )
//	    {
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   07-1. 승인 결과 DB 처리(res_cd == "0000")                                = */
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =        각 결제수단을 구분하시어 DB 처리를 하시기 바랍니다.                 = */
//	    /* = -------------------------------------------------------------------------- = */
//	        if ( res_cd.equals( "0000" ) )
//	        {
//	        	paymentTransaction.setCheckWhether(true);
//	            // 07-1-1. 신용카드
//	            //if ( use_pay_method.equals( "100000000000" ) ) {
//	                  // 07-1-1-1. 복합결제(신용카드+포인트)
//	            //    if ( pnt_issue.equals( "SCSK" ) || pnt_issue.equals( "SCWB" ) ){}
//	            //}
//				// 07-1-2. 계좌이체
//				//if (use_pay_method.equals("010000000000")) {}
//				// 07-1-3. 가상계좌
//				//if (use_pay_method.equals("001000000000")) {}
//				// 07-1-4. 포인트
//				//if (use_pay_method.equals("000100000000")) {}
//				// 07-1-5. 휴대폰
//				//if (use_pay_method.equals("000010000000")) {}
//				// 07-1-6. 상품권
//				//if (use_pay_method.equals("000000001000")) {}
//	        }
//
//	        /* = -------------------------------------------------------------------------- = */
//	        /* =   07-2. 승인 실패 DB 처리(res_cd != "0000")                                = */
//	        /* = -------------------------------------------------------------------------- = */
//	        if( !"0000".equals ( res_cd ) )
//	        {
//	        }
//	    }
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   07. 승인 및 실패 결과 DB 처리 END                                        = */
//	    /* = ========================================================================== = */
//	    paymentTransaction.setCardName(card_name);
//	    paymentTransaction.setCardNo(card_no);
//	    paymentTransaction.setAmount(amount);
//	    paymentTransaction.setTno(tno); 
//	    paymentTransaction.setResponseCode(res_cd);
//	    paymentTransaction.setResponseMessage(res_msg);
//	    paymentTransaction.setOrderIdx(ordr_idxx);
//	    
//	    //logger.info("결제 결과 코드    = {} " , res_cd);
//	    //logger.info("결제 결과 메세지 = {} " , res_msg);
//
//		return paymentTransaction;
//	}
//
//	@Override
//	public ResponsePaymentTransaction autoPaymentCancle(String tno, String custIp, String ordrId, String resonMessage) throws Exception {
//		/* = ========================================================================== = */
//	    /* =   08. 승인 결과 DB 처리 실패시 : 자동취소                                  = */
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =      승인 결과를 DB 작업 하는 과정에서 정상적으로 승인된 건에 대해         = */
//	    /* =      DB 작업을 실패하여 DB update 가 완료되지 않은 경우, 자동으로          = */
//	    /* =      승인 취소 요청을 하는 프로세스가 구성되어 있습니다.                   = */
//	    /* =                                                                            = */
//	    /* =      DB 작업이 실패 한 경우, bSucc 라는 변수(String)의 값을 "false"        = */
//	    /* =      로 설정해 주시기 바랍니다. (DB 작업 성공의 경우에는 "false" 이외의    = */
//	    /* =      값을 설정하시면 됩니다.)                                              = */
//	    /* = -------------------------------------------------------------------------- = */
//		
//		ResponsePaymentTransaction paymentTransaction = new ResponsePaymentTransaction();
//		String res_cd  = "";                               // 결과 코드
//	    String res_msg ;
//		try {
//			
//			// 승인 결과 DB 처리 에러시 bSucc값을 false로 설정하여 거래건을 취소 요청
//			J_PP_CLI_N c_PayPlus = new J_PP_CLI_N();
//
//			c_PayPlus.mf_init( "", paymentConfig.g_conf_gw_url, g_conf_gw_port, g_conf_tx_mode, paymentConfig.g_conf_log_dir );
//			c_PayPlus.mf_init_set();   
//		    int mod_data_set_no;
//
//		    c_PayPlus.mf_init_set();
//
//		    String tran_cd = "00200000";
//
//		    mod_data_set_no = c_PayPlus.mf_add_set( "mod_data" );
//
//		    c_PayPlus.mf_set_us( mod_data_set_no, "tno"      , tno      ); // KCP 원거래 거래번호
//		    c_PayPlus.mf_set_us( mod_data_set_no, "mod_type" , "STSC"   ); // 원거래 변경 요청 종류
//		    c_PayPlus.mf_set_us( mod_data_set_no, "mod_ip"   , custIp  ); // 변경 요청자 IP
//		    c_PayPlus.mf_set_us( mod_data_set_no, "mod_desc" , resonMessage  ); // 변경 사유
//
//		    c_PayPlus.mf_do_tx( paymentConfig.g_conf_site_cd, paymentConfig.g_conf_site_key, tran_cd, "", ordrId, g_conf_log_level, "1" );
//
//		    res_cd  = c_PayPlus.m_res_cd;                                 // 결과 코드
//		    res_msg = c_PayPlus.m_res_msg;                                // 결과 메시지
//		    
//		    
//		    paymentTransaction.setResponseCode(res_cd);
//		    paymentTransaction.setResponseMessage(res_msg);
//		} catch (Exception e) {
//			paymentTransaction.setResponseCode(res_cd);
//			//TODO 
//		    paymentTransaction.setResponseMessage("심각!!!!  결제 자동 취소에 실패 하였습니다.");
//			
//		    e.printStackTrace();
//		}
//		
//		return paymentTransaction;
//	}
//
//	@Override
//	public ResponsePaymentTransaction paymentCancle(HttpServletRequest request , String transactionNo, String reason) throws Exception {
//
//		
//		ResponsePaymentTransaction paymentTransaction = new ResponsePaymentTransaction();
//		String message = "";
//		if (!request.getMethod().equals("POST")) {
//			message = "잘못된 경로로 접속하였습니다.";
//			
//			paymentTransaction.setCheckWhether(false);
//			paymentTransaction.setResponseMessage(message);
//			return paymentTransaction;
//		}
//
//		request.setCharacterEncoding ( "euc-kr" ) ;
//	    /* ============================================================================== */
//	    /* =   02. 지불 요청 정보 설정                                                  = */
//	    /* = -------------------------------------------------------------------------- = */
//		String req_tx         = "";                                                     // 취소요청
//	    String tran_cd        = "";                                                     // 업무코드
//	    String cust_ip        =  request.getRemoteAddr()                  			   ;// 요청 IP
//	    /* = -------------------------------------------------------------------------- = */
//	    String res_cd         = "";                                                     // 응답코드
//	    String res_msg        = "";                                                     // 응답 메세지
//	    String tno            = "";                                                     // KCP 거래 고유 번호
//	    /* = -------------------------------------------------------------------------- = */
//	    String mod_type       = "";                                                     // 변경TYPE(승인취소시 필요)
//	    String mod_desc       = "";                                                     // 변경사유
//	    String panc_mod_mny   = "";                                                     // 부분취소 금액
//	    String panc_rem_mny   = "";                                                     // 부분취소 가능 금액
//	    String mod_tax_mny    = "";                                                     // 공급가 부분 취소 요청 금액
//	    String mod_vat_mny    = "";                                                     // 부과세 부분 취소 요청 금액
//	    String mod_free_mny   = "";                                                     // 비과세 부분 취소 요청 금액
//	    /* ============================================================================== */
//	    /* =   02. 지불 요청 정보 설정 END                                              = */
//	    /* ============================================================================== */
//
//
//	    /* ============================================================================== */
//	    /* =   03. 인스턴스 생성 및 초기화(변경 불가)                                   = */
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =       결제에 필요한 인스턴스를 생성하고 초기화 합니다.                     = */
//	    /* = -------------------------------------------------------------------------- = */
//		J_PP_CLI_N c_PayPlus = new J_PP_CLI_N();
//
//		c_PayPlus.mf_init("", paymentConfig.g_conf_gw_url, g_conf_gw_port, g_conf_tx_mode, paymentConfig.g_conf_log_dir);
//		c_PayPlus.mf_init_set();
//
//	    /* ============================================================================== */
//	    /* =   03. 인스턴스 생성 및 초기화 END                                          = */
//	    /* ============================================================================== */
//
//
//	    /* ============================================================================== */
//	    /* =   04. 처리 요청 정보 설정                                                  = */
//	    /* = -------------------------------------------------------------------------- = */
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   04-1. 취소/매입 요청                                                     = */
//	    /* = -------------------------------------------------------------------------- = */
//	    if ( req_tx.equals( "" ) )
//	    {
//	        int    mod_data_set_no;
//
//	        tran_cd = "00200000";
//	        mod_data_set_no = c_PayPlus.mf_add_set( "mod_data" );
//
//	        c_PayPlus.mf_set_us( mod_data_set_no, "tno",        transactionNo          ); // KCP 원거래 거래번호
//	        c_PayPlus.mf_set_us( mod_data_set_no, "mod_type",   "STSC"      ); // 전체취소 STSC / 부분취소 STPC 
//	        c_PayPlus.mf_set_us( mod_data_set_no, "mod_ip",     cust_ip     ); // 변경 요청자 IP
//	        c_PayPlus.mf_set_us( mod_data_set_no, "mod_desc",   reason          ); // 변경 사유
//
//	        if ( mod_type.equals( "STPC" ) ) // 부분취소의 경우
//	        {
//	            c_PayPlus.mf_set_us( mod_data_set_no, "mod_mny", "" ); // 취소요청금액
//	            c_PayPlus.mf_set_us( mod_data_set_no, "rem_mny", "" ); // 취소가능잔액
//
//	            //복합거래 부분 취소시 주석을 풀어 주시기 바랍니다.
//	            //c_PayPlus.mf_set_us( mod_data_set_no, "tax_flag",     "TG03"                       ); // 복합과세 구분
//	            //c_PayPlus.mf_set_us( mod_data_set_no, "mod_tax_mny",  mod_tax_mny                  ); // 공급가 부분 취소 요청 금액
//	            //c_PayPlus.mf_set_us( mod_data_set_no, "mod_vat_mny",  mod_vat_mny                  ); // 부과세 부분 취소 요청 금액
//	            //c_PayPlus.mf_set_us( mod_data_set_no, "mod_free_mny", mod_free_mny                 ); // 비과세 부분 취소 요청 금액
//	        }
//	    }
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   04. 처리 요청 정보 설정 END                                              = */
//	    /* = ========================================================================== = */
//
//
//	    /* = ========================================================================== = */
//	    /* =   05. 실행                                                                 = */
//	    /* = -------------------------------------------------------------------------- = */
//	    if ( tran_cd.length() > 0 )
//	    {
//	        c_PayPlus.mf_do_tx( paymentConfig.g_conf_site_cd, paymentConfig.g_conf_site_key, tran_cd, "", "", PaymentConfig.g_conf_log_level, "1" );
//
//	        res_cd  = c_PayPlus.m_res_cd;  // 결과 코드
//	        res_msg = c_PayPlus.m_res_msg; // 결과 메시지
//	    }
//	    else
//	    {
//	        c_PayPlus.m_res_cd  = "9562";
//	        c_PayPlus.m_res_msg = "연동 오류";
//	    }
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   05. 실행 END                                                             = */
//	    /* ============================================================================== */
//
//		if ( res_cd.equals( "0000" ) ) // 정상결제 인 경우
//	    {
//	        logger.info( "취소요청이 완료되었습니다.      <br>");
//	        logger.info( "결과코드 : "      + res_cd   + "<br>");
//	        logger.info( "결과메세지 : "    + res_msg  + "<p>");
//	    }
//	    else
//	    {
//	        logger.info( "취소요청이 처리 되지 못하였습니다.  <br>");
//	        logger.info( "결과코드 : "      + res_cd       + "<br>");
//	        logger.info( "결과메세지 : "    + res_msg      + "<p>");
//	    }
//
//		paymentTransaction.setResponseCode(res_cd);
//	    paymentTransaction.setResponseMessage(res_msg);
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   06. 취소 결과 처리 END                                                   = */
//	    /* ============================================================================== */
//		
//		return paymentTransaction;
//	}

}
