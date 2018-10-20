package main;

public class GraphNode<T> {

	private T element;
	private boolean visited;
	
	public GraphNode(T element) {
		this.setElement(element);
		this.setVisited(false);
	}
	
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public void print() {
		System.out.println(this.toString());
	}
	
	@Override
	public String toString() {
		return "GN(N:" + this.element + "/V:" + this.visited + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getElement().equals(((GraphNode<T>) obj).getElement());
//		return this.toString().equals(obj.toString());
	}
	
}
