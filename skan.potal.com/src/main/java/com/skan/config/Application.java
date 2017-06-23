package com.skan.tms.mobile.config;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Description : WAR packing 이후  다른 Application 위에 올리기 위한 방안으로  SpringBootServletInitializer 을 상속 받아 사용한다.
 * 
 *
 *
 * @author skan
 * @since 2016. 5. 25.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class ApplicationTMSMobile 
	extends SpringBootServletInitializer 
{
	
	/**
	 * Create a deployable war file
	 * Servlet 3.0 support
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationTMSMobile.class);
	}
	
	public static void main(String[] args) {
		
		// hot deploy 후 자동 리스타트 하지  않도록 한다, pom.xml에 true로 설정하였지만, Run Application 프로퍼티에서 false로 설정.
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(ApplicationTMSMobile.class, args);
	}
	
	/**
	 * JSONP 사용을 위한  custom MessageConverter 
	 * @return
	 */
	@Bean
    public HttpMessageConverters customConverters() {
        return new HttpMessageConverters(false, Collections.<HttpMessageConverter<?> >singleton(new MappingJackson2HttpMessageConverter()));
    }
	
}
