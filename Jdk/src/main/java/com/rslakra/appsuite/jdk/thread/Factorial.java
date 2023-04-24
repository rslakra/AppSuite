package com.rslakra.appsuite.jdk.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 10/25/19 10:50 PM
 */
public class Factorial implements Runnable {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(Factorial.class);

    private final int number;

    public Factorial(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        int fact = 1;
        for (int i = 1; i <= number; i++) {
            fact = fact * i;
        }

        LOGGER.debug("Factorial of " + number + " is: " + fact);
    }

    public static void testOneThread(int number) {
        Thread t1 = new Thread(new Factorial(number));
        t1.start();
    }

    public static void testMultiThread(int number) {
        for (int i = 1; i <= 5; i++) {
            Thread t1 = new Thread(new Factorial(number));
            t1.setName("Thread-" + i);
            t1.start();
        }
    }

    public static void testMultiThreads(int number) {
        Factorial factorial = new Factorial(number);
        for (int i = 1; i <= 5; i++) {
            Thread t1 = new Thread(factorial);
            t1.setName("Thread-" + i);
            t1.start();
        }
    }

    public static void testMultiThread() {
        for (int i = 1; i <= 5; i++) {
            Thread t1 = new Thread(new Factorial(i));
            t1.setName("Thread-" + i);
            t1.start();
        }
    }

    public static void main(String[] args) {
        int num = 5;
        LOGGER.debug("testOneThread");
        testOneThread(num);
        LOGGER.debug("testMultiThread");
        testMultiThread(num);
        LOGGER.debug("testMultiThreads");
        testMultiThreads(num);
        LOGGER.debug("testMultiThread-no args");
        testMultiThread();
    }
}
