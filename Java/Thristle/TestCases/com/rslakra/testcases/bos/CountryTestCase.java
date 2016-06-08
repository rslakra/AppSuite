package com.rslakra.testcases.bos;

import com.rslakra.bos.Continent;
import com.rslakra.bos.Country;

public class CountryTestCase extends AbstractTestCase {
	
	public CountryTestCase() {
		super(new Country());
	}
	
	/**
	 * 
	 */
	@Override
	public void populateDefault() {
		Country country = getBusinessObject();
		country.setName("India");
		country.setCode("91");
		
		Continent continent = new Continent();
		continent.setName("Asia");
		continent.setArea("0");
		country.setContinent(continent);
		System.out.println(country);
	}
	
	public static void main(String[] args) {
		AbstractTestCase testCase = new CountryTestCase();
		testCase.populateDefault();
		Country country = testCase.getBusinessObject();
		System.out.println(country);
	}
	
}
