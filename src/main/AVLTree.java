package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an AVL tree.
 * 
 * @author mskrz
 *
 * @param <T> Data type of the element assigned to the tree's nodes
 */
public class AVLTree<T extends Comparable<T>> {

	private AVLNode<T> root;

	/**
	 * Method intersects two trees and return new tree with mutual elements.
	 * Algorithm traverse this tree with the pre-orser traversal and checks for
	 * mutual elements with the other tree.
	 * 
	 * @param otherTree an AVLTree that should be intersected with this tree.
	 * @return a new AVL tree formed by intersecting two trees
	 */
	public AVLTree<T> intersection(AVLTree<T> otherTree) {
		List<T> elementsOfOtherTree = getElementsFromTree(otherTree);
		AVLTree<T> newTree = getIntersectedTree(this, elementsOfOtherTree);
		return newTree;
	}

	private AVLTree<T> getIntersectedTree(AVLTree<T> avlTree, List<T> elementsOfOtherTree) {
		AVLTree<T> newTree = new AVLTree<T>();
		addNodeToNewTree(avlTree.getRoot(), newTree, elementsOfOtherTree);
		return newTree;
	}

	private void addNodeToNewTree(AVLNode<T> theRoot, AVLTree<T> newTree, List<T> elementsOfOtherTree) {
		if (theRoot != null) {
			if (elementsOfOtherTree.contains(theRoot.getElement())) {
				newTree.add(theRoot.getElement());
			}
			addNodeToNewTree(theRoot.getLeft(), newTree, elementsOfOtherTree);
			addNodeToNewTree(theRoot.getRight(), newTree, elementsOfOtherTree);
		}

	}

	private List<T> getElementsFromTree(AVLTree<T> tree) {
		ArrayList<T> elements = new ArrayList<>();
		AVLNode<T> nodeToAdd = tree.getRoot();
		addNodeToTheElementList(nodeToAdd, elements);
		return elements;
	}

	private void addNodeToTheElementList(AVLNode<T> theRoot, List<T> listOfElements) {
		if (theRoot != null) {
			listOfElements.add(theRoot.getElement());
			addNodeToTheElementList(theRoot.getLeft(), listOfElements);
			addNodeToTheElementList(theRoot.getRight(), listOfElements);
		}
	}

	/**
	 * Joins to trees together. Uses preorder traversal when adding nodes from the
	 * other tree.
	 * 
	 * @param treeToJoin an AVLTree we want to join this tree
	 * @return new tree formed of this tree and treeToJoin
	 */
	public AVLTree<T> join(AVLTree<T> treeToJoin) {
		AVLTree<T> newTree = new AVLTree<T>();
		List<AVLNode<T>> listOfNodesToAdd = new ArrayList<AVLNode<T>>();
		addNodesFromTreeToTheList(this, listOfNodesToAdd);
		addNodesFromTreeToTheList(treeToJoin, listOfNodesToAdd);
		for (AVLNode<T> node : listOfNodesToAdd) {
			newTree.add(node.getElement());
		}
		return newTree;
	}

	private void addNodesFromTreeToTheList(AVLTree<T> tree, List<AVLNode<T>> listOfNodes) {
		AVLNode<T> nodeToAdd = tree.getRoot();
		addNodesToTheList(listOfNodes, nodeToAdd);
	}

	private void addNodesToTheList(List<AVLNode<T>> listOfNodes, AVLNode<T> theRoot) {
		if (theRoot != null && !listConatinsNode(listOfNodes, theRoot)) {
			listOfNodes.add(theRoot);
			addNodesToTheList(listOfNodes, theRoot.getLeft());
			addNodesToTheList(listOfNodes, theRoot.getRight());
		}
	}

	private boolean listConatinsNode(List<AVLNode<T>> listOfNodes, AVLNode<T> theRoot) {
		ArrayList<T> elementsInList = new ArrayList<T>();
		for (AVLNode<T> node : listOfNodes) {
			elementsInList.add(node.getElement());
		}
		return elementsInList.contains(theRoot.getElement());
	}

	/**
	 * Returns consecutive nodes according to the inorder traversal of a tree
	 * 
	 * @return a String reflecting inorder traversal
	 */
	public String inOrderTraversal() {
		if (this.getRoot() == null) {
			return "";
		} else {
			return inOrderTraversal(getRoot());
		}
	}

	private String inOrderTraversal(AVLNode<T> theRoot) {
		if (theRoot != null)
			return (inOrderTraversal(theRoot.getLeft()) + theRoot.toString() + inOrderTraversal(theRoot.getRight()));
		else
			return ("-");
	}

	/**
	 * Returns consecutive nodes according to the postorder traversal of a tree
	 * 
	 * @return a String reflecting posteorder traversal
	 */
	public String postOrderTraversal() {
		if (this.getRoot() == null) {
			return "";
		} else {
			return postOrderTraversal(getRoot());
		}
	}

	private String postOrderTraversal(AVLNode<T> theRoot) {
		if (theRoot != null)
			return (postOrderTraversal(theRoot.getLeft()) + postOrderTraversal(theRoot.getRight())
					+ theRoot.toString());
		else
			return ("-");
	}

	/**
	 * Searches for a node with the specific element with an iterative way.
	 * 
	 * @param element Element representing a node.
	 * @return Boolean value depending on the outcome of the search operation.
	 */
	public boolean searchIterative(T element) {
		if (getRoot() == null) {
			return false;
		} else {
			AVLNode<T> nodeToCheck = this.getRoot();
			while (true) {
				if (element.compareTo(nodeToCheck.getElement()) < 0) {
					if (nodeToCheck.getLeft() == null) {
						return false;
					} else {
						nodeToCheck = nodeToCheck.getLeft();
					}
				} else if (element.compareTo(nodeToCheck.getElement()) > 0) {
					if (nodeToCheck.getRight() == null) {
						return false;
					} else {
						nodeToCheck = nodeToCheck.getRight();
					}
				} else if (element.compareTo(nodeToCheck.getElement()) == 0) {
					return true;
				}
			}
		}
	}

	/**
	 * Adds a new node to the tree with the specified node element in the iterative
	 * way.
	 * 
	 * @param element Element that represents an added node.
	 */
	public void addIterative(T element) {
		if (getRoot() == null) {
			setRoot(new AVLNode<T>(element));
		} else {
			AVLNode<T> nodeToCheck = this.getRoot();
			while (true) {
				if (element.compareTo(nodeToCheck.getElement()) < 0) {
					if (nodeToCheck.getLeft() == null) {
						nodeToCheck.setLeft(new AVLNode<T>(element));
						break;
					} else {
						nodeToCheck = nodeToCheck.getLeft();
					}
				} else if (element.compareTo(nodeToCheck.getElement()) > 0) {
					if (nodeToCheck.getRight() == null) {
						nodeToCheck.setRight(new AVLNode<T>(element));
						break;
					} else {
						nodeToCheck = nodeToCheck.getRight();
					}
				} else if (element.compareTo(nodeToCheck.getElement()) == 0) {
					throw new IllegalArgumentException("Node already exist!");
				}
			}
		}
	}

	public AVLNode<T> remove(AVLNode<T> theRoot, T element) {
		if (theRoot == null)
			throw new RuntimeException("element does not exist!");
		else if (element.compareTo(theRoot.getElement()) < 0)
			theRoot.setLeft(remove(theRoot.getLeft(), element));
		else if (element.compareTo(theRoot.getElement()) > 0)
			theRoot.setRight(remove(theRoot.getRight(), element));
		else {
			if (theRoot.getLeft() == null && theRoot.getRight() == null) {
				return null;
			} else if (theRoot.getRight() == null) {
				return theRoot.getLeft();
			} else if (theRoot.getLeft() == null) {
				return theRoot.getRight();
			} else {
				theRoot.setElement(getMax(theRoot.getLeft()));
				theRoot.setLeft(remove(theRoot.getLeft(), theRoot.getElement()));
			}
		}
		theRoot.updateHeight();
		return theRoot;
	}

	/**
	 * Recursive implementation of the algorithm searching for and returning node
	 * with maximum element
	 * 
	 * @param theRoot The root of the tree
	 * @return Maximum element of the tree
	 */
	public T getMax(AVLNode<T> theRoot) {
		if (theRoot == null) {
			return null;
		} else {
			return getMaxRec(theRoot);
		}
	}

	private T getMaxRec(AVLNode<T> theRoot) {
		if (theRoot.getRight() == null) {
			return theRoot.getElement();
		} else {
			return getMaxRec(theRoot.getRight());
		}
	}

	/**
	 * Iterative implementation of the algorithm searching for and returning node
	 * with maximum element
	 * 
	 * @param theRoot The root of the tree
	 * @return Maximum element of the tree
	 */
	public T getMax2(AVLNode<T> theRoot) {
		while (theRoot.getRight() != null) {
			theRoot = theRoot.getRight();
		}
		return theRoot.getElement();
	}

	/**
	 * Searches for a node with the specific element with a recursive way.
	 * 
	 * @param element Element representing a node.
	 * @return Boolean value depending on the outcome of the search operation.
	 */
	public boolean search(T element) {
		return search(getRoot(), element);
	}

	private boolean search(AVLNode<T> theRoot, T element) {
		if (theRoot == null) {
			return false;
		} else if (element.compareTo(theRoot.getElement()) == 0) {
			return true;
		} else if (element.compareTo(theRoot.getElement()) < 0) {
			return search(theRoot.getLeft(), element);
		} else if (element.compareTo(theRoot.getElement()) > 0) {
			return search(theRoot.getRight(), element);
		}

		return false;
	}

	/**
	 * Adds a new node to the tree with the specified node element. The operation is
	 * recursive.
	 * 
	 * @param element Element that represents an added node.
	 */
	public void add(T element) {
		setRoot(add(getRoot(), element));
	}

	private AVLNode<T> add(AVLNode<T> theRoot, T element) {
		if (theRoot == null) {
			return new AVLNode<T>(element);
		}

		if (element.compareTo(theRoot.getElement()) == 0) {
			throw new RuntimeException("element already exists!");
		} else if (element.compareTo(theRoot.getElement()) < 0) {
			theRoot.setLeft(add(theRoot.getLeft(), element));
		} else if (element.compareTo(theRoot.getElement()) > 0) {
			theRoot.setRight(add(theRoot.getRight(), element));
		}

		theRoot.updateHeight();
		return theRoot;
	}

	/**
	 * Sets the root node of the tree.
	 * 
	 * @param root Root that is assigned as a tree root
	 */
	public void setRoot(AVLNode<T> root) {
		this.root = root;
	}

	/**
	 * Gets the root node of the tree.
	 * 
	 * @return root Root that is assigned as a tree root
	 */
	public AVLNode<T> getRoot() {
		return root;
	}

	/**
	 * Returns consecutive nodes according to the preorder traversal of a tree
	 * 
	 * @return a String reflecting preorder traversal
	 */
	@Override
	public String toString() {
		return toString(this.root);
	}

	private String toString(AVLNode<T> theRoot) {
		if (theRoot != null)
			return (theRoot.toString() + toString(theRoot.getLeft()) + toString(theRoot.getRight()));
		else
			return ("-");
	}
}
