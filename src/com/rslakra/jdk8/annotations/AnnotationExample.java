/**
 * 
 */
package com.rslakra.jdk8.annotations;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Rohtash Singh Lakra
 */
public class AnnotationExample {
	
	/**
	 * Returns true if the value is even number otherwise false.
	 * 
	 * Java7: Multiple values of the annotation
	 * 
	 * @param value
	 * @return
	 */
	@TestCases({ @TestCase(value = 1, expected = false), @TestCase(value = 2, expected = true) })
	public boolean isEven(Integer value) {
		return (value % 2 == 0);
	}
	
	/**
	 * Java8 way of doing the same thing as in <code>isEven</code> method above.
	 * 
	 * @param value
	 * @return
	 */
	@TestCase(value = 3, expected = true)
	@TestCase(value = 4, expected = false)
	public boolean isOdd(Integer value) {
		return !isEven(value);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		AnnotationExample aExample = new AnnotationExample();
		
		try {
			Method[] methods = aExample.getClass().getMethods();
			System.out.println("methods:" + methods);
			
			Method evenMethod = aExample.getClass().getMethod("isEven", Integer.class);
			System.out.println("evenMethod:" + evenMethod);
			TestCases aTestCases = evenMethod.getAnnotation(TestCases.class);
			System.out.println(aExample.isEven(30));
			Arrays.asList(aTestCases.value()).forEach(tc -> System.out.println(tc.value()));
		} catch(NoSuchMethodException | SecurityException ex) {
			ex.printStackTrace();
		}
		
		System.out.println();
		
		try {
			Method oddMethod = aExample.getClass().getMethod("isOdd", new Class[] { Integer.class });
			System.out.println("oddMethod:" + oddMethod);
			TestCases aTestCases = oddMethod.getAnnotation(TestCases.class);
			System.out.println(aExample.isOdd(30));
			Arrays.asList(aTestCases.value()).forEach(tc -> System.out.println(tc.value()));
		} catch(NoSuchMethodException | SecurityException ex) {
			ex.printStackTrace();
		}
		
	}
	
}
