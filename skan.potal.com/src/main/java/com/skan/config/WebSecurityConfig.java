package com.skan.config;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.skan.auth.provider.LoginAuthenticationProvider;
import com.skan.auth.provider.handler.AuthFailureHandler;
import com.skan.auth.provider.handler.AuthSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired private ServletContext servletContext;
	@Autowired private LoginAuthenticationProvider AuthLoginProvider;
	@Autowired private AuthFailureHandler authFailureHandler;
	@Autowired private AuthSuccessHandler authSuccessHandler; 
	
	/*
	 * 사용자 PROVIDER 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(AuthLoginProvider);
	}
	
	/*
	 * 임의 사용자
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers("/assest/**");
	}
	
	
	/*
	 * 기본설정 
	 * (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		
		String CONTEXT_PATH = servletContext.getContextPath();
		logger.debug("context path ..... = {}"  ,  CONTEXT_PATH);
		
		
		http.headers().frameOptions().disable();
		http.headers().xssProtection().block(false);	
		
		http.authorizeRequests()
			.antMatchers("/mypage/**").authenticated()
			.antMatchers(
						"/**"
//						,"/home"
//						,"/signin/facebook"
//						,"/sign/**"
//						,"/signup"
//						,"/outside/**"
//						,"/file/attachment/*"
//						,"/userinfofind/*"
//						,"/user/reservitionHistory/virtualAccountProcessing"
					).permitAll()
			//.antMatchers("/**/*delete").authenticated()
			//.antMatchers("/**/*Modify").authenticated()
			//.antMatchers("/**/*Form").authenticated()
			.anyRequest().authenticated()
		.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.loginProcessingUrl("/loginProcess")
				.defaultSuccessUrl("/loginSuccess")
				.failureHandler(authFailureHandler)
				.successHandler(authSuccessHandler)
				//.failureForwardUrl("/login")
				//.successForwardUrl("/admin/store/storeList")
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
			.permitAll()
		.and()
			.sessionManagement()
				.maximumSessions(1) 		
				.expiredUrl("/login");
		
		http.csrf().disable();
		http.cors();
		
		
		//////////////////////////////////
		// EXCEPTION 설정 (SimpleMappingExceptionResolver 를 이용하여 Global Exception 설정을 이용함으로 해당 핸들러로 호출되지 않음.)
		/////////////////////////////////
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandlerImpl());
	}
	
	@Bean
	public AccessDeniedHandlerImpl accessDeniedHandlerImpl() {
		AccessDeniedHandlerImpl accessDeniedHandlerImpl = new AccessDeniedHandlerImpl();
		accessDeniedHandlerImpl.setErrorPage("/error/error_403");
		return accessDeniedHandlerImpl;
	}
	
	
}
