package com.skan.tms.mobile.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * Description : Web.xml 의  Servlet Error Page   
 * @author skan
 * @since 2016. 10. 10.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Configuration
public class ErrorConfig implements EmbeddedServletContainerCustomizer {

	private static final String PATH_400 = "/errors/400";
	private static final String PATH_401 = "/errors/401";
	private static final String PATH_404 = "/errors/404";
	private static final String PATH_405 = "/errors/405";
	private static final String PATH_500 = "/errors/500";
	
	
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, PATH_400));
		container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, PATH_401));
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, PATH_404));
		container.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, PATH_405));
		container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, PATH_500));
		
		final ErrorPage errorPage = new ErrorPage("/errors/error");
		container.addErrorPages(errorPage);
	}
	
	/**
	 * ServerProperties 상속 후  customize 구현으로 사용 하지 않음.
	 * @return
	 */
//	@Deprecated
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer(){
//		return (continer-> {
//			final org.springframework.boot.web.servlet.ErrorPage errorPage = new ErrorPage(PATH);
//			continer.addErrorPages(errorPage);
//		});
//	}
//	@Bean
//	public EmbeddedServletContainerFactory servletContainer() {
//	    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
//	    factory.setPort(9000);
//	    factory.setSessionTimeout(10, TimeUnit.MINUTES);
//	    factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
//	    return factory;
//	}
	
}
