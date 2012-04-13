package com.dongbu.farm.common.utils;

import javax.servlet.http.HttpServletRequest;

import com.dongbu.farm.login.model.Member;

import java.lang.IllegalStateException;


public abstract class SessionUtils {
	
	public static Member getMember(HttpServletRequest request) {
		try {
			Member member = (Member) request.getSession().getAttribute("member");
			return member;
		} catch (Exception e) {
			throw new IllegalStateException("The session does not have proper authentication." , e );
		}
	}
	
	public static String getUserName(HttpServletRequest request) {
		try {
			Member member = (Member) request.getSession().getAttribute("member");
			return member.getUser_name();
		} catch (Exception e) {
			throw new IllegalStateException("The session does not have proper authentication." , e);
		}
	}
	public static String getUserID(HttpServletRequest request) {
		try {
			Member member = (Member) request.getSession().getAttribute("member");
			return member.getUser_id();
		} catch (Exception e) {
			throw new IllegalStateException("The session does not have proper authentication." , e);
		}
	}
	public static String getUserDept(HttpServletRequest request) {
		try {
			Member member = (Member) request.getSession().getAttribute("member");
			return member.getDept_code();
		} catch (Exception e) {
			throw new IllegalStateException("The session does not have proper authentication." , e);
		}
	}
}
