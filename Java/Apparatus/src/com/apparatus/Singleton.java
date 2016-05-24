package com.apparatus;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Singleton class and don't allow to override methods.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 31, 2015 10:10:37 AM
 */
final public class Singleton implements Cloneable, Serializable {
	
	// serialVersionUID
	private static final long serialVersionUID = 1L;
	
	// singleton
	private static Singleton instance;
	
	private int value;
	
	/**
	 * 
	 */
	private Singleton() {
		if(null != instance) {
			throw new InstantiationError("Duplicate instance creation is not allowed!");
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static Singleton getInstance() {
		if(instance == null) {
			// handle multi-threading
			synchronized(Singleton.class) {
				if(instance == null) {
					instance = new Singleton();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * 
	 */
	public void printHashCode() {
		System.out.println(getClass().getSimpleName() + ", hashCode:" + this.hashCode());
	}
	
	/**
	 * To maintain the singleton guarantee, you must also override a
	 * <code>clone</code> method.
	 * 
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Clone is not allowed.");
	}
	
	/**
	 * To maintain the singleton guarantee, you must also provide a
	 * <code>readResolve</code> method.
	 * 
	 * @return
	 * @throws ObjectStreamException
	 */
	private Object readResolve() throws ObjectStreamException {
		return Singleton.this;
	}
	
	// /**
	// * Avoid using custom classloaders - all singletons should be loaded by
	// * common parent classloader.
	// *
	// *
	// * @param classname
	// * @return
	// * @throws ClassNotFoundException
	// */
	// private static Class<?> getClass(String className) throws
	// ClassNotFoundException {
	// ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	// if(classLoader == null) {
	// classLoader = Singleton.class.getClassLoader();
	// }
	// return (classLoader.loadClass(className));
	// }
}
