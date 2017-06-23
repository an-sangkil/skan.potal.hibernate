package com.skan.potal.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * Description : Application Context를 얻어오기 위한 유틸리티
 * 					일반 클레스에서 BEAN을 찾아오고자 할경우 사용한다.  
 * @author skan
 * @since  2016. 11. 30.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Component
@SuppressWarnings("static-access")
public class ApplicationContextProvider implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public ApplicationContext getContext() {
		return this.applicationContext;
	}
	
	public static <T> T getBean(String beanName, Class<T> requiredType){
		return applicationContext.getBean(beanName, requiredType); 
	}
	
	public static Object getBean(String beanName){
		return applicationContext.getBean(beanName); 
	}
}
