package main.ashu.data_structure.binarytreesandbst.binarytrees;

public class SortedLLToBBST {
	static LNode head;

	/* Link list Node */
	class LNode {
		int data;
		LNode next, prev;

		LNode(int d) {
			data = d;
			next = prev = null;
		}
	}

	/* A Binary Tree Node */
	class TNode {
		int data;
		TNode left, right;

		TNode(int d) {
			data = d;
			left = right = null;
		}
	}

	public TNode sortedListToBST() {
		/* Count the number of nodes in Linked List */
		int n = countNodes(head);

		/* Construct BST */
		return sortedListToBSTRecur(n);
	}

	public TNode sortedListToBSTRecur(int n) {
		/* Base Case */
		if (n <= 0)
			return null;

		/* Recursively construct the left subtree */
		TNode left = sortedListToBSTRecur(n / 2);

		/*
		 * head_ref now refers to middle node, make middle node as root of BST
		 */
		TNode root = new TNode(head.data);

		// Set pointer to left subtree
		root.left = left;

		/*
		 * Change head pointer of Linked List for parent recursive calls
		 */
		head = head.next;

		/*
		 * Recursively construct the right subtree and link it with root. The
		 * number of nodes in right subtree is total nodes - nodes in left
		 * subtree - 1 (for root)
		 */
		root.right = sortedListToBSTRecur(n - n / 2 - 1);

		return root;
	}

	int countNodes(LNode head) {
		int count = 0;
		LNode temp = head;
		while (temp != null) {
			temp = temp.next;
			count++;
		}
		return count;
	}

	void push(int new_data) {
		/* allocate node */
		LNode new_node = new LNode(new_data);

		/*
		 * since we are adding at the begining, prev is always NULL
		 */
		new_node.prev = null;

		/* link the old list off the new node */
		new_node.next = head;

		/* change prev of head node to new node */
		if (head != null)
			head.prev = new_node;

		/* move the head to point to the new node */
		head = new_node;
	}

	/* Function to print nodes in a given linked list */
	void printList(LNode node) {
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
	}
}
