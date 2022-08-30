package com.rslakra.core.algos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 1/14/20 4:39 PM
 */
public class TowerOfHanoi {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(TowerOfHanoi.class);

    /**
     * Tower of Hanoi.
     *
     * @param disks
     * @param A
     * @param B
     * @param C
     */
    public void towerOfHanoi(int disks, int A, int B, int C) {
        if (disks > 0) {
            towerOfHanoi(disks - 1, A, C, B);
            LOGGER.debug(String.format("Move disk from %d to %d", A, C));
            towerOfHanoi(disks - 1, B, A, C);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TowerOfHanoi toh = new TowerOfHanoi();
        for (int i = 0; i < 10; i++) {
            LOGGER.debug("Tower of Hanoi with " + i + " disks.");
            toh.towerOfHanoi(i, 1, 2, 3);
            LOGGER.debug("\n");
        }
    }

}
