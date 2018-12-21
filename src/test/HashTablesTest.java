package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.AVLTree;
import main.HashTable;

/**
 * Custom test case for hash tables.
 * @author mskrz
 *
 */
public class HashTablesTest {
	
	@Test
	public void testAVLToHashTable() {
		HashTable<Integer> a = new HashTable<>(5, HashTable.LINEAR_PROBING, 0.5);
		AVLTree<Integer> b = new AVLTree<Integer>();
		b.add(6);
		b.add(4);
		a.addTree(b);
		assertEquals(0.4, a.getLF(), 0.1);
		assertEquals("[0] (0) = null - [1] (1) = 6 - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - ",  a.toString());
	}
	
	@Test
	public void testAVLToHashTable2() {
		HashTable<Integer> a = new HashTable<>(5, HashTable.LINEAR_PROBING, 0.5);
		AVLTree<Integer> b = new AVLTree<Integer>();
		b.add(6);
		b.add(4);
		b.add(3);
		b.add(5);
		a.addTree(b);

		assertEquals(0.36, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 5 - [6] (1) = 6 - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ",  a.toString());
	}
	
	@Test
	public void testAVLToHashTable3() {
		HashTable<Integer> a = new HashTable<>(5, HashTable.LINEAR_PROBING, 0.5);
		AVLTree<Integer> b = new AVLTree<Integer>();
		b.add(6);
		b.add(4);
		b.add(3);
		b.add(5);
		b.add(12);
		a.addTree(b);
		
		assertEquals(0.45, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 12 - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 5 - [6] (1) = 6 - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ",  a.toString());

		a.remove(3);
		assertEquals("[0] (0) = null - [1] (1) = 12 - [2] (0) = null - [3] (2) = 3 - [4] (1) = 4 - [5] (1) = 5 - [6] (1) = 6 - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ",  a.toString());
		a.remove(4);
		a.remove(6);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (0) = null - [5] (1) = 12 - [6] (1) = 5 - ",  a.toString());
	}
	
	@Test
	public void testConstructorAndJoin() {
		HashTable<Integer> a = new HashTable<>(5, HashTable.LINEAR_PROBING, 0.5);
		a.add(4);
		a.add(13);
		assertEquals(0.4, a.getLF(), 0.1);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ",  a.toString());
		
		HashTable<Integer> b = new HashTable<Integer>(a);
		assertEquals(0.4, b.getLF(), 0.1);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ",  b.toString());
		b.add(24);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ",  a.toString());
		assertEquals(0.27, b.getLF(), 0.01);
		
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ",  a.join(b).toString());
	}
	
	@Test
	public void testJoin() {
		HashTable<Integer> a = new HashTable<>(5, HashTable.LINEAR_PROBING, 0.5);
		a.add(4);
		a.add(13);
		
		HashTable<Integer> b = new HashTable<>(5, HashTable.LINEAR_PROBING, 0.5);
		b.add(24);
		b.add(3);
		
		assertEquals(0.36, a.join(b).getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (1) = 13 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 24 - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ", a.join(b).toString());
	}

/*	@Test
	public void testAll() {
		HashTable<Integer> a = new HashTable<>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals(2,  a.f(7,0));
		assertEquals(3,  a.f(7,1));
		assertEquals(4,  a.f(7,2));
		assertEquals(0,  a.f(7,3));

		HashTable<Integer> b = new HashTable<>(5, HashTable.QUADRATIC_PROBING, 0.5);
		assertEquals(2,  b.f(7,0));
		assertEquals(3,  b.f(7,1));
		assertEquals(1,  b.f(7,2));
		assertEquals(1,  b.f(7,3));
		
		HashTable<Integer> c = new HashTable<>(5, HashTable.DOUBLE_HASHING, 0.5);
		assertEquals(2,  c.f(7,0));
		assertEquals(4,  c.f(7,1));
		assertEquals(1,  c.f(7,2));
		assertEquals(3,  c.f(7,3));
		assertEquals(0,  c.f(7,4));

		assertEquals(0,  c.f(0,0));
		assertEquals(1,  c.f(2,4));
		assertEquals(2,  c.f(3,3));
		assertEquals(3,  c.f(32,1));
		assertEquals(4,  c.f(1045, 2));
	}

	@Test
	public void testAllChars() {
		HashTable<Character> a = new HashTable<>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals(0,  a.f('A',0));
		assertEquals(1,  a.f('A',1));
		assertEquals(2,  a.f('A',2));
		assertEquals(3,  a.f('A',3));

		HashTable<Character> b = new HashTable<>(5, HashTable.QUADRATIC_PROBING, 0.5);
		assertEquals(0,  b.f('A',0));
		assertEquals(1,  b.f('A',1));
		assertEquals(4,  b.f('A',2));
		assertEquals(4,  b.f('A',3));
		
		assertEquals(0, b.getLF(), 0);
		
		HashTable<Character> c = new HashTable<>(5, HashTable.DOUBLE_HASHING, 0.5);
		assertEquals(0,  c.f('A',0));
		assertEquals(1,  c.f('A',1));
		assertEquals(2,  c.f('A',2));
		assertEquals(3,  c.f('A',3));
		assertEquals(4,  c.f('A',4));

		assertEquals(2,  c.f('a',0));
		assertEquals(4,  c.f('a',1));
		assertEquals(1,  c.f('a',2));
		assertEquals(3,  c.f('a',3));
		assertEquals(0,  c.f('a',4));
	}

	@Test
	public void testToString() {
		HashTable<Character> a = new HashTable<>(5, HashTable.LINEAR_PROBING, 0.5);
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);

		assertEquals("[0] (1) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ",  a.toString());
		assertTrue(a.search(3));
		assertFalse(a.search(12));
	}
	
	@Test
	public void testAdd() {
		HashTable<Integer> a = new HashTable<>(5, HashTable.DOUBLE_HASHING, 0.5);
		a.add(4);
		assertEquals(0.2,  a.getLF(), 0.1);
		a.add(13);
		assertEquals(0.4,  a.getLF(), 0.1);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		
		a.add(24); //dynamic resizing
		
		assertEquals(0.27,  a.getLF(), 0.1);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ", a.toString());
		
		a.add(3);
		
		assertEquals("[0] (1) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		assertEquals(true, a.search(3));
		assertEquals(false, a.search(12));

		HashTable<Integer> b = new HashTable<>(5, HashTable.QUADRATIC_PROBING, 1.0);
		b.add(4);
		b.add(13);
		b.add(24);
		b.add(3);
		
		assertEquals("[0] (1) = 24 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
		assertEquals(true, b.search(3));
		assertEquals(false, b.search(12));
		
		HashTable<Integer> c = new HashTable<>(5, HashTable.DOUBLE_HASHING, 1.0);
		c.add(4);
		c.add(13);
		c.add(24);
		c.add(3);
		assertEquals("[0] (0) = null - [1] (1) = 3 - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
		
		c.remove(24);
		assertEquals("[0] (0) = null - [1] (1) = 3 - [2] (2) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
		
		assertEquals(true, c.search(3));
		c.add(15);
		assertEquals("[0] (1) = 15 - [1] (1) = 3 - [2] (2) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
		
		assertEquals(true, c.search(3));
		c.remove(13);
		assertEquals(true, c.search(3));
	}
	
	@Test
	public void testRemove() {
		HashTable<Integer> a = new HashTable<>(5, HashTable.LINEAR_PROBING, 1.0);
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);
		a.remove(24);
		assertEquals(true,  a.search(3));
		assertEquals("[0] (2) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		
		a.add(15);
		assertEquals(true,  a.search(3));
		assertEquals("[0] (1) = 15 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		
		
		HashTable<Integer> b = new HashTable<>(5, HashTable.QUADRATIC_PROBING, 1.0);
		b.add(4);
		b.add(13);
		b.add(24);
		b.add(3);
		
		b.remove(24);
		assertEquals(true,  b.search(3));
		assertEquals("[0] (2) = 24 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
		
		b.add(15);
		assertEquals(true, b.search(3));
		assertEquals("[0] (1) = 15 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
	}
	
	 @Test
	    public void testPrimeNumber() throws Exception
	    {
	        // Example
	        HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);
	       
	        assertEquals(2, a.getNextPrimeNumber(1));
	        assertEquals(3, a.getNextPrimeNumber(2));
	        assertEquals(5, a.getNextPrimeNumber(3));
	        assertEquals(5, a.getNextPrimeNumber(4));
	        assertEquals(7, a.getNextPrimeNumber(5));
	        assertEquals(7, a.getNextPrimeNumber(6));
	        assertEquals(11, a.getNextPrimeNumber(7));
	        assertEquals(11, a.getNextPrimeNumber(8));
	        assertEquals(11, a.getNextPrimeNumber(9));
	        assertEquals(11, a.getNextPrimeNumber(10));
	        assertEquals(13, a.getNextPrimeNumber(11));
	         
	        assertEquals(13, a.getPrevPrimeNumber(15));
	        assertEquals(13, a.getPrevPrimeNumber(14));
	        assertEquals(11, a.getPrevPrimeNumber(13));
	        assertEquals(11, a.getPrevPrimeNumber(12));
	        assertEquals(7, a.getPrevPrimeNumber(11));
	        assertEquals(7, a.getPrevPrimeNumber(10));
	        assertEquals(7, a.getPrevPrimeNumber(9));
	        assertEquals(7, a.getPrevPrimeNumber(8));
	        assertEquals(5, a.getPrevPrimeNumber(7));
	        assertEquals(5, a.getPrevPrimeNumber(6));
	        assertEquals(3, a.getPrevPrimeNumber(5));
	        assertEquals(3, a.getPrevPrimeNumber(4));
	        assertEquals(2, a.getPrevPrimeNumber(3));
	    }*/
	
}
