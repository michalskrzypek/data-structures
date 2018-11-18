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
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('a');
		a.add('b');
		assertEquals("a(1)-b(0)--", a.toString());
		a.add('c');
		assertEquals("b(0)a(0)--c(0)--", a.toString());
		a.add('d');
		a.add('e');
		assertEquals("b(1)a(0)--d(0)c(0)--e(0)--", a.toString());

		a.add('f');
		assertEquals("d(0)b(0)a(0)--c(0)--e(1)-f(0)--", a.toString());
		
		
		System.out.println("----------------- DOUBLE ROTATION -----------------");
		AVLTree<Character> b = new AVLTree<Character>();
		b.add('e');
		b.add('g');
		b.add('b');
		b.add('d');
		b.add('c');
		assertEquals("e(-1)c(0)b(0)--d(0)--g(0)--", b.toString());
		
		System.out.println("----------------- FULL TEST -----------------");
		AVLTree<Character> c = new AVLTree<Character>();
		c.add('a');
		c.add('b');
		c.add('d');
		assertEquals("b(0)a(0)--d(0)--", c.toString());
		
		AVLTree<Character> e = new AVLTree<Character>();
		e.add('c');
		e.add('g');
		e.add('i');
		e.add('d');
		assertEquals("g(-1)c(1)-d(0)--i(0)--", e.toString());

		assertEquals("d(0)b(0)a(0)--c(0)--g(1)-i(0)--", c.joins(e).toString());
		
	}
}
