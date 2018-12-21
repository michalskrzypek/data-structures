package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.AVLTree;
import main.HashTable;

/**
 * Tests avl tree methods
 * @author mskrz
 *
 */
public class AVLTreeTest {

	/**
	 * Custom personal test. Testing to string method for various options.
	 */
	@Test
	public void test() {
/*		//this is new branch
		AVLTree<Character> a = new AVLTree<Character>();
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
		
		b.remove(15);
		assertEquals("643--5--1298--11---", b.toString());
		
		b.remove(12);
		assertEquals("643--5--98--11--", b.toString());
		

		System.out.println("-------------------TREE PRE-ORDER TEST-----------------");
		
		AVLTree<Character> c = new AVLTree<Character>();
		c.add('b');
		c.add('a');
		c.add('d');
		c.add('c');
		c.add('g');
		c.add('i');
		c.add('h');
		System.out.println("-------------------PASSED-----------------");

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
		assertEquals("b(3)a(0)--d(2)c(0)--g(1)-i(0)--", d.joins(e).toString());
		System.out.println("-------------------PASSED-----------------");
		
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
		g.add('a');
		assertEquals("a(1)-d(0)--", f.intersection(g).toString());
		System.out.println("-------------------PASSED-----------------");
		*/
/*		System.out.println("-------------------TREE BF TEST -----------------");
		AVLTree<Character> h = new AVLTree<Character>();
		h.add('b');
		h.add('a');
		h.add('d');
		h.add('c');
		h.add('g');
		h.add('i');
		h.add('h');
		assertEquals("b(3)a(0)--d(2)c(0)--g(2)-i(-1)h(0)---", h.toString());*/

/*		AVLTree<Integer> b = new AVLTree<Integer>();
		b.add(14);
		b.add(26);
		b.add(59);
		b.add(48);
		b.add(63);
		b.add(55);
		b.add(89);
		b.add(11);
		b.add(72);
		b.add(19);
		System.out.println(b.toString());
*/
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		HashTable<Integer> a = new HashTable<>(5, HashTable.LINEAR_PROBING, 0.5);
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);
		
		b.addHashTable(a);
		assertEquals("13(-1)4(-1)3(0)---24(0)--", b.toString());
		
		b.add(48);
		b.add(26);
		b.add(84);
		b.add(14);
		b.add(39);
		b.add(56);
		b.add(87);
		b.add(12);
		b.add(31);
		b.add(46);
		b.add(53);
		b.add(74);
		b.add(95);
		b.add(33);
		b.add(65);
		b.add(81);
		System.out.println(b.toString());
		
		b.remove(48);
		System.out.println(b.toString());
	
	}
	
	
}
