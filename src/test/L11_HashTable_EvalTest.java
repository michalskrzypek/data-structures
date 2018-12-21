package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.HashTable;

public class L11_HashTable_EvalTest
{
	@Test
	public void testPrimeNumber() throws Exception
	{
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);

		for (int i = 2; i < 100; i++)
		{
			boolean isPrime = true;

			for (int j = 2; j <= i / 2; j++)
			{
				if ((i % j) == 0)
				{
					isPrime = false;
					break;
				}
			}

			if (isPrime)
			{
				assertTrue(i + " is a prime number!", a.isPrime(i));
			}
			else
			{
				assertFalse(i + " is not a prime number!", a.isPrime(i));
			}
		}

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
		assertEquals(13, a.getNextPrimeNumber(12));
		assertEquals(17, a.getNextPrimeNumber(13));

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

	@Test
	public void testAddLinear()
	{
		HashTable<Integer> a = new HashTable<Integer>(13, HashTable.LINEAR_PROBING, 1.0);

		a.add(4);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(14);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(1);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(27);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(3);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 3 - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(21);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 3 - [6] (0) = null - [7] (0) = null - [8] (1) = 21 - [9] (0) = null - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(48);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 3 - [6] (0) = null - [7] (0) = null - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(55);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 3 - [6] (1) = 55 - [7] (0) = null - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(2);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 3 - [6] (1) = 55 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(37);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 3 - [6] (1) = 55 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (1) = 37 - [12] (0) = null - ", a.toString());

		a.add(86);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 3 - [6] (1) = 55 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (1) = 86 - [11] (1) = 37 - [12] (0) = null - ", a.toString());

		a.add(5);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 3 - [6] (1) = 55 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (1) = 86 - [11] (1) = 37 - [12] (1) = 5 - ", a.toString());

		a.add(7);
		assertEquals("[0] (1) = 7 - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 3 - [6] (1) = 55 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (1) = 86 - [11] (1) = 37 - [12] (1) = 5 - ", a.toString());
	}

	@Test
	public void testAddQuadratic()
	{
		HashTable<Integer> a = new HashTable<Integer>(13, HashTable.QUADRATIC_PROBING, 1.0);

		a.add(4);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(14);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(1);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(27);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (0) = null - [4] (1) = 4 - [5] (1) = 27 - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(3);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(21);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (0) = null - [7] (0) = null - [8] (1) = 21 - [9] (0) = null - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(48);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (0) = null - [7] (0) = null - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(55);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (0) = null - [7] (1) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(2);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (1) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(37);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (1) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (1) = 37 - [12] (0) = null - ", a.toString());

		a.add(86);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (1) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (1) = 37 - [12] (1) = 86 - ", a.toString());

		a.add(6);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (1) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (1) = 6 - [11] (1) = 37 - [12] (1) = 86 - ", a.toString());

		a.add(12);
		assertEquals("[0] (1) = 12 - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (1) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (1) = 6 - [11] (1) = 37 - [12] (1) = 86 - ", a.toString());
	}

	@Test
	public void testRemoveLinear()
	{
		HashTable<Integer> a = new HashTable<Integer>(13, HashTable.LINEAR_PROBING, 1.0);

		a.add(4);
		a.add(14);
		a.add(1);
		a.add(27);
		a.add(3);
		a.add(21);
		a.add(48);
		a.add(55);
		a.add(2);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 3 - [6] (1) = 55 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.remove(1);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (2) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 3 - [6] (1) = 55 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.remove(3);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (2) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (2) = 3 - [6] (1) = 55 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.remove(55);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (2) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (2) = 3 - [6] (2) = 55 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.remove(14);
		assertEquals("[0] (0) = null - [1] (2) = 14 - [2] (2) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (2) = 3 - [6] (2) = 55 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(18);
		assertEquals("[0] (0) = null - [1] (2) = 14 - [2] (2) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 18 - [6] (2) = 55 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(29);
		assertEquals("[0] (0) = null - [1] (2) = 14 - [2] (2) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 18 - [6] (1) = 29 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(47);
		assertEquals("[0] (0) = null - [1] (2) = 14 - [2] (2) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 18 - [6] (1) = 29 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (1) = 47 - [11] (0) = null - [12] (0) = null - ", a.toString());
	}

	@Test
	public void testRemoveQuadratic()
	{
		HashTable<Integer> a = new HashTable<Integer>(13, HashTable.QUADRATIC_PROBING, 1.0);

		a.add(4);
		a.add(14);
		a.add(1);
		a.add(27);
		a.add(3);
		a.add(21);
		a.add(48);
		a.add(55);
		a.add(2);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (1) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.remove(1);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (2) = 1 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (1) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.remove(3);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (2) = 1 - [3] (2) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (1) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.remove(55);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (2) = 1 - [3] (2) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (2) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.remove(14);
		assertEquals("[0] (0) = null - [1] (2) = 14 - [2] (2) = 1 - [3] (2) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (2) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(23);
		assertEquals("[0] (0) = null - [1] (2) = 14 - [2] (2) = 1 - [3] (2) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (2) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (1) = 23 - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(33);
		assertEquals("[0] (0) = null - [1] (2) = 14 - [2] (2) = 1 - [3] (2) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (1) = 33 - [8] (1) = 21 - [9] (1) = 48 - [10] (1) = 23 - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.add(6);
		assertEquals("[0] (0) = null - [1] (2) = 14 - [2] (1) = 6 - [3] (2) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (1) = 33 - [8] (1) = 21 - [9] (1) = 48 - [10] (1) = 23 - [11] (0) = null - [12] (0) = null - ", a.toString());
	}

	@Test
	public void testSearchLinear()
	{
		HashTable<Integer> a = new HashTable<Integer>(13, HashTable.LINEAR_PROBING, 1.0);

		a.add(4);
		a.add(14);
		a.add(1);
		a.add(27);
		a.add(3);
		a.add(21);
		a.add(48);
		a.add(55);
		a.add(2);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (1) = 3 - [6] (1) = 55 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.remove(1);
		a.remove(3);
		a.remove(55);
		a.remove(14);
		assertEquals("[0] (0) = null - [1] (2) = 14 - [2] (2) = 1 - [3] (1) = 27 - [4] (1) = 4 - [5] (2) = 3 - [6] (2) = 55 - [7] (1) = 2 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		assertFalse(a.search(14));
		assertFalse(a.search(1));
		assertTrue(a.search(27));
		assertTrue(a.search(4));
		assertFalse(a.search(3));
		assertFalse(a.search(55));
		assertTrue(a.search(2));
		assertTrue(a.search(21));
		assertTrue(a.search(48));
	}

	@Test
	public void testSearchQuadratic()
	{
		HashTable<Integer> a = new HashTable<Integer>(13, HashTable.QUADRATIC_PROBING, 1.0);

		a.add(4);
		a.add(14);
		a.add(1);
		a.add(27);
		a.add(3);
		a.add(21);
		a.add(48);
		a.add(55);
		a.add(2);
		assertEquals("[0] (0) = null - [1] (1) = 14 - [2] (1) = 1 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (1) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		a.remove(1);
		a.remove(3);
		a.remove(55);
		a.remove(14);
		assertEquals("[0] (0) = null - [1] (2) = 14 - [2] (2) = 1 - [3] (2) = 3 - [4] (1) = 4 - [5] (1) = 27 - [6] (1) = 2 - [7] (2) = 55 - [8] (1) = 21 - [9] (1) = 48 - [10] (0) = null - [11] (0) = null - [12] (0) = null - ", a.toString());

		assertFalse(a.search(14));
		assertFalse(a.search(1));
		assertFalse(a.search(3));
		assertTrue(a.search(4));
		assertTrue(a.search(27));
		assertTrue(a.search(2));
		assertFalse(a.search(55));
		assertTrue(a.search(21));
		assertTrue(a.search(48));
	}
}