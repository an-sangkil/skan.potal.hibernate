/*
 * $Id: org.eclipse.jdt.ui.prefs,v 1.1 2010/03/13 01:26:23 smrscvs1 Exp $
 * created by    : AN SANG KIL
 * creation-date : 2010. 5. 18.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.utils;

public class StringUtils {
	
	/**
	 * Null Check
	 * @param strValue : 확인 할 값
	 * @return
	 */
	public static String isNull(String strValue){
		String str = "";
		if(strValue != null ){str = strValue; }
		else{str="";}
		return str;
	}
	
	/**
	 * Null Check
	 * @param strValue				: 확인 할 값
	 * @param alternativeValue		: Null 일경우 다음 값으로 대체<supplement>대체값</supplement>
	 * @return
	 */
	public static String isNull(String strValue, String alternativeValue){
		String str = "";
		if(strValue != null ){
			str = strValue; 
		} else {
			str=alternativeValue;
		}
		return str;
	}
	
	/**
	 * source  = "안녕하세요 ECMS System 입니다. @@~TEST~@@ " +
	 *			  "메일 보내기 공통모듈 작성 중입니다. @@~TEST~@@ "+
	 *			  "지금 이자리에도 있지요,";
	 * subject = "@@~TEST~@@";
	 * object = "성공합시다";
	 * result "안녕하세요 OOO System 입니다 . 성공합시다 메일보내기 공통모듈 작성 중입니다. 성공합시다. 지금이자리에도 있지요,
	 * @param source
	 * @param subject
	 * @param object
	 * @return
	 */
	public static String replace(String source, String subject, String object){
		StringBuffer rtnStr = new StringBuffer();
		String preStr = "";
		String nextStr = source;
		
		while (source.indexOf(subject) >= 0) {
			System.out.println(source.indexOf(subject));
			preStr  = source.substring(0, source.indexOf(subject));
			nextStr = source.substring(source.indexOf(subject)+subject.length(), source.length());
			source = nextStr;

			rtnStr.append(preStr).append(object);
		}
		rtnStr.append(nextStr);
		return rtnStr.toString();
	}
	
}
