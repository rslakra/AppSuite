package com.apparatus.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @created 01/12/2012 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
public class Page {
	private int pageSize = 5;
	private int noOfPages;
	private int currentPage;
	private int previousPage;
	private int nextPage;

	/** contents */
	private List<String> contents;

	/**
	 * 
	 */
	public Page() {
		this(new ArrayList<String>());
	}

	/**
	 * 
	 * @param contents
	 *            a list of string contents.
	 */
	public Page(List<String> contents) {
		this.contents = contents;
		noOfPages = (contents.size() == 0 ? 1 : contents.size() / pageSize);
		nextPage = previousPage = currentPage = 1;
	}

	public int getNoOfPages() {
		return noOfPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public boolean hasPreviousPage() {
		return (getCurrentPage() > 1);
	}

	public int getPreviousPage() {
		if (hasPreviousPage()) {
			previousPage = getCurrentPage() - 1;
			currentPage--;
		}
		return previousPage;
	}

	public boolean hasNextPage() {
		return (getCurrentPage() < getNoOfPages());
	}

	public int getNextPage() {
		if (hasNextPage()) {
			nextPage = getCurrentPage() + 1;
			currentPage++;
		}
		return nextPage;
	}

	/**
	 * Returns the current page contents.
	 * 
	 * @param page
	 * @return
	 */
	public List<String> getContents(int page) {
		return contents.subList(currentPage * pageSize - pageSize,
				(currentPage * pageSize));
	}

	/**
	 * Represents the string representation of the object.
	 * 
	 * @return
	 */
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();

		return sBuilder.toString();
	}

	/**
	 * Prints the current page information.
	 * 
	 * -------------------------------------------------------------- <Previous>
	 * xx/total <Next>
	 * -------------------------------------------------------------- Contents
	 * --------------------------------------------------------------
	 * 
	 * @return
	 */
	public String printCurrentPage() {
		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append("-----------------------------------------------------------------\n");
		sBuilder.append(getPreviousPage()).append("\t\t\t\t");
		sBuilder.append(getCurrentPage()).append("/").append(getNoOfPages());
		sBuilder.append("\t\t\t\t").append(getNextPage()).append("\n");
		sBuilder.append("-----------------------------------------------------------------\n");
		List<String> curPageContents = getContents(getCurrentPage());
		for (String str : curPageContents) {
			sBuilder.append(str).append("\n");
		}
		sBuilder.append("-----------------------------------------------------------------\n");
		return sBuilder.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> lContents = new ArrayList<String>();
		for (int i = 0; i < 50; i++) {
			lContents.add("This is [ " + (i + 1) + " ] line.");
		}
		// System.out.println(lContents);
		Page pages = new Page(lContents);
		System.out.println(pages.printCurrentPage());
	}
}