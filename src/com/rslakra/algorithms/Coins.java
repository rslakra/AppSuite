package com.rslakra.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Coins related algorithms.
 *
 * @author Rohtash Singh Lakra
 * @date 06/25/2017 08:39:53 PM
 */
public final class Coins {
	
	/**
	 * Count to a desired value by choosing the least possible coins. For
	 * example, If we are provided coins of ₹ 1, 2, 5 and 10 and we are asked to
	 * count ₹ 18, it will count 4 coins. But if we slightly change the problem
	 * then the same approach may not be able to produce the same optimum
	 * result.
	 * 
	 * For the currency system, where we have coins of 1, 7, 10 value, counting
	 * coins for value 18 will be absolutely optimum but for count like 15, it
	 * may use more coins than necessary. For example, the greedy approach will
	 * use 10 + 1 + 1 + 1 + 1 + 1, total 6 coins. Whereas the same problem could
	 * be solved by using only 3 coins (7 + 7 + 1).
	 * 
	 * @param coins
	 * @param count
	 * @return
	 */
	public static void countCoins(int[] coins, int count) {
		List<Integer> totalCoins = new ArrayList<Integer>();
		int ctr = coins.length - 1;
		int value = count;
		while(ctr >= 0 && value > 0) {
			if(value > coins[ctr]) {
				totalCoins.add(coins[ctr]);
				value -= coins[ctr];
			}
			if(value > coins[ctr]) {
				continue;
			}
			ctr--;
		}
		
		System.out.println("Coins:" + java.util.Arrays.toString(coins));
		System.out.println("No. of Coins:" + totalCoins.size() + " needed to count:" + count);
		System.out.println(totalCoins);
		System.out.println();
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] coins = { 1, 2, 5, 10 };
		int count = 18;
		countCoins(coins, count);
		System.out.println();
		coins = new int[] { 1, 7, 10 };
		count = 15;
		countCoins(coins, count);
	}
	
}
