package com.skan.potal.common.utils;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.CDATASection;

/**
 * Description : 문자발송 유틸리티  
 * @author skan
 * @since  2017. 1. 19.
 * @version 
 *
 * Copyright (C) 2017 by SKAN Corp. All right reserved.
 */
@Component
public class SMSSoapUtils {
	
	@Value(value = "${sms.id:kna2017}")
	String id="kna2017" ; 
	
	@Value(value = "${sms.pwd:kna54321@@}")
	String pwd="kna54321@@"; 
	
	@Value(value = "${sms.sender:0315402000}")
	String sender = "0315402000"; 
	
	@Value(value = "${sms.senderName:국립수목원}")
	String senderName;
	
	@Value(value = "${sms.receiveName:knkcorp}")
	String receiveName; 
	
	String reserveDate; 
	String mappingKey;
	
	/**
	 * 
	 * @param megType  메세지 타입 1:SMS, 3:(LMS, MMS(파일))
	 * @param title	      제목 
	 * @param message	메세지
	 * @param receiver 받는사람 번호
	 * @param receiveName 받는 사람 이름
	 * @return
	 * @throws Exception
	 */
	public boolean sendSms(String megType , String title, String message, String receiver, String receiveName) throws Exception {
		return sendSms(id, pwd, sender, senderName, receiver, receiveName, megType, title, message, reserveDate, mappingKey);
	}

	private boolean sendSms(String id , String pwd, String sender, String senderName
			,String receiver, String receiveName, String megType , String title, String message , String reserveDate, String mappingKey) throws Exception {
		
		MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage soapMessage = factory.createMessage();
        //SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPBody soapBody = soapMessage.getSOAPBody();
        
        // SOAP Envelope
        //SOAPEnvelope envelope = soapPart.getEnvelope();
        //envelope.addNamespaceDeclaration("example", serverURI);
        SOAPElement soapBodyElem0 = soapBody.addBodyElement(new QName("http://smssvs.e-ncom.co.kr/SMSSvc/", "SMS_Send"));
        String data = 
				        "<xml_data>"
				       +"    <row>"
				       +"        <id>"+id+"</id>"
				       +"        <pwd>"+pwd+"</pwd>"
				       +"        <sender>"+sender+"</sender>"
				       +"        <send_name>"+senderName+"</send_name>" 
				       +"        <receiver>"+receiver+"</receiver> "
				       +"        <receive_name>"+receiveName+"</receive_name> "
				       +"        <msg_type>"+megType+"</msg_type> "
				       +"        <titl>"+title+"</titl> "
				       +"        <msg>"+message+"</msg> "
				       +"        <ReserveDT></ReserveDT>" 
				       +"        <MappingKey></MappingKey>" 
				       +"     </row> "
				       +"</xml_data>"; 
       
         
        CDATASection cdata = soapBodyElem0.getOwnerDocument().createCDATASection(data);
		SOAPElement soapBodyElem1 = soapBodyElem0.addChildElement("xmlData");
        soapBodyElem1.appendChild(cdata);
		// SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("xml_data");
		// SOAPElement soapBodyElem3 = soapBodyElem2.addChildElement("row");
		// soapBodyElem3.addChildElement("id").addTextNode("Knk2012");
		// soapBodyElem3.addChildElement("pwd").addTextNode("Knk54321");
		// soapBodyElem3.addChildElement("sender").addTextNode("0629608860");
		// soapBodyElem3.addChildElement("send_name").addTextNode("광산문화예술회관");
		// soapBodyElem3.addChildElement("receiver").addTextNode("1234");
		// soapBodyElem3.addChildElement("receive_name").addTextNode("1234");
		// soapBodyElem3.addChildElement("msg_type").addTextNode("1");
		// soapBodyElem3.addChildElement("titl").addTextNode("TEST");
		// soapBodyElem3.addChildElement("ReserveDT").addTextNode("");
		// soapBodyElem3.addChildElement("MappingKey").addTextNode("");
         
         MimeHeaders headers = soapMessage.getMimeHeaders();
         headers.addHeader("SOAPAction", "http://smssvs.e-ncom.co.kr/SMSSvc/SMS_Send");

         soapMessage.saveChanges();

         /* Print the request message */
         System.out.print("Request SOAP Message:");
         soapMessage.writeTo(System.out);
         System.out.println();
         
         // Create SOAP Connection
         SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
         SOAPConnection soapConnection = soapConnectionFactory.createConnection();
         

         // Send SOAP Message to SOAP Server
         String url = "http://smssvs.e-ncom.co.kr/SMSSvc/Sms.asmx";
         SOAPMessage soapResponse = soapConnection.call(soapMessage, url);

         // Process the SOAP Response
         // print SOAP Response
         System.out.print("Response SOAP Message:");
         soapResponse.writeTo(System.out);
         
         
		return false;


		
	}
}
