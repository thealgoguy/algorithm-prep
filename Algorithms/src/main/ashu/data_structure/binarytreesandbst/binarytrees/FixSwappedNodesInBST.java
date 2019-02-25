package main.ashu.data_structure.binarytreesandbst.binarytrees;

import java.awt.SecondaryLoop;

//https://www.programcreek.com/2014/05/leetcode-recover-binary-search-tree-java/
//Two elements of a binary search tree (BST) are swapped by mistake. 
//Recover the tree without changing its structure.
/*Inorder traveral will return values in an increasing order.
 So if an element is less than its previous element,the previous element is a swapped node.*/
public class FixSwappedNodesInBST {
	Node first = null, second = null, prev = null;

	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}

	public Node correctBST(Node root) {
		if (root == null)
			return null;
		inorder(root);
		if (first != null && second != null) {
			int t = first.data;
			first.data = second.data;
			second.data = t;
		}
		return root;
	}

	public void inorder(Node root) {
		if (root == null)
			return;
		inorder(root.left);
		// if at leaf node
		if (prev == null) {
			prev = root;
		} else { //at some internal node
			if (prev.data > root.data) {
				if (first == null)
					first = prev;
				else
					second = root;
			}
			prev = root;
		}
        //recurse for right subtree
		inorder(root.right);
	}

	private void printInorder(Node root) {
		if (root != null) {
			printInorder(root.left);
			System.out.print(root.data + " ");
			printInorder(root.right);
		}
	}

	public static void main(String args[]) {
		/*
		 * 6 / \ 10 2 / \ / \ 1 3 7 12
		 * 
		 * 10 and 2 are swapped
		 */
		Node root = new Node(6);
		root.left = new Node(10);
		root.right = new Node(2);
		root.left.left = new Node(1);
		root.left.right = new Node(3);
		root.right.right = new Node(12);
		root.right.left = new Node(7);

		System.out.println("Inorder Traversal" + " of the original tree");
		FixSwappedNodesInBST fsn = new FixSwappedNodesInBST();
		fsn.printInorder(root);

		fsn.correctBST(root);
		System.out.print("Swapped nodes are : ");
		System.out.println(fsn.first.data + " , " + fsn.second.data + "\n");

		System.out.println("\nInorder Traversal" + " of the fixed tree");
		fsn.printInorder(root);
	}
}
