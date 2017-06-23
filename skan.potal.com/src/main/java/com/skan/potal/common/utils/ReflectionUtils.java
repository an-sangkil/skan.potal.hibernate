package com.skan.potal.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * Description :
 * 
 * @author skan
 * @since 2017. 1. 12.
 * @version
 *
 * 			Copyright (C) 2017 by SKAN Corp. All right reserved.
 */
public class ReflectionUtils {
	
	/**
	 * Obejct -> Map<.> 의 형태로 변환
	 * @param t
	 * @return
	 */
	public static <T> Map<?, ?> objectConvertToMap(T t) {

		Class<?> clazz = t.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Map<String, Object> map = new HashMap<>();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(t));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return map;
	}
	
	/**
	 * List<Obejct> -> List<Map<.>> 의 형태로 변환
	 * @param t
	 * @return
	 */
	public static <T> List<Map<?, ?>> objectListConvertToMap(List<T> t) {
		List<Map<?, ?>> maplist = new ArrayList<>();
		for (T objectClass : t) {
			Class<?> clazz = objectClass.getClass();
			Field[] fields = clazz.getDeclaredFields();
			Map<String, Object> map = new HashMap<>();
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					map.put(field.getName(), StringUtils.defaultString(field.get(objectClass) != null ? field.get(objectClass).toString():""));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			maplist.add(map);
		}
		return maplist;
	}
}
