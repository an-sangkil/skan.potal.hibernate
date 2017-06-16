package com.skan.auth.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * Description : 
 * @author skan
 * @since  2016. 11. 9.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Documented
@Retention(RUNTIME)
@Target(value=ElementType.ANNOTATION_TYPE)
public @interface Secured {
	// 의도된 빈공간으로   @SKANSecured 에서 사용함.
}
