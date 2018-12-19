package main;

/**
 * Class representing a node in the AVL tree
 * 
 * @author Michal Skrzypek
 *
 * @param <T> Generic parameter representing node element
 */
public class AVLNode<T extends Comparable<T>> {

	/**
	 * element(code) of the node
	 */
	private T element;
	private AVLNode<T> left;
	private AVLNode<T> right;
	private int height; // Balance Factor

	/**
	 * Constructor creating an AVL node with a specific element
	 * 
	 * @param element Represents a node and is used for nodes comparison
	 */
	public AVLNode(T element) {
		this.setElement(element);
	}

	/**
	 * Constructor creating an AVL node with a specific element and child nodes
	 * 
	 * @param element Represents a node and is used for nodes comparison
	 * @param left    A left child of a node
	 * @param right   A right child of a node
	 */
	public AVLNode(T element, AVLNode<T> left, AVLNode<T> right) {
		this.setElement(element);
		this.setLeft(left);
		this.setRight(right);
	}
	
	/**
	 * Calculates and returns the Balance Factor of the node.
	 * @return a BF value
	 */
	public int getBF() {
		int rightHeight = -1;
		int leftHeight = -1;
		if(right != null) {
//			right.updateHeight();
			rightHeight = right.getHeight();
		}
		if(left != null) {
//			left.updateHeight();
			leftHeight = left.getHeight();
		}
		return rightHeight - leftHeight;
	}
	
	/**
	 * Sets the updated height of the node
	 */
	public void updateHeight() {
		this.height = calculateHeight();
	}
	
	/**
	 * Calculates height of the node recursively
	 * @return new height
	 */
	private int calculateHeight() {
		if (this.getLeft() == null && this.getRight() == null) {
			return 0;
		} else if(this.getLeft() == null){
			return 1 + this.getRight().calculateHeight(); 
		} else if(this.getRight() == null) {
			return 1 + this.getLeft().calculateHeight(); 
		} else {
			return 1 +(Math.max(this.getLeft().calculateHeight(), this.getRight().calculateHeight()));
		}
	}

	/**
	 * Returns the height of the node
	 * @return the height of the node
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets a new height to the node
	 * @param height height to be set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Prints a representation of a node
	 */
	public void print() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return this.getElement().toString() + "(" + getBF() + ")";
	}
/*	@Override
	public String toString() {
		return this.getElement().toString() + "(" + getHeight() + ")";
	}
*/
	/**
	 * Gets element of a node
	 * 
	 * @return Element of a node
	 */
	public T getElement() {
		return element;
	}

	/**
	 * Sets the specific element to the node.
	 * 
	 * @param element Element to be assigned to the node.
	 */
	public void setElement(T element) {
		this.element = element;
	}

	/**
	 * Gets left child of the node
	 * 
	 * @return Node that is the left child of the node
	 */
	public AVLNode<T> getLeft() {
		return left;
	}

	/**
	 * Sets left child of the node
	 * 
	 * @param left Node that is the left child of the node
	 */
	public void setLeft(AVLNode<T> left) {
		this.left = left;
	}

	/**
	 * Gets right child of the node
	 * 
	 * @return Node that is the tight child of the node
	 */
	public AVLNode<T> getRight() {
		return right;
	}

	/**
	 * Sets right child of the node
	 * 
	 * @return Node that is the right child of the node
	 */
	public void setRight(AVLNode<T> right) {
		this.right = right;
	}
}
