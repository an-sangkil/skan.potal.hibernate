package com.skan.tms.mobile.web.payment.normal;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skan.tms.mobile.web.model.ResponsePaymentTransaction;

@Component
public class KcpPaymentServiceAdmin extends PaymentService {

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
	
//	@Autowired private PaymentAdminConfig paymentAdminConfig; 
//
//	@Override
//	public ResponsePaymentTransaction payment(HttpServletRequest request, BigDecimal totalPrice) throws Exception {
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
//		
//		/* ============================================================================== */
//
//	    /* ============================================================================== */
//	    /* =   01. 지불 요청 정보 설정                                                  = */
//	    /* = -------------------------------------------------------------------------- = */
//	    String    pay_method = StringUtils.defaultString( request.getParameter( "pay_method" ) ); // 결제 방법
//	    String    ordr_idxx  = StringUtils.defaultString( request.getParameter( "ordr_idxx"  ) ); // 주문 번호
//	    String    good_name  = StringUtils.defaultString( request.getParameter( "good_name"  ) ); // 상품 정보
//	    String    good_mny   = StringUtils.defaultString( request.getParameter( "good_mny"   ) ); // 결제 금액
//	    String    buyr_name  = StringUtils.defaultString( request.getParameter( "buyr_name"  ) ); // 주문자 이름
//	    String    buyr_mail  = StringUtils.defaultString( request.getParameter( "buyr_mail"  ) ); // 주문자 E-Mail
//	    String    buyr_tel1  = StringUtils.defaultString( request.getParameter( "buyr_tel1"  ) ); // 주문자 전화번호
//	    String    buyr_tel2  = StringUtils.defaultString( request.getParameter( "buyr_tel2"  ) ); // 주문자 휴대폰번호
//	    String    req_tx     = StringUtils.defaultString( request.getParameter( "req_tx"     ) ); // 요청 종류
//	    String    currency   = StringUtils.defaultString( request.getParameter( "currency"   ) ); // 화폐단위 (WON/USD)
//	    String    quotaopt   = StringUtils.defaultString( request.getParameter( "quotaopt"   ) ); // 할부개월수 옵션
//	    /* = -------------------------------------------------------------------------- = */
//	    String    mod_type     = StringUtils.defaultString( request.getParameter( "mod_type"    ) ); // 변경TYPE(승인취소시 필요)
//	    String    mod_desc     = StringUtils.defaultString( request.getParameter( "mod_desc"    ) ); // 변경사유
//	    String    panc_mod_mny = "";                                                  // 부분취소 금액
//	    String    panc_rem_mny = "";                                                  // 부분취소 가능 금액
//	    String    mod_tax_mny  = StringUtils.defaultString( request.getParameter( "mod_tax_mny" ) ); // 공급가 부분 취소 요청 금액
//	    String    mod_vat_mny  = StringUtils.defaultString( request.getParameter( "mod_vat_mny" ) ); // 부과세 부분 취소 요청 금액
//	    String    mod_free_mny = StringUtils.defaultString( request.getParameter( "mod_free_mny") ); // 비과세 부분 취소 요청 금액
//	    /* = -------------------------------------------------------------------------- = */
//	    String    tran_cd    = "";                                                 // 트랜잭션 코드
//	    String    bSucc      = "";                                                 // DB 작업 성공 여부
//	    /* = -------------------------------------------------------------------------- = */
//	    String    res_cd     = "";                                                 // 결과코드
//	    String    res_msg    = "";                                                 // 결과메시지
//	    String    tno        = "";                                                 // 거래번호
//	    /* = -------------------------------------------------------------------------- = */
//	    String    amount          = StringUtils.defaultString( request.getParameter( "amount"          ) );  // 결제 금액
//	    String    card_pay_method = StringUtils.defaultString( request.getParameter( "card_pay_method" ) );  // 카드 결제 방법
//	    String    card_cd         = "";                                                       // 카드 코드
//	    String    card_name       = "";                                                       // 카드명
//	    String    app_time        = "";                                                       // 승인시간
//	    String    app_no          = "";                                                       // 승인번호
//	    String    noinf           = "";                                                       // 무이자여부
//	    String    quota           = "";                                                       // 할부개월
//	    /* ============================================================================== */
//
//
//	    /* ============================================================================== */
//	    /* =   02. 인스턴스 생성 및 초기화                                              = */
//	    /* = -------------------------------------------------------------------------- = */
//	    J_PP_CLI_N  c_PayPlus = new J_PP_CLI_N();
//
//	    c_PayPlus.mf_init( "", paymentAdminConfig.g_conf_gw_url, PaymentAdminConfig.g_conf_gw_port, PaymentAdminConfig.g_conf_tx_mode, paymentAdminConfig.g_conf_log_dir );
//	    c_PayPlus.mf_init_set();
//	    /* ============================================================================== */
//
//
//	    /* ============================================================================== */
//	    /* =   03. 처리 요청 정보 설정, 실행                                            = */
//	    /* = -------------------------------------------------------------------------- = */
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   03-1. 승인 요청                                                          = */
//	    /* = -------------------------------------------------------------------------- = */
//	        // 업체 환경 정보
//	        String cust_ip = request.getRemoteAddr();
//
//	        if ( req_tx.equals( "pay" ) )
//	        {
//	            tran_cd = "00100000";
//
//	            int payx_data_set;
//	            int common_data_set;
//
//	            payx_data_set   = c_PayPlus.mf_add_set( "payx_data" );
//	            common_data_set = c_PayPlus.mf_add_set( "common"    );
//
//
//	            c_PayPlus.mf_set_us( common_data_set, "amount",   good_mny    );
//	            c_PayPlus.mf_set_us( common_data_set, "currency", currency    );
//
//	            c_PayPlus.mf_set_us( common_data_set, "cust_ip",  cust_ip     );
//	            c_PayPlus.mf_set_us( common_data_set, "escw_mod", "N"         );
//
//	            c_PayPlus.mf_add_rs( payx_data_set, common_data_set );
//
//	            // 주문 정보
//	            int ordr_data_set;
//
//	            ordr_data_set = c_PayPlus.mf_add_set( "ordr_data" );
//
//	            c_PayPlus.mf_set_us( ordr_data_set, "ordr_idxx", ordr_idxx );
//	            c_PayPlus.mf_set_us( ordr_data_set, "good_name", good_name );
//	            c_PayPlus.mf_set_us( ordr_data_set, "good_mny",  good_mny  );
//	            c_PayPlus.mf_set_us( ordr_data_set, "buyr_name", buyr_name );
//	            c_PayPlus.mf_set_us( ordr_data_set, "buyr_tel1", buyr_tel1 );
//	            c_PayPlus.mf_set_us( ordr_data_set, "buyr_tel2", buyr_tel2 );
//	            c_PayPlus.mf_set_us( ordr_data_set, "buyr_mail", buyr_mail );
//
//	            // XXX 실제 결재 데이터를 넣는 부분
//	            if (pay_method.equals("CARD") )
//	            {
//	                int card_data_set;
//
//	                card_data_set = c_PayPlus.mf_add_set( "card" );
//
//	                c_PayPlus.mf_set_us( card_data_set, "card_mny",     good_mny   );        // 결제 금액             
//	                c_PayPlus.mf_set_us( card_data_set, "card_tx_type", "11111000" );
//	                c_PayPlus.mf_set_us( card_data_set, "quota",        request.getParameter( "quota"   ) );
//	                c_PayPlus.mf_set_us( card_data_set, "card_no",      request.getParameter( "card_no" ) );
//	                c_PayPlus.mf_set_us( card_data_set, "card_expiry",  request.getParameter( "expiry_yy" ) + request.getParameter( "expiry_mm" ) );               
//	                
//	                c_PayPlus.mf_add_rs( payx_data_set, card_data_set );
//	            }
//	        }
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   03-2. 취소/매입 요청                                                     = */
//	    /* = -------------------------------------------------------------------------- = */
//	        else if ( req_tx.equals( "mod" ) )
//	        {
//	            int     mod_data_set_no;
//
//	            tran_cd = "00200000";
//	            mod_data_set_no = c_PayPlus.mf_add_set( "mod_data" );
//
//	            c_PayPlus.mf_set_us( mod_data_set_no, "tno",        request.getParameter( "tno"       ) );      // KCP 원거래 거래번호
//	            c_PayPlus.mf_set_us( mod_data_set_no, "mod_type",   mod_type                            );      // 원거래 변경 요청 종류
//	            c_PayPlus.mf_set_us( mod_data_set_no, "mod_ip",     cust_ip                             );      // 변경 요청자 IP
//	            c_PayPlus.mf_set_us( mod_data_set_no, "mod_desc",   request.getParameter( "mod_desc"  ) );      // 변경 사유
//
//	            if ( mod_type.equals( "STPC" ) ) // 부분취소의 경우
//	            {
//	                c_PayPlus.mf_set_us( mod_data_set_no, "mod_mny", request.getParameter( "mod_mny" ) ); // 취소요청금액
//	                c_PayPlus.mf_set_us( mod_data_set_no, "rem_mny", request.getParameter( "rem_mny" ) ); // 취소가능잔액
//
//	                //복합거래 부분 취소시 주석을 풀어 주시기 바랍니다.
//	                //c_PayPlus.mf_set_us( mod_data_set_no, "tax_flag",     "TG03"                       ); // 복합과세 구분
//	                //c_PayPlus.mf_set_us( mod_data_set_no, "mod_tax_mny",  mod_tax_mny                  ); // 공급가 부분 취소 요청 금액
//	                //c_PayPlus.mf_set_us( mod_data_set_no, "mod_vat_mny",  mod_vat_mny                  ); // 부과세 부분 취소 요청 금액
//	                //c_PayPlus.mf_set_us( mod_data_set_no, "mod_free_mny", mod_free_mny                 ); // 비과세 부분 취소 요청 금액
//	            }
//	        }
//	    /* ============================================================================== */
//
//
//	    /* ============================================================================== */
//	    /* =   03-3. 실행                                                               = */
//	    /* ------------------------------------------------------------------------------ */
//	        if ( tran_cd.length() > 0 )
//	        {
//	            c_PayPlus.mf_do_tx( paymentAdminConfig.g_conf_site_cd, paymentAdminConfig.g_conf_site_key, tran_cd, cust_ip, ordr_idxx, PaymentAdminConfig.g_conf_log_level, "1" );
//	        }
//	        else
//	        {
//	            c_PayPlus.m_res_cd  = "9562";
//	            c_PayPlus.m_res_msg = "연동 오류";
//	        }
//	        res_cd  = c_PayPlus.m_res_cd;                      // 결과 코드
//	        res_msg = c_PayPlus.m_res_msg;                     // 결과 메시지
//	    /* ============================================================================== */
//
//
//	    /* ============================================================================== */
//	    /* =   04. 승인 결과 처리                                                       = */
//	    /* = -------------------------------------------------------------------------- = */
//	        if ( req_tx.equals( "pay" ) )
//	        {
//	            if ( res_cd.equals( "0000" ) )
//	            {
//	                tno = c_PayPlus.mf_get_res( "tno" );       // KCP 거래 고유 번호
//	                paymentTransaction.setCheckWhether(true);
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   04-1. 신용카드 승인 결과 처리                                            = */
//	    /* = -------------------------------------------------------------------------- = */
//	                if ( pay_method.equals( "CARD" ) )
//	                {
//	                    card_cd   = c_PayPlus.mf_get_res( "card_cd"   );
//	                    card_name = c_PayPlus.mf_get_res( "card_name" );
//	                    app_time  = c_PayPlus.mf_get_res( "app_time"  );
//	                    app_no    = c_PayPlus.mf_get_res( "app_no"    );
//	                    noinf     = c_PayPlus.mf_get_res( "noinf"     );
//	                    quota     = c_PayPlus.mf_get_res( "quota"     );
//	                }
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   04-2. 승인 결과를 업체 자체적으로 DB 처리 작업하시는 부분입니다.         = */
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =         승인 결과를 DB 작업 하는 과정에서 정상적으로 승인된 건에 대해      = */
//	    /* =         DB 작업을 실패하여 DB update 가 완료되지 않은 경우, 자동으로       = */
//	    /* =         승인 취소 요청을 하는 프로세스가 구성되어 있습니다.                = */
//	    /* =         DB 작업이 실패 한 경우, bSucc 라는 변수(String)의 값을 "false"     = */
//	    /* =         로 세팅해 주시기 바랍니다. (DB 작업 성공의 경우에는 "false" 이외의 = */
//	    /* =         값을 세팅하시면 됩니다.)                                           = */
//	    /* = -------------------------------------------------------------------------- = */
//	                bSucc = "";             // DB 작업 실패일 경우 "false" 로 세팅
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   04-3. DB 작업 실패일 경우 자동 승인 취소                                 = */
//	    /* = -------------------------------------------------------------------------- = */
//	                if ( bSucc.equals("false") )
//	                {
//	                    int mod_data_set_no;
//
//	                    c_PayPlus.mf_init_set();
//
//	                    tran_cd = "00200000";
//
//	                    mod_data_set_no = c_PayPlus.mf_add_set( "mod_data" );
//
//	                    c_PayPlus.mf_set_us( mod_data_set_no, "tno",      tno    );                         // KCP 원거래 거래번호
//	                    c_PayPlus.mf_set_us( mod_data_set_no, "mod_type", "STSC" );                         // 원거래 변경 요청 종류
//	                    c_PayPlus.mf_set_us( mod_data_set_no, "mod_ip",   cust_ip );                        // 변경 요청자 IP
//	                    c_PayPlus.mf_set_us( mod_data_set_no, "mod_desc", "가맹점 결과 처리 오류 - 가맹점에서 자동 취소 요청" );   // 변경 사유
//	                    c_PayPlus.mf_do_tx( paymentAdminConfig.g_conf_site_cd, paymentAdminConfig.g_conf_site_key, tran_cd, cust_ip, ordr_idxx, PaymentAdminConfig.g_conf_log_level, "1" );
//
//	                    res_cd  = c_PayPlus.m_res_cd;
//	                    res_msg = c_PayPlus.m_res_msg;
//	                }
//
//	            }    // End of [res_cd = "0000"]
//
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =   04-4. 승인 실패를 업체 자체적으로 DB 처리 작업하시는 부분입니다.         = */
//	    /* = -------------------------------------------------------------------------- = */
//	            else
//	            {
//	            }
//	        }
//	    /* ============================================================================== */
//
//
//	    /* ============================================================================== */
//	    /* =   05. 취소/매입 결과 처리                                                  = */
//	    /* = -------------------------------------------------------------------------- = */
//	        else if ( req_tx.equals( "mod" ) )
//	        {
//	            if ( res_cd.equals( "0000" ) )
//	            {
//	                if ( mod_type.equals ( "STPC") )
//	                {
//	                    amount       = c_PayPlus.mf_get_res( "amount"       ); // 총 금액
//	                    panc_mod_mny = c_PayPlus.mf_get_res( "panc_mod_mny" ); // 부분취소 요청금액
//	                    panc_rem_mny = c_PayPlus.mf_get_res( "panc_rem_mny" ); // 부분취소 가능금액
//	                }
//	            }
//	        }
//	    /* ============================================================================== */
//
//
//	    /* ============================================================================== */
//	    /* =   06. 폼 구성 및 결과페이지 호출                                           = */
//	    /* ============================================================================== */
//	        paymentTransaction.setAmount(amount);
//		    paymentTransaction.setTno(tno); 
//		    paymentTransaction.setResponseCode(res_cd);
//		    paymentTransaction.setResponseMessage(res_msg);
//		    paymentTransaction.setOrderIdx(ordr_idxx);
//		
//		
//		return paymentTransaction;
//	}
//
//	@Override
//	public ResponsePaymentTransaction autoPaymentCancle(String tno, String custIp, String ordrId, String resonMessage)
//			throws Exception {
//
//		ResponsePaymentTransaction paymentTransaction = new ResponsePaymentTransaction();
//		
//		String res_cd = ""; // 결과 코드
//		String res_msg;
//		try {
//
//			// 승인 결과 DB 처리 에러시 bSucc값을 false로 설정하여 거래건을 취소 요청
//			 /* ============================================================================== */
//		    /* =   02. 인스턴스 생성 및 초기화                                              = */
//		    /* = -------------------------------------------------------------------------- = */
//		    J_PP_CLI_N  c_PayPlus = new J_PP_CLI_N();
//
//		    c_PayPlus.mf_init( "", paymentAdminConfig.g_conf_gw_url, PaymentAdminConfig.g_conf_gw_port, PaymentAdminConfig.g_conf_tx_mode, paymentAdminConfig.g_conf_log_dir );
//		    c_PayPlus.mf_init_set();
//		    
//		    ////////////////////////////
//		    //
//		    ///////////////////////////
//
//			int mod_data_set_no;
//
//			c_PayPlus.mf_init_set();
//
//			String tran_cd = "00200000";
//
//			mod_data_set_no = c_PayPlus.mf_add_set("mod_data");
//
//			c_PayPlus.mf_set_us(mod_data_set_no, "tno", tno); // KCP 원거래 거래번호
//			c_PayPlus.mf_set_us(mod_data_set_no, "mod_type", "STSC"); // 원거래 변경
//																		// 요청 종류
//			c_PayPlus.mf_set_us(mod_data_set_no, "mod_ip", custIp); // 변경 요청자 IP
//			c_PayPlus.mf_set_us(mod_data_set_no, "mod_desc", "가맹점 결과 처리 오류 - 가맹점에서 자동 취소 요청"); // 변경 사유
//			c_PayPlus.mf_do_tx(paymentAdminConfig.g_conf_site_cd, paymentAdminConfig.g_conf_site_key, tran_cd, custIp, ordrId, PaymentAdminConfig.g_conf_log_level, "1");
//
//			res_cd = c_PayPlus.m_res_cd;
//			res_msg = c_PayPlus.m_res_msg;
//
//			paymentTransaction.setResponseCode(res_cd);
//		    paymentTransaction.setResponseMessage(res_msg);
//		} catch (Exception e) {
//			paymentTransaction.setResponseCode(res_cd);
//		    paymentTransaction.setResponseMessage("심각!!!!  결제 자동 취소에 실패 하였습니다.");
//
//			e.printStackTrace();
//		}
//
//		return paymentTransaction;
//	}
//
//	@Override
//	public ResponsePaymentTransaction paymentCancle(HttpServletRequest request, String transactionNo, String reason)
//			throws Exception {
//		ResponsePaymentTransaction paymentTransaction = new ResponsePaymentTransaction();
//		
//		String message = "";
//		if (!request.getMethod().equals("POST")) {
//			message = "잘못된 경로로 접속하였습니다.";
//			
//			paymentTransaction.setCheckWhether(false);
//		    paymentTransaction.setResponseMessage(message);
//			return paymentTransaction;
//		}
//
//		request.setCharacterEncoding ( "euc-kr" ) ;
//
//		String res_cd = ""; // 결과 코드
//		String res_msg;
//		try {
//
//			// 승인 결과 DB 처리 에러시 bSucc값을 false로 설정하여 거래건을 취소 요청
//			 /* ============================================================================== */
//		    /* =   02. 인스턴스 생성 및 초기화                                              = */
//		    /* = -------------------------------------------------------------------------- = */
//		    J_PP_CLI_N  c_PayPlus = new J_PP_CLI_N();
//
//		    c_PayPlus.mf_init( "", paymentAdminConfig.g_conf_gw_url, PaymentAdminConfig.g_conf_gw_port, PaymentAdminConfig.g_conf_tx_mode, paymentAdminConfig.g_conf_log_dir );
//		    c_PayPlus.mf_init_set();
//		    
//		    ////////////////////////////
//		    //
//		    ///////////////////////////
//
//			int mod_data_set_no;
//
//			c_PayPlus.mf_init_set();
//
//			String tran_cd = "00200000";
//
//			mod_data_set_no = c_PayPlus.mf_add_set("mod_data");
//
//			c_PayPlus.mf_set_us(mod_data_set_no, "tno", transactionNo); // KCP 원거래 거래번호
//			c_PayPlus.mf_set_us(mod_data_set_no, "mod_type", "STSC"); // 원거래 변경
//																		// 요청 종류
//			c_PayPlus.mf_set_us(mod_data_set_no, "mod_ip", request.getRemoteAddr()); // 변경 요청자 IP
//			c_PayPlus.mf_set_us(mod_data_set_no, "mod_desc", "가맹점 결과 처리 오류 - 가맹점에서 자동 취소 요청"); // 변경 사유
//			c_PayPlus.mf_do_tx(paymentAdminConfig.g_conf_site_cd, paymentAdminConfig.g_conf_site_key, tran_cd, request.getRemoteAddr(), "", PaymentAdminConfig.g_conf_log_level, "1");
//
//			res_cd = c_PayPlus.m_res_cd;
//			res_msg = c_PayPlus.m_res_msg;
//
//			paymentTransaction.setResponseCode(res_cd);
//		    paymentTransaction.setResponseMessage(res_msg);
//		} catch (Exception e) {
//			paymentTransaction.setResponseCode(res_cd);
//		    paymentTransaction.setResponseMessage("심각!!!!  결제 자동 취소에 실패 하였습니다.");
//
//			e.printStackTrace();
//		}
//
//		return paymentTransaction;
//	}

}
