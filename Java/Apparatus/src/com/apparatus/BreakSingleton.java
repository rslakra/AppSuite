package com.apparatus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Singleton Breaker.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 31, 2015 10:32:40 AM
 */
public class BreakSingleton {
	
	private static Singleton singleton = null;
	
	/**
	 * 
	 */
	public static void breakSingletonWithReflection() {
		System.out.println();
		
		Singleton instanceOne = Singleton.getInstance();
		instanceOne.printHashCode();
		System.out.println(instanceOne.hashCode());
		
		Singleton instanceTwo = null;
		try {
			Constructor<?>[] constructors = Singleton.class.getDeclaredConstructors();
			for(Constructor<?> constructor : constructors) {
				// should break the rule here.
				constructor.setAccessible(true);
				instanceTwo = (Singleton) constructor.newInstance();
				break;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		instanceTwo.printHashCode();
		System.out.println(instanceTwo.hashCode());
	}
	
	/**
	 * 
	 */
	public static void breakSingletonWithSerialization() {
		System.out.println();
		
		final String fileName = "singleton.ser";
		// getting singleton instance
		Singleton instanceOne = Singleton.getInstance();
		instanceOne.setValue(10);
		
		try {
			// Serialize to a file
			ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(fileName));
			objectOutput.writeObject(instanceOne);
			objectOutput.close();
			
			// now changed the value of singleton
			instanceOne.setValue(20);
			
			// Serialize to a file
			ObjectInput objectInput = new ObjectInputStream(new FileInputStream(fileName));
			Singleton instanceTwo = (Singleton) objectInput.readObject();
			objectInput.close();
			
			System.out.println("Instance One Value= " + instanceOne.getValue());
			System.out.println("Instance Two Value= " + instanceTwo.getValue());
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public static void breakSingletonWithClone() {
		System.out.println();
		final String fileName = "singleton.ser";
		// getting singleton instance
		Singleton singleton = Singleton.getInstance();
		singleton.setValue(10);
		try {
			Singleton clonedObject = (Singleton) singleton.clone();
			System.out.println(clonedObject);
			System.out.println(clonedObject.hashCode());
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @author Rohtash Singh (rohtash.singh@gmail.com)
	 * @version 1.0.0
	 * @since Jul 31, 2015 11:00:51 AM
	 */
	private static class SingletonRunnable implements Runnable {
		public void run() {
			Singleton threadSingleton = Singleton.getInstance();
			synchronized(BreakSingleton.class) {
				if(singleton == null)
					singleton = threadSingleton;
			}
			
			System.out.println("Thread 1st Object Hash:" + singleton.hashCode());
			System.out.println("Thread 2nd Object Hash:" + threadSingleton.hashCode());
		}
	}
	
	/**
	 * 
	 * @throws InterruptedException
	 */
	public static void breakSingletonWithThreads() throws InterruptedException {
		System.out.println();
		
		// Both threads call Singleton.getInstance().
		Thread threadOne = new Thread(new SingletonRunnable());
		Thread threadTwo = new Thread(new SingletonRunnable());
		threadOne.start();
		threadTwo.start();
		threadOne.join();
		threadTwo.join();
	}
	
	/**
	 * 
	 */
	public static void breakSingletonWithClassLoads() {
		System.out.println();
		final String className = "com.rslakra.core.Singleton";
		String jarFilePath = System.getProperty("user.dir") + File.separator + "JavaExamples-v1.0.0.jar";
		System.out.println("jarFilePath:" + jarFilePath);
		
		// 1st class loader
		Object singleton1 = null;
		try {
			ClassLoader classLoader1 = new URLClassLoader(new URL[] { new File(jarFilePath).toURI().toURL() });
			System.out.println("classLoader1:" + classLoader1);
			Class<?> classObject1 = classLoader1.loadClass(className);
			Method getInstance1 = classObject1.getDeclaredMethod("getInstance");
			singleton1 = getInstance1.invoke(null);
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(NoSuchMethodException e) {
			e.printStackTrace();
		} catch(SecurityException e) {
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		} catch(InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 2nd class loader
		Object singleton2 = null;
		try {
			ClassLoader classLoader2 = new URLClassLoader(new URL[] { new File(jarFilePath).toURI().toURL() });
			System.out.println("classLoader2:" + classLoader2);
			Class<?> classObject2 = classLoader2.loadClass(className);
			Method getInstance2 = classObject2.getDeclaredMethod("getInstance");
			singleton2 = getInstance2.invoke(null);
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(NoSuchMethodException e) {
			e.printStackTrace();
		} catch(SecurityException e) {
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		} catch(InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 3rd class loader
		Object singleton = null;
		try {
			ClassLoader classLoader = new URLClassLoader(new URL[] { new File(jarFilePath).toURI().toURL() });
			System.out.println("classLoader:" + classLoader);
			Class<?> classObject = Class.forName(className, true, classLoader);
			Method getInstance = classObject.getDeclaredMethod("getInstance");
			singleton = getInstance.invoke(null);
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(NoSuchMethodException e) {
			e.printStackTrace();
		} catch(SecurityException e) {
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		} catch(InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println("Classload 1st Object Hash:" + singleton1.hashCode());
		System.out.println("Classload 2nd Object Hash:" + singleton2.hashCode());
		System.out.println("Classload 3rd Object Hash:" + singleton.hashCode());
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 1st try
		try {
			breakSingletonWithReflection();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		// 2nd try
		try {
			breakSingletonWithSerialization();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		// 3rd try
		try {
			breakSingletonWithClone();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		// 4th try
		try {
			breakSingletonWithThreads();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		// 5th try
		try {
			breakSingletonWithClassLoads();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
}
