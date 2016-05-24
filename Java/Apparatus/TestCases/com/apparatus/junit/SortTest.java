package com.apparatus.junit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTest {
	
	static class Book {
		public String name;
		public int type;
		
		public String toString() {
			return ("Book<" + name + ", " + type + ">");
		}
	}
	
	public static void main(String[] args) {
		
		List<Book> list = new ArrayList<Book>();
		Book book = new Book();
		book.name = "Rohtash";
		book.type = 0;
		list.add(book);
		
		book = new Book();
		book.name = "Muthyala";
		book.type = 2;
		list.add(book);
		
		book = new Book();
		book.name = "Muthyala";
		book.type = 0;
		list.add(book);
		
		book = new Book();
		book.name = "Singh";
		book.type = 0;
		list.add(book);
		
		book = new Book();
		book.name = "Naveen";
		book.type = 1;
		list.add(book);
		
		book = new Book();
		book.name = "Rohtash";
		book.type = 1;
		list.add(book);
		
		book = new Book();
		book.name = "Muthyala";
		book.type = 0;
		list.add(book);
		
		book = new Book();
		book.name = "Muthyala";
		book.type = 1;
		list.add(book);
		
		System.out.println("Unsorted List:");
		System.out.println(list);
		
		Collections.sort(list, new Comparator<Book>() {
			public int compare(Book o1, Book o2) {
				// System.out.println("o1:" + o1 + ", o2:" + o2);
				int strResult = o1.name.compareToIgnoreCase(o2.name);
				// System.out.println("strResult:" + strResult);
				if(strResult != 0) {
					return strResult;
				} else {
					if(o1.type < o2.type) {
						// System.out.println("-1");
						return -1;
					} else if(o1.type > o2.type) {
						// System.out.println("1");
						return 1;
					}
					// System.out.println("0");
					return 0;
				}
				
				// int strResult = (o1.type - o2.type);
				// return (strResult == 0 ? o1.name.compareToIgnoreCase(o2.name)
				// : strResult);
			}
		});
		
		System.out.println("Sorted List:");
		System.out.println(list);
		
	}
	
}
