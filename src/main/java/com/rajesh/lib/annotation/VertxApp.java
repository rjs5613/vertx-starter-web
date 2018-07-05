/**
 * 
 */
package com.rajesh.lib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotaion for Main App of the VertxApplication
 * 
 * @author rrajeshkumar
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface VertxApp {

	/**
	 * Base package from where application need to scan for
	 * {@link DeployableVerticle} , {@link SubRouter} etc.
	 * 
	 * @return
	 */
	String basePackage() default "";

	/**
	 * Specifies if the application is web app if true HttpServer will be created
	 * and all the routes will be registered
	 * 
	 * @return
	 */
	boolean isWebApp() default false;

}
