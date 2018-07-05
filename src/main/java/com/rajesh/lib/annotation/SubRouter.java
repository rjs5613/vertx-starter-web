package com.rajesh.lib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.vertx.ext.web.Router;

/**
 * Annotation to Create Sub Router
 * 
 * @author rrajeshkumar
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SubRouter {

	/**
	 * Specify the path using which subrouter need to be mounted on parent
	 * {@link Router}
	 * 
	 * @return
	 */
	String path();

}
