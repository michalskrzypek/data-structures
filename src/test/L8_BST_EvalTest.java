package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.AVLTree;

public class L8_BST_EvalTest
{
	@Test
	public void testAdd()
	{
		AVLTree<Character> tree = new AVLTree<Character>();

		tree.add('d');
		tree.add('f');
		tree.add('b');

		assertEquals("d(1)b(0)--f(0)--", tree.toString());

		tree.add('h');
		tree.add('g');
		tree.add('a');

		assertEquals("d(3)b(1)a(0)---f(2)-h(1)g(0)---", tree.toString());

		try
		{
			tree.add('d');
			fail("An exception should have been raised because 'd' already exists.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testSearch()
	{
		AVLTree<Character> tree = new AVLTree<Character>();

		tree.add('b');
		tree.add('a');
		tree.add('d');
		tree.add('c');
		tree.add('g');
		tree.add('i');
		tree.add('h');

		assertEquals("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---", tree.toString());

		assertTrue(tree.search('a'));
		assertTrue(tree.search('d'));
		assertTrue(tree.search('i'));
		assertTrue(tree.search('b'));
		assertFalse(tree.search('e'));
	}

	@Test
	public void testRemove()
	{
		AVLTree<Character> tree = new AVLTree<Character>();

		tree.add('b');
		tree.add('a');
		tree.add('d');
		tree.add('c');
		tree.add('g');
		tree.add('i');
		tree.add('h');

		assertEquals("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---", tree.toString());

		// Scenario III
		tree.remove('d');

		assertEquals("b(4)a(0)--c(3)-g(2)-i(1)h(0)---", tree.toString());

		// Scenario II
		tree.remove('g');

		assertEquals("b(3)a(0)--c(2)-i(1)h(0)---", tree.toString());

		// Scenario III
		tree.remove('b');

		assertEquals("a(3)-c(2)-i(1)h(0)---", tree.toString());

		// Scenario II
		tree.remove('i');

		assertEquals("a(2)-c(1)-h(0)--", tree.toString());

		// Scenario I
		tree.remove('h');

		assertEquals("a(1)-c(0)--", tree.toString());

		// Scenario I
		tree.remove('c');

		assertEquals("a(0)--", tree.toString());

		// Scenario I
		tree.remove('a');

		assertEquals("-", tree.toString());
	}

	@Test
	public void testBasicOperations()
	{
		AVLTree<Character> tree = new AVLTree<Character>();

		tree.add('b');
		tree.add('a');
		tree.add('d');
		tree.add('c');
		tree.add('g');
		tree.add('i');
		tree.add('h');

		assertEquals("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---", tree.toString());

		// Scenario III
		tree.remove('d');

		assertEquals("b(4)a(0)--c(3)-g(2)-i(1)h(0)---", tree.toString());

		// Scenario II
		tree.remove('g');

		assertEquals("b(3)a(0)--c(2)-i(1)h(0)---", tree.toString());

		assertTrue(tree.search('a'));
		assertTrue(tree.search('i'));
		assertTrue(tree.search('c'));
		assertTrue(tree.search('b'));
		assertFalse(tree.search('d'));
		assertFalse(tree.search('g'));
		assertFalse(tree.search('e'));

		tree.add('d');
		tree.add('g');

		assertEquals("b(5)a(0)--c(4)-i(3)h(2)d(1)-g(0)----", tree.toString());

		assertTrue(tree.search('a'));
		assertTrue(tree.search('i'));
		assertTrue(tree.search('c'));
		assertTrue(tree.search('b'));
		assertTrue(tree.search('d'));
		assertTrue(tree.search('g'));
		assertFalse(tree.search('e'));

		// Scenario III
		tree.remove('b');

		assertEquals("a(5)-c(4)-i(3)h(2)d(1)-g(0)----", tree.toString());

		assertTrue(tree.search('a'));
		assertTrue(tree.search('i'));
		assertTrue(tree.search('c'));
		assertFalse(tree.search('b'));
		assertTrue(tree.search('d'));
		assertTrue(tree.search('g'));
		assertFalse(tree.search('e'));
	}

	@Test
	public void testJoinsSample()
	{
		AVLTree<Character> a = new AVLTree<Character>();

		a.add('b');
		a.add('a');
		a.add('d');

		assertEquals("b(1)a(0)--d(0)--", a.toString());

		AVLTree<Character> b = new AVLTree<Character>();

		b.add('c');
		b.add('g');
		b.add('i');
		b.add('d');

		assertEquals("c(2)-g(1)d(0)--i(0)--", b.toString());

		AVLTree<Character> result = a.joins(b);

		assertEquals("b(3)a(0)--d(2)c(0)--g(1)-i(0)--", result.toString());
	}

	@Test
	public void testJoins()
	{
		AVLTree<Integer> a = new AVLTree<Integer>();

		a.add(5);
		a.add(3);
		a.add(1);
		a.add(2);
		a.add(7);

		assertEquals("5(3)3(2)1(1)-2(0)---7(0)--", a.toString());

		AVLTree<Integer> b = new AVLTree<Integer>();

		b.add(7);
		b.add(6);
		b.add(8);

		assertEquals("7(1)6(0)--8(0)--", b.toString());

		AVLTree<Integer> resultAA = a.joins(a);

		assertEquals("5(3)3(2)1(1)-2(0)---7(0)--", resultAA.toString());

		AVLTree<Integer> resultBB = b.joins(b);

		assertEquals("7(1)6(0)--8(0)--", resultBB.toString());

		AVLTree<Integer> resultAB = a.joins(b);

		assertEquals("5(3)3(2)1(1)-2(0)---7(1)6(0)--8(0)--", resultAB.toString());

		AVLTree<Integer> resultBA = b.joins(a);

		assertEquals("7(5)6(4)5(3)3(2)1(1)-2(0)-----8(0)--", resultBA.toString());

		AVLTree<Integer> resultABBA = resultAB.joins(resultBA);

		assertEquals("5(3)3(2)1(1)-2(0)---7(1)6(0)--8(0)--", resultABBA.toString());

		assertEquals("5(3)3(2)1(1)-2(0)---7(0)--", a.toString());
		assertEquals("7(1)6(0)--8(0)--", b.toString());
	}

	@Test
	public void testIntersectionSample()
	{
		AVLTree<Character> a = new AVLTree<Character>();

		a.add('b');
		a.add('a');
		a.add('d');

		assertEquals("b(1)a(0)--d(0)--", a.toString());

		AVLTree<Character> b = new AVLTree<Character>();

		b.add('c');
		b.add('g');
		b.add('i');
		b.add('d');

		assertEquals("c(2)-g(1)d(0)--i(0)--", b.toString());

		AVLTree<Character> result = a.intersection(b);

		assertEquals("d(0)--", result.toString());
	}

	@Test
	public void testIntersection()
	{
		AVLTree<Integer> a = new AVLTree<Integer>();

		a.add(5);
		a.add(3);
		a.add(1);
		a.add(2);
		a.add(7);
		a.add(6);
		a.add(9);

		assertEquals("5(3)3(2)1(1)-2(0)---7(1)6(0)--9(0)--", a.toString());

		AVLTree<Integer> b = new AVLTree<Integer>();

		b.add(4);
		b.add(1);
		b.add(6);
		b.add(8);
		b.add(7);
		b.add(0);
		b.add(5);

		assertEquals("4(3)1(1)0(0)---6(2)5(0)--8(1)7(0)---", b.toString());

		AVLTree<Integer> resultAA = a.intersection(a);

		assertEquals("5(3)3(2)1(1)-2(0)---7(1)6(0)--9(0)--", resultAA.toString());

		AVLTree<Integer> resultBB = b.intersection(b);

		assertEquals("4(3)1(1)0(0)---6(2)5(0)--8(1)7(0)---", resultBB.toString());

		AVLTree<Integer> resultAB = a.intersection(b);

		assertEquals("5(2)1(0)--7(1)6(0)---", resultAB.toString());

		AVLTree<Integer> resultBA = b.intersection(a);

		assertEquals("1(2)-6(1)5(0)--7(0)--", resultBA.toString());

		AVLTree<Integer> resultABBA = resultAB.intersection(resultBA);

		assertEquals("5(2)1(0)--7(1)6(0)---", resultABBA.toString());

		assertEquals("5(3)3(2)1(1)-2(0)---7(1)6(0)--9(0)--", a.toString());
		assertEquals("4(3)1(1)0(0)---6(2)5(0)--8(1)7(0)---", b.toString());
	}
}