/**
 * Copyright (c) 2009 Emerging Health IT. All rights reserved.
 * 
 * @author Rohtash Singh (rosingh@emerginghealthit.com)
 * @version 1.0
 */
package com.devamatre.core.lang;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The <tt>Stack</tt> class represents a last-in-first-out (LIFO) stack of
 * generic items. It supports the usual <em>push</em> and <em>pop</em>
 * operations, along with methods for peeking at the top item, testing if the
 * stack is empty, and iterating through the items in LIFO order.
 * <p>
 * All stack operations except iteration are constant time.
 * <p>
 * For additional documentation, see <a href="/algs4/13stacks">Section 1.3</a>
 * of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Stack<E> implements Iterable<E> {
	private int size; // size of the stack
	private Node first; // top of stack

	// helper linked list class
	private class Node {
		private E item;
		private Node next;
	}

	/**
	 * Create an empty stack.
	 */
	public Stack() {
		first = null;
		size = 0;
	}

	/**
	 * Is the stack empty?
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Return the number of items in the stack.
	 */
	public int size() {
		return size;
	}

	/**
	 * Add the item to the stack.
	 */
	public void push(E item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		size++;
	}

	/**
	 * Delete and return the item most recently added to the stack. Throw an
	 * exception if no such item exists because the stack is empty.
	 */
	public E pop() {
		if (isEmpty())
			throw new RuntimeException("Stack underflow");
		E item = first.item; // save item to return
		first = first.next; // delete first node
		size--;
		return item; // return the saved item
	}

	/**
	 * Return the item most recently added to the stack. Throw an exception if
	 * no such item exists because the stack is empty.
	 */
	public E peek() {
		if (isEmpty())
			throw new RuntimeException("Stack underflow");
		return first.item;
	}

	/**
	 * Return string representation.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (E item : this)
			s.append(item + " ");
		return s.toString();
	}

	/**
	 * Return an iterator to the stack that iterates through the items in LIFO
	 * order.
	 */
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<E> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E item = current.item;
			current = current.next;
			return item;
		}
	}

	/**
	 * A test client.
	 */
	public static void main(String[] args) {
		Stack<String> strStack = new Stack<String>();
		// while (!StdIn.isEmpty()) {
		// String item = SystemUtils.getStdIn().readString();
		// if (!item.equals("-"))
		// s.push(item);
		// else if (!s.isEmpty())
		// StdOut.print(s.pop() + " ");
		// }
		// StdOut.println("(" + s.size() + " left on stack)");
	}
}