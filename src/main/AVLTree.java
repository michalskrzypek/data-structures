package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an AVL tree.
 * 
 * @author Michal Skrzypek
 *
 * @param <T> Data type of the element assigned to the tree's nodes
 */
public class AVLTree<T extends Comparable<T>> {

	private AVLNode<T> root;
	private int height;

	/**
	 * Get the height of the tree starting in the root node.
	 * 
	 * @return an integer representing the height of the tree.
	 */
	public int getHeight() {
		height = calculateHeight(getRoot());
		return height;
	}

	/**
	 * Recursive algorithm for calculating the height of the tree.
	 * 
	 * @param root every next node in the recursion
	 * @return height of the root node.
	 */
	private int calculateHeight(AVLNode<T> root) {
		if (root == null) {
			return 0;
		} else if (root.getLeft() == null && root.getRight() == null) {
			return 1;
		} else if (root.getLeft() == null) {
			return 1 + calculateHeight(root.getRight());
		} else if (root.getRight() == null) {
			return 1 + calculateHeight(root.getLeft());
		} else {
			return 1 + (Math.max(calculateHeight(root.getLeft()), calculateHeight(root.getRight())));
		}
	}

	/**
	 * Reorganizes the tree if the balance factor at any node is incorrect (when |BF| > 1).
	 * 
	 * @param theRoot the node the algorithm is applied to.
	 * @return the node with updated height.
	 */
	public AVLNode<T> updateBF(AVLNode<T> theRoot) {
		if (theRoot.getBF() == -2) {// left rotation
			if (theRoot.getLeft().getBF() > 0) {
				theRoot = doubleLeftRotation(theRoot);
			} else {
				theRoot = singleLeftRotation(theRoot);
			}
		} else if (theRoot.getBF() == 2) {// right rotation
			if (theRoot.getRight().getBF() < 0) {
				theRoot = doubleRightRotation(theRoot);
			} else {
				theRoot = singleRightRotation(theRoot);
			}
		}
		theRoot.updateHeight();
		return theRoot;
	}

	/**
	 * Performs single left rotation on a tree with the given node.
	 * 
	 * @param the node with the incorrect BF
	 * @return an updated node.
	 */
	private AVLNode<T> singleLeftRotation(AVLNode<T> b) {
		AVLNode<T> left = b.getLeft();
		AVLNode<T> rightChildOfLeft = b.getLeft().getRight();
		left.setRight(b);
		b.setLeft(rightChildOfLeft);
		b.updateHeight();
		return left;
	}

	/**
	 * Performs single right rotation on a tree with the given node.
	 * 
	 * @param the node with the incorrect BF
	 * @return an updated node
	 */
	private AVLNode<T> singleRightRotation(AVLNode<T> b) {
		AVLNode<T> right = b.getRight();
		AVLNode<T> leftChildOfRight = b.getRight().getLeft();
		right.setLeft(b);
		b.setRight(leftChildOfRight);
		b.updateHeight();
		return right;
	}

	/**
	 * Performs double left rotation on a tree for the given node.
	 * 
	 * @param the node where the problem occurred (where BF was incorrect)
	 * @return replaced node
	 */
	private AVLNode<T> doubleLeftRotation(AVLNode<T> b) {
		AVLNode<T> left = b.getLeft();
		AVLNode<T> rightChildOfLeft = b.getLeft().getRight();
		AVLNode<T> ii = b.getLeft().getRight().getLeft();
		AVLNode<T> iii = b.getLeft().getRight().getRight();

		rightChildOfLeft.setLeft(b);
		rightChildOfLeft.setRight(left);
		left.setRight(ii);
		b.setLeft(iii);

		b.updateHeight();
		left.updateHeight();

		return rightChildOfLeft;
	}

	/**
	 * Performs double right rotation on a tree with the given node.
	 * 
	 * @param the node where the problem occurred (where BF was incorrect)
	 * @return replaced node
	 */
	private AVLNode<T> doubleRightRotation(AVLNode<T> b) {
		AVLNode<T> right = b.getRight();
		AVLNode<T> leftChildOfright = b.getRight().getLeft();
		AVLNode<T> ii = b.getRight().getLeft().getRight();
		AVLNode<T> iii = b.getRight().getLeft().getLeft();

		leftChildOfright.setLeft(b);
		leftChildOfright.setRight(right);
		right.setLeft(ii);
		b.setRight(iii);

		b.updateHeight();
		right.updateHeight();

		return leftChildOfright;
	}

	/**
	 * Method intersects two trees and returns new tree with mutual elements.
	 * Algorithm traverse this tree with the pre-order traversal and checks for
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
	
	/**
	 * Return all elements contained in a tree (accessing the nodes with the preorder traversal)
	 * @param tree a tree from which the elements are taken
	 * @return a list with the elements
	 */
	private List<T> getElementsFromTree(AVLTree<T> tree) {
		ArrayList<T> elements = new ArrayList<>();
		AVLNode<T> nodeToAdd = tree.getRoot();
		addNodeToTheElementList(nodeToAdd, elements);
		return elements;
	}

	/**
	 * Adds element of the node to the list 
	 * @param theRoot a node from which the element is taken
	 * @param listOfElements a list to which elements are added
	 */
	private void addNodeToTheElementList(AVLNode<T> theRoot, List<T> listOfElements) {
		if (theRoot != null) {
			listOfElements.add(theRoot.getElement());
			addNodeToTheElementList(theRoot.getLeft(), listOfElements);
			addNodeToTheElementList(theRoot.getRight(), listOfElements);
		}
	}

	/**
	 * Returns resulting tree which is the outcome of the intersecting operation
	 * @param avlTree a tree to be be intersected with other tree
	 * @param elementsOfOtherTree elements of other tree
	 * @return new intersected tree
	 */
	private AVLTree<T> getIntersectedTree(AVLTree<T> avlTree, List<T> elementsOfOtherTree) {
		AVLTree<T> newTree = new AVLTree<T>();
		addNodeToNewTree(avlTree.getRoot(), newTree, elementsOfOtherTree);
		return newTree;
	}
	
	/**
	 * Recursively adds consecutive nodes to the list
	 * @param theRoot the node being added
	 * @param newTree new tree formed of intersected nodes 
	 * @param elementsOfOtherTree elements of the other tree
	 */
	private void addNodeToNewTree(AVLNode<T> theRoot, AVLTree<T> newTree, List<T> elementsOfOtherTree) {
		if (theRoot != null) {
			if (elementsOfOtherTree.contains(theRoot.getElement())) {
				newTree.add(theRoot.getElement());
			}
			addNodeToNewTree(theRoot.getLeft(), newTree, elementsOfOtherTree);
			addNodeToNewTree(theRoot.getRight(), newTree, elementsOfOtherTree);
		}
	}

	/**
	 * Joins two trees together. Uses preorder traversal when adding nodes from the
	 * other tree.
	 * 
	 * @param treeToJoin an AVLTree we want to join to this tree
	 * @return new tree formed of this tree and treeToJoin
	 */
	public AVLTree<T> joins(AVLTree<T> treeToJoin) {
		AVLTree<T> newTree = new AVLTree<T>();
		List<AVLNode<T>> listOfNodesToAdd = new ArrayList<AVLNode<T>>();
		addNodesFromTreeToTheList(this, listOfNodesToAdd);
		addNodesFromTreeToTheList(treeToJoin, listOfNodesToAdd);
		for (AVLNode<T> node : listOfNodesToAdd) {
			newTree.add(node.getElement());
		}
		return newTree;
	}

	/**
	 * Gets all the nodes from the tree and adds them recursively to the list
	 * @param tree the tree from which the nodes are obtained
	 * @param listOfNodes the list to which the nodes are added
	 */
	private void addNodesFromTreeToTheList(AVLTree<T> tree, List<AVLNode<T>> listOfNodes) {
//		AVLNode<T> nodeToAdd = tree.getRoot();
		addNodesToTheList(listOfNodes, tree.getRoot());
	}

	/**
	 * Adds recursively nodes from the tree (according to the preorder traversal) to the provided list
	 * @param listOfNodes the list to which the nodes are added
	 * @param theRoot current node that is being added to the list
	 */
	private void addNodesToTheList(List<AVLNode<T>> listOfNodes, AVLNode<T> theRoot) {
		if (theRoot != null) {
			if (!listConatinsNode(listOfNodes, theRoot)) {
				listOfNodes.add(theRoot);
			}
			addNodesToTheList(listOfNodes, theRoot.getLeft());
			addNodesToTheList(listOfNodes, theRoot.getRight());
		}
	}

	/**
	 * Checks whether the list (to which we add joined nodes) already contains the given node with a specific element
	 * @param listOfNodes the list to which the nodes are added
	 * @param theRoot current node that is being added to the list
	 * @return a boolean value representing outcome of the operation
	 */
	private boolean listConatinsNode(List<AVLNode<T>> listOfNodes, AVLNode<T> theRoot) {
		ArrayList<T> elementsInList = new ArrayList<T>();
		for (AVLNode<T> node : listOfNodes) {
			elementsInList.add(node.getElement());
		}
		return elementsInList.contains(theRoot.getElement());
	}

	/**
	 * Returns String object representing consecutive nodes according to the in order traversal of a tree
	 * 
	 * @return a String reflecting in order traversal of a tree
	 */
	public String inOrderTraversal() {
		if (this.getRoot() == null) {
			return "";
		} else {
			return inOrderTraversal(getRoot());
		}
	}

	/**
	 * Performs in order traversal 
	 * @param theRoot currently examining node
	 * @return a full textual representation of a tree (according to the in order traversal)
	 */
	private String inOrderTraversal(AVLNode<T> theRoot) {
		if (theRoot != null)
			return (inOrderTraversal(theRoot.getLeft()) + theRoot.toString() + inOrderTraversal(theRoot.getRight()));
		else
			return ("-");
	}

	/**
	 * Returns String object representing consecutive nodes according to the post order traversal of a tree
	 * 
	 * @return a String reflecting post order traversal of a tree
	 */
	public String postOrderTraversal() {
		if (this.getRoot() == null) {
			return "";
		} else {
			return postOrderTraversal(getRoot());
		}
	}

	/**
	 * Performs post order traversal 
	 * @param theRoot currently examining node
	 * @return a full textual representation of a tree (according to the post order traversal)
	 */
	private String postOrderTraversal(AVLNode<T> theRoot) {
		if (theRoot != null)
			return (postOrderTraversal(theRoot.getLeft()) + postOrderTraversal(theRoot.getRight())
					+ theRoot.toString());
		else
			return ("-");
	}

	/**
	 * Searches for a node with the specific element in an iterative way.
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
	 * Adds a new node to the tree with the specified element in the iterative way.
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
			
//			updateBF(nodeToCheck);
		}
	}

	/**
	 * Deletes the node from the tree with the specified element and sets new root if applicable
	 * @param element the element of the node to be removed
	 */
	public void remove(T element) {
		setRoot(remove(getRoot(), element));
	}

	/**
	 * Deletes the node from the tree with the specified element 
	 * @param element the element of the node to be removed
	 */
	private AVLNode<T> remove(AVLNode<T> theRoot, T element) {
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
		return (updateBF(theRoot));
//		theRoot.updateHeight();
//		return theRoot;
	}

	/**
	 * Recursive implementation of the algorithm searching for and returning node
	 * with maximum element
	 * 
	 * @param theRoot The root node from which the recursion starts 
	 * @return Maximum element of the subtree
	 */
	public T getMax(AVLNode<T> theRoot) {
		if (theRoot == null) {
			return null;
		} else {
			return getMaxRec(theRoot);
		}
	}

	/**
	 * Recursive version to obtain the maximum node
	 * @param theRoot the root node from which the recursion starts
	 * @return the maximum element of the subtree
	 */
	private T getMaxRec(AVLNode<T> theRoot) {
		if (theRoot.getRight() == null) {
			return theRoot.getElement();
		} else {
			return getMaxRec(theRoot.getRight());
		}
	}

	/**
	 * Iterative implementation of the algorithm searching for and returning node
	 * with a maximum element
	 * 
	 * @param theRoot The node from which the iteration starts 
	 * @return Maximum element of the subtree
	 */
	public T getMax2(AVLNode<T> theRoot) {
		while (theRoot.getRight() != null) {
			theRoot = theRoot.getRight();
		}
		return theRoot.getElement();
	}

	/**
	 * Searches for a node with the specific element.
	 * 
	 * @param element Element representing a node.
	 * @return Boolean value depending on the outcome of the search operation.
	 */
	public boolean search(T element) {
		return search(getRoot(), element);
	}

	/**
	 * Searches for the node with the specified element in the tree  in a recursive way.
	 * @param theRoot the node currently examined
	 * @param element element of the node to be found
	 * @return a boolean value for the found node
	 */
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
	 * Adds a new node to the tree with the specified node element.
	 * 
	 * @param element Element that represents the added node.
	 */
	public void add(T element) {
		setRoot(add(getRoot(), element));
	}

	/**
	 * Recursive implementation of the adding algorithm.
	 * @param theRoot the node to which we compare the element 
	 * @param element element to be inserted as the node
	 * @return node to which the element was inserted
	 */
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

		return (updateBF(theRoot));
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
	 * @return a String object reflecting preorder traversal
	 */
	@Override
	public String toString() {
		return toString(this.root);
	}

	/**
	 * Tree represented in the preorder traversal.
	 * @param theRoot starting node of the traversal
	 * @return string object representing a tree
	 */
	private String toString(AVLNode<T> theRoot) {
		if (theRoot != null)
			return (theRoot.toString() + toString(theRoot.getLeft()) + toString(theRoot.getRight()));
		else
			return ("-");
	}
}
