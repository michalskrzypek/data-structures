package test;
import static org.junit.Assert.*;

import org.junit.Test;

import main.HashTable;

public class L12_HashTable_EvalTest
{
	@Test
	public void testAddLinear()
	{
		HashTable<Integer> a = new HashTable<Integer>(3, HashTable.LINEAR_PROBING, 0.5);

		assertEquals(0.00, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - ", a.toString());

		a.add(4);
		assertEquals(0.33, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 4 - [2] (0) = null - ", a.toString());

		a.add(14);
		assertEquals(0.29, a.getLF(), 0.01);
		assertEquals("[0] (1) = 14 - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - ", a.toString());

		a.add(1);
		assertEquals(0.43, a.getLF(), 0.01);
		assertEquals("[0] (1) = 14 - [1] (1) = 1 - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - ", a.toString());

		a.add(27);
		assertEquals(0.24, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - ", a.toString());

		a.add(3);
		assertEquals(0.29, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - ", a.toString());

		a.add(21);
		assertEquals(0.35, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 21 - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - ", a.toString());

		a.add(48);
		assertEquals(0.41, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 21 - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (1) = 48 - [16] (0) = null - ", a.toString());

		a.add(55);
		assertEquals(0.47, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 21 - [6] (1) = 55 - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (1) = 48 - [16] (0) = null - ", a.toString());

		a.add(2);
		assertEquals(0.24, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());

		a.add(37);
		assertEquals(0.27, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());

		a.add(86);
		assertEquals(0.30, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());

		a.add(5);
		assertEquals(0.32, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 5 - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());

		a.add(7);
		assertEquals(0.35, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 5 - [6] (0) = null - [7] (1) = 7 - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());

		a.add(44);
		assertEquals(0.38, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 5 - [6] (0) = null - [7] (1) = 7 - [8] (1) = 44 - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());

		a.add(81);
		assertEquals(0.41, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 5 - [6] (0) = null - [7] (1) = 7 - [8] (1) = 44 - [9] (1) = 81 - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());

		a.add(118);
		assertEquals(0.43, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 5 - [6] (0) = null - [7] (1) = 7 - [8] (1) = 44 - [9] (1) = 81 - [10] (1) = 118 - [11] (1) = 48 - [12] (1) = 86 - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());
	}

	@Test
	public void testAddQuadratic()
	{
		HashTable<Integer> a = new HashTable<Integer>(3, HashTable.QUADRATIC_PROBING, 0.5);

		assertEquals(0.00, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - ", a.toString());

		a.add(4);
		assertEquals(0.33, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 4 - [2] (0) = null - ", a.toString());

		a.add(14);
		assertEquals(0.29, a.getLF(), 0.01);
		assertEquals("[0] (1) = 14 - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - ", a.toString());
		
		a.add(1);
		assertEquals(0.43, a.getLF(), 0.01);
		assertEquals("[0] (1) = 14 - [1] (1) = 1 - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - ", a.toString());
		
		a.add(27);
		assertEquals(0.24, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - ", a.toString());
		
		a.add(3);
		assertEquals(0.29, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - ", a.toString());
		
		a.add(21);
		assertEquals(0.35, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 21 - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - ", a.toString());
		
		a.add(48);
		assertEquals(0.41, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 21 - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (1) = 48 - [16] (0) = null - ", a.toString());
		
		a.add(55);
		assertEquals(0.47, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 21 - [6] (0) = null - [7] (0) = null - [8] (1) = 55 - [9] (0) = null - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (1) = 48 - [16] (0) = null - ", a.toString());
		
		a.add(2);
		assertEquals(0.24, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());
		
		a.add(37);
		assertEquals(0.27, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());
		
		a.add(86);
		assertEquals(0.30, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());
		
		a.add(6);
		assertEquals(0.32, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (1) = 6 - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());
		
		a.add(12);
		assertEquals(0.35, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (1) = 6 - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (1) = 12 - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());

		a.add(44);
		assertEquals(0.38, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (1) = 6 - [7] (1) = 44 - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (1) = 12 - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());

		a.add(81);
		assertEquals(0.41, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (1) = 6 - [7] (1) = 44 - [8] (1) = 81 - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (1) = 12 - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());

		a.add(118);
		assertEquals(0.43, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (1) = 6 - [7] (1) = 44 - [8] (1) = 81 - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (1) = 12 - [14] (1) = 14 - [15] (0) = null - [16] (1) = 118 - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());
	}

	@Test
	public void testAddDoubleHashing()
	{
		HashTable<Integer> a = new HashTable<Integer>(3, HashTable.DOUBLE_HASHING, 0.5);

		assertEquals(0.00, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - ", a.toString());

		a.add(4);
		assertEquals(0.33, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 4 - [2] (0) = null - ", a.toString());

		a.add(14);
		assertEquals(0.29, a.getLF(), 0.01);
		assertEquals("[0] (1) = 14 - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - ", a.toString());
		
		a.add(1);
		assertEquals(0.43, a.getLF(), 0.01);
		assertEquals("[0] (1) = 14 - [1] (1) = 1 - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - ", a.toString());
		
		a.add(27);
		assertEquals(0.24, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - ", a.toString());
		
		a.add(3);
		assertEquals(0.29, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - ", a.toString());
		
		a.add(21);
		assertEquals(0.35, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (1) = 21 - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - ", a.toString());
		
		a.add(48);
		assertEquals(0.41, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 48 - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (1) = 21 - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - ", a.toString());
		
		a.add(55);
		assertEquals(0.47, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (1) = 3 - [4] (1) = 4 - [5] (1) = 48 - [6] (0) = null - [7] (1) = 55 - [8] (0) = null - [9] (1) = 21 - [10] (1) = 27 - [11] (0) = null - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - ", a.toString());
		
		a.add(2);
		assertEquals(0.24, a.getLF(), 0.01);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());
		
		a.add(37);
		assertEquals(0.27, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (0) = null - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());
		
		a.add(86);
		assertEquals(0.30, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());
		
		a.add(6);
		assertEquals(0.32, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (1) = 6 - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (0) = null - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());
		
		a.add(12);
		assertEquals(0.35, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (1) = 6 - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (1) = 12 - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());

		a.add(44);
		assertEquals(0.38, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (1) = 6 - [7] (1) = 44 - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (0) = null - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (1) = 12 - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());

		a.add(81);
		assertEquals(0.41, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (1) = 6 - [7] (1) = 44 - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (0) = null - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (1) = 81 - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (1) = 12 - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());

		a.add(118);
		assertEquals(0.43, a.getLF(), 0.01);
		assertEquals("[0] (1) = 37 - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (1) = 4 - [5] (0) = null - [6] (1) = 6 - [7] (1) = 44 - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 48 - [12] (1) = 86 - [13] (1) = 118 - [14] (1) = 14 - [15] (0) = null - [16] (0) = null - [17] (0) = null - [18] (1) = 55 - [19] (1) = 81 - [20] (0) = null - [21] (1) = 21 - [22] (0) = null - [23] (0) = null - [24] (0) = null - [25] (0) = null - [26] (0) = null - [27] (1) = 27 - [28] (0) = null - [29] (0) = null - [30] (0) = null - [31] (1) = 12 - [32] (0) = null - [33] (0) = null - [34] (0) = null - [35] (0) = null - [36] (0) = null - ", a.toString());
	}
}