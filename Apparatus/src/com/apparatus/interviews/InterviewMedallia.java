package com.apparatus.interviews;

import java.util.Random;
import java.util.TreeSet;

// given to me initially
interface Iterator<T> {
	boolean hasNext();
	
	T next();
}

/**
 * 
 * @author Rohtash Singh Lakra
 * @created May 1, 2016 7:28:27 PM
 * 
 */
public class InterviewMedallia {
	
	// static final int MAX_COUNT = 1000000;
	static final int MAX_COUNT = 5;
	static final int MAX_NUMBER = 100000000;
	static final int BUFFER_SIZE = 10 * 1024;
	
	static class RandomGenerator {
		private final Random random = new Random();
		
		/**
		 * Returns the random number up to the 100000000.
		 * 
		 * @return
		 */
		public int nextInt() {
			return random.nextInt(MAX_NUMBER);
		}
		
		/**
		 * Returns the random number up to the vien maxNumber.
		 * 
		 * @param maxNumber
		 * @return
		 */
		public int nextInt(int maxNumber) {
			return random.nextInt(maxNumber);
		}
		
		/**
		 * 
		 * @param minNumber
		 * @param maxNumber
		 * @return
		 */
		public double randomInRange(double minNumber, double maxNumber) {
			double range = maxNumber - minNumber;
			double scaled = random.nextDouble() * range;
			double shifted = scaled + minNumber;
			return shifted;
		}
		
		/**
		 * 
		 * @param maxNumber
		 * @return
		 */
		public double nextDouble(int maxNumber) {
			return randomInRange(-1.1, maxNumber);
		}
	}
	
	/**
	 * 
	 * @author Rohtash Singh Lakra
	 * @created May 6, 2016 4:04:39 PM
	 */
	static class DoubleTree extends TreeSet<Double> implements Iterator<Double> {
		
		/** serialVersionUID */
		private static final long serialVersionUID = 1L;
		
		/**
		 * @see java.util.TreeSet#add(java.lang.Object)
		 */
		public boolean add(Double value) {
			if(isFull()) {
				save();
			} else {
				super.add(value);
			}
			return false;
		}
		
		public void save() {
		}
		
		public void read() {
			
		}
		
		/**
		 * 
		 * @return
		 */
		public boolean isFull() {
			return (size() >= MAX_COUNT);
		}
		
		/**
		 * @see com.apparatus.interviews.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return false;
		}
		
		/**
		 * @see com.apparatus.interviews.Iterator#next()
		 */
		@Override
		public Double next() {
			return null;
		}
	}
	
	// public static Iterator<Double> sort(final Iterator<Double> doubleNumbers)
	// {
	// DoubleTree doubleTree = new DoubleTree();
	// Set<Double> treeSet = new TreeSet<Double>();
	// List<Integer> fileList = new LinkedList<Integer>();
	// // Max element count in data structures. Max number of elements is
	// LIMIT * LIMIT.
	// while(numberset.haxNext())
	// {
	// int fileName = 1;
	// int len = treeSet.size();
	// while(treeSet.size() < Limit)
	// {
	// treeSet.add(numberset.next())
	// if(!numberSet.hasNext())
	// break;
	// }
	//
	//
	// FileWriter fw = new FileWriter(fileName);
	// while (treeSet.hasNext())
	// {
	// fw.write(treeSet.next());
	// }
	// fw.close();
	// fileList.add(fileName);
	// treeSet.clear();
	//
	// } //end of while loop for nuber set
	
	// List<FileReader> fileReaders = new LinkedList<FileReader>();
	// for (int count = 0; count < fileList.length(); count++)
	// {
	// String fileName = fileList[count];
	// FileReader reader = new FileReader(fileName);
	// fileReaders.add(reader);
	//
	// }
	//
	// for(int i = 0; i<fileReader.length(), i++)
	// {
	// if(fileReader[i].hasNext())
	// {
	//
	// treeSet.add(fileReader.next());
	// }
	// }
	//
	// }
	
	public static void main(String[] args) {
		RandomGenerator randomGenerator = new RandomGenerator();
		DoubleTree doubleTree = new DoubleTree();
		int numbers = 10;
		System.out.println("Random Numbers:");
		Double nextDouble = null;
		for(int i = 0; i < numbers; i++) {
			// System.out.println(randomGenerator.nextInt(numbers));
			nextDouble = randomGenerator.nextDouble(numbers);
			doubleTree.add(nextDouble);
		}
		
		System.out.println("Size:" + doubleTree.size());
		System.out.println(doubleTree);
	}
	
}

// given later
class FileWriter {
	public FileWriter(String fileName) {
		// TODO: Write your code here
	}
	
	public void write(double value) {
		// TODO: Write your code here
		
	}
	
	public void close() {
		// TODO: Write your code here
		
	}
}

class FileReader {
	public FileReader(String fileName) {
		// TODO: Write your code here
	}
	
	public boolean hasNext() {
		// TODO: Write your code here
		return false;
	}
	
	public double next() {
		// TODO: Write your code here
		return Double.MIN_VALUE;
	}
	
	public void close() {
		// TODO: Write your code here
	}
}

class Pair<Key, Value> {
	private final Key key;
	private final Value value;
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public Pair(Key key, Value value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * @return the key
	 */
	public final Key getKey() {
		return key;
	}
	
	/**
	 * @return the value
	 */
	public final Value getValue() {
		return value;
	}
	
}