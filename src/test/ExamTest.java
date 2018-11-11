package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.Graph;

public class ExamTest
{
	private static final double DELTA = 0.0001;

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
	public void test()
	{
		Graph<Character> g = new Graph<Character>(6);

		try
		{
			g.addNode('A');
			g.addNode('B');
			g.addNode('C');
			g.addNode('D');
			g.addNode('E');
			g.addNode('F');
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the initial nodes were not added succesfully.");
		}

		try
		{
			g.addEdge('A', 'B', 1.0);
			g.addEdge('A', 'E', 5.0);
			g.addEdge('A', 'F', 7.0);
			g.addEdge('B', 'A', 1.0);
			g.addEdge('B', 'C', 2.0);
			g.addEdge('C', 'F', 3.0);
			g.addEdge('E', 'C', 4.0);
			g.addEdge('E', 'D', 3.0);
			g.addEdge('F', 'D', 1.0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the initial edges were not added succesfully.");
		}

		assertArrayEquals(new boolean[][] {
				{ false,  true, false, false,  true,  true },
				{  true, false,  true, false, false, false },
				{ false, false, false, false, false,  true },
				{ false, false, false, false, false, false },
				{ false, false,  true,  true, false, false },
				{ false, false, false,  true, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] {
				{   0.0,   1.0,   0.0,   0.0,   5.0,   7.0 },
				{   1.0,   0.0,   2.0,   0.0,   0.0,   0.0 },
				{   0.0,   0.0,   0.0,   0.0,   0.0,   3.0 },
				{   0.0,   0.0,   0.0,   0.0,   0.0,   0.0 },
				{   0.0,   0.0,   4.0,   3.0,   0.0,   0.0 },
				{   0.0,   0.0,   0.0,   1.0,   0.0,   0.0 }, }, g.getWeight());

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('F');

			assertTrue( g.existsPath('A', 'B', excludedNodes));
			assertTrue( g.existsPath('A', 'C', excludedNodes));
			assertTrue( g.existsPath('A', 'D', excludedNodes));
			assertTrue( g.existsPath('A', 'E', excludedNodes));
			assertTrue( g.existsPath('B', 'A', excludedNodes));
			assertTrue( g.existsPath('B', 'C', excludedNodes));
			assertTrue( g.existsPath('B', 'D', excludedNodes));
			assertTrue( g.existsPath('B', 'E', excludedNodes));
			assertFalse(g.existsPath('C', 'A', excludedNodes));
			assertFalse(g.existsPath('C', 'B', excludedNodes));
			assertFalse(g.existsPath('C', 'D', excludedNodes));
			assertFalse(g.existsPath('C', 'E', excludedNodes));
			assertFalse(g.existsPath('D', 'A', excludedNodes));
			assertFalse(g.existsPath('D', 'B', excludedNodes));
			assertFalse(g.existsPath('D', 'C', excludedNodes));
			assertFalse(g.existsPath('D', 'E', excludedNodes));
			assertFalse(g.existsPath('E', 'A', excludedNodes));
			assertFalse(g.existsPath('E', 'B', excludedNodes));
			assertTrue( g.existsPath('E', 'C', excludedNodes));
			assertTrue( g.existsPath('E', 'D', excludedNodes));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the proposed method failed.");
		}

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('C');
			excludedNodes.add('E');

			assertTrue( g.existsPath('A', 'B', excludedNodes));
			assertTrue( g.existsPath('A', 'D', excludedNodes));
			assertTrue( g.existsPath('A', 'F', excludedNodes));
			assertTrue( g.existsPath('B', 'A', excludedNodes));
			assertTrue( g.existsPath('B', 'D', excludedNodes));
			assertTrue( g.existsPath('B', 'F', excludedNodes));
			assertFalse(g.existsPath('D', 'A', excludedNodes));
			assertFalse(g.existsPath('D', 'B', excludedNodes));
			assertFalse(g.existsPath('D', 'F', excludedNodes));
			assertFalse(g.existsPath('F', 'A', excludedNodes));
			assertFalse(g.existsPath('F', 'B', excludedNodes));
			assertTrue( g.existsPath('F', 'D', excludedNodes));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the proposed method failed.");
		}

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('C');
			excludedNodes.add('F');

			assertTrue( g.existsPath('A', 'B', excludedNodes));
			assertTrue( g.existsPath('A', 'D', excludedNodes));
			assertTrue( g.existsPath('A', 'E', excludedNodes));
			assertTrue( g.existsPath('B', 'A', excludedNodes));
			assertTrue( g.existsPath('B', 'D', excludedNodes));
			assertTrue( g.existsPath('B', 'E', excludedNodes));
			assertFalse(g.existsPath('D', 'A', excludedNodes));
			assertFalse(g.existsPath('D', 'B', excludedNodes));
			assertFalse(g.existsPath('D', 'E', excludedNodes));
			assertFalse(g.existsPath('E', 'A', excludedNodes));
			assertFalse(g.existsPath('E', 'B', excludedNodes));
			assertTrue( g.existsPath('E', 'D', excludedNodes));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the proposed method failed.");
		}

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('E');
			excludedNodes.add('F');

			assertTrue( g.existsPath('A', 'B', excludedNodes));
			assertTrue( g.existsPath('A', 'C', excludedNodes));
			assertFalse(g.existsPath('A', 'D', excludedNodes));
			assertTrue( g.existsPath('B', 'A', excludedNodes));
			assertTrue( g.existsPath('B', 'C', excludedNodes));
			assertFalse(g.existsPath('B', 'D', excludedNodes));
			assertFalse(g.existsPath('C', 'A', excludedNodes));
			assertFalse(g.existsPath('C', 'B', excludedNodes));
			assertFalse(g.existsPath('C', 'D', excludedNodes));
			assertFalse(g.existsPath('D', 'A', excludedNodes));
			assertFalse(g.existsPath('D', 'B', excludedNodes));
			assertFalse(g.existsPath('D', 'C', excludedNodes));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the proposed method failed.");
		}

		System.out.println("SUCCESS: checkpoint 1 reached.");

		try
		{
			g.removeEdge('A', 'E');
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: edge from 'A' to 'E' was not removed correctly.");
		}

		assertArrayEquals(new boolean[][] {
				{ false,  true, false, false, false,  true },
				{  true, false,  true, false, false, false },
				{ false, false, false, false, false,  true },
				{ false, false, false, false, false, false },
				{ false, false,  true,  true, false, false },
				{ false, false, false,  true, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] {
				{   0.0,   1.0,   0.0,   0.0,   0.0,   7.0 },
				{   1.0,   0.0,   2.0,   0.0,   0.0,   0.0 },
				{   0.0,   0.0,   0.0,   0.0,   0.0,   3.0 },
				{   0.0,   0.0,   0.0,   0.0,   0.0,   0.0 },
				{   0.0,   0.0,   4.0,   3.0,   0.0,   0.0 },
				{   0.0,   0.0,   0.0,   1.0,   0.0,   0.0 }, }, g.getWeight());

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('F');

			assertTrue( g.existsPath('A', 'B', excludedNodes));
			assertTrue( g.existsPath('A', 'C', excludedNodes));
			assertFalse(g.existsPath('A', 'D', excludedNodes));
			assertFalse(g.existsPath('A', 'E', excludedNodes));
			assertTrue( g.existsPath('B', 'A', excludedNodes));
			assertTrue( g.existsPath('B', 'C', excludedNodes));
			assertFalse(g.existsPath('B', 'D', excludedNodes));
			assertFalse(g.existsPath('B', 'E', excludedNodes));
			assertFalse(g.existsPath('C', 'A', excludedNodes));
			assertFalse(g.existsPath('C', 'B', excludedNodes));
			assertFalse(g.existsPath('C', 'D', excludedNodes));
			assertFalse(g.existsPath('C', 'E', excludedNodes));
			assertFalse(g.existsPath('D', 'A', excludedNodes));
			assertFalse(g.existsPath('D', 'B', excludedNodes));
			assertFalse(g.existsPath('D', 'C', excludedNodes));
			assertFalse(g.existsPath('D', 'E', excludedNodes));
			assertFalse(g.existsPath('E', 'A', excludedNodes));
			assertFalse(g.existsPath('E', 'B', excludedNodes));
			assertTrue( g.existsPath('E', 'C', excludedNodes));
			assertTrue( g.existsPath('E', 'D', excludedNodes));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the proposed method failed.");
		}

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('C');
			excludedNodes.add('E');

			assertTrue( g.existsPath('A', 'B', excludedNodes));
			assertTrue( g.existsPath('A', 'D', excludedNodes));
			assertTrue( g.existsPath('A', 'F', excludedNodes));
			assertTrue( g.existsPath('B', 'A', excludedNodes));
			assertTrue( g.existsPath('B', 'D', excludedNodes));
			assertTrue( g.existsPath('B', 'F', excludedNodes));
			assertFalse(g.existsPath('D', 'A', excludedNodes));
			assertFalse(g.existsPath('D', 'B', excludedNodes));
			assertFalse(g.existsPath('D', 'F', excludedNodes));
			assertFalse(g.existsPath('F', 'A', excludedNodes));
			assertFalse(g.existsPath('F', 'B', excludedNodes));
			assertTrue( g.existsPath('F', 'D', excludedNodes));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the proposed method failed.");
		}

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('C');
			excludedNodes.add('F');

			assertTrue( g.existsPath('A', 'B', excludedNodes));
			assertFalse(g.existsPath('A', 'D', excludedNodes));
			assertFalse(g.existsPath('A', 'E', excludedNodes));
			assertTrue( g.existsPath('B', 'A', excludedNodes));
			assertFalse(g.existsPath('B', 'D', excludedNodes));
			assertFalse(g.existsPath('B', 'E', excludedNodes));
			assertFalse(g.existsPath('D', 'A', excludedNodes));
			assertFalse(g.existsPath('D', 'B', excludedNodes));
			assertFalse(g.existsPath('D', 'E', excludedNodes));
			assertFalse(g.existsPath('E', 'A', excludedNodes));
			assertFalse(g.existsPath('E', 'B', excludedNodes));
			assertTrue( g.existsPath('E', 'D', excludedNodes));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the proposed method failed.");
		}

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('E');
			excludedNodes.add('F');

			assertTrue( g.existsPath('A', 'B', excludedNodes));
			assertTrue( g.existsPath('A', 'C', excludedNodes));
			assertFalse(g.existsPath('A', 'D', excludedNodes));
			assertTrue( g.existsPath('B', 'A', excludedNodes));
			assertTrue( g.existsPath('B', 'C', excludedNodes));
			assertFalse(g.existsPath('B', 'D', excludedNodes));
			assertFalse(g.existsPath('C', 'A', excludedNodes));
			assertFalse(g.existsPath('C', 'B', excludedNodes));
			assertFalse(g.existsPath('C', 'D', excludedNodes));
			assertFalse(g.existsPath('D', 'A', excludedNodes));
			assertFalse(g.existsPath('D', 'B', excludedNodes));
			assertFalse(g.existsPath('D', 'C', excludedNodes));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the proposed method failed.");
		}

		System.out.println("SUCCESS: checkpoint 2 reached.");

		try
		{
			g.addEdge('A', 'E', 5.0);
			g.addEdge('C', 'B', 6.0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: edge from 'A' to 'E' was not removed correctly.");
		}

		try
		{
			g.removeNode('F');
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: node 'F' was not removed correctly.");
		}

		assertArrayEquals(new boolean[][] {
				{ false,  true, false, false,  true },
				{  true, false,  true, false, false },
				{ false,  true, false, false, false },
				{ false, false, false, false, false },
				{ false, false,  true,  true, false } }, getSubmatrix(g.getEdges(), 0, 4, 0, 4));
		assertArrayEquals(new double[][] {
				{   0.0,   1.0,   0.0,   0.0,   5.0 },
				{   1.0,   0.0,   2.0,   0.0,   0.0 },
				{   0.0,   6.0,   0.0,   0.0,   0.0 },
				{   0.0,   0.0,   0.0,   0.0,   0.0 },
				{   0.0,   0.0,   4.0,   3.0,   0.0 }, }, getSubmatrix(g.getWeight(), 0, 4, 0, 4));

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('C');

			assertTrue( g.existsPath('A', 'B', excludedNodes));
			assertTrue( g.existsPath('A', 'D', excludedNodes));
			assertTrue( g.existsPath('A', 'E', excludedNodes));
			assertTrue( g.existsPath('B', 'A', excludedNodes));
			assertTrue( g.existsPath('B', 'D', excludedNodes));
			assertTrue( g.existsPath('B', 'E', excludedNodes));
			assertFalse(g.existsPath('D', 'A', excludedNodes));
			assertFalse(g.existsPath('D', 'B', excludedNodes));
			assertFalse(g.existsPath('D', 'E', excludedNodes));
			assertFalse(g.existsPath('E', 'A', excludedNodes));
			assertFalse(g.existsPath('E', 'B', excludedNodes));
			assertTrue( g.existsPath('E', 'D', excludedNodes));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the proposed method failed.");
		}

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('C');
			excludedNodes.add('E');

			assertTrue( g.existsPath('A', 'B', excludedNodes));
			assertFalse(g.existsPath('A', 'D', excludedNodes));
			assertTrue( g.existsPath('B', 'A', excludedNodes));
			assertFalse(g.existsPath('B', 'D', excludedNodes));
			assertFalse(g.existsPath('D', 'A', excludedNodes));
			assertFalse(g.existsPath('D', 'B', excludedNodes));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the proposed method failed.");
		}

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('B');

			assertTrue( g.existsPath('A', 'C', excludedNodes));
			assertTrue( g.existsPath('A', 'D', excludedNodes));
			assertTrue( g.existsPath('A', 'E', excludedNodes));
			assertFalse(g.existsPath('C', 'A', excludedNodes));
			assertFalse(g.existsPath('C', 'D', excludedNodes));
			assertFalse(g.existsPath('C', 'E', excludedNodes));
			assertFalse(g.existsPath('D', 'A', excludedNodes));
			assertFalse(g.existsPath('D', 'C', excludedNodes));
			assertFalse(g.existsPath('D', 'E', excludedNodes));
			assertFalse(g.existsPath('E', 'A', excludedNodes));
			assertTrue( g.existsPath('E', 'C', excludedNodes));
			assertTrue( g.existsPath('E', 'D', excludedNodes));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("FAIL: the proposed method failed.");
		}

		System.out.println("SUCCESS: checkpoint 3 reached.");

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('B');

			assertFalse(g.existsPath('A', 'F', excludedNodes));
			fail("FAIL: node 'F' does not exist.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('B');

			assertFalse(g.existsPath('F', 'A', excludedNodes));
			fail("FAIL: node 'F' does not exist.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			List<Character> excludedNodes = new ArrayList<Character>();
			excludedNodes.add('F');

			assertFalse(g.existsPath('C', 'A', excludedNodes));
			fail("FAIL: node 'F' does not exist.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("SUCCESS: checkpoint 4 reached.");
	}
}