package com.skan.potal.web.payment.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skan.potal.web.payment.normal.PaymentConfig;

//import kr.co.kcp.net.connection.ConnectionKCP;
//import kr.co.kcp.net.connection.dto.ParamData;
//import kr.co.kcp.net.connection.util.HttpJsonXml;
//import kr.co.kcp.net.connection.util.OpenHash;

@Controller
@RequestMapping(value="/payment")
public class KcpMobileController {
//
//	@Autowired PaymentConfig paymentConfig;
//	
//	@RequestMapping(value = "/kcpMobileOrderApproval", method = RequestMethod.POST)
//	private void kcpMobileOrderApproval(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		response.setHeader("Cache-Control", "no-store");
//		response.setHeader("Pragma", "no-cache");
//		response.setDateHeader("Expires", 0);
//
//		//ConnectionKCP suc = new ConnectionKCP();  // KCP 인증 정보를 저장하기 위한 Object                     ( 통신의 기본이 되는 객체 ) - 필수
//	    //OpenHash      oh  = new OpenHash();       // KCP 와 통신시 데이터 위변조를 확인 하기 위한 Hash Object ( 업체와 KCP 간의 통신시 데이터 위변조를 확인 하기 위해 필요. 미설정시 통신 구간만 라이브러리에서 자체적으로 hash 처리 )
//	    //HttpJsonXml   hjx = new HttpJsonXml();    // 응답값 get value 형식으로 가져올수 있는 Object           ( Java 또는 JSP 내에서 데이터를 파싱할때 필요 - XML 또는 JSON )
//	    ParamData     pd  = new ParamData();      // 파라메타 값을 세팅할수 있는 bean Object                  ( String, HashMap 등으로 대체 가능 );
//	    
//	    String sellChannelType = request.getParameter("sellChannelType");
//		String siteCode = request.getParameter("site_cd");
//		String orderID = request.getParameter("ordr_idxx");
//		String paymentAmount = request.getParameter("good_mny");
//		String paymentMethod = request.getParameter("pay_method");
//		String productName = request.getParameter("good_name");
//		String response_type = request.getParameter("response_type");
//		String Ret_URL = request.getParameter("Ret_URL");
//		String escrow = request.getParameter("escw_used") == null ? "" : request.getParameter("escw_used");
//		
//		pd.setGood_mny(paymentAmount);
//		pd.setGood_name(productName);
//		pd.setOrdr_idxx(orderID);
//		pd.setPay_method(paymentMethod);
//		pd.setRet_URL(Ret_URL);
//		pd.setSite_cd(siteCode);
//		pd.setEscw_used(escrow);
//
//		if (response_type.equals("JSON") || response_type.equals("XML")) {
//			pd.setResponse_type(response_type);
//		}
//		
//		response.getWriter().print(suc.kcpPaymentSmartPhone( request, paymentConfig.g_conf_server,pd, "UTF-8" , paymentConfig.g_conf_log_dir ) ); // 이클립스 상에서 메서드를 클릭 후 F2를 누르면 상세 내용이 나옵니다.
//	}
}
