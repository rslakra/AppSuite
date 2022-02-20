package com.rslakra.jdk8.lambda;

import java.util.function.Function;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @since Dec 30, 2020 16:02:34
 */
public class LambdaExample {
    private static final String MESSAGE = "Hello, Lambda";

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        // function call
        Function<String, Integer> function = fn -> fn.length();
        System.out.println(function.apply(MESSAGE));

        // lambda call
        Runnable runnable = () -> System.out.println(MESSAGE);
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
    }
}
