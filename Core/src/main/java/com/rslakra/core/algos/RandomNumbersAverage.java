package com.rslakra.core.algos;

import com.rslakra.core.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomNumbersAverage {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomNumbersAverage.class);
    private boolean active = false;

    /**
     * Returns an average of the given numbers.
     *
     * @param inputArray
     * @return
     */
    public int average(final List<Integer> inputArray) {
        int average = 0;
        if (BeanUtils.isNotEmpty(inputArray)) {
            int total = 0;
            for (int index = 0; index < inputArray.size(); index++) {
                total += inputArray.get(index);
            }

            average = (total / inputArray.size());
        }

        LOGGER.debug("average:{}", average);
        return average;
    }


    /**
     * @return
     */
    public List<Integer> readRandomNumbers() {
        List<Integer> listNumbers = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        final Random rand = new Random();
        int inputNumber = 0;
        do {
            System.out.println("Please enter a non-negative number:");
            inputNumber = in.nextInt();
            if (inputNumber > 0) {
                int total = 0;
                for (int i = 0; i < listNumbers.size(); i++) {
                    listNumbers.add(rand.nextInt(100));
                }
            }
        } while (inputNumber != 0);
        in.close();

        LOGGER.debug("average:{}", average(listNumbers));

        return listNumbers;
    }

    /**
     * @return
     */
    public void randomNumbersAverage() {
        List<Integer> listNumbers = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        final Random rand = new Random();
        int inputNumber = 0;
        do {
            System.out.println("Please enter a non-negative number:");
            inputNumber = in.nextInt();
            if (inputNumber > 0) {
                int total = 0;
                for (int i = 0; i < listNumbers.size(); i++) {
                    listNumbers.add(rand.nextInt(100));
                }
            }
        } while (inputNumber != 0);
        in.close();

        LOGGER.debug("average:{}", average(listNumbers));
    }

}
