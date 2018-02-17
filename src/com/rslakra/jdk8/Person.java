/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009-2018.
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
package com.rslakra.jdk8;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-01-26 07:42:55 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class Person {
	private String name;
	private String ageString;
	private LocalDate birthDate;
	private int age;
	private char sex;
	
	/**
	 * 
	 * @param name
	 */
	public Person(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * The name to be set.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the birthDate
	 *
	 * @return birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	/**
	 * The birthDate to be set.
	 * 
	 * @param birthDate
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
		if(this.birthDate != null) {
			Period period = Period.between(this.birthDate, LocalDate.now());
			this.ageString = period.toString();
			this.age = period.getYears();
		}
	}
	
	/**
	 * Returns the full age as string.
	 *
	 * @return age
	 */
	public String getAgeString() {
		return ageString;
	}
	
	/**
	 * Returns the age.
	 *
	 * @return age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Returns the sex.
	 *
	 * @return sex
	 */
	public char getSex() {
		return sex;
	}
	
	/**
	 * The sex to be set.
	 * 
	 * @param sex
	 */
	public void setSex(char sex) {
		this.sex = sex;
	}
	
	/**
	 * Returns true if the age is >= 18 years.
	 * 
	 * @return
	 */
	public boolean isAdult() {
		return getAge() >= 18;
	}
	
	/**
	 * Returns the string representation of this object.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ("[" + getName() + ", " + getAgeString() + " (" + getAge() + ") Years, Sex:" + getSex() + "]");
	}
	

}
