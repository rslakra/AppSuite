package com.rslakra.core.algos;

import java.util.Random;
import java.util.Scanner;

public class RandomNumbersAverage {

    /**
     * @return
     */
    public void randomNumbersAverage() {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        boolean active = false;
        int usersNumber = 0;
        do {
            System.out.println("Please enter a non-negative number");
            usersNumber = in.nextInt();
            if (usersNumber > 0) {
                int[] numbersList = new int[usersNumber];
                int total = 0;
                for (int i = 0; i < numbersList.length; i++) {
                    numbersList[i] = rand.nextInt(100);
                    total += numbersList[i];
                }

                int average = (total / numbersList.length);
                System.out.println("The average is " + average);
            }
        } while (usersNumber != 0);

        in.close();
    }

}
