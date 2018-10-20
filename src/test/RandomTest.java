package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Graph;

public class RandomTest
{
	@Test
	public void Test_Edit_A()
	{
		Graph<Character> g1 = new Graph<Character>(3);

		assertEquals(0, g1.getSize());

		try
		{
			g1.addNode('a');
		}
		catch (Exception e)
		{
			System.out.println("This should NOT be happening: " + e);
		}

		assertEquals(1, g1.getSize());
		assertEquals(0, g1.getNode('a'));
		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } }, g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		// Test nodes for nodes not found
		assertEquals(-1, g1.getNode('b'));

		// No repeated nodes allowed
		try
		{
			g1.addNode('a');
		}
		catch (Exception e)
		{
			System.out.println("This should be happening: " + e);
		}

		try
		{
			g1.addNode('b');
			g1.addNode('c');
		}
		catch (Exception e)
		{
			System.out.println("This should NOT be happening: " + e);
		}

		assertEquals(3, g1.getSize());
		assertEquals(0, g1.getNode('a'));
		assertEquals(1, g1.getNode('b'));
		assertEquals(2, g1.getNode('c'));

		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } }, g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		// Testing edges
		try
		{
			assertEquals(false, g1.existsEdge('b', 'd'));
		}
		catch (Exception e)
		{
			System.out.println("This should be happening: " + e);
		}

		try
		{
			assertEquals(false, g1.existsEdge('b', 'c'));
		}
		catch (Exception e)
		{
			System.out.println("This should NOT be happening: " + e);
		}

		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } }, g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		try
		{
			g1.addEdge('a', 'b', 3.0);
			g1.addEdge('b', 'c', 5.0);
			assertEquals(true, g1.existsEdge('b', 'c'));
			assertEquals(true, g1.existsEdge('a', 'b'));
		}
		catch (Exception e)
		{
			System.out.println("This should NOT be happening: " + e);
		}

		assertArrayEquals(new boolean[][] { { false, true, false }, { false, false, true }, { false, false, false } }, g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 3.0, 0.0 }, { 0.0, 0.0, 5.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		// SOURCES
		try
		{
			assertEquals(true, g1.isSourceNode('a'));
			assertEquals(false, g1.isSourceNode('b'));
			assertEquals(false, g1.isSourceNode('c'));
			assertEquals(false, g1.isDrainNode('a'));
			assertEquals(false, g1.isDrainNode('b'));
			assertEquals(true, g1.isDrainNode('c'));

			assertEquals(1, g1.countDrainNodes());
			assertEquals(1, g1.countSourceNodes());
		}
		catch (Exception e)
		{
			System.out.println("This should NOT be happening: " + e);
		}

		try
		{
			g1.removeNode('a');
		}
		catch (Exception e)
		{
			System.out.println("This should NOT be happening: " + e);
		}

		boolean[][] e1 = new boolean[][] { { false, false }, { true, false } };
		double[][] w1 = new double[][] { { 0.0, 0.0 }, { 5.0, 0.0 } };
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				assertEquals(g1.getEdges()[i][j], e1[i][j]);
				assertEquals(g1.getWeight()[i][j], w1[i][j], 0.001);
			}
		}

		try
		{
			assertEquals(true, g1.isSourceNode('b'));
			assertEquals(false, g1.isSourceNode('c'));

			assertEquals(false, g1.isDrainNode('b'));
			assertEquals(true, g1.isDrainNode('c'));

			assertEquals(1, g1.countDrainNodes());
			assertEquals(1, g1.countSourceNodes());
		}
		catch (Exception e)
		{
			System.out.println("This should NOT be happening: " + e);
		}
	}
}