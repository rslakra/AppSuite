package com.rslakra.testcases.bos;

import java.util.Date;

import com.rslakra.bos.library.Book;

public class BookTestCase {
	
	public static void main(String[] args) {
		Book book = new Book();
		book.setId(1001);
		book.setTitle("Java");
		book.setAuthor("Brush Eckle");
		book.setPublishedOn(new Date());
		book.setPublisher("O'relly");
		
		System.out.println(book);
		System.out.println("Date : " + book.dateToString(book.getPublishedOn()));
		System.out.println(book.toXmlString());
	}
	
}
