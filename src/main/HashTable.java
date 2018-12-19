package main;

import java.util.ArrayList;

//R - previos prime number before B (lower than B)... f(x) = (x + i*(R - x%R)) % B
// third constant double hashing, constructor set R to prev prime number of B
// implement getter for associative array
// logic implemented in the method dynamicResizewith parameter
/**
 * Class representing a dictionary type data structure - a hash table
 * 
 * @author Michal Skrzypek
 *
 * @param <T> element stored in the hash node
 */
public class HashTable<T> {

	public final static int LINEAR_PROBING = 0;
	public final static int QUADRATIC_PROBING = 1;
	public final static int DOUBLE_HASHING = 2;
	/*
	 * public final static double LOWER_THRESHOLD = 0.16d; public final static
	 * double UPPER_THRESHOLD = 0.5d;
	 */
	private int n = 0;
	private int R = 5;
	private int B = 7;
	private int redispersionType = LINEAR_PROBING;
	private double minLF = 0.5;

	private ArrayList<HashNode<T>> associativeArray;

	/**
	 * Constructor initializing new Hash Table
	 * 
	 * @param b                total slots in the hashtable
	 * @param redispersionType type of open addressing
	 * @param minLF            indicated the upper bound of acceptable the load
	 *                         factor
	 */
	public HashTable(int b, int redispersionType, double minLF) {
		this.B = b;
		this.R = getPrevPrimeNumber(this.B);
		this.redispersionType = redispersionType;
		this.minLF = minLF;

		associativeArray = new ArrayList<HashNode<T>>(B);
		for (int i = 0; i < B; i++) {
			associativeArray.add(new HashNode<T>());
		}
	}

	/**
	 * Returns the Load Factor of the hash table
	 * 
	 * @return a double indicating the Load Factor
	 */
	public double getLF() {
		return (double) n / B;
	}

	/**
	 * Gets the number of nodes in the hash table
	 * 
	 * @return a number indicating the number of nodes in the hash table
	 */
	public int getN() {
		return this.n;
		/*
		 * double sum = 0; for (HashNode<T> n : associativeArray) { if (n.getStatus() ==
		 * HashNode.VALID) { sum++; } } return sum;
		 */
	}

	/**
	 * Removes the node from the hash table with the specified element
	 * 
	 * @param element the element from the node to be removed
	 */
	public void remove(T element) {
		int i = 0;
		while (i < B) {
			int slot = f(element, i);
			if (associativeArray.get(slot).getStatus() == HashNode.VALID
					&& associativeArray.get(slot).getElement().equals(element)) {
				associativeArray.get(slot).setStatus(HashNode.DELETED);
				n--;
				return;
			} else if (associativeArray.get(slot).getStatus() == HashNode.EMPTY) {
				throw new RuntimeException("Element: " + element + " does not exist in the hash table");
			} else {
				i++;
			}
		}
	}

	/**
	 * Adds a node with the provided element to the hash table
	 * 
	 * @param element the element in the node to be added
	 */
	public void add(T element) {

		int i = 0;
		while (i < B) {
			int slot = f(element, i);
			if (associativeArray.get(slot).getStatus() == HashNode.EMPTY
					|| associativeArray.get(slot).getStatus() == HashNode.DELETED) {
				associativeArray.get(slot).setElement(element);
				associativeArray.get(slot).setStatus(HashNode.VALID);
				n++;
				break;
			} else {
				i++;
			}
		}

		if (this.getLF() > this.minLF) {
			dynamicResizing();
		}
	}

	/**
	 * Algorithm for resizing the hash table once the LF is above the upper bound
	 */
	public void dynamicResizing() {
		int newSize = getNextPrimeNumber(this.B * 2);
		dynamicResizing(newSize);
	}


	private void dynamicResizing(int newSize) {
		ArrayList<HashNode<T>> newAssociativeArray = initializeArray(newSize);
		setB(newSize);
		this.R = getPrevPrimeNumber(newSize);
		this.n = 0;
		
		for (int i = 0; i < associativeArray.size(); i++) {
			T element = associativeArray.get(i).getElement();
			if (element != null) {
				int j = 0;
				while (j < newSize) {
					int slot = f(element, j);
					if (newAssociativeArray.get(slot).getStatus() == HashNode.EMPTY) {
						newAssociativeArray.get(slot).setElement(element);
						newAssociativeArray.get(slot).setStatus(HashNode.VALID);
						n++;
						break;
					} else {
						j++;
					}
				}
			}
		}
		
		this.associativeArray = newAssociativeArray;
	}
	

	private ArrayList<HashNode<T>> initializeArray(int size) {
		ArrayList<HashNode<T>> newAssociativeArray = new ArrayList<HashNode<T>>(size);
		for (int i = 0; i < size; i++) {
			newAssociativeArray.add(new HashNode<T>());
		}
		return newAssociativeArray;
	}

	/**
	 * Searches for the node with the provided element in the hash table
	 * 
	 * @param element to be searched for in the hash table's nodes
	 * @return a boolean value indicating whether the node wit provided element was
	 *         found in the hash table
	 */
	public boolean search(T element) {
		boolean found = false;
		int i = 0;
		while (i < B) {
			int slot = f(element, i);
			if (associativeArray.get(slot).getStatus() == HashNode.VALID
					&& associativeArray.get(slot).getElement().equals(element)) {
				found = true;
				break;
			} else if (associativeArray.get(slot).getStatus() == HashNode.EMPTY) {
				break;
			} else {
				i++;
			}
		}
		return found;
	}
//f(x) = (x + i*(R - x%R)) % B

	/**
	 * Hashing function for storing elements in the hash table
	 * 
	 * @param element to be stored in the hash table
	 * @param i       Attempt number
	 * @return slot in the array where the element should be placed
	 */
	public int f(T element, int i) {
		switch (redispersionType) {
		case LINEAR_PROBING:
			return (element.hashCode() + i) % B;
		case QUADRATIC_PROBING:
			return (int) (element.hashCode() + Math.pow(i, 2)) % B;
		case DOUBLE_HASHING:
			return (int) ((element.hashCode() + i * (R - (element.hashCode() % R))) % B);
		default:
			throw new RuntimeException("Wrong open addressing method applied!");
		}
	}

	/**
	 * Prints out the hash table
	 */
	public void print() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < associativeArray.size(); i++) {
			HashNode<T> node = associativeArray.get(i);
			sb.append("[" + i + "] (" + node.getStatus() + ") = " + node.getElement() + " - ");
		}
		return sb.toString();
	}

	/**
	 * Sets new value for B
	 * 
	 * @param b size of the hash table
	 */
	public void setB(int b) {
		if (isPrime(b)) {
			B = b;
		} else {
			throw new RuntimeException(b + " is not a prime number!");
		}
	}

	/**
	 * Returns the first prime number above the number provided
	 * 
	 * @param n first prime number under the resulting prime number
	 * @return the first prime number above n
	 */
	public int getNextPrimeNumber(int n) {
		for (int i = n + 1;; i++) {
			if (isPrime(i)) {
				return i;
			}
		}
	}

	/**
	 * Returns the first prime number below the number provided
	 * 
	 * @param n first prime number above the resulting prime number
	 * @return the first prime number below n
	 */
	public int getPrevPrimeNumber(int n) {
		int result = 2;
		for (int i = n - 1; i > 0; i--) {
			if (isPrime(i)) {
				return i;
			}
		}
		return result;
	}

	/**
	 * Checks whether the number is prime
	 * 
	 * @param n number to check
	 * @return a boolean value (true if prime)
	 */
	public boolean isPrime(int n) {
		int i = 2;
		while (i <= n / 2) {
			if (n % i == 0) {
				return false;
			}
			i++;
		}
		return true;
	}

	public ArrayList<HashNode<T>> getAssociativeArray() {
		return associativeArray;
	}

	/*
	 * private boolean isAvailable() { for (HashNode<T> n : associativeArray) { if
	 * (n.getStatus() == HashNode.EMPTY || n.getStatus() == HashNode.DELETED) {
	 * return true; } } return false; }
	 */
}
