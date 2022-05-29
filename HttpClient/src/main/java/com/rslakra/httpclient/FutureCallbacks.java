package com.rslakra.httpclient;

import org.apache.http.concurrent.FutureCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 3/26/21 5:27 PM
 */
public class FutureCallbacks {

    private static final Logger LOGGER = LoggerFactory.getLogger(FutureCallbacks.class);

    public static <T> FutureCallback<T> ignoreCallback() {
        return new IgnoreFutureCallback<>();
    }


    public static <T> FutureCallback<T> defaultCallback(final Consumer<T> successFunction) {
        return new DefaultCallback<>(successFunction, exception -> {
            // failed case ignored
        });
    }

    public static <T> FutureCallback<T> defaultCallback(final Consumer<T> successFunction,
                                                        final Consumer<Exception> failureFunction) {
        return new DefaultCallback<>(successFunction, failureFunction);
    }

    /**
     * @param <T>
     */
    public static class IgnoreFutureCallback<T> implements FutureCallback<T> {

        @Override
        public void completed(T t) {
            LOGGER.info("Default http call completed!");
        }

        @Override
        public void failed(Exception e) {
            LOGGER.error("Default http call failed!");
        }

        @Override
        public void cancelled() {
            LOGGER.warn("Default http call cancelled!");
        }
    }

    /**
     * @param <T>
     */
    public static class DefaultCallback<T> extends IgnoreFutureCallback<T> {

        private final Consumer<T> successFunction;
        private final Consumer<Exception> failureFunction;

        /**
         * @param successFunction
         * @param failureFunction
         */
        public DefaultCallback(Consumer<T> successFunction, Consumer<Exception> failureFunction) {
            this.successFunction = successFunction;
            this.failureFunction = failureFunction;
        }

        @Override
        public void completed(T t) {
            super.completed(t);
            this.successFunction.accept(t);
        }

        @Override
        public void failed(Exception ex) {
            super.failed(ex);
            this.failureFunction.accept(ex);
        }
    }
}
