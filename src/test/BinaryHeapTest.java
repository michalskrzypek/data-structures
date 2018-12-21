package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.BinaryHeap;

/**
 * Tests BinaryHeap methods
 * @author mskrz
 *
 */
public class BinaryHeapTest {

	/**
	 * Tests adding new nodes
	 */
	@Test
	public void testAdd() {
		BinaryHeap<Integer> a = new BinaryHeap<Integer>();
		a.add(10);
		a.add(9);
		a.add(8);
		assertTrue(a.toString().equals("[8, 10, 9]"));
		a.add(7);
		assertEquals("[7, 8, 9, 10]", a.toString());
		a.add(6);
		assertEquals("[6, 7, 9, 10, 8]", a.toString());
		a.add(5);
		assertEquals("[5, 7, 6, 10, 8, 9]", a.toString());
		a.add(4);
		assertEquals("[4, 7, 5, 10, 8, 9, 6]", a.toString());
	}
	
	/**
	 * Tests obtaining min element of the binary heap
	 */
	@Test
	public void testGetMin() {
		BinaryHeap<Integer> a = new BinaryHeap<Integer>();
		a.add(9);
		a.add(8);
		a.add(7);
		a.add(6);
		a.add(5);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		assertEquals("[1, 3, 2, 4, 7, 8, 5, 9, 6]", a.toString());
		assertEquals(1,  (int) a.getMin());
		assertEquals("[2, 3, 5, 4, 7, 8, 6, 9]", a.toString());
	}

	/**
	 * Tests adding getting min value and returning string representation of the binary heap.
	 */
	@Test
	public void testFull() {
		BinaryHeap<Character> a = new BinaryHeap<Character>();
		a.add('f');
		a.add('g');
		a.add('a');
		a.add('z');
		a.add('d');
		assertEquals("[a, d, f, z, g]", a.toString());
		assertEquals('a',  (int) a.getMin());
		assertEquals("[d, g, f, z]", a.toString());
	}
	
	@Test
	public void testConstructor() {
		Integer[] i = new Integer[]{10,9,8,7,6,5,4,3,2,1};
		BinaryHeap<Integer> a = new BinaryHeap<Integer>(i);
		assertEquals("[1, 2, 4, 3, 6, 5, 8, 10, 7, 9]", a.toString());
	}

	/**
	 * tests remove method and inverse resizing.
	 */
	@Test
	public void testRemove() {
		Integer[] i = new Integer[]{3, 4, 12, 6, 5, 16, 15};
		BinaryHeap<Integer> a = new BinaryHeap<Integer>(i);
		a.remove(4);
		assertEquals("[3, 5, 12, 6, 15, 16]", a.toString());
	}	
	
}
