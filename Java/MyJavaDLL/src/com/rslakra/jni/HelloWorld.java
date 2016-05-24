package com.rslakra.jni;

public class HelloWorld {
	private String name;

	public HelloWorld(String name) {
		this.name = name;
	}

	public String sayHello() {
		return ("Hello " + name);
	}

	public static void main(String[] args) {
//		DOMConfigurator.configure("log4j.xml");
//		Logger log = Logger.getLogger(HelloWorld.class);
//		HelloWorld helloWorld = new HelloWorld("Testing");
//		log.info(helloWorld);
//		helloWorld.sayHello();
		
	}
}