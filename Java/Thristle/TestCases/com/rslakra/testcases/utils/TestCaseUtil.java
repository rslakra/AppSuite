package com.rslakra.testcases.utils;

import java.util.ArrayList;
import java.util.List;

import com.rslakra.bos.Address;
import com.rslakra.bos.City;
import com.rslakra.bos.Continent;
import com.rslakra.bos.Country;
import com.rslakra.bos.Phone;
import com.rslakra.bos.State;
import com.rslakra.bos.User;

public class TestCaseUtil {
	
	public static void populateDefaults(Continent continent) {
		continent.setName("Asia");
		continent.setArea("0");
	}
	
	public static void populateDefaults(Country country) {
		country.setName("India");
		country.setCode("91");
	}
	
	public static void populateDefaults(State state) {
		state.setName("Haryana");
		state.setCode("01262");
	}
	
	public static void populateDefaults(City city) {
		city.setName("Rohtak");
		city.setCode("01262");
		city.setZip("124001");
	}
	
	public static void populateDefaults(Phone phone) {
		phone.setCountryCode("+91");
		phone.setAreaCode("01261");
		phone.setNumber("9818199533");
		phone.setExtensions(getPhoneExtensions());
	}
	
	public static void populateDefaults(Address address) {
		address.setId(1);
		address.setStreet("H. No. 248");
		address.setProvince("Kanheli");
		// Phone phone = new Phone();
		// populateDefaults(phone);
		address.setPhoneNumber("+91-9818199533");
		address.setMobileNumber("+91-9818199533");
		address.setEmail("rohtash.singh@devmatre.com");
		address.setWebSite("www.testing.com");
	}
	
	public static void populateDefaults(User user) {
		user.setId(1601);
		user.setPassword("Password");
	}
	
	public static List<String> getPhoneExtensions() {
		List<String> phoneExtensions = new ArrayList<String>();
		phoneExtensions.add("318521");
		phoneExtensions.add("318522");
		phoneExtensions.add("318523");
		phoneExtensions.add("318524");
		phoneExtensions.add("318525");
		phoneExtensions.add("318526");
		return phoneExtensions;
	}
	
}
