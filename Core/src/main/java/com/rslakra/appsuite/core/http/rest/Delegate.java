package com.rslakra.appsuite.core.http.rest;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Rohtash Lakra
 * @created 4/15/20 9:53 AM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.PARAMETER})
public @interface Delegate {

    Class value() default Delegate.Target.class;

    int strategy() default 0;

    int genericTypeIndex() default -1;

    String target() default "";

    String type() default "";

    @Retention(RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target({ElementType.PARAMETER})
    public @interface Target {

        String value() default "";
    }
}
