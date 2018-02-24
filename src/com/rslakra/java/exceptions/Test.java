/******************************************************************************
 * Copyright (C) Devamatre Inc 2006-2018. All rights reserved.
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
package com.rslakra.java.exceptions;

/**
 * TODO Auto-generated comments.
 *
 * @author rohtash.singh
 * @version May 19, 2006
 *
 */
public class Test {

	private static Test instance = new Test();

	public static Test getInstance() {
		return instance;
	}

	public void testArrayStoreException() {
		// Throws an ArrayStoreException
		Object x[] = new String[3];
		x[0] = new Integer(0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// uncomment to test it.
		// Test.getInstance().testArrayStoreException();
		Library library = new Library(new Book("Java", new Pages(50)));
		System.out.println(library);
		Library l = (Library) library.clone();
		System.out.println(l);

		System.out.println("java.compiler  : " + System.getProperty("java.compiler"));
	}

}

class Library implements Cloneable {

	private Book book;

	public Library(Book book) {
		this.book = book;
	}

	public Object clone() {
		System.out.println("Library Clone!");
		Library clone = null;
		try {
			clone = (Library) super.clone();
			clone.setBook(new Book(new Pages()));
		} catch (CloneNotSupportedException c) {
			System.out.println("Returnning Null!");

		}
		return clone;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String toString() {
		return "Library " + getBook();
	}
}

class Book {
	String name;
	Pages pages;

	public Book(Pages pages) {
		name = "Default Value";
		this.pages = pages;
	}

	public Book(String name, Pages pages) {
		this.name = name;
		this.pages = pages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String toString() {
		return "Book Name : " + getName() + ",  " + getPages();
	}
}

class Pages {
	int pages;

	public Pages() {
		pages = 0;
	}

	public Pages(int pages) {
		this.pages = pages;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String toString() {
		return "Pages : " + getPages();
	}
}