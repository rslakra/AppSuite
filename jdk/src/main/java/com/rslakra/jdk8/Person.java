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
