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
package com.rslakra.algorithms.linkedlist;

/**
 * 
 * @author Rohtash Singh Lakra
 * @date 11/09/2017 06:54:35 AM
 */
public class LinkedList {

	private Node head;

	class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
			next = null;
		}
	}

	/**
	 * Returns true if the head is null;
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return (head == null);
	}

	/**
	 * 
	 * @param node
	 * @param data
	 * @return
	 */
	Node linkFirst(Node node, int data) {

		if (node == null) {
			node = new Node(data);
		} else {
			node.next = linkLast(node.next, data);
		}

		return node;
	}

	/**
	 * Inserts the node at the beginning of the list.
	 * 
	 * @param data
	 */
	public void push(int data) {
		Node newNode = new Node(data);
		newNode.next = head;
		head = newNode;
	}

	/**
	 * 
	 * @param node
	 * @param data
	 * @return
	 */
	Node linkLast(Node node, int data) {
		if (node == null) {
			node = new Node(data);
		} else {
			node.next = linkLast(node.next, data);
		}

		return node;
	}

	/**
	 * Adds a node at the of the linked list.
	 * 
	 * @param data
	 */
	public void addLast(int data) {
		head = linkLast(head, data);
	}

	/**
	 * Returns the count from the given node.
	 * 
	 * @param node
	 * @return
	 */
	public int getCount(Node node) {
		if (node == null) {
			return 0;
		} else {
			return (1 + getCount(node.next));
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getCount() {
		int count = 0;
		boolean useRecursion = true;
		if (useRecursion) {
			count = getCount(head);
		} else {
			Node tmp = head;
			while (tmp != null) {
				count++;
				tmp = tmp.next;
			}
		}
		return count;
	}

	/**
	 * Deletes the first node.
	 */
	public void deleteFirst() {
		if (head != null) {
			head = head.next;
		}
	}

	/**
	 * Returns the previous node.
	 * 
	 * @param key
	 * @return
	 */
	private Node findParentNode(int key) {
		Node prevNode = null;
		Node temp = head;
		while (temp != null) {
			if (temp.data == key) {
				break;
			}
			prevNode = temp;
			temp = temp.next;
		}

		return prevNode;
	}

	/**
	 * Swaps the left and right nodes.
	 * 
	 * @param leftNode
	 * @param rightNode
	 */
	public void swapNode(Node leftNode, Node rightNode) {
		if (leftNode != null && rightNode != null) {
			Node temp = leftNode.next;
			leftNode.next = rightNode.next;
			rightNode.next = temp;
		}
	}

	/**
	 * Swap nodes for two given keys. Nodes should be swapped by changing links.
	 * It has following cases to be handled. 1) x and y may or may not be
	 * adjacent. 2) Either x or y may be a head node. 3) Either x or y may be
	 * last node. 4) x and/or y may not be present in linked list.
	 * 
	 * @param leftKey
	 * @param rightKey
	 */
	public void swapNodes(int leftKey, int rightKey) {
		if (head != null && leftKey != rightKey) {
			// find left node and it's parent
			Node leftNodeParent = null;
			Node leftNode = head;
			while (leftNode != null && leftNode.data != leftKey) {
				leftNodeParent = leftNode;
				leftNode = leftNode.next;
			}

			// find left node and it's parent
			Node rightNodeParent = null;
			Node rightNode = head;
			while (rightNode != null && rightNode.data != rightKey) {
				rightNodeParent = rightNode;
				rightNode = rightNode.next;
			}

			if (leftNode != null && rightNode != null) {
				// if left node parent is null
				if (leftNodeParent == null) {
					head = rightNode;
				} else {
					leftNodeParent.next = rightNode;
				}

				// if right node parent is null
				if (rightNodeParent == null) {
					head = leftNode;
				} else {
					rightNodeParent.next = leftNode;
				}

				Node temp = leftNode.next;
				leftNode.next = rightNode.next;
				rightNode.next = temp;
			}
		}
	}

	/**
	 * Reverses the linked list by changing the links between nodes.
	 */
	public void reverse() {
		Node previous = null;
		Node current = head;
		Node next = null;
		// change next to previous, previous to current and current to next.
		while (current != null) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		head = previous;
	}

	/**
	 * 
	 * @param previous
	 * @param current
	 */
	public Node reverseRecursively(Node previous, Node current) {
		if (current.next == null) {
			head = current;
			current.next = previous;
			return null;
		}

		Node next = current.next;
		current.next = previous;
		reverseRecursively(current, next);
		return head;
	}

	/**
	 * 
	 * @param previous
	 * @param current
	 */
	public void reverseRecursively() {
		head = reverseRecursively(null, head);
	}

	/**
	 * Write a SortedMerge() function that takes two lists, each of which is
	 * sorted in increasing order, and merges the two together into one list
	 * which is in increasing order. SortedMerge() should return the new list.
	 * The new list should be made by splicing together the nodes of the first
	 * two lists.
	 * 
	 * For example if the first linked list a is 5->10->15 and the other linked
	 * list b is 2->3->20, then SortedMerge() should return a pointer to the
	 * head node of the merged list 2->3->5->10->15->20.
	 * 
	 * There are many cases to deal with: either ‘a’ or ‘b’ may be empty, during
	 * processing either ‘a’ or ‘b’ may run out first, and finally there’s the
	 * problem of starting the result list empty, and building it up while going
	 * through ‘a’ and ‘b’.
	 * 
	 * @param listFirst
	 * @param listSecond
	 */
	public static LinkedList sortedMerge(LinkedList linkedList1, LinkedList linkedList2) {
		LinkedList linkListMerged = null;
		// both linked lists are null, return null
		if ((linkedList1 == null || linkedList1.isEmpty()) && (linkedList2 == null || linkedList2.isEmpty())) {
			return linkListMerged;
		}

		// If first linked list is null, return second linked list.
		if (linkedList1 == null || linkedList1.isEmpty()) {
			return linkedList2;
		}

		// if 2nd linked list is null, return 1st linked list.
		if (linkedList2 == null || linkedList2.isEmpty()) {
			return linkedList1;
		}

		linkListMerged = new LinkedList();
		Node temp1 = linkedList1.head;
		Node temp2 = linkedList1.head;
		Node nextNode = linkListMerged.head;
		while (temp1 != null && temp2 != null) {
			// fist find the smaller node.
			Node newNode = (temp1.data < temp2.data ? temp1 : temp2);
			if (nextNode == null) {
				linkListMerged.head = newNode;
				nextNode = linkListMerged.head;
				nextNode.next = (nextNode == temp1 ? temp2 : temp1);
				nextNode = nextNode.next;
				temp1 = temp1.next;
				temp2 = temp2.next;
			} else {
				// nextNode.next = newNode;
				nextNode.next = null;
				break;
			}
		}

		return linkListMerged;
	}

	/**
	 * Prints the list.
	 */
	public void printList() {
		Node tmp = head;
		StringBuilder sBuilder = new StringBuilder();
		while (tmp != null) {
			if (tmp == head) {
				sBuilder.append(tmp.data);
			} else {
				sBuilder.append(" ").append(tmp.data);
			}
			tmp = tmp.next;
		}

		System.out.println(sBuilder.toString());
	}

	public static void main(String[] args) {
		/* Start with the empty list */
		LinkedList linkList = new LinkedList();
		linkList.push(1);
		linkList.push(2);
		linkList.push(3);
		linkList.push(4);
		linkList.push(5);
		linkList.push(6);
		linkList.printList();

		// linkList.deleteFirst();
		// linkList.deleteFirst();
		// linkList.printList();
		// System.out.println("Nodes Count:" + linkList.getCount());

		// linkList.swapNodes(1, 5);
		// linkList.reverse();
		// linkList.reverseRecursively();
		// linkList.printList();

		LinkedList linkList1 = new LinkedList();
		linkList1.addLast(10);
		linkList1.addLast(22);
		linkList1.addLast(30);
		linkList1.addLast(43);
		linkList1.addLast(50);
		linkList1.addLast(64);
		linkList1.printList();

		LinkedList linkList2 = new LinkedList();
		linkList2.addLast(11);
		linkList2.addLast(20);
		linkList2.addLast(33);
		linkList2.addLast(34);
		linkList2.addLast(55);
		linkList2.addLast(60);
		linkList2.printList();

		LinkedList mergedLinkedList = LinkedList.sortedMerge(linkList1, linkList2);
		mergedLinkedList.printList();

	}

}
