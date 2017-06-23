/**
 * 
 */
package com.skan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.skan.potal.common.utils.ApplicationContextProvider;
import com.skan.potal.interceptor.SessionInterceptor;

/**
 * <pre>
 * Class Name  : SpringConfig.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 5. 31.          skan               최초생성
 * </pre>
 *
 * @author skan
 * @since 2016. 5. 31.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 * 
 * 
 */
@Configuration
@ComponentScan(basePackages={
								 "com.skan.auth"
								,"com.skan.potal"
								,"com.skan.tms"
							}
				, includeFilters={
									 @ComponentScan.Filter(Controller.class)
									,@ComponentScan.Filter(Service.class)
									,@ComponentScan.Filter(Component.class)
								 })
public class SpringConfig {
	
	
	@Configuration
	@Profile("test")
	@PropertySource(value={
			"classpath:application-test.properties"
			,"classpath:payment-test.properties"
			})
	public  static class testDataSource {
		
	}
	
	
	@Configuration
	@Profile("local")
	@PropertySource(value={
			"classpath:application-local.properties"
			,"classpath:payment-local.properties"
			})
	public  static class localDataSource {
		
	}
	
	@Configuration
	@Profile("dev")
	@PropertySource(value={
			"classpath:application-dev.properties"
			,"classpath:payment-dev.properties"
			})
	public static class devDataSource {
		
	}
	
	@Configuration
	@Profile("staging")
	@PropertySource(value={
			"classpath:application-staging.properties"
			,"classpath:payment-staging.properties"
			})
	public static class stagingDataSource {
		
	}
	
	
	@Configuration
	@Profile("prod")
	@PropertySource(value={
			//"classpath:properties/application-prod.properties"
			"classpath:application-prod.properties"
			,"classpath:payment-prod.properties"
			})
	public static class prodDataSource {
		
	}
	
	@Bean
	public ApplicationContextProvider applicationContextProvider () {
		return new ApplicationContextProvider();
	}
	
	@Bean
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
		return new WebMvcConfigurerAdapter() {
			
			/**
			 * 로그인 체크 인터셉터 
			 */
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(new SessionInterceptor())
							.excludePathPatterns(
									"/"
									,"/login/**"
									,"/sign/**"
									,"/signin/**"
									,"/signup/**"
							);
			}
		};
	}
	
}

