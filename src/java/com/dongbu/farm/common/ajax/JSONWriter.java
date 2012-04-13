/*
 * $Id: JSONWriter.java,v 1.1 2010/03/17 06:09:27 smrscvs3 Exp $
 * created by    : jiwoong
 * creation-date : 2008. 7. 30.
 * =========================================================
 * Copyright (c) 2008 Maninsoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.ajax;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.CharacterIterator;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JSONWriter {
	private static final Log logger = LogFactory.getLog(JSONWriter.class);
	/**
	 * false: null -> ""
	 */
	private boolean isNullable = false;

	private StringBuffer buf = new StringBuffer();

	public void setNullable(boolean b) {
		isNullable = b;
	}

	public String write(Object object) throws Exception {
		buf.setLength(0);
		value(object);
		return buf.toString();
	}

	public String write(long n) throws Exception {
		return write(new Long(n));
	}

	public Object write(double d) throws Exception {
		return write(new Double(d));
	}

	public String write(char c) throws Exception {
		return write(new Character(c));
	}

	public String write(boolean b) throws Exception {
		return write(Boolean.valueOf(b));
	}

	private void value(Object object) throws Exception {
		if (object == null) {
			if (isNullable) {
				add("null");
			} else {
				add("\"\"");
			}
		} else if (object instanceof Class)
			string(object);
		else if (object instanceof Boolean)
			bool(((Boolean) object).booleanValue());
		else if (object instanceof Number)
			add(object);
		else if (object instanceof String)
			string(object);
		else if (object instanceof Character)
			string(object);
		else if (object instanceof Map)
			map((Map) object);
		else if (object.getClass().isArray())
			array(object);
		else if (object instanceof Collection)
			array(((Collection) object).iterator());
		else
			bean(object);
	}

	private void bean(Object object) throws Exception {
		add("{");
		BeanInfo info;
		String name = "";
		try {
			info = Introspector.getBeanInfo(object.getClass());
			PropertyDescriptor[] props = info.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				PropertyDescriptor prop = props[i];
				name = prop.getName();
				Method accessor = prop.getReadMethod();

				if (accessor == null) {
					accessor = object.getClass().getMethod("get" + String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1), null);
				}

				Object value = accessor.invoke(object, null);
				if (value instanceof Date) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime((Date) value);
					value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(((Date) value));
				}
				add(name, value);
				if (i < props.length - 1)
					add(',');
			}
		} catch (Exception e) {
			logger.error("error reading [name] property " + name);
			throw e;
		}
		add("}");
	}

	private void add(String name, Object value) throws Exception {
		add('"');
		add(name);
		add("\":");
		value(value);
	}

	private void map(Map map) throws Exception {
		add("{");
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			value(key);
			add(":");
			value(map.get(key));
			if (it.hasNext())
				add(",");
		}
		add("}");
	}

	private void array(Iterator it) throws Exception {
		add("[");
		while (it.hasNext()) {
			value(it.next());
			if (it.hasNext())
				add(",");
		}
		add("]");
	}

	private void array(Object object) throws Exception {
		add("[");
		int length = Array.getLength(object);
		for (int i = 0; i < length; ++i) {
			value(Array.get(object, i));
			if (i < length - 1)
				add(',');
		}
		add("]");
	}

	private void bool(boolean b) {
		add(b ? "true" : "false");
	}

	private void string(Object obj) {
		add('"');
		CharacterIterator it = new StringCharacterIterator(obj.toString());
		for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
			if (c == '"')
				add("\\\"");
			else if (c == '\\')
				add("\\\\");
			else if (c == '/')
				add("\\/");
			else if (c == '\b')
				add("\\b");
			else if (c == '\f')
				add("\\f");
			else if (c == '\n')
				add("\\n");
			else if (c == '\r')
				add("\\r");
			else if (c == '\t')
				add("\\t");
			else if (Character.isISOControl(c)) {
				unicode(c);
			} else {
				add(c);
			}
		}
		add('"');
	}

	private void add(Object obj) {
		buf.append(obj);
	}

	private void add(char c) {
		buf.append(c);
	}

	static char[] hex = "0123456789ABCDEF".toCharArray();

	private void unicode(char c) {
		add("\\u");
		int n = c;
		for (int i = 0; i < 4; ++i) {
			int digit = (n & 0xf000) >> 12;
			add(hex[digit]);
			n <<= 4;
		}
	}
}
