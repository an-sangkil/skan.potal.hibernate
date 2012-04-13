package com.dongbu.farm.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class IdGenerator {
	private static long counter = 0L;

	/**
	 * ���̵� ��´�.
	 * 
	 * @param prefix ���ξ�
	 * @return
	 */
	public static String generate(String prefix) {
		String id = null;
		synchronized (IdGenerator.class) {
			id = Long.toHexString(System.currentTimeMillis() + counter++).toUpperCase();
		}
		if (prefix != null)
			id = prefix + id;
		return id;
	}

	/**
	 * �ʿ��� ������ ���̵� ��´�.
	 * 
	 * @param n �ʿ��� ���̵� ����
	 * @param prefix ���ξ�
	 * @return
	 */
	public static List<String> generateForMulti(int n, String prefix) {
		Calendar currentDate = Calendar.getInstance();
		List<String> ids = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			currentDate.set(Calendar.SECOND, i);
			String id = null;
			synchronized (IdGenerator.class) {
				id = Long.toHexString(System.currentTimeMillis() + counter++).toUpperCase();
			}
			if (prefix != null)
				id = prefix + id;
			ids.add(id);
		}
		return ids;
	}

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

}
