/**
 * 
 */
package com.rslakra.jdk8.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * An annotation that has more than one method, is called Multi-Value
 * annotation.
 * We can provide the default value also.
 * 
 * How to apply Multi-Value Annotation
 * Let's see the code to apply the multi-value annotation.
 * 
 * @MultiValueAnnotation(name="Rohtash Singh",age=35,sex='M')
 *                                     These values can be anything of secified
 *                                     type.
 * 
 * @author Rohtash Singh Lakra
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiValueAnnotation {
	
	String name() default "";
	
	int age() default 0;
	
	char sex() default ' ';
}
