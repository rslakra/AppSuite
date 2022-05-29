package com.rslakra.jdk.thread;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @Created Oct 12, 2019 3:40:02 PM
 */
public class Summation extends Thread {

    private static Random random = new Random();
    private List<Integer> listNumbers;
    private long sum;

    public static List<Integer> listNumbers(int size) {
        final List<Integer> numbers = new ArrayList<>(size);
        for (int index = 0; index < size; index++) {
            numbers.add(random.nextInt(100) + 1); // 1..100
        }

        return Collections.synchronizedList(numbers);
    }

    public Summation(List<Integer> listNumbers) {
        this.listNumbers = listNumbers;
    }

    public long getSum() {
        return sum;
    }

    public void run() {
        sum = linearSum(this.listNumbers);
    }

    /**
     * @param listNumbers
     * @return
     */
    public static long linearSum(List<Integer> listNumbers) {
        int total = 0;

        for (int i = 0; i < listNumbers.size(); i++) {
            total += listNumbers.get(i);
        }

        return total;
    }

    /**
     * @param listNumbers
     * @return
     */
    public static long parallelSum(List<Integer> listNumbers) {
        return parallelSum(listNumbers, Runtime.getRuntime().availableProcessors());
    }

    /**
     * @param listNumbers
     * @param threads
     * @return
     */
    public static long parallelSum(List<Integer> listNumbers, int threads) {
        Summation[] sumThreads = new Summation[threads];
        int listSize = (int) Math.ceil(listNumbers.size() * 1.0 / threads);
        final AtomicInteger counter = new AtomicInteger();
        final Collection<List<Integer>> allLists = listNumbers.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / listSize)).values();
        Iterator<List<Integer>> listItr = allLists.iterator();
        for (int i = 0; i < threads; i++) {
            sumThreads[i] = new Summation(listItr.next());
            sumThreads[i].start();
        }

        // let all the threads finish
        try {
            for (Summation thread : sumThreads) {
                thread.join();
            }
        } catch (InterruptedException e) {
        }

        int total = 0;
        for (Summation thread : sumThreads) {
            total += thread.getSum();
        }

        return total;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int SIZE = 100000000;
//        int SIZE = 10000;
        System.out.println("Starting");
        List<Integer> listNumbers = Summation.listNumbers(SIZE);

        long sum = 0;
        long start = System.currentTimeMillis();
        sum = Summation.linearSum(listNumbers);
        System.out.println("Linear, sum:" + sum + ", took: " + (System.currentTimeMillis() - start)); // Single: 44

        start = System.currentTimeMillis();
        sum = Summation.parallelSum(listNumbers);
        System.out.println("Parallel, sum:" + sum + ", took: " + (System.currentTimeMillis() - start)); // Parallel: 25
    }

}
