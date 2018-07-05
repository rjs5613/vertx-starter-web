package com.rajesh.lib.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.vertx.core.http.HttpMethod;

/**
 * Annotation to be put over Rest Endpoint handler to register path and endpoint
 * 
 * @author rrajeshkumar
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface RouteMapping {

	/**
	 * Path to which the handler need to be registered
	 * 
	 * @return
	 */
	String path();

	/**
	 * Http Method Handled by the Handler
	 * 
	 * @return
	 */
	HttpMethod httpMethod() default HttpMethod.GET;

}
