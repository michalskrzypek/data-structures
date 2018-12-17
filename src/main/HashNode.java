package main;

public class HashNode<T> {

	public static final int EMPTY = 0;
	public static final int VALID = 1;
	public static final int DELETED = 2;
	
	private T element;
	private int status;
	
	public HashNode() {
		this.status = EMPTY;
	}
	
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
