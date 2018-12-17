package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.BinaryHeap;

public class L10_BinaryHeap_EvalTest_2
{
	@Test
	public void testAdd()
	{
		BinaryHeap<Character> heap = new BinaryHeap<Character>();

		heap.add('u');
		heap.add('n');
		heap.add('c');
		heap.add('o');
		heap.add('p');
		heap.add('y');
		heap.add('r');
		heap.add('i');
		heap.add('g');
		heap.add('h');
		heap.add('t');
		heap.add('a');
		heap.add('b');
		heap.add('l');
		heap.add('e');

		assertEquals("[a, g, b, i, h, c, e, u, o, p, t, y, n, r, l]", heap.toString());
	}

	@Test
	public void testGetMin()
	{
		BinaryHeap<Character> heap = new BinaryHeap<Character>();

		heap.add('c');
		heap.add('e');
		heap.add('n');
		heap.add('t');
		heap.add('r');
		heap.add('i');
		heap.add('f');
		heap.add('u');
		heap.add('g');
		heap.add('a');
		heap.add('d');
		heap.add('o');

		assertEquals("[a, c, f, g, d, n, i, u, t, r, e, o]", heap.toString());

		assertEquals('a', heap.getMin().charValue());
		assertEquals("[c, d, f, g, e, n, i, u, t, r, o]", heap.toString());

		assertEquals('c', heap.getMin().charValue());
		assertEquals("[d, e, f, g, o, n, i, u, t, r]", heap.toString());

		assertEquals('d', heap.getMin().charValue());
		assertEquals("[e, g, f, r, o, n, i, u, t]", heap.toString());

		assertEquals('e', heap.getMin().charValue());
		assertEquals("[f, g, i, r, o, n, t, u]", heap.toString());

		assertEquals('f', heap.getMin().charValue());
		assertEquals("[g, o, i, r, u, n, t]", heap.toString());

		assertEquals('g', heap.getMin().charValue());
		assertEquals("[i, o, n, r, u, t]", heap.toString());

		assertEquals('i', heap.getMin().charValue());
		assertEquals("[n, o, t, r, u]", heap.toString());

		assertEquals('n', heap.getMin().charValue());
		assertEquals("[o, r, t, u]", heap.toString());

		assertEquals('o', heap.getMin().charValue());
		assertEquals("[r, u, t]", heap.toString());

		assertEquals('r', heap.getMin().charValue());
		assertEquals("[t, u]", heap.toString());

		assertEquals('t', heap.getMin().charValue());
		assertEquals("[u]", heap.toString());

		assertEquals('u', heap.getMin().charValue());
		assertEquals("[]", heap.toString());

		try
		{
			assertEquals(null, heap.getMin());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@Test
	public void testRemove()
	{
		BinaryHeap<Character> heap = new BinaryHeap<Character>();

		heap.add('c');
		heap.add('e');
		heap.add('n');
		heap.add('t');
		heap.add('r');
		heap.add('i');
		heap.add('f');
		heap.add('u');
		heap.add('g');
		heap.add('a');
		heap.add('d');
		heap.add('o');

		assertEquals("[a, c, f, g, d, n, i, u, t, r, e, o]", heap.toString());

		heap.remove('g');
		assertEquals("[a, c, f, o, d, n, i, u, t, r, e]", heap.toString());

		heap.remove('i');
		assertEquals("[a, c, e, o, d, n, f, u, t, r]", heap.toString());

		heap.remove('o');
		assertEquals("[a, c, e, r, d, n, f, u, t]", heap.toString());

		heap.remove('u');
		assertEquals("[a, c, e, r, d, n, f, t]", heap.toString());

		heap.remove('a');
		assertEquals("[c, d, e, r, t, n, f]", heap.toString());

		heap.remove('e');
		assertEquals("[c, d, f, r, t, n]", heap.toString());

		heap.remove('r');
		assertEquals("[c, d, f, n, t]", heap.toString());

		heap.remove('d');
		assertEquals("[c, n, f, t]", heap.toString());

		heap.remove('f');
		assertEquals("[c, n, t]", heap.toString());

		heap.remove('n');
		assertEquals("[c, t]", heap.toString());

		heap.remove('t');
		assertEquals("[c]", heap.toString());

		heap.remove('c');
		assertEquals("[]", heap.toString());

		try
		{
			heap.remove('x');
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@Test
	public void testConstructor()
	{
		Character[] input = {'u', 'n', 'c', 'o', 'p', 'y', 'r', 'i', 'g', 'h', 't', 'a', 'b', 'l', 'e'};
		BinaryHeap<Character> heap = new BinaryHeap<Character>(input);

		assertEquals("[a, g, b, i, h, c, e, n, o, p, t, y, u, l, r]", heap.toString());
	}
}