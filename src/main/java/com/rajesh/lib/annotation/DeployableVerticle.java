package com.rajesh.lib.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation to Denote the verticle need to be deployed
 * 
 * @author rrajeshkumar
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface DeployableVerticle {

}
