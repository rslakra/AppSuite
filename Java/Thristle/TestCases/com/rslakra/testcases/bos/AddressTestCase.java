package com.rslakra.testcases.bos;

import com.rslakra.bos.Address;
import com.rslakra.bos.City;
import com.rslakra.bos.Continent;
import com.rslakra.bos.Country;
import com.rslakra.bos.State;
import com.rslakra.testcases.utils.TestCaseUtil;

public class AddressTestCase {
	
	public static void main(String[] args) {
		Address address = new Address();
		TestCaseUtil.populateDefaults(address);
		
		City city = new City();
		TestCaseUtil.populateDefaults(city);
		address.setCity(city);
		
		State state = new State();
		TestCaseUtil.populateDefaults(state);
		city.setState(state);
		
		Country country = new Country();
		TestCaseUtil.populateDefaults(country);
		state.setCountry(country);
		
		Continent continent = new Continent();
		TestCaseUtil.populateDefaults(continent);
		country.setContinent(continent);
		
		System.out.println(address);
	}
	
}
