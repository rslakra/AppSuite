/******************************************************************************
 * Copyright (C) Devamatre Technologies 2009
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.core.lang;

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