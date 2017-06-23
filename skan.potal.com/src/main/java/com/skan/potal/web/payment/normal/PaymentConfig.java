package com.skan.potal.web.payment.normal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * Description : 결재정보 환경설정  
 * @author skan
 * @since  2016. 12. 2.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Component
public class PaymentConfig {
	
	/* ============================================================================== */
    /* =   PAGE : 결제 정보 환경 설정 PAGE                                          = */
    /* =----------------------------------------------------------------------------= */
    /* =   연동시 오류가 발생하는 경우 아래의 주소로 접속하셔서 확인하시기 바랍니다.= */
    /* =   접속 주소 : http://kcp.co.kr/technique.requestcode.do                    = */
    /* =----------------------------------------------------------------------------= */
    /* =   Copyright (c)  2016  NHN KCP Inc.   All Rights Reserverd.                = */
    /* ============================================================================== */

    /* ============================================================================== */
    /* = ※ 주의 ※                                                                 = */
    /* = * 지불 데이터 설정                                                         = */
    /* =----------------------------------------------------------------------------= */
    /* = ※ 주의 ※                                                                 = */    
    /* = * g_conf_log_dir 변수 설정                                                 = */
    /* =   log 디렉토리 설정                                                        = */
    /* ============================================================================== */
	@Value("${payment.g_conf_log_dir:example}")
    public  String g_conf_log_dir;             // LOG 디렉토리 절대경로 입력
    
    /* ============================================================================== */
    /* = ※ 주의 ※                                                                 = */
    /* = * g_conf_gw_url 설정                                                       = */
    /* =----------------------------------------------------------------------------= */
    /* = 테스트 시 : testpaygw.kcp.co.kr로 설정해 주십시오.                         = */
    /* = 실결제 시 : paygw.kcp.co.kr로 설정해 주십시오.                             = */
    /* ============================================================================== */
	@Value("${payment.g_conf_gw_url:example}")
    public  String g_conf_gw_url;

    /* ============================================================================== */
    /* = ※ 주의 ※                                                                 = */
    /* = * g_conf_js_url 설정                                                       = */
    /* =----------------------------------------------------------------------------= */
    /* = 테스트 시 : src="http://pay.kcp.co.kr/plugin/payplus_test.js"              = */
    /* =         src="https://pay.kcp.co.kr/plugin/payplus_test.js"             = */
    /* = 실결제 시 : src="http://pay.kcp.co.kr/plugin/payplus.js"                   = */
    /* =         src="https://pay.kcp.co.kr/plugin/payplus.js"                  = */
    /* =                                                                            = */
    /* = 테스트 시(UTF-8) : src="http://pay.kcp.co.kr/plugin/payplus_test_un.js"    = */
    /* =                 src="https://pay.kcp.co.kr/plugin/payplus_test_un.js"   = */
    /* = 실결제 시(UTF-8) : src="http://pay.kcp.co.kr/plugin/payplus_un.js"         = */
    /* =                 src="https://pay.kcp.co.kr/plugin/payplus_un.js"        = */
    /* ============================================================================== */
	@Value("${payment.g_conf_js_url:defaultValue}")
    public  String g_conf_js_url;

    /* ============================================================================== */
    /* = 스마트폰 SOAP 통신 설정                                                    = */
    /* =----------------------------------------------------------------------------= */
    /* = 테스트 시 : false                                                          = */
    /* = 실결제 시 : true                                                           = */
    /* ============================================================================== */
	@Value("${payment.g_conf_server:true}")
    public   boolean g_conf_server;

    /* ============================================================================== */
    /* = g_conf_site_cd, g_conf_site_key 설정                                       = */
    /* = 실결제시 KCP에서 발급한 사이트코드(site_cd), 사이트키(site_key)를 반드시   = */
    /* = 변경해 주셔야 결제가 정상적으로 진행됩니다.                                = */
    /* =----------------------------------------------------------------------------= */
    /* = 테스트 시 : 사이트코드(T0000)와 사이트키(3grptw1.zW0GSo4PQdaGvsF__)로      = */
    /* =            설정해 주십시오.                                                = */
    /* = 실결제 시 : 반드시 KCP에서 발급한 사이트코드(site_cd)와 사이트키(site_key) = */
    /* =            로 설정해 주십시오.                                             = */
    /* ============================================================================== */
	@Value("${payment.g_conf_site_cd:defaultValue}")
    public  String g_conf_site_cd;
	@Value("${payment.g_conf_site_key:defaultValue}")
    public  String g_conf_site_key;

    /* ============================================================================== */
    /* = g_conf_site_name 설정                                                      = */
    /* =----------------------------------------------------------------------------= */
    /* = 사이트명 설정(한글 불가) : 반드시 영문자로 설정하여 주시기 바랍니다.       = */
    /* ============================================================================== */
	@Value("${payment.g_conf_site_name:defaultValue}")
    public  String g_conf_site_name;

    /* ============================================================================== */
    /* = 지불 데이터 셋업 (변경 불가)                                               = */
    /* ============================================================================== */
    public static final String g_conf_log_level = "3";
    public static final String g_conf_gw_port   = "8090";        // 포트번호(변경불가)
    public static final String module_type      = "01";          // 변경불가
    public static final int    g_conf_tx_mode   = 0;             // 변경불가

}
