/**
 * 
 */
package com.rslakra.jdk8.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that has one method, is called single-value annotation.
 * We can provide the default value also.
 * 
 * Let's see the code to apply the single value annotation.
 * 
 * @SingleValueAnnotation(value=10)
 * 									The value can be anything.
 * 
 * @author Rohtash Singh Lakra
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SingleValueAnnotation {
	
	int value() default 0;
	
}
