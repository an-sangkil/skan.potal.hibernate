package com.skan.potal.common.jsonp;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@ControllerAdvice
public class JsonPAdvice extends AbstractJsonpResponseBodyAdvice {

	public JsonPAdvice(){
		super("callback");
	}
}
