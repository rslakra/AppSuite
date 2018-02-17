/**
 * 
 */
package com.rslakra.java.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.rslakra.jdk8.Person;
import com.rslakra.utils.CoreHelper;

/**
 * @author Rohtash Singh Lakra
 */
public class ReflectionExample {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Person person = new Person("Rohtash Lakra");
		Method[] methods = person.getClass().getMethods();
		System.out.println("methods:" + methods);
		System.out.println();
		
		try {
			Method method = person.getClass().getMethod("getName");
			System.out.println(method.getName() + " is getter:" + CoreHelper.isGetter(method));
			
			method = person.getClass().getMethod("setName", String.class);
			System.out.println(method.getName() + " is setter:" + CoreHelper.isSetter(method));
			
			method = person.getClass().getMethod("isAdult");
			System.out.println(method.getName() + " is getter:" + CoreHelper.isGetter(method));
			
			// Read private field 'middleName' by reflection
			Name name = new Name("Rohtash", "Lakra");
			Field middleNameField = Name.class.getDeclaredField("middleName");
			middleNameField.setAccessible(true);
			String middleName = (String) middleNameField.get(name);
			System.out.println(middleName);
			// set value of the private field
			middleNameField.set(name, "Singh");
			
			// set middle name by reflection
			Method setMiddleNameMethod = Name.class.getDeclaredMethod("setMiddleName", String.class);
			setMiddleNameMethod.setAccessible(true);
			setMiddleNameMethod.invoke(name, "Singh");
			
			// get middle name by reflection method
			Method getMiddleNameMethod = Name.class.getDeclaredMethod("getMiddleName", null);
			getMiddleNameMethod.setAccessible(true);
			middleName = (String) getMiddleNameMethod.invoke(name, null);
			System.out.println(middleName);
			
		} catch(NoSuchMethodException | SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException | InvocationTargetException ex) {
			ex.printStackTrace();
		}
	}
	
}
