package com.rslakra.testcases.bos;

import com.rslakra.bos.Continent;

public class ContinentTestCase extends AbstractTestCase {
	
	public ContinentTestCase() {
		super(new Continent());
	}
	
	/**
	 * 
	 */
	@Override
	public void populateDefault() {
		Continent continent = getBusinessObject();
		continent.setName("Asia");
		continent.setArea("0");
	}
	
	public static void main(String[] args) {
		AbstractTestCase testCase = new ContinentTestCase();
		testCase.populateDefault();
		Continent continent = testCase.getBusinessObject();
		System.out.println(continent);
		
	}
	
}
