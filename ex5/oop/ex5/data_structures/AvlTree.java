/**
 * 
 */
package oop.ex5.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author roigreenberg
 *
 */
public class AvlTree implements Iterable<Integer>{
	private Node root;
	private Node currentNode;
	private Node parentNode;
	
	private int size = 0;
	private static final int NOT_CONTAIN = -1;
	/**
	 * A default constructor
	 */
	public AvlTree(){
		
	}
	
	/**
	 * A data constructor -
	 * a constructor that builds the tree by adding the elements in the input array one by one.
	 * If the same value appears twice (or more) in the list, it is ignored
	 * 
	 * @param data values to add to tree
	 */
	public AvlTree(int[] data){
		for (int value: data)
			add(value);
	}
	
	/**
	 * A copy constructor - 
	 * a constructor that builds the tree a copy of an existing tree
	 * 
	 * @param tree an AvlTree
	 */
	public AvlTree(AvlTree tree) {
		for (int value: tree)
			add(value);
	}
	
	/**
	 * Add a new node with key newVAlue into the tree
	 * 
	 * @param newValue new value to add to the tree
	 * @return false iff newValue already exist in the tree
	 */
	public boolean add(int newValue){
		Node newNode = new Node(newValue);
		if (root == null) {
			root = newNode;
			size += 1;
			return true;
		}
		if (contains(newValue) == -1){
			size += 1;
			if (newNode.data < parentNode.data){
				if (parentNode.left == null){
					parentNode.left = newNode; 
				} 
			} else {
				if (parentNode.right == null){
					parentNode.right = newNode; 
				}
			}
			newNode.parent = parentNode;
			if (parentNode.height == 0){
				currentNode = parentNode;
				while (currentNode != null){
					currentNode.height = currentNode.setHeight();
					if (Math.abs(currentNode.balanceFactor()) > 1){
						balance(currentNode);
						break;
					}
					currentNode = currentNode.parent;
				}
			}
			return true;
		}
		// if the value already in the tree
		return false;
	}

	 
	 
	 
	 
	
	
	/**
	 * Does tree contain a given input value
	 * 
	 * @param searchVal value to search for
	 * @return if searchVal is found in the tree, return the deapth of the node
	 * (where 0 is the root) 
	 * Otherwise return -1
	 */
	public int contains(int searchVal){
		int depth =0;
		if (root == null) {
			return NOT_CONTAIN;
		}
		if (root.data == searchVal) {
			currentNode = root;
			return depth;
		}
		parentNode = root;
		if (parentNode.data > searchVal){
			currentNode = parentNode.left;
		} else {
			currentNode = parentNode.right;
		}
		while (currentNode !=null){
			depth +=1 ;
			if (currentNode.data == searchVal)
				return depth;
			
			parentNode = currentNode;
			if (currentNode.data > searchVal){
				currentNode = currentNode.left;
			} else {
				currentNode = currentNode.right;
			}
		}
		return NOT_CONTAIN;
	}
	
	/**
	 * Remove a node from the tree, if it exists
	 * 
	 * @param toDelete value to delete
	 * @return true iff toDelete is found and deleted
	 */
	public boolean delete(int toDelete){
		if (contains(toDelete) != -1){
			size -= 1;
			if (currentNode.left == null || currentNode.right == null)
				deleteFromAvl(currentNode);
			else {
				Node successor = findSuccessor(currentNode);
				currentNode.data = successor.data;
				deleteFromAvl(successor);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * doing the actual deletion of the node from the tree
	 * recalculate the heights after the deletion
	 * @param deleteNode - node to delete
	 */
	private void deleteFromAvl(Node deleteNode){
		parentNode = deleteNode.parent;
		if (parentNode != null){
			if (parentNode.left == deleteNode){
				if (deleteNode.left == null){
					parentNode.left = deleteNode.right;
				} else {
					parentNode.left = deleteNode.left;
				}
			} else if (deleteNode.left == null){
				parentNode.right = deleteNode.right;
			} else {
				parentNode.right = deleteNode.left;
			}
			calcHeights(parentNode);
		} else {
			root = null;
		}
		while (parentNode != null){
			 if (Math.abs(parentNode.balanceFactor()) > 1){
				 balance(parentNode);
			 }
			 parentNode = parentNode.parent;
		 }
	}
	
	
	/**
	 * 
	 * @return number of nodes in the tree
	 */
	public int size(){
		return size;
	}
	
	/**
	 * @return iterator to the Avl Tree. the return can pass over the tree nodes
	 * in ascending order
	 */
	public Iterator<Integer> iterator(){
		
		return new inOrderIteration();
		
	}
	
	/**
	 * an inner class to implement the iterator
	 * @author roigreenberg
	 *
	 */
	private class inOrderIteration implements Iterator<Integer>{
		Node curNode, nextNode;
		/**
		 * the constructor
		 * get the first value as the smallest value in tree
		 */
		private inOrderIteration(){
			nextNode = findSmallest();
		}
		/**
		 * @return true iff there still values in tree
		 */
		public boolean hasNext() {
			if (nextNode != null){
				return true;
			} else {
				return false;
			}
		}
		/**
		 * @return the next value in ascending order
		 * @throws NoSuchElementException when past the ast value
		 */
		public Integer next(){
			if (hasNext()){
				curNode = nextNode;
				nextNode = findSuccessor(nextNode);
				return curNode.data;
			} else {
				throw new NoSuchElementException();
			}
			
		}
		/**
		 * method not support
		 */
		public void remove(){
			throw new UnsupportedOperationException();
			
		}
	}
	
	/**
	 * look for the successor of the given node
	 * the successor is the smallest value bigger from the given node
	 * @param currentNode - the node to look for his successor
	 * @return the successor of the given node. null if the node is the maximum
	 */
	private Node findSuccessor (Node currentNode){
		if (currentNode.right != null){
			currentNode = currentNode.right;
			while (currentNode.left != null)
				currentNode = currentNode.left;
			return currentNode;
			
		} else {
			while ((currentNode.parent != null) && 
					(currentNode.data > currentNode.parent.data)){
				currentNode = currentNode.parent;
			}
			return currentNode.parent;
		}
	}
	
	/**
	 * 
	 * @return the smallest node in the tree
	 */
	private Node findSmallest(){
		currentNode = root;
		while (currentNode.left != null)
			currentNode = currentNode.left;
		return currentNode;
	}

	
	/**
	 * This method calculates the minimum number of nodes in an AVL tree of height h
	 * 
	 * @param h height of the tree (a non-negetive number)
	 * @return minimum number of nodes in the tree
	 */
	public static int findMinNodes(int h){
		if (h==0)
			return 1;
		if (h==1)
			return 2;
		return (1 + findMinNodes(h-1) + findMinNodes(h-2));
	}
	
	
	/**
	 * rebalance the tree using the rotation method
	 * at the end, calculate the heights of the node that might changed
	 * @param curNode the node that might unbalanced the tree
	 */
	private void balance (Node curNode){
		Node nodeA, nodeB, nodeC, nodeD;
		nodeA = curNode;
		nodeD = nodeA.parent;
		if (curNode.balanceFactor() == -2){
			if (curNode.right.balanceFactor() <= 0) {
				// RR case
				nodeB = nodeA.right;
				nodeC = nodeB.right;
				nodeA.right = nodeB.left;
				if (nodeA.right != null){
					nodeA.right.parent = nodeA;
				}
				nodeB.left = nodeA;
				nodeA.parent = nodeB;
				setNodes(nodeA, nodeB, nodeD);
				calcHeights(nodeA);
				calcHeights(nodeB.parent);
			} else {
				// RL case
				nodeB = nodeA.right;
				nodeC = nodeB.left;
				nodeB.left = nodeC.right;
				if (nodeB.left != null){
					nodeB.left.parent = nodeB;
				}
				nodeA.right = nodeC.left;
				if (nodeA.right != null){
					nodeA.right.parent = nodeA;
				}
				nodeC.right = nodeB;
				nodeB.parent = nodeC;
				nodeC.left = nodeA;
				nodeA.parent = nodeC;
				setNodes(nodeA, nodeC, nodeD);
				calcHeights(nodeA);
				calcHeights(nodeB);
			}
		} else {
			if (curNode.left.balanceFactor() >= 0) {
				// LL case
				nodeB = nodeA.left;
				nodeC = nodeB.left;
				nodeA.left = nodeB.right;
				if (nodeA.left != null){
					nodeA.left.parent = nodeA;
				}
				nodeB.right = nodeA;
				nodeA.parent = nodeB;
				setNodes(nodeA, nodeB, nodeD);
				calcHeights(nodeA);
				calcHeights(nodeB.parent);
			} else {
				// LR case
				nodeB = nodeA.left;
				nodeC = nodeB.right;
				nodeA.left = nodeC.right;
				if (nodeA.left != null){
					nodeA.left.parent = nodeA;
				}
				nodeB.right = nodeC.left;
				if (nodeB.right != null){
					nodeB.right.parent = nodeB;
				}
				nodeC.left = nodeB;
				nodeB.parent = nodeC;
				nodeC.right = nodeA;
				nodeA.parent = nodeC;
				setNodes(nodeA, nodeC, nodeD);
				calcHeights(nodeA);
				calcHeights(nodeB);
			}
		}
	}
	
	/**
	 * a continue for the balance method
	 * set the nodes that changed during the rebalance
	 * those change are for every rotation case.
	 * @param node1 - node receive from balance method
	 * @param node2 - node receive from balance method
	 * @param node3 - node receive from balance method
	 */
	private void setNodes(Node node1, Node node2, Node node3){
		if (node3 == null){
			this.root = node2;
			this.root.parent = null;
		} else {
			if (node3.right == node1){
				node3.right = node2;
			} else {
				node3.left = node2;
			}
		}
		node2.parent = node3;
	}
	
	/**
	 * recalculating the heights of the nodes that might have
	 *  changes after rebalance or deletion starting from given node
	 *  up to the unchanged node.
	 * @param node - the starting node that need recalculating the height
	 */
	private void calcHeights(Node node){
		boolean changed = true;
		int oldHeights;
		Node curNode = node;
		while ((curNode != null) && (changed)){
			oldHeights = curNode.height;
			curNode.height = curNode.setHeight();
			changed = (curNode.height != oldHeights);
			curNode = curNode.parent;
		}
	}
	
	/**
	 * static inner class that implement the nodes for the AVL Tree
	 * @author roigreenberg
	 *
	 */
	private static class Node {
		private Node left = null;
		private Node right = null;
		private Node parent = null;
		public int data;
		private int height = 0;
		
		/**
		 * A default constructor for empty node
		 */
		private Node() {

		}
		
		/**
		 * A constructor for node with data
		 * @param data - the data for the new node
		 */
		private Node(int data) {
			this.data = data;
		}
		
		/**
		 * calculate the balance factor
		 * negetive in case the right sub-tree is higher
		 * positive in case the left sub-tree is higher
		 * zero in case both sub-trees height are equal
		 * @return the balance factor of the node
		 */
		private int balanceFactor (){
			if ((this.left == null) && ( this.right == null)){
				return 0;
			}
			if (this.left == null){
				return -this.right.height - 1;
			} else if (this.right == null){ 
				return this.left.height + 1;
			} else {
				return this.left.height - this.right.height;
			}
		}
		
		/**
		 * calculate the height of the node from the height of the node sons
		 * @return the height of the node
		 */
		private int setHeight(){
			if ((this.left == null) && ( this.right == null)){
				return 0;
			} else if (this.left == null){
				return this.right.height + 1;
			} else if (this.right == null){
				return this.left.height + 1;
			} else {
				return Math.max(this.left.height, this.right.height) +1;
			}
			 
		}
	}
}
