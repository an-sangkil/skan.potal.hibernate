/*
 * $Id: StringReplaceTest.java ,v 1.1 2010. 9. 10. 오후 5:23:03 dongbuFarm Exp $
 * created by    : An Sang Kil
 * creation-date : 2010. 9. 10.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package test;

public class StringReplaceTest {
	
	
	
	public static void main(String args[]){
		String source, subject,object;
		
		source  = "안녕하세요 EDMS System 입니다. @@~TEST~@@ " +
				  "메일 보내기 공통모듈 작성 중입니다. @@~TEST~@@ "+
				  "지금 이자리에도 있지요,";
		subject = "@@~TEST~@@";
		
		object = "성공합시다";
		
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
		System.out.println("~end~");
		System.out.println(rtnStr);
	}
}
