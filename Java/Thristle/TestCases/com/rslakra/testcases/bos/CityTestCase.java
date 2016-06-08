package com.rslakra.testcases.bos;

import com.rslakra.bos.City;
import com.rslakra.bos.Continent;
import com.rslakra.bos.Country;
import com.rslakra.bos.State;
import com.rslakra.testcases.utils.TestCaseUtil;

public class CityTestCase {
	
	public static void main(String[] args) {
		City city = new City();
		TestCaseUtil.populateDefaults(city);
		
		State state = new State();
		TestCaseUtil.populateDefaults(state);
		city.setState(state);
		
		Country country = new Country();
		TestCaseUtil.populateDefaults(country);
		state.setCountry(country);
		
		Continent continent = new Continent();
		TestCaseUtil.populateDefaults(continent);
		country.setContinent(continent);
		
		city.setState(state);
		System.out.println(city);
	}
	
}
