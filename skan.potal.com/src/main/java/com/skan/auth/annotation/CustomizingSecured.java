package com.skan.auth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.skan.potal.common.code.AuthorizationCode;


/**
 * 
 * Description : ENUM TYPE 으로 사용하는 Authorization Annoation.
 * @author skan
 * @since  2016. 11. 9.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Secured
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CustomizingSecured {
	
	AuthorizationCode[] value() default{ AuthorizationCode.DEFAULT_USER };
	
}
