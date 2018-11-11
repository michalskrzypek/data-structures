package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Graph<T> {

	public static final int INDEX_NOT_FOUND = -1;
	public static final int EMPTY = -1;
	public static final double INFINITE = Double.MAX_VALUE;
	private int size; // maximum graph size of weight and edges matrix which cannot be increased
	private ArrayList<GraphNode<T>> nodes;
	private boolean[][] edges;
	private double[][] weight;
	private double[][] a;
	private int[][] p;
	private double[][] d;
	private int[] pd;
	private Map<Integer, Double> v;
	private int allExist = 0;

	public Graph(int n) {
		size = n;
		edges = new boolean[n][n];
		weight = new double[n][n];
		nodes = new ArrayList<GraphNode<T>>(n);
		a = new double[n][n];
		p = new int[n][n];
		d = new double[1][n];
		pd = new int[n];
	}

	public boolean existsPath(T departure, T arrival, List<T> excludeNodes) {
		int departureNodeIndex = getNode(departure);
		int arrivalNodeIndex = getNode(arrival);
		if (departureNodeIndex == INDEX_NOT_FOUND || arrivalNodeIndex == INDEX_NOT_FOUND) {
			throw new IllegalArgumentException("Departure or/and arrival node does not exist");
		}
		allExist = 0;
		for (T node : excludeNodes) {
			nodes.stream().map(g -> g.getElement()).forEach(ch -> {
				if (ch.equals(node)) {
					allExist++;
				}
			});
			if (allExist == 0) {
				throw new IllegalArgumentException();
			}
		}

//		DijkstraWithoutNodes2(departure, excludeNodes);
		DijkstraWithoutSpecifiNodes(departure, excludeNodes);
//		Dijkstra(departure);
		if (d[0][arrivalNodeIndex] != INFINITE) {
			return true;
		}
		return false;
	}

	public void DijkstraWithoutNodes2(T departureNode, List<T> excludeNodes) {
		initDijkstra(departureNode);

		int nodeIndex = getNode(departureNode);
		if (nodeIndex == -1) {
			throw new IllegalArgumentException("Node does not exist");
		}

		v = new HashMap<>(); // map that stores pairs [index][weight from nodeIndex to index]
		for (int i = 0; i < getSize(); i++) {
			if (i != nodeIndex) { // nodeIndex is already in set S <- this is initialized in initDiakstra method
				v.put(i, d[0][i]);
			}
		}

		while (!v.isEmpty()) {
			ArrayList<Double> values = new ArrayList<>();
			v.values().iterator().forEachRemaining(val -> values.add(val));
			Collections.sort(values);

			// It works like in normal DIjkstra algorithm. We take the node that is
			// connected to our node via edge with the smallest weight and check if it can
			// be a node that mediates between any other node.
			double smallestWeight = values.get(0);
			int indexOfTheSmallestWeight = getKeysByValue(v, smallestWeight);
			if (smallestWeight == INFINITE) { // it means that there is no direct node left use as a pivot
				break;
			}

			for (int j = 0; j < getSize(); j++) {
				if (j != nodeIndex && !excludeNodes.contains(nodes.get(j).getElement())) {
					if (edges[indexOfTheSmallestWeight][j]) {
						if (d[0][indexOfTheSmallestWeight] + weight[indexOfTheSmallestWeight][j] < d[0][j]) {
							d[0][j] = d[0][indexOfTheSmallestWeight] + weight[indexOfTheSmallestWeight][j];
							pd[j] = indexOfTheSmallestWeight;
						}
					}
				}
			}
			v.remove(indexOfTheSmallestWeight); // removing node from v set, basically it means putting node to S set
			updateV();
		}
	}

	private void DijkstraWithoutSpecifiNodes(T departureNode, List<T> excludeNodes) {
		initDijkstra(departureNode);

		int nodeIndex = getNode(departureNode);
		if (nodeIndex == -1) {
			throw new IllegalArgumentException("Node does not exist");
		}
		nodes.get(nodeIndex).setVisited(true);

		double minimumCost = INFINITE;
		T pivot = departureNode;

		/*
		 * List<Integer> indexesOfExcludedNodes = new ArrayList<>(); for (T node :
		 * excludeNodes) { int index = getNode(node); if (index == INDEX_NOT_FOUND) {
		 * throw new IllegalArgumentException("Node does not exist"); } else {
		 * indexesOfExcludedNodes.add(index); } }
		 */

		for (int i = 1; i < getSize(); i++) {

			for (int j = 0; j < getSize(); j++) {
				for (int k = 0; k < getSize(); k++) {
//					if (!indexesOfExcludedNodes.contains(k)) {
					if (!excludeNodes.contains(nodes.get(k).getElement())) {
						if (nodes.get(j).isVisited() && !nodes.get(k).isVisited()) {
							if (weight[j][k] != 0 && weight[j][k] < minimumCost) {
								minimumCost = weight[j][k];
								pivot = nodes.get(k).getElement();
							}
						}
					}
				}
			}

			for (int k = 0; k < getSize(); k++) {
//				if (!indexesOfExcludedNodes.contains(k)) {
				if (!excludeNodes.contains(nodes.get(k).getElement())) {
					if (!nodes.get(k).isVisited() && weight[getNode(pivot)][k] != 0) {
						if (d[0][getNode(pivot)] + weight[getNode(pivot)][k] < d[0][k]) {
							d[0][k] = d[0][getNode(pivot)] + weight[getNode(pivot)][k];
							pd[k] = getNode(pivot);
						}
					}
				}
			}
			nodes.get(getNode(pivot)).setVisited(true);
			minimumCost = INFINITE;
		}
	}

	/**
	 * Implementation of Dijkstra algorithm with the complexity of O(n^3)
	 * 
	 * @param departureNode
	 */
	public void Dijkstra(T departureNode) {
		initDijkstra(departureNode);

		int nodeIndex = getNode(departureNode);
		if (nodeIndex == -1) {
			throw new IllegalArgumentException("Node does not exist");
		}
		nodes.get(nodeIndex).setVisited(true);

		double minimumCost = INFINITE;
		T pivot = departureNode;

		for (int i = 1; i < getSize(); i++) {

			for (int j = 0; j < getSize(); j++) {
				for (int k = 0; k < getSize(); k++) {
					if (nodes.get(j).isVisited() && !nodes.get(k).isVisited()) {
						if (weight[j][k] != 0 && weight[j][k] < minimumCost) {
							minimumCost = weight[j][k];
							pivot = nodes.get(k).getElement();
						}
					}
				}
			}

			for (int k = 0; k < getSize(); k++) {
				if (!nodes.get(k).isVisited() && weight[getNode(pivot)][k] != 0) {
					if (d[0][getNode(pivot)] + weight[getNode(pivot)][k] < d[0][k]) {
						d[0][k] = d[0][getNode(pivot)] + weight[getNode(pivot)][k];
						pd[k] = getNode(pivot);
					}
				}
			}
			nodes.get(getNode(pivot)).setVisited(true);
			minimumCost = INFINITE;
		}
	}

	/**
	 * Implementation of Dijkstra algorithm with the complexity of O(n^2)
	 * 
	 * @param departureNode
	 */
	public void Dijkstra2(T departureNode) {
		initDijkstra(departureNode);

		int nodeIndex = getNode(departureNode);
		if (nodeIndex == -1) {
			throw new IllegalArgumentException("Node does not exist");
		}

		v = new HashMap<>(); // map that stores pairs [index][weight from nodeIndex to index]
		for (int i = 0; i < getSize(); i++) {
			if (i != nodeIndex) { // nodeIndex is already in set S <- this is initialized in initDiakstra method
				v.put(i, d[0][i]);
			}
		}

		while (!v.isEmpty()) {
			ArrayList<Double> values = new ArrayList<>();
			v.values().iterator().forEachRemaining(val -> values.add(val));
			Collections.sort(values);

			// It works like in normal DIjkstra algorithm. We take the node that is
			// connected to our node via edge with the smallest weight and check if it can
			// be a node that mediates between any other node.
			double smallestWeight = values.get(0);
			int indexOfTheSmallestWeight = getKeysByValue(v, smallestWeight);
			if (smallestWeight == INFINITE) { // it means that there is no direct node left use as a pivot
				break;
			}

			for (int j = 0; j < getSize(); j++) {
				if (j != nodeIndex) {
					if (edges[indexOfTheSmallestWeight][j]) {
						if (d[0][indexOfTheSmallestWeight] + weight[indexOfTheSmallestWeight][j] < d[0][j]) {
							d[0][j] = d[0][indexOfTheSmallestWeight] + weight[indexOfTheSmallestWeight][j];
							pd[j] = indexOfTheSmallestWeight;
						}
					}
				}
			}
			v.remove(indexOfTheSmallestWeight); // removing node from v set, basically it means putting node to S set
			updateV();
		}
	}

	private void updateV() {
		for (Integer index : v.keySet()) {
			v.put(index, d[0][index]);
		}
	}

	private static Integer getKeysByValue(Map<Integer, Double> map, Double value) {
		return map.entrySet().stream().filter(entry -> Objects.equals(entry.getValue(), value)).map(Map.Entry::getKey)
				.findFirst().get();
	}

	public void initDijkstra(T departureNode) {
		int nodeIndex = getNode(departureNode);
		if (nodeIndex == INDEX_NOT_FOUND) {
			throw new IllegalArgumentException("Node does not exist");
		}

		for (int j = 0; j < getSize(); j++) {
			nodes.get(j).setVisited(false);
			this.d[0][j] = this.weight[nodeIndex][j];
			this.pd[j] = EMPTY;

			if (!this.edges[nodeIndex][j]) {
				this.d[0][j] = INFINITE;
			} else {
				this.pd[j] = nodeIndex;
			}
		}

		this.d[0][nodeIndex] = INFINITE;
		this.pd[nodeIndex] = EMPTY;
	}

	public int[] getPD() {
		return pd;
	}

	public double[][] getD() {
		return d;
	}

	public String printFloydPath(T departure, T arrival) throws Exception {
		if (departure.equals(arrival)) {
//			return departure.toString();
			return "";
		}
		int depIndex = getNode(departure);
		int arrIndex = getNode(arrival);
		if (depIndex == INDEX_NOT_FOUND || arrIndex == INDEX_NOT_FOUND) {
			throw new IllegalArgumentException("Departure and/or arrival node does not exist");
		}

		String aux = "";

		if (a[depIndex][arrIndex] != INFINITE) { // Exists a path from dep node to arr node
			if (p[depIndex][arrIndex] != EMPTY) { // Exists a node between dep and arr nodes
				T elementBetween = nodes.get(p[depIndex][arrIndex]).getElement();
				aux = printFloydPath(departure, elementBetween) + elementBetween.toString(); // or + aux instead of
																								// elementbetwwen
			} else { // exists direct path between dep and arr nodes
				return aux;
			}
		} else {
			return "";
		}

		return aux;
	}

	public void floyd(int An) {
		if (An > this.size) {
			throw new IllegalArgumentException("Size of the nodes array is smaller than " + An);
		}

		this.initsFloyd();

		for (int k = 0; k < An; k++)
			for (int i = 0; i < An; i++)
				for (int j = 0; j < An; j++)
					if (this.a[i][k] + this.a[k][j] < this.a[i][j]) {
						this.a[i][j] = this.a[i][k] + this.a[k][j];
						this.p[i][j] = k;
					}
	}

	protected void initsFloyd() {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				this.a[i][j] = this.weight[i][j];

				if (!this.edges[i][j]) {
					this.a[i][j] = INFINITE;
				}
				if (i == j) {
					this.a[i][j] = 0.0;
				}

				this.p[i][j] = EMPTY;
			}
		}
	}

	public double[][] getA() { // cost matrix
		return this.a;
	}

	public int[][] getP() { // Pathway matrix , P[i][j]
		return this.p;
	}

	public String traverseGraphDF(T element) {
		resetVisited();

		int v = getNode(element);
		if (v == -1) {
			throw new IllegalArgumentException("Node does not exist!");
		}
		return DFPrint(v);
	}

	private void resetVisited() {
		nodes.stream().forEach(n -> n.setVisited(false));
	}

	private String DFPrint(int v) {
		GraphNode<T> theNode = nodes.get(v);
		theNode.setVisited(true);
		String aux = theNode.getElement() + "-";
		for (int node = 0; node < nodes.size(); node++) {
			if (edges[v][node] && !nodes.get(node).isVisited()) {
				aux = aux + DFPrint(node);
			}
		}
		return aux;
	}

	public int countDrainNodes() {
		int sum = (int) nodes.stream().filter(node -> isDrainNode(node.getElement())).count();
		return sum;
	}

	public int countSourceNodes() {
		int sum = (int) nodes.stream().filter(node -> isSourceNode(node.getElement())).count();
		return sum;
	}

	public boolean isDrainNode(T element) {
		int nodeIndex = getNode(element);
		int arrivalEdges = 0;
		if (nodeIndex == -1) {
			throw new IllegalArgumentException("Node does not exist!");
		} else {
			for (int i = 0; i < nodes.size(); i++) {
				if (edges[nodeIndex][i]) {
					return false;
				}
				if (edges[i][nodeIndex]) {
					arrivalEdges++;
				}
			}
		}
		if (arrivalEdges == 0) {
			return false;
		}
		return true;
	}

	public boolean isSourceNode(T element) {
		int nodeIndex = getNode(element);
		int departuringNodes = 0;
		if (nodeIndex == -1) {
			throw new IllegalArgumentException("Node does not exist!");
		} else {
			for (int i = 0; i < nodes.size(); i++) {
				if (edges[i][nodeIndex]) {
					return false;
				}
				if (edges[nodeIndex][i]) {
					departuringNodes++;
				}
			}
		}
		if (departuringNodes == 0) {
			return false;
		}
		return true;
	}

	public void removeNode(T element) throws Exception {
		int nodeIndex = getNode(element);
		if (nodeIndex == -1) {
			throw new IllegalArgumentException("Node does not exist!");
		} else {
			if (nodeIndex != getLastNodeIndex()) { // if it is not the last node
				GraphNode<T> lastNode = nodes.get(getLastNodeIndex()); // last node before removal, it will be the node
																		// that will be in the place of removed one
				nodes.set(nodeIndex, lastNode);
				nodes.remove(getLastNodeIndex());

				int lastNodeIndex = getLastNodeIndex(); // current last index, after removal
				int lastNodeIndexBeforeRemoval = lastNodeIndex + 1;
				for (int j = 0; j <= lastNodeIndex; j++) { // we iterate through each existing nodes
					edges[j][nodeIndex] = edges[j][lastNodeIndexBeforeRemoval];
					edges[nodeIndex][j] = edges[lastNodeIndexBeforeRemoval][j];
					weight[nodeIndex][j] = weight[lastNodeIndexBeforeRemoval][j];
					weight[j][nodeIndex] = weight[j][lastNodeIndexBeforeRemoval];
				}
				// loop (diagonal)
				edges[nodeIndex][nodeIndex] = edges[nodes.size()][nodes.size()];
				weight[nodeIndex][nodeIndex] = weight[nodes.size()][nodes.size()];
			} else {
				nodes.remove(nodeIndex);
			}
		}
	}

	private int getLastNodeIndex() {
		return getSize() - 1;
	}

	public void addEdge(T origin, T dest, double weight) throws Exception {
		int originIndex = getNode(origin);
		int destIndex = getNode(dest);
		if (originIndex == -1 || destIndex == -1) {
			throw new IllegalArgumentException("One of the nodes can not be found!");
		}
		if (originIndex == destIndex) {
			throw new IllegalArgumentException("Node of the origin and destination must be different!");
		}

		boolean edgeExist = existsEdge(origin, dest);
		if (edgeExist) {
			throw new Exception("Edge already exist!");
		} else {
			this.edges[originIndex][destIndex] = true;
			this.weight[originIndex][destIndex] = weight;
		}
	}

	public void removeEdge(T origin, T dest) throws Exception {
		if (existsEdge(origin, dest)) {
			int originIndex = getNode(origin);
			int destIndex = getNode(dest);
			this.edges[originIndex][destIndex] = false;
			this.weight[originIndex][destIndex] = 0.0;
		} else {
			throw new Exception("Edge does not exist!");
		}
	}

	public boolean existsEdge(T origin, T dest) throws Exception {
		int originIndex = getNode(origin);
		int destIndex = getNode(dest);
		if (originIndex == -1 || destIndex == -1) {
			throw new IllegalArgumentException("One of the nodes does not exist!");
		}
		return edges[originIndex][destIndex];
	}

	public void addNode(T element) throws Exception {
		GraphNode<T> newNode = null;
		if (canAddNode(element)) {
			newNode = new GraphNode<T>(element);
			nodes.add(newNode);

			int newNodeIndex = nodes.indexOf(newNode);
			for (int i = 0; i < nodes.size(); i++) {
				edges[newNodeIndex][i] = false;
				edges[i][newNodeIndex] = false;
				weight[newNodeIndex][i] = 0.0;
				weight[i][newNodeIndex] = 0.0;
			}
		}
	}

	private boolean canAddNode(T element) throws Exception {
		if (nodes.size() >= this.size) {
			throw new IndexOutOfBoundsException(
					"The list is full. It can only have maximum of " + this.size + " nodes.");
		}
		if (this.getNode(element) != -1) {
			throw new Exception("Node has already been added!");
		}
		return true;
	}

	public int getNode(T element) {
		for (GraphNode<T> node : nodes) {
			if (element.equals(node.getElement())) {
				return nodes.indexOf(node);
			}
		}
		return -1;
	}

	public ArrayList<GraphNode<T>> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<GraphNode<T>> nodes) {
		this.nodes = nodes;
	}

	public boolean[][] getEdges() {
		return edges;
	}

	public void setEdges(boolean[][] edges) {
		this.edges = edges;
	}

	public double[][] getWeight() {
		return weight;
	}

	public void setWeight(double[][] weights) {
		this.weight = weights;
	}

	public int getSize() {
		return this.nodes.size();
	}

	public void print() {
		for (int k = 0; k < nodes.size(); k++) {
			nodes.get(k).print();
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(edges[i][j] + "(");
				System.out.print(weight[i][j] + ") ");
			}
			System.out.println();
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
