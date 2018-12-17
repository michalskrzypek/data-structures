package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.HashTable;

public class HashTablesTest {

	@Test
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
	}

/*	@Test
	public void testToString() {
		HashTable<Character> a = new HashTable<>(5, HashTable.LINEAR_PROBING, 0.5);
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);

		assertEquals("[0] (1) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ",  a.toString());
		assertTrue(a.search(3));
		assertFalse(a.search(12));
	}*/
	
	@Test
	public void testAdd() {
		HashTable<Integer> a = new HashTable<>(5, HashTable.LINEAR_PROBING, 1.0);
		a.add(4);
		a.add(13);
		a.add(24);
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
	    }
	
}
