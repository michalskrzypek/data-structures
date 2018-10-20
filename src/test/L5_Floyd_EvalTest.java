package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Graph;

public class L5_Floyd_EvalTest
{
	private static double[][] getSubmatrix(double[][] matrix, int minRow, int maxRow, int minCol, int maxCol)
	{
		double[][] submatrix = new double[maxRow - minRow + 1][maxCol - minCol + 1];

		for (int i = minRow; i <= maxRow; i++)
			for (int j = minCol; j <= maxCol; j++)
				submatrix[i][j] = matrix[i][j];

		return submatrix;
	}

	private static int[][] getSubmatrix(int[][] matrix, int minRow, int maxRow, int minCol, int maxCol)
	{
		int[][] submatrix = new int[maxRow - minRow + 1][maxCol - minCol + 1];

		for (int i = minRow; i <= maxRow; i++)
			for (int j = minCol; j <= maxCol; j++)
				submatrix[i][j] = matrix[i][j];

		return submatrix;
	}

	private static boolean[][] getSubmatrix(boolean[][] matrix, int minRow, int maxRow, int minCol, int maxCol)
	{
		boolean[][] submatrix = new boolean[maxRow - minRow + 1][maxCol - minCol + 1];

		for (int i = minRow; i <= maxRow; i++)
			for (int j = minCol; j <= maxCol; j++)
				submatrix[i][j] = matrix[i][j];

		return submatrix;
	}

	@Test
	public void Test_Edit()
	{
		Graph<Character> g = new Graph<Character>(3);

		assertEquals(0, g.getSize());

		try
		{
			g.addNode('a');
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: node 'a' should have been added to the graph.");
		}

		assertEquals(1, g.getSize());
		assertEquals(0, g.getNode('a'));
		assertArrayEquals(new boolean[][] {
				{ false, false, false },
				{ false, false, false },
				{ false, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] {
				{ 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0 } }, g.getWeight());

		// Test nodes for nodes not found
		assertEquals(-1, g.getNode('b'));

		// No repeated nodes allowed
		try
		{
			g.addNode('a');
			fail("FAIL: node 'a' is already in the graph, so addNode() should have thrown an exception.");
		}
		catch (Exception e)
		{
			System.out.println("This should be happening: " + e);
		}

		try
		{
			g.addNode('b');
			g.addNode('c');
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: nodes 'b' and 'c' should have been added to the graph.");
		}

		assertEquals(3, g.getSize());
		assertEquals(0, g.getNode('a'));
		assertEquals(1, g.getNode('b'));
		assertEquals(2, g.getNode('c'));

		assertArrayEquals(new boolean[][] {
				{ false, false, false },
				{ false, false, false },
				{ false, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] {
				{ 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0 } }, g.getWeight());

		// Testing edges
		try
		{
			assertEquals(false, g.existsEdge('b', 'd'));
			fail("FAIL: node 'd' does not exist in the graph, and thus existsEdge should have thrown an exception.");
		}
		catch (Exception e)
		{
			System.out.println("This should be happening: " + e);
		}

		try
		{
			assertEquals(false, g.existsEdge('b', 'c'));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: nodes 'b' and 'c' exist, so existsEdge() should not have thrown an exception.");
		}

		assertArrayEquals(new boolean[][] {
				{ false, false, false },
				{ false, false, false },
				{ false, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] {
				{ 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0 } }, g.getWeight());

		try
		{
			g.addEdge('a', 'b', 3.0);
			g.addEdge('b', 'c', 5.0);
			assertEquals(true, g.existsEdge('b', 'c'));
			assertEquals(true, g.existsEdge('a', 'b'));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the edges {a,b} and {b,c} should have been added.");
		}

		assertArrayEquals(new boolean[][] {
				{ false, true, false },
				{ false, false, true },
				{ false, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] {
				{ 0.0, 3.0, 0.0 },
				{ 0.0, 0.0, 5.0 },
				{ 0.0, 0.0, 0.0 } }, g.getWeight());

		try
		{
			g.removeNode('a');
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: node 'a' should have been removed from the graph.");
		}

		assertArrayEquals(new boolean[][] {
				{ false, false },
				{ true, false } }, getSubmatrix(g.getEdges(), 0, 1, 0, 1));
		assertArrayEquals(new double[][] {
				{ 0.0, 0.0 },
				{ 5.0, 0.0 } }, getSubmatrix(g.getWeight(), 0, 1, 0, 1));
	}

	@Test
	public void Test_Floyd_A()
	{
		Graph<String> g = new Graph<String>(6);

		assertEquals(0, g.getSize());

		try
		{
			g.addNode("V1");
			g.addNode("V2");
			g.addNode("V3");
			g.addNode("V4");
			g.addNode("V5");
			g.addNode("V6");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the nodes should have been created.");
		}

		assertEquals(6, g.getSize());
		assertEquals(0, g.getNode("V1"));
		assertEquals(1, g.getNode("V2"));
		assertEquals(2, g.getNode("V3"));
		assertEquals(3, g.getNode("V4"));
		assertEquals(4, g.getNode("V5"));
		assertEquals(5, g.getNode("V6"));
		assertArrayEquals(new boolean[][] {
				{ false, false, false, false, false, false },
				{ false, false, false, false, false, false },
				{ false, false, false, false, false, false },
				{ false, false, false, false, false, false },
				{ false, false, false, false, false, false },
				{ false, false, false, false, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] {
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, g.getWeight());

		try
		{
			g.addEdge("V1", "V2", 3.0);
			g.addEdge("V1", "V3", 4.0);
			g.addEdge("V1", "V5", 8.0);

			g.addEdge("V2", "V5", 5.0);

			g.addEdge("V3", "V5", 3.0);

			g.addEdge("V5", "V6", 3.0);
			g.addEdge("V5", "V4", 7.0);

			g.addEdge("V6", "V4", 2.0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the edges should have been added.");
		}

		assertArrayEquals(new boolean[][] {
				{ false, true, true, false, true, false },
				{ false, false, false, false, true, false },
				{ false, false, false, false, true, false },
				{ false, false, false, false, false, false },
				{ false, false, false, true, false, true },
				{ false, false, false, true, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] {
				{ 0.0, 3.0, 4.0, 0.0, 8.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 5.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 3.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 7.0, 0.0, 3.0 },
				{ 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 } }, g.getWeight());
		try
		{
			assertEquals("V1-V2-V5-V4-V6-V3-", g.traverseGraphDF("V1"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: node 'V1' exists in the graph, so no exception should have been thrown.");
		}

		g.floyd(g.getSize());

		assertArrayEquals(new double[][] {
				{ 0.0, 3.0, 4.0, 12.0, 7.0, 10.0 },
				{ Graph.INFINITE, 0.0, Graph.INFINITE, 10.0, 5.0, 8.0 },
				{ Graph.INFINITE, Graph.INFINITE, 0.0, 8.0, 3.0, 6.0 },
				{ Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 0.0, Graph.INFINITE, Graph.INFINITE },
				{ Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 5.0, 0.0, 3.0 },
				{ Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 2.0, Graph.INFINITE, 0.0 } }, g.getA());
		assertArrayEquals(new int[][] {
				{ Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 5, 2, 4 },
				{ Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 5, Graph.EMPTY, 4 },
				{ Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 5, Graph.EMPTY, 4 },
				{ Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY },
				{ Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 5, Graph.EMPTY, Graph.EMPTY },
				{ Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY } }, g.getP());

		try
		{
			assertEquals("V1V3V5V6", "V1" + g.printFloydPath("V1", "V6") + "V6");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: nodes 'V1' and 'V6' exist, so no exception should have been thrown.");
		}
	}

	@Test
	public void Test_Floyd_B()
	{
		Graph<String> g = new Graph<String>(6);

		assertEquals(0, g.getSize());

		try
		{
			g.addNode("Spain");
			g.addNode("Venezuela");
			g.addNode("UK");
			g.addNode("Poland");
			g.addNode("Greece");
			g.addNode("Japan");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the nodes should have been created.");
		}

		assertEquals(6, g.getSize());
		assertEquals(0, g.getNode("Spain"));
		assertEquals(1, g.getNode("Venezuela"));
		assertEquals(2, g.getNode("UK"));
		assertEquals(3, g.getNode("Poland"));
		assertEquals(4, g.getNode("Greece"));
		assertEquals(5, g.getNode("Japan"));

		assertArrayEquals(new boolean[][] {
				{ false, false, false, false, false, false },
				{ false, false, false, false, false, false },
				{ false, false, false, false, false, false },
				{ false, false, false, false, false, false },
				{ false, false, false, false, false, false },
				{ false, false, false, false, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] {
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, g.getWeight());

		// Test nodes for nodes not found
		assertEquals(-1, g.getNode("Ecuador"));

		// No repeated nodes allowed
		try
		{
			g.addNode("Venezuela");
			fail("FAIL: node 'Venezuela' is already in the graph, so addNode() should have thrown an exception.");
		}
		catch (Exception e)
		{
			System.out.println("This should be happening: " + e);
		}

		// Testing edges
		try
		{
			g.existsEdge("Venezuela", "Ecuador");
			fail("FAIL: node 'Ecuador' does not exist in the graph, and thus existsEdge should have thrown an exception.");
		}
		catch (Exception e)
		{
			System.out.println("This should be happening: " + e);
		}

		try
		{
			assertEquals(false, g.existsEdge("Greece", "Spain"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: nodes 'Greece' and 'Spain' exist, so existsEdge() should not have thrown an exception.");
		}

		try
		{
			g.addEdge("Spain", "Venezuela", 3.0);
			g.addEdge("Spain", "Greece", 2.0);
			g.addEdge("Venezuela", "Poland", 2.0);
			g.addEdge("Greece", "UK", 1.0);
			g.addEdge("UK", "Poland", 4.0);
			g.addEdge("Poland", "Spain", 1.0);
			g.addEdge("Poland", "Greece", 3.0);
			g.addEdge("Poland", "Japan", 1.0);
			g.addEdge("Japan", "Spain", 1.0);
			g.addEdge("Japan", "Poland", 2.0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the edges should have been added.");
		}

		assertArrayEquals(new boolean[][] {
				{ false, true, false, false, true, false },
				{ false, false, false, true, false, false },
				{ false, false, false, true, false, false },
				{ true, false, false, false, true, true },
				{ false, false, true, false, false, false },
				{ true, false, false, true, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] {
				{ 0.0, 3.0, 0.0, 0.0, 2.0, 0.0 },
				{ 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 4.0, 0.0, 0.0 },
				{ 1.0, 0.0, 0.0, 0.0, 3.0, 1.0 },
				{ 0.0, 0.0, 1.0, 0.0, 0.0, 0.0 },
				{ 1.0, 0.0, 0.0, 2.0, 0.0, 0.0 } }, g.getWeight());

		g.floyd(g.getSize());

		assertArrayEquals(new double[][] {
				{ 0.0, 3.0, 3.0, 5.0, 2.0, 6.0 },
				{ 3.0, 0.0, 6.0, 2.0, 5.0, 3.0 },
				{ 5.0, 8.0, 0.0, 4.0, 7.0, 5.0 },
				{ 1.0, 4.0, 4.0, 0.0, 3.0, 1.0 },
				{ 6.0, 9.0, 1.0, 5.0, 0.0, 6.0 },
				{ 1.0, 4.0, 4.0, 2.0, 3.0, 0.0 } }, g.getA());
		assertArrayEquals(new int[][] {
				{ Graph.EMPTY, Graph.EMPTY, 4, 1, Graph.EMPTY, 3 },
				{ 3, Graph.EMPTY, 4, Graph.EMPTY, 3, 3 },
				{ 3, 3, Graph.EMPTY, Graph.EMPTY, 3, 3 },
				{ Graph.EMPTY, 0, 4, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY },
				{ 3, 3, Graph.EMPTY, 2, Graph.EMPTY, 3 },
				{ Graph.EMPTY, 0, 4, Graph.EMPTY, 0, Graph.EMPTY } }, g.getP());

		try
		{
			assertEquals("SpainVenezuelaPolandJapan", "Spain" + g.printFloydPath("Spain", "Japan") + "Japan");
			assertEquals("SpainGreeceUK", "Spain" + g.printFloydPath("Spain", "UK") + "UK");
			assertEquals("SpainVenezuelaPoland", "Spain" + g.printFloydPath("Spain", "Poland") + "Poland");
			assertEquals("PolandSpainVenezuela", "Poland" + g.printFloydPath("Poland", "Venezuela") + "Venezuela");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: all the nodes exist, so no exception should have been thrown.");
		}

		try
		{
			g.removeNode("Greece");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: node 'Greece' should have been removed from the graph.");
		}

		assertArrayEquals(new boolean[][] {
				{ false, true, false, false, false },
				{ false, false, false, true, false },
				{ false, false, false, true, false },
				{ true, false, false, false, true },
				{ true, false, false, true, false } }, getSubmatrix(g.getEdges(), 0, 4, 0, 4));
		assertArrayEquals(new double[][] {
				{ 0.0, 3.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 2.0, 0.0 },
				{ 0.0, 0.0, 0.0, 4.0, 0.0 },
				{ 1.0, 0.0, 0.0, 0.0, 1.0 },
				{ 1.0, 0.0, 0.0, 2.0, 0.0 } }, getSubmatrix(g.getWeight(), 0, 4, 0, 4));

		g.floyd(g.getSize());

		assertArrayEquals(new double[][] {
				{ 0.0, 3.0, Graph.INFINITE, 5.0, 6.0 },
				{ 3.0, 0.0, Graph.INFINITE, 2.0, 3.0 },
				{ 5.0, 8.0, 0.0, 4.0, 5.0 },
				{ 1.0, 4.0, Graph.INFINITE, 0.0, 1.0 },
				{ 1.0, 4.0, Graph.INFINITE, 2.0, 0.0 } }, getSubmatrix(g.getA(), 0, 4, 0, 4));
		assertArrayEquals(new int[][] {
				{ Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 1, 3 },
				{ 3, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 3 },
				{ 3, 3, Graph.EMPTY, Graph.EMPTY, 3 },
				{ Graph.EMPTY, 0, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY },
				{ Graph.EMPTY, 0, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY } }, getSubmatrix(g.getP(), 0, 4, 0, 4));

		try
		{
			assertEquals("SpainVenezuelaPolandJapan", "Spain" + g.printFloydPath("Spain", "Japan") + "Japan");
			assertEquals("SpainUK", "Spain" + g.printFloydPath("Spain", "UK") + "UK");
			assertEquals("SpainVenezuelaPoland", "Spain" + g.printFloydPath("Spain", "Poland") + "Poland");
			assertEquals("PolandSpainVenezuela", "Poland" + g.printFloydPath("Poland", "Venezuela") + "Venezuela");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: all the nodes exist, so no exception should have been thrown.");
		}

		try
		{
			g.removeEdge("Poland", "Japan");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the edge {Poland,Japan} should have been removed.");
		}

		assertArrayEquals(new boolean[][] {
				{ false, true, false, false, false },
				{ false, false, false, true, false },
				{ false, false, false, true, false },
				{ true, false, false, false, false },
				{ true, false, false, true, false } }, getSubmatrix(g.getEdges(), 0, 4, 0, 4));
		assertArrayEquals(new double[][] {
				{ 0.0, 3.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 2.0, 0.0 },
				{ 0.0, 0.0, 0.0, 4.0, 0.0 },
				{ 1.0, 0.0, 0.0, 0.0, 0.0 },
				{ 1.0, 0.0, 0.0, 2.0, 0.0 } }, getSubmatrix(g.getWeight(), 0, 4, 0, 4));

		g.floyd(g.getSize());

		assertArrayEquals(new double[][] {
				{ 0.0, 3.0, Graph.INFINITE, 5.0, Graph.INFINITE },
				{ 3.0, 0.0, Graph.INFINITE, 2.0, Graph.INFINITE },
				{ 5.0, 8.0, 0.0, 4.0, Graph.INFINITE },
				{ 1.0, 4.0, Graph.INFINITE, 0.0, Graph.INFINITE },
				{ 1.0, 4.0, Graph.INFINITE, 2.0, 0.0 } }, getSubmatrix(g.getA(), 0, 4, 0, 4));
		assertArrayEquals(new int[][] {
				{ Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 1, Graph.EMPTY },
				{ 3, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY },
				{ 3, 3, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY },
				{ Graph.EMPTY, 0, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY },
				{ Graph.EMPTY, 0, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY } }, getSubmatrix(g.getP(), 0, 4, 0, 4));

		try
		{
			assertEquals("SpainJapan", "Spain" + g.printFloydPath("Spain", "Japan") + "Japan");
			assertEquals("SpainUK", "Spain" + g.printFloydPath("Spain", "UK") + "UK");
			assertEquals("SpainVenezuelaPoland", "Spain" + g.printFloydPath("Spain", "Poland") + "Poland");
			assertEquals("PolandSpainVenezuela", "Poland" + g.printFloydPath("Poland", "Venezuela") + "Venezuela");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: all the nodes exist, so no exception should have been thrown.");
		}
	}
}