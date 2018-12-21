package main;

/**
 * Class represents a node of the hash table
 * @author Michal Skrzypek
 *
 * @param <T> data type of the element inside the hash node
 */
public class HashNode<T> {

	/**
	 * Numerical representation of the node status: "Empty". Applied to the node when it does not contain any element.
	 */
	public static final int EMPTY = 0;
	/**
	 * Numerical representation of the node status: "Valid". Applied to the node when it contains an element.
	 */
	public static final int VALID = 1;
	/**
	 * Numerical representation of the node status: "Deleted". Applied to the node when its element has been deleted and is open to contain any other element..
	 */
	public static final int DELETED = 2;
	
	private T element;
	private int status;
	
	/**
	 * Empty constructor. Initializes the status of the node as "Empty".
	 */
	public HashNode() {
		this.status = EMPTY;
	}
	
	/**
	 * Constructor cloning new HashNode of the already existing one.
	 * @param other Different hash node from which new hash node is being cloned.
	 */
	public HashNode(HashNode<T> other) {
		this();
		this.element = other.getElement();
		this.status = other.getStatus();
	}
	
	/**
	 * Returns the element inside of the hash node.
	 * @return element contained by the hash node.
	 */
	public T getElement() {
		return element;
	}
	
	/**
	 * Sets new element to the hash node.
	 * @param element the new element to be set.
	 */
	public void setElement(T element) {
		this.element = element;
	}
	
	/**
	 * Returns the status of the hash node.
	 * @return status representing the hash node.
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Sets another status to the hash node.
	 * @param status a status to be set as a representative of the hash node.
	 */
	public void setStatus(int status) {
		this.status = status;
	}
}
