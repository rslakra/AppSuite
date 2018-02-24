/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.algorithms.tree;

/**
 * Three Algorithms.
 *
 * @author Rohtash Singh Lakra
 * @date 07/03/2017 04:12:06 PM
 */
public class Trees {

	private static final boolean useRecursion = false;

	static class Node<T> {

		private T data;
		private Node<T> leftChild;
		private Node<T> rightChild;

		/**
		 * 
		 * @param data
		 */
		public Node(T data) {
			this.data = data;
			leftChild = null;
			rightChild = null;
		}

		/**
		 * Returns the data.
		 * 
		 * @return
		 */
		public T getData() {
			return data;
		}

		/**
		 * The data to be set.
		 * 
		 * @param data
		 */
		public void setData(T data) {
			this.data = data;
		}

		/**
		 * Returns the leftChild.
		 * 
		 * @return
		 */
		public Node<T> getLeftChild() {
			return leftChild;
		}

		/**
		 * The leftChild to be set.
		 * 
		 * @param leftChild
		 */
		public void setLeftChild(Node<T> leftChild) {
			this.leftChild = leftChild;
		}

		/**
		 * 
		 * @return
		 */
		public boolean hasLeftChild() {
			return (this.leftChild != null);
		}

		/**
		 * Returns the rightChild.
		 * 
		 * @return
		 */
		public Node<T> getRightChild() {
			return rightChild;
		}

		/**
		 * The rightChild to be set.
		 * 
		 * @param rightChild
		 */
		public void setRightChild(Node<T> rightChild) {
			this.rightChild = rightChild;
		}

		/**
		 * 
		 * @return
		 */
		public boolean hasRightChild() {
			return (this.rightChild != null);
		}

		/**
		 * 
		 * @param rootNode
		 * @return
		 */
		public String leftNodeTraversal(Node<T> rootNode) {
			StringBuilder nodeBuilder = new StringBuilder();
			if (rootNode != null) {
				nodeBuilder.append(rootNode.getData());
				// traversal only left tree.
				if (useRecursion) {
					if (rootNode.hasLeftChild()) {
						// recursion
						nodeBuilder.append(" ").append(rootNode.getLeftChild().getData());
					}
				} else {
					Node<T> tempNode = rootNode.getLeftChild();
					while (tempNode != null) {
						// append left node
						nodeBuilder.append(" ");
						nodeBuilder.append(tempNode.getData());
						tempNode = tempNode.getLeftChild();
					}
				}
			}

			return nodeBuilder.toString();
		}

		/**
		 * 
		 * @param rootNode
		 * @return
		 */
		public String rightgNodeTraversal(Node<T> rootNode) {
			StringBuilder nodeBuilder = new StringBuilder();
			if (rootNode != null) {
				nodeBuilder.append(rootNode.getData());
				// traversal only right tree.
				if (useRecursion) {
					if (rootNode.hasRightChild()) {
						// recursion
						nodeBuilder.append(" ").append(rootNode.getRightChild().getData());
					}
				} else {
					Node<T> tempNode = rootNode.getRightChild();
					while (tempNode != null) {
						// append left node
						nodeBuilder.append(" ");
						nodeBuilder.append(tempNode.getData());
						tempNode = tempNode.getRightChild();
					}
				}
			}

			return nodeBuilder.toString();
		}

		/**
		 * Returns the string representation of this object.
		 * 
		 * Pre-Order Traversal
		 * 
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			StringBuilder nodeBuilder = new StringBuilder();
			if (getData() != null) {
				nodeBuilder.append(getData());

				// append left node
				if (hasLeftChild()) {
					nodeBuilder.append(" ");
					nodeBuilder.append(leftNodeTraversal(getLeftChild()));
				}

				// append right node.
				if (hasRightChild()) {
					nodeBuilder.append(" ");
					nodeBuilder.append(rightgNodeTraversal(getRightChild()));
				}
			}

			return nodeBuilder.toString();
		}
	}

	static enum TraversalMode {
		PRE_ORDER_TRAVERSAL, IN_ORDER_TRAVERSAL, POST_ORDER_TRAVERSAL;
	}

	static class IntBinaryTree {
		Node<Integer> rootNode;

		/**
		 * Adds the node as the child of the parent node. the position.
		 * 
		 * @param parentNode
		 * @param currentNode
		 */
		protected void insertNode(Node<Integer> parentNode, Node<Integer> currentNode, boolean isLeft) {
			if (isLeft) {
				parentNode.setLeftChild(currentNode);
			} else {
				parentNode.setRightChild(currentNode);
			}
		}

		/**
		 * Inserts an element in a tree/create a tree.
		 * 
		 * 
		 * @param parentNode
		 * @param newNode
		 */
		public void insertNode(int data) {
			Node<Integer> tempNode = new Node<Integer>(data);
			if (rootNode == null) {
				rootNode = tempNode;
			} else {
				Node<Integer> parentNode = rootNode;
				Node<Integer> currentNode = rootNode;
				if (currentNode.getData() != data) {
					while (currentNode != null) {
						if (data < currentNode.getData()) {
							// goto left subtree
							currentNode = currentNode.getLeftChild();
							// add new node
							if (currentNode == null) {
								insertNode(parentNode, tempNode, true);
							}
						} else if (data > currentNode.getData()) {
							// goto right subtree
							currentNode = currentNode.getRightChild();
							// add new node
							if (currentNode == null) {
								insertNode(parentNode, tempNode, false);
							}
						}
					}
				}
			}
		}

		/**
		 * Searches an element in a tree.
		 * 
		 * @param findWhat
		 */
		public Node<Integer> searchNode(Integer findWhat) {
			Node<Integer> currentNode = rootNode;
			if (rootNode.getData() != findWhat) {
				while (currentNode != null) {
					if (findWhat < currentNode.getData()) {
						// goto left subtree
						currentNode = currentNode.getLeftChild();
					} else if (findWhat > currentNode.getData()) {
						// goto right subtree
						currentNode = currentNode.getRightChild();
					}
				}
			}

			return currentNode;
		}

		/**
		 * 
		 * @param tMode
		 */
		public void nodeTraversal(TraversalMode tMode) {
			switch (tMode) {
			case PRE_ORDER_TRAVERSAL:
				preOrderTraversal();
				break;
			case IN_ORDER_TRAVERSAL:
				inOrderTraversal();
				break;
			case POST_ORDER_TRAVERSAL:
				postOrderTraversal();
				break;
			default:
				System.out.println("Invalid Traversal!");
				break;
			}
		}

		/**
		 * Traverses a tree in a pre-order manner. Until all nodes are traversed
		 * − Step 1 − Visit root node. Step 2 − Recursively traverse left
		 * subtree. Step 3 − Recursively traverse right subtree.
		 */
		public void preOrderTraversal() {

		}

		/**
		 * Traverses a tree in an in-order manner. Until all nodes are traversed
		 * − Step 1 − Recursively traverse left subtree. Step 2 − Visit root
		 * node. Step 3 − Recursively traverse right subtree.
		 */
		public void inOrderTraversal() {

		}

		/**
		 * Traverses a tree in a post-order manner.
		 * 
		 * Until all nodes are traversed − Step 1 − Recursively traverse left
		 * subtree. Step 2 − Recursively traverse right subtree. Step 3 − Visit
		 * root node.
		 */
		public void postOrderTraversal() {

		}

		/**
		 * Removes the node of the tree.
		 * 
		 * @param node
		 */
		public void removeNode(Node<Integer> node) {

		}

		/**
		 * 
		 */
		public String toString() {
			return "[" + (rootNode == null ? "" : rootNode.toString()) + "]";
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		IntBinaryTree bTree = new IntBinaryTree();
		bTree.insertNode(8);
		bTree.insertNode(10);
		bTree.insertNode(11);
		System.out.println(bTree);
		Node<Integer> nodeFound = bTree.searchNode(8);
		System.out.println(nodeFound);

		bTree.preOrderTraversal();
		bTree.inOrderTraversal();
		bTree.postOrderTraversal();
	}

}
