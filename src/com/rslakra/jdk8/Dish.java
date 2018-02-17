package com.rslakra.jdk8;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 02/02/2018 01:04:58 PM
 */
public class Dish {
	
	public enum Type {
		MEAT,
		FISH,
		OTHER
	}
	
	private final String name;
	private final boolean vegetarian;
	private final int calories;
	private final Type type;
	
	/**
	 * 
	 * @param name
	 * @param vegetarian
	 * @param calories
	 * @param type
	 */
	public Dish(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isVegetarian() {
		return vegetarian;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCalories() {
		return calories;
	}
	
	/**
	 * 
	 * @return
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return name;
	}
}