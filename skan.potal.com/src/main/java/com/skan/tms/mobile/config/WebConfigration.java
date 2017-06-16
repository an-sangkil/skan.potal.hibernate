package com.skan.tms.mobile.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.skan.tms.mobile.common.exception.CustomHandlerExceptionResolver;

@Configuration
public class WebConfigration extends WebMvcConfigurerAdapter {
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
	}
	
	/**
	 * Welcome Page 설정
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		 registry.addViewController("/").setViewName("forward:/index");
	}
	
	/**
	 * 정적 리소스 경로
	 * @param registry
	 */
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assect/**");
				//.addResourceLocations("/index");  물리적 위치 절대 경로
	}

	@Bean(name = "customHandlerExceptionResolver")
	public CustomHandlerExceptionResolver createSimpleMappingExceptionResolver() {

		CustomHandlerExceptionResolver r = new CustomHandlerExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "/error/databaseError");
		mappings.setProperty("InvalidCreditCardException", "/error/creditCardError");
		mappings.setProperty("AccessDeniedException", "/error/error_403");

		r.setExceptionMappings(mappings); // None by default
		r.setDefaultErrorView("/error/error"); // No default
		r.setExceptionAttribute("ex"); // Default is "exception"
		//r.setWarnLogCategory("com.knkcorp"); // No default

		return r;
	}

}
