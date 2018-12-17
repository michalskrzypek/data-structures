package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Class representing Priority Queue as a Binary Heap
 * 
 * @author mskrz
 *
 * @param <T> element in a heap node
 */
public class BinaryHeap<T extends Comparable<T>> {

	private ArrayList<T> heap;

	/**
	 * Constructor initializing an empty heap
	 */
	public BinaryHeap() {
		heap = new ArrayList<>();
	}

	/**
	 * Constructor initializing a heap filled up with elements provided, according
	 * to filtering down
	 * 
	 * @param elements The elements to be inserted into the heap
	 */
	public BinaryHeap(T[] elements) {
		heap = new ArrayList<>(Arrays.asList(elements));

		for (int i = heap.size() / 2 - 1; i >= 0; i--) {
			filterDown(i);
		}
	}

	/**
	 * Removes provided element from the heap
	 * 
	 * @param element Element to be removed from the heap
	 */
	public void remove(T element) {
		filterUpUnconditional(heap.indexOf(element));
		getMin();
	}

	/**
	 * Returns the element with the highest priority in the heap
	 * 
	 * @return The element with the highest priority
	 */
	public T getMin() {
		T min = heap.get(0);

		Collections.swap(heap, 0, heap.size() - 1);
		heap.remove(heap.size() - 1);
		filterDown(0);

		return min;
	}

	/**
	 * Traverse the heap according to filtering down, starting with the element at
	 * the given position
	 * 
	 * @param pos A position in the heap to be filtered down
	 */
	public void filterDown(int pos) {
		T element;
		T child;
		while (pos < heap.size() / 2) {
			int childPos1 = pos * 2 + 1;
			int childPos2 = pos * 2 + 2;
			element = heap.get(pos);

			if (childPos2 < heap.size()) {
				child = heap.get(childPos1).compareTo(heap.get(childPos2)) < 0 ? heap.get(childPos1)
						: heap.get(childPos2);
			} else if (childPos1 < heap.size()) {
				child = heap.get(childPos1);
			} else {
				break;
			}

			if (element.compareTo(child) > 0) {
				int childPos = heap.indexOf(child);
				Collections.swap(heap, pos, childPos);
				pos = childPos;
			} else {
				break;
			}
		}
	}

	/**
	 * Unconditional filter up method, moves element at the given position to the
	 * root of the heap
	 * 
	 * @param pos An element's position to be filtered up to the root
	 */
	private void filterUpUnconditional(int pos) {
		int fatherPos = (pos - 1) / 2;
		while (fatherPos >= 0) {
			Collections.swap(heap, pos, fatherPos);
			pos = fatherPos;

			if (pos - 1 < 0) {
				break;
			}

			fatherPos = (pos - 1) / 2;
		}
	}

	/**
	 * Adds the element to the heap according to filtering up
	 * 
	 * @param element Element to be added into the heap
	 */
	public void add(T element) {
		heap.add(element);
		filterUp(heap.size() - 1);
	}

	/**
	 * Conditional filter up method, based on priority of the elements
	 * 
	 * @param pos An element's position to be filtered up to the root
	 */
	public void filterUp(int pos) {
		T element;
		T father;
		int fatherPos = (pos - 1) / 2;
		while (fatherPos >= 0) {
			element = heap.get(pos);
			father = heap.get(fatherPos);
			if (element.compareTo(father) < 0) {
				Collections.swap(heap, pos, fatherPos);
				pos = fatherPos;

				if (pos - 1 < 0) {
					break;
				}

				fatherPos = (pos - 1) / 2;
			} else {
				break;
			}
		}
	}

	/**
	 * Check whether the heap is empty
	 * 
	 * @return a boolean indicating the emptiness of the heap
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	/**
	 * Prints out the heap as an array
	 */
	public void print() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return heap.toString();
	}
}
