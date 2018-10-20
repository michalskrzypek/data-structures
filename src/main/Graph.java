package main;

import java.util.ArrayList;

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

	public Graph(int n) {
		size = n;
		edges = new boolean[n][n];
		weight = new double[n][n];
		nodes = new ArrayList<GraphNode<T>>(n);
		a = new double[n][n];
		p = new int[n][n];
		d = new double[1][n];

	}

	public void Dijkstra(T departureNode) {

	}

	private void initDijkstra(T departureNode) {
		int nodeIndex = getNode(departureNode);

		for (int j = 0; j < this.size; j++) {
			if (!this.edges[nodeIndex][j]) {
				this.d[0][j] = INFINITE;
			}
			if (nodeIndex == j) {
				this.d[0][nodeIndex] = INFINITE;
			}

			this.p[i][j] = EMPTY;
		}
	}

	public int[] getPD() {
		return pd;
	}

	public double[][] getD() {
		return d;
	}

	public String printFloydPath(T departure, T arrival) throws Exception {
		if (departure.equals(arrival)) {
			return departure.toString();
		}
		int depIndex = getNode(departure);
		int arrIndex = getNode(arrival);
		if (depIndex == -1 || arrIndex == -1) {
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
		return DFPrint(v);
	}

	private void resetVisited() {
		for (int i = 0; i < nodes.size(); i++)
			nodes.get(i).setVisited(false);
	}

	private String DFPrint(int v) {
		GraphNode<T> theNode = nodes.get(v);
		theNode.setVisited(true);
		String aux = theNode.getElement() + "-";
		for (int node = 0; node < nodes.size(); node++) {
			if (edges[v][node]) {
				if (!nodes.get(node).isVisited()) {
					aux = aux + DFPrint(node);
				}
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
