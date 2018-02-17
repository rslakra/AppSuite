package com.rslakra.algorithms.matrix;

public class Matrix {
	
	/**
	 * 
	 * @param data
	 */
	public static void printMatrix(int[][] data) {
		for(int row = 0; row < data.length; row++) {
			for(int column = 0; column < data.length; column++) {
				System.out.print(data[row][column] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
}
