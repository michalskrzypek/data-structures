package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.AVLTree;

public class L9_Test_BF {

	@Test
	public void test() {
		/*
		 * AVLTree<Character> a = new AVLTree<Character>(); a.add('b'); a.add('a');
		 * a.add('d'); a.add('c'); a.add('g'); a.add('i'); a.add('h');
		 * assertEquals("b(3)a(0)--d(2)c(0)--g(2)-i(-1)h(0)---", a.toString());
		 */

		System.out.println("----------------- SINGLE ROTATION -----------------");
		/*
		 * AVLTree<Character> a = new AVLTree<Character>(); a.add('a'); a.add('b');
		 * assertEquals("a(1)-b(0)--", a.toString()); a.add('c');
		 * assertEquals("b(0)a(0)--c(0)--", a.toString()); a.add('d'); a.add('e');
		 * assertEquals("b(1)a(0)--d(0)c(0)--e(0)--", a.toString());
		 * 
		 * a.add('f'); assertEquals("d(0)b(0)a(0)--c(0)--e(1)-f(0)--", a.toString());
		 */

		AVLTree<Integer> z = new AVLTree<Integer>();
		z.add(10);
		z.add(6);
		z.add(15);
		z.add(4);
		z.add(13);
		z.add(18);
		z.add(17);
		z.add(20);
		z.add(16);
		assertEquals("10(1)6(-1)4(0)---17(0)15(0)13(0)--16(0)--18(1)-20(0)--", z.toString());

		System.out.println("----------------- DOUBLE ROTATION -----------------");
		AVLTree<Character> b = new AVLTree<Character>();
		b.add('e');
		b.add('g');
		b.add('b');
		b.add('d');
		b.add('c');
		assertEquals("e(-1)c(0)b(0)--d(0)--g(0)--", b.toString());
		assertEquals(3, b.getHeight(), 0);

		System.out.println("----------------- FULL TEST -----------------");
		AVLTree<Character> c = new AVLTree<Character>();
		c.add('a');
		c.add('b');
		c.add('d');
		assertEquals("b(0)a(0)--d(0)--", c.toString());
		assertEquals(2, c.getHeight(), 0);

		AVLTree<Character> e = new AVLTree<Character>();
		e.add('c');
		e.add('g');
		e.add('i');
		e.add('d');
		assertEquals("g(-1)c(1)-d(0)--i(0)--", e.toString());
		assertEquals(3, e.getHeight(), 0);

		assertEquals("d(0)b(0)a(0)--c(0)--g(1)-i(0)--", c.joins(e).toString());

	}
}
