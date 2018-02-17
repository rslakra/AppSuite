package com.rslakra.jdk8.lambdaexpr;

public class MyRunnable implements Runnable {
	
	/*
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for(int i = 1; i <= 5; i++) {
			System.out.println("Thread[" + i + "]" + Thread.currentThread().getName());
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Thread mThread = new Thread(new MyRunnable());
		mThread.start();
		try {
			mThread.join();
		} catch(InterruptedException ex) {
			// ex.printStackTrace();
		}
		
		System.out.println("\n");
		
		// with lambda expression.
		Runnable lambdaRunnable = () -> {
			for(int i = 1; i <= 5; i++) {
				System.out.println("Thread[" + i + "]" + Thread.currentThread().getName());
			}
		};
		
		Thread lambdaThread = new Thread(lambdaRunnable);
		lambdaThread.start();
		try {
			lambdaThread.join();
		} catch(InterruptedException ex) {
			// ex.printStackTrace();
		}
	}
}
