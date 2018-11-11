package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.AVLTree;

public class AVLTreeTest {

	@Test
	public void test() {
/*		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		assertEquals("b--", a.toString());
		assertEquals("-b-", a.inOrderTraversal());
		assertEquals("--b", a.postOrderTraversal());
		
		a.add('a');
		assertEquals("ba---", a.toString());
		assertEquals("-a-b-", a.inOrderTraversal());
		assertEquals("--a-b", a.postOrderTraversal());
		
		a.add('d');
		assertEquals("ba--d--", a.toString());
		assertEquals("-a-b-d-", a.inOrderTraversal());
		assertEquals("--a--db", a.postOrderTraversal());
		
		a.add('c');
		assertEquals("ba--dc---", a.toString());
		assertEquals("-a-b-c-d-", a.inOrderTraversal());
		assertEquals("--a--c-db", a.postOrderTraversal());
		
		a.add('g');
		assertEquals("ba--dc--g--", a.toString());
		assertEquals("-a-b-c-d-g-", a.inOrderTraversal());
		assertEquals("--a--c--gdb", a.postOrderTraversal());
		
		a.addIterative('i');
		assertEquals("ba--dc--g-i--", a.toString());
		assertEquals("-a-b-c-d-g-i-", a.inOrderTraversal());
		assertEquals("--a--c---igdb", a.postOrderTraversal());
		
		a.addIterative('h');
		assertEquals("ba--dc--g-ih---", a.toString());
		assertEquals("-a-b-c-d-g-h-i-", a.inOrderTraversal());
		assertEquals("--a--c---h-igdb", a.postOrderTraversal());
		
		assertTrue(a.search('i'));
		assertFalse(a.search('j'));
		
		assertTrue(a.searchIterative('i'));
		assertTrue(a.searchIterative('a'));
		assertTrue(a.searchIterative('b'));
		assertTrue(a.searchIterative('c'));
		assertTrue(a.searchIterative('d'));
		assertFalse(a.searchIterative('x'));
		assertFalse(a.searchIterative('q'));

		assertEquals('i', (char) a.getMax(a.getRoot()));
		
		
		System.out.println("-------------------TREE TEST 2-----------------");
		
		AVLTree<Integer> b = new AVLTree<Integer>();
		b.add(6);
		b.add(4);
		b.add(3);
		b.add(5);
		b.add(12);
		b.add(9);
		b.add(15);
		b.add(8);
		b.add(11);
		assertEquals("643--5--1298--11--15--", b.toString());
		
		b.remove(b.getRoot(), 15);
		assertEquals("643--5--1298--11---", b.toString());
		
		b.remove(b.getRoot(), 12);
		assertEquals("643--5--98--11--", b.toString());
		*/

		System.out.println("-------------------TREE PRE-ORDER TEST-----------------");
		
		AVLTree<Character> c = new AVLTree<Character>();
		c.add('b');
		c.add('a');
		c.add('d');
		c.add('c');
		c.add('g');
		c.add('i');
		c.add('h');

		assertEquals("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---", c.toString());
		
		System.out.println("-------------------TREE JOIN TEST -----------------");	
		AVLTree<Character> d = new AVLTree<Character>();
		d.add('b');
		d.add('a');
		d.add('d');
		AVLTree<Character> e = new AVLTree<Character>();
		e.add('c');
		e.add('g');
		e.add('i');
		e.add('d');
		assertEquals("b(3)a(0)--d(2)c(0)--g(1)-i(0)--", d.join(e).toString());
		
		System.out.println("-------------------TREE INTERSECTION TEST -----------------");	
		AVLTree<Character> f = new AVLTree<Character>();
		f.add('b');
		f.add('a');
		f.add('d');
		AVLTree<Character> g = new AVLTree<Character>();
		g.add('c');
		g.add('g');
		g.add('i');
		g.add('d');
		assertEquals("d(0)--", f.intersection(g).toString());
	}
	
}
