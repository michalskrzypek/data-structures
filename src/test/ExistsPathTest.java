package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.Graph;

public class ExistsPathTest {

	@Test
	public void test() {
		Graph<Character> g = new Graph<Character>(4);

		System.out.println("TEST DIJKSTRA BEGINS ***");
		assertEquals(0, g.getSize());

		try {
			g.addNode('A');
			g.addNode('B');
			g.addNode('D');
			g.addNode('E');
		} catch (Exception e) {
			System.out.println("No repeated nodes are allowed" + e);
		}
		
		assertEquals(4, g.getSize());
		
		try {
			g.addEdge('A', 'B', 4.0);
			g.addEdge('A', 'D', 5.0);
			g.addEdge('D', 'A', 1.0);
			g.addEdge('B', 'E', 4.0);
			g.addEdge('E', 'B', 3.0);
			g.addEdge('E', 'D', 1.0);
			
		} catch (Exception e) {
			System.out.println("Starting or arrival node does not exists" + e);
		}
		
		

		List<Character> excludeNodes = new ArrayList<Character>();
		assertTrue(g.existsPath('E', 'A', excludeNodes));
		assertTrue(g.existsPath('D', 'E', excludeNodes));
		excludeNodes.add('B');
		assertTrue(g.existsPath('E', 'A', excludeNodes));
		assertFalse(g.existsPath('D', 'E', excludeNodes));
	}
}
