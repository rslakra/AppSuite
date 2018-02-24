/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
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
package com.rslakra.java.io;

public class NumberConvert {

	/*
	 * This function converts numbers into numbers in string form.
	 * 
	 * @param num This is the number to be converted into String form
	 * 
	 * @returns The number in String form. i.e., a String
	 */
	public String ConvertNumberToString(int num) {
		// Used to measure the length of the String
		String numstring = Integer.toString(num);
		// Used to store the result of the String and this is the variable
		// returned
		String result = "";
		// Used to store the length of the number in digits
		int length = numstring.length();
		// This is used to store the position of a digit on which operation is
		// performed
		int count = length;

		System.out.println("Got a number of " + length + " digits");

		// Start of a loop
		for (int i = 1; i <= length; i++) {
			int value;

			if (count <= 4) // if a four or less digit number is being processed
				value = (int) num / ((int) Math.pow(10, length - i));
			else { // if more than four digit number is given then executed
				value = (int) num / 1000;
				result = result + ConvertNumberToString(value) + "Thousands, ";
				count = 3;
				i = length - 3;
				num = num - (value * 1000);
				continue;
			}

			if (count == 2) { // if count is 2 then the number is at Tens place
				switch (value) {
				case 1:
					if (num == 11) {
						result = result + "Eleven";
					} else if (num == 12) {
						result = result + "Twelve";
					} else if (num == 13) {
						result = result + "Thirteen";
					} else if (num == 14) {
						result = result + "Fourteen";
					} else if (num == 15) {
						result = result + "Fifteen";
					} else if (num == 16) {
						result = result + "Sixteen";
					} else if (num == 17) {
						result = result + "Seventeen";
					} else if (num == 18) {
						result = result + "Eighteen";
					} else if (num == 19) {
						result = result + "Nineteen";
					}
					break;
				case 2:
					result = result + "Twenty";
					break;
				case 3:
					result = result + "Thirty";
					break;
				case 4:
					result = result + "Forty";
					break;
				case 5:
					result = result + "Fifty";
					break;
				case 6:
					result = result + "Sixty";
					break;
				case 7:
					result = result + "Seventy";
					break;
				case 8:
					result = result + "Eighty";
					break;
				case 9:
					result = result + "Ninty";
					break;
				default:
					break;
				}
			} else {
				switch (value) {
				case 1:
					result = result + "One";
					break;
				case 2:
					result = result + "Two";
					break;
				case 3:
					result = result + "Three";
					break;
				case 4:
					result = result + "Four";
					break;
				case 5:
					result = result + "Five";
					break;
				case 6:
					result = result + "Six";
					break;
				case 7:
					result = result + "Seven";
					break;
				case 8:
					result = result + "Eight";
					break;
				case 9:
					result = result + "Nine";
					break;
				case 10:
					result = result + "Ten";
					break;
				default:
					break;
				}

				if (count == 5) {

				} else if (count == 4) { // if a fourth postion number is
											// processed
					// then add
					result = result + " Thousand";
				} else if (count == 3) { // if a third position number is
											// processed
					// then add
					result = result + " Hundred and";
				}
			}

			// add a space after every number
			result = result + " ";
			// decrement the position of the digit to be processed
			count = count - 1;
			// deduct the value of the num so that the processed digit goes out
			num = num - (value * (int) Math.pow(10, length - i));
		}
		// return the number in string form
		return result;
	}

	public static void main(String[] args) {
		int num = 148305;
		/*
		 * System.out.println("Enter a number"); try { num=System.in.read();
		 * }catch(IOException e) { e.printStackTrace(); }
		 */
		NumberConvert numcon = new NumberConvert();
		String result = numcon.ConvertNumberToString(num);
		System.out.println(result);
		
		System.out.println("\n");
        IntToEnglish itoe = new IntToEnglish();
        try {
    		num = 5;
			System.out.println(num + " => " + itoe.english_number(num));
    		num = 5040;
			System.out.println(num + " => " + itoe.english_number(num));
    		num = 539023;
			System.out.println(num + " => " + itoe.english_number(num));
    		num = 999045940;
			System.out.println(num + " => " + itoe.english_number(num));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	static class IntToEnglish {
	    static String[] to_19 = { "zero",  "one",   "two",  "three", "four",   "five",   "six",
	        "seven", "eight", "nine", "ten",   "eleven", "twelve", "thirteen",
	        "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
	    static String[] tens  = { "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	    static String[] denom = { "",
	        "thousand",     "million",         "billion",       "trillion",       "quadrillion",
	        "quintillion",  "sextillion",      "septillion",    "octillion",      "nonillion",
	        "decillion",    "undecillion",     "duodecillion",  "tredecillion",   "quattuordecillion",
	        "sexdecillion", "septendecillion", "octodecillion", "novemdecillion", "vigintillion" };

//	    public static void main(String[] argv) throws Exception {
//	        int tstValue = Integer.parseInt(argv[0]);
//	        IntToEnglish itoe = new IntToEnglish();
//	        System.out.println(itoe.english_number(tstValue));
//	        /* for (int i = 0; i < 2147483647; i++) {
//	            System.out.println(itoe.english_number(i));
//	        } */
//	    }
	    
	    // convert a value < 100 to English.
	    private String convert_nn(int val) throws Exception {
	        if (val < 20)
	            return to_19[val];
	        for (int v = 0; v < tens.length; v++) {
	            String dcap = tens[v];
	            int dval = 20 + 10 * v;
	            if (dval + 10 > val) {
	                if ((val % 10) != 0)
	                    return dcap + "-" + to_19[val % 10];
	                return dcap;
	            }        
	        }
	        throw new Exception("Should never get here, less than 100 failure");
	    }
	    // convert a value < 1000 to english, special cased because it is the level that kicks 
	    // off the < 100 special case.  The rest are more general.  This also allows you to
	    // get strings in the form of "forty-five hundred" if called directly.
	    private String convert_nnn(int val) throws Exception {
	        String word = "";
	        int rem = val / 100;
	        int mod = val % 100;
	        if (rem > 0) {
	            word = to_19[rem] + " hundred";
	            if (mod > 0) {
	                word = word + " ";
	            }
	        }
	        if (mod > 0) {
	            word = word + convert_nn(mod);
	        }
	        return word;
	    }
	    public String english_number(int val) throws Exception {
	        if (val < 100) {
	            return convert_nn(val);
	        }
	        if (val < 1000) {
	            return convert_nnn(val);
	        }
	        for (int v = 0; v < denom.length; v++) {
	            int didx = v - 1;
	            int dval = new Double(Math.pow(1000, v)).intValue();
	            if (dval > val) {
	                int mod = new Double(Math.pow(1000, didx)).intValue();
	                int l = val / mod;
	                int r = val - (l * mod);
	                String ret = convert_nnn(l) + " " + denom[didx];
	                if (r > 0) {
	                    ret = ret + ", " + english_number(r);
	                }
	                return ret;
	            }
	        }
	        throw new Exception("Should never get here, bottomed out in english_number");
	        
	    }
	}
}