/**
 * 
 */
package com.rslakra.jdk8.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that has more than one method, is called Multi-Value
 * annotation.
 * 
 * We can provide the default value also.
 * 
 * @author Rohtash Singh Lakra
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestCases {
	
	TestCase[] value();
	
}
