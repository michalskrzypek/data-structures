package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Graph;

public class L6_Dijkstra_sampleTest {

	@Test
	public void Test() {
		Graph<String> g = new Graph<String>(6);

		System.out.println("TEST DIJKSTRA BEGINS ***");
		assertEquals(0, g.getSize());

		try {
			g.addNode("V1");
			g.addNode("V2");
			g.addNode("V3");
			g.addNode("V4");
			g.addNode("V5");
			g.addNode("V6");
		} catch (Exception e) {
			System.out.println("No repeated nodes are allowed" + e);
		}
		assertEquals(6, g.getSize());
		assertEquals(0, g.getNode("V1"));
		assertEquals(1, g.getNode("V2"));
		assertEquals(2, g.getNode("V3"));
		assertEquals(3, g.getNode("V4"));
		assertEquals(4, g.getNode("V5"));
		assertEquals(5, g.getNode("V6"));
		assertArrayEquals(new boolean[][] { { false, false, false, false, false, false },
				{ false, false, false, false, false, false }, { false, false, false, false, false, false },
				{ false, false, false, false, false, false }, { false, false, false, false, false, false },
				{ false, false, false, false, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, g.getWeight());
		g.print();

		try {
			g.addEdge("V1", "V2", 3.0);
			g.addEdge("V1", "V3", 4.0);
			g.addEdge("V1", "V5", 8.0);

			g.addEdge("V2", "V5", 5.0);

			g.addEdge("V3", "V5", 3.0);

			g.addEdge("V5", "V6", 3.0);
			g.addEdge("V5", "V4", 7.0);

			g.addEdge("V6", "V4", 2.0);
		} catch (Exception e) {
			System.out.println("Starting or arrival node does not exists" + e);
		}

		assertArrayEquals(new boolean[][] { { false, true, true, false, true, false },
				{ false, false, false, false, true, false }, { false, false, false, false, true, false },
				{ false, false, false, false, false, false }, { false, false, false, true, false, true },
				{ false, false, false, true, false, false } }, g.getEdges());

		assertArrayEquals(new double[][] { { 0.0, 3.0, 4.0, 0.0, 8.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 5.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 3.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 7.0, 0.0, 3.0 },
				{ 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 } }, g.getWeight());
		
		g.initDijkstra("V1");
		assertArrayEquals(new double[][] { { Graph.INFINITE, 3.0, 4.0, Graph.INFINITE, 8.0, Graph.INFINITE } }, g.getD());
		assertArrayEquals(new int[] { -1, 0, 0, -1, 0, -1 }, g.getPD());

		g.initDijkstra("V2");
		assertArrayEquals(new double[][] { { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 5.0, Graph.INFINITE } }, g.getD());
		assertArrayEquals(new int[] { -1, -1, -1, -1, 1, -1 }, g.getPD());
		
		g.Dijkstra("V1");
		assertArrayEquals(new double[][] { { Graph.INFINITE, 3.0, 4.0, 12.0, 7.0, 10.0 } }, g.getD());
		assertArrayEquals(new int[] { -1, 0, 0, 5, 2, 4 }, g.getPD());

		g.Dijkstra("V2");
		assertArrayEquals(new double[][] { { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 10.0, 5.0, 8.0 } },
				g.getD());
		assertArrayEquals(new int[] { -1, -1, -1, 5, 1, 4 }, g.getPD());

		try {
			g.removeNode("V1");
		} catch (Exception e) {
			System.out.println("Starting or arrival node does not exists" + e);
		}

		assertArrayEquals(new boolean[][] { { false, false, false, true, false, false },
				{ false, false, false, false, true, false }, { false, false, false, false, true, false },
				{ false, false, false, false, false, false }, { true, false, false, true, false, true },
				{ false, false, false, true, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 5.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 3.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 3.0, 0.0, 0.0, 7.0, 0.0, 3.0 },
				{ 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 } }, g.getWeight());

		//TILL NOW EVERYTHING WORKS PERFECT, BUT WHY WE STILL HAVE ANY VALUES AT THE LAST INDEX OF d AND pd arrays?
		
		g.Dijkstra("V2");
		assertArrayEquals(new double[][] { { 8, Graph.INFINITE, Graph.INFINITE, 10.0, 5.0, 8.0 } }, g.getD());
		assertArrayEquals(new int[] { 4, -1, -1, 0, 1, 4 }, g.getPD());

		g.Dijkstra("V3");
		assertArrayEquals(new double[][] { { 6, Graph.INFINITE, Graph.INFINITE, 8.0, 3.0, 8.0 } }, g.getD());
		assertArrayEquals(new int[] { 4, -1, -1, 0, 2, 4 }, g.getPD());
	}
}