package com.skan.auth.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.skan.auth.annotation.Secured;
import com.skan.tms.mobile.common.code.AuthorizationCode;



/**
 * 
 * Description : AOP 롤체그에서 사용할 유틸
 * 					 선언한 어노테이션에서 가지고 있는 ROLE 의 목록 리턴
 * 
 *  			http://stackoverflow.com/questions/3271659/use-enum-type-as-a-value-parameter-for-rolesallowed-annotation
 *  			http://marobiana.tistory.com/54
 *  			http://kang594.blog.me/39704853
 * 				
 *  
 * @author skan
 * @since  2016. 11. 9.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class RolesAllowedUtil {

    /** get the array of allowed AuthorizationCodes for a given class **/
    public static List<AuthorizationCode> getAuthorizationCodesAllowedFromAnnotations(
            Annotation[] annotations) {
        List<AuthorizationCode> AuthorizationCodesAllowed = new ArrayList<AuthorizationCode>();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().isAnnotationPresent(Secured.class)) {
                AuthorizationCode[] AuthorizationCodes = getAuthorizationCodesFromAnnotation(annotation);
                if (AuthorizationCodes != null)
                    for (AuthorizationCode AuthorizationCode : AuthorizationCodes)
                        AuthorizationCodesAllowed.add(AuthorizationCode);
            }
        }
        return AuthorizationCodesAllowed;
    }

    public static AuthorizationCode[] getAuthorizationCodesFromAnnotation(Annotation annotation) {
        Method[] methods = annotation.annotationType().getMethods();
        for (Method method : methods) {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            Class<?> componentType = returnType.getComponentType();
            if (name.equals("value") && returnType.isArray()
                    && AuthorizationCode.class.isAssignableFrom(componentType)) {
                AuthorizationCode[] features;
                try {
                    features = (AuthorizationCode[]) (method.invoke(annotation,
                            new Object[] {}));
                } catch (Exception e) {
                    throw new RuntimeException(
                            "Error executing value() method in "
                                    + annotation.getClass().getCanonicalName(),
                            e);
                }
                return features;
            }
        }
        throw new RuntimeException(
                "No value() method returning a AuthorizationCode[] type "
                        + "was found in annotation "
                        + annotation.getClass().getCanonicalName());
    }

}