/**
 * 
 */
package com.rslakra.jdk8.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * An annotation that has no method, is called marker annotation.
 * 
 * The @Override and @Deprecated are marker annotations.
 * 
 * @author Rohtash Singh Lakra
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MarketAnnotation {
	
}
