package main;

import java.util.Random;

/**
 * Class used for testing graph algorithms performances
 * @author mskrz
 *
 */
public class GraphPerformanceTest {

	private static Random random = new Random();
	private static final String CLASS_NAME = "main.GraphPerformanceTest";

	/**
	 * Tests all of the class methods and save the outcome to the files.
	 * @param args
	 */
	public static void main(String[] args) {
		TestBench.testAlgorithm(CLASS_NAME, "runFloyd", 100, 300, 3, "012_Graph_Floyd.txt");
//		TestBench.testAlgorithm(CLASS_NAME, "runDijkstra", 100, 300, 3, "022_Graph_Dijkstra.txt");
//		TestBench.testAlgorithm(CLASS_NAME, "initGraph", 100, 300, 3, "03_Graph_Build.txt");
	}

	/**
	 * Initializes a complete  graph.
	 * @param n Max number of nodes.
	 * @return Graph with specific nodes number and edges between all of them.
	 * @throws Exception
	 */
	public static Graph<Integer> initGraph(long n) throws Exception {
		Graph<Integer> graph = new Graph<>((int) n);
		for (int k = 0; k < n; k++) {
			graph.addNode(k);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j) {
					double weight = random.nextDouble() * 10;
					graph.addEdge(graph.getNode(i), graph.getNode(j), weight);
				}
			}
		}
		return graph;
	}
	
	/**
	 * Performs dijkstra algoriithm within a specific graph of n nodes.
	 * @param n Number of nodes in a graph.
	 * @throws Exception
	 */
	public static void runDijkstra(long n) throws Exception {
		Graph<Integer> graph = initGraph(n);
		graph.Dijkstra2(graph.getNode(0));
	}

	/**
	 * Performs floys algoriithm within a specific graph of n nodes.
	 * @param n Number of nodes in a graph.
	 * @throws Exception
	 */
	public static void runFloyd(long n) throws Exception {
		Graph<Integer> graph = initGraph(n);
		graph.floyd(graph.getSize());
	}

}
