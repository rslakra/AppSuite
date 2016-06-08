package com.rslakra.testcases.bos;

import com.rslakra.bos.Continent;
import com.rslakra.bos.Country;
import com.rslakra.bos.State;
import com.rslakra.testcases.utils.TestCaseUtil;

public class StateTestCase {
	
	public static void main(String[] args) {
		State state = new State();
		TestCaseUtil.populateDefaults(state);
		
		Country country = new Country();
		TestCaseUtil.populateDefaults(country);
		state.setCountry(country);
		
		Continent continent = new Continent();
		TestCaseUtil.populateDefaults(continent);
		country.setContinent(continent);
		System.out.println(state);
	}
	
}
