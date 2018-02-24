/******************************************************************************
 * Copyright (C) Devamatre Inc 2008. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.java;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * This class generates unique random number between the range specified.
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @created 28/10/2008 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
public final class UniqueRandomNumber {

	/* random number generator */
	private Random random;

	private Set<Integer> uniqueRandoms;

	private int start;
	private int end;
	private int howMany;

	/**
	 * 
	 * @param start
	 * @param end
	 * @param howMany
	 */
	public UniqueRandomNumber(int start, int end, int howMany) {
		random = new Random();
		uniqueRandoms = new HashSet<Integer>();
		this.start = start;
		this.end = end;
		this.howMany = howMany;
	}

	/**
	 * 
	 * @param aArgs
	 */
	public static final void main(String... aArgs) {
		UniqueRandomNumber uniqueRandomNumber = new UniqueRandomNumber(1, 10, 6);

		System.out.println("Generating random integers");
		Set<Integer> uniq = uniqueRandomNumber.generateRandomNumbers();
		for (int num : uniq) {
			System.out.println(num);
		}
	}

	/**
	 * Returns the list of unique generated number.
	 * 
	 * @return
	 */
	public Set<Integer> generateRandomNumbers() {
		do {
			// don't generate more than the total required.
			if (uniqueRandoms.size() >= howMany) {
				break;
			}

			/* generate random numbers */
			for (int ctr = 1; ctr <= howMany; ctr++) {
				int randomNo = getRandomNumber(start, end);
				// if (!uniqueRandoms.contains(randomNo)) {
				uniqueRandoms.add(randomNo);
				// }

				// don't generate more than the total required.
				if (uniqueRandoms.size() >= howMany) {
					break;
				}
			}
		} while (uniqueRandoms.size() <= howMany);

		// (int[]) v.toArray(new String[0]);
		return uniqueRandoms;
	}

	/**
	 * This method generate random numbers between starting and end numbers.
	 * 
	 * @param aStart
	 * @param aEnd
	 * @param aRandom
	 * @return
	 */
	public int getRandomNumber(int iStart, int iEnd) {
		if (iStart > iEnd) {
			throw new IllegalArgumentException(iStart + " must be less than "
					+ iEnd);
		}
		/* range, casting to long to avoid overflow problems */
		long range = (long) iEnd - (long) iStart + 1;

		/* compute a fraction of the range, 0 <= frac < range */
		long fraction = (long) (range * random.nextDouble());
		int randomNumber = (int) (fraction + iStart);
		return randomNumber;
	}
}