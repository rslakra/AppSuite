package com.rslakra.core.algos;

/**
 * @Author Rohtash Lakra
 * @Since 1/14/20 4:39 PM
 */
public class TowerOfHanoi {

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
            System.out.println(String.format("Move disk from %d to %d", A, C));
            towerOfHanoi(disks - 1, B, A, C);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TowerOfHanoi toh = new TowerOfHanoi();
        for (int i = 0; i < 10; i++) {
            System.out.println("Tower of Hanoi with " + i + " disks.");
            toh.towerOfHanoi(i, 1, 2, 3);
            System.out.println();
        }
    }

}
