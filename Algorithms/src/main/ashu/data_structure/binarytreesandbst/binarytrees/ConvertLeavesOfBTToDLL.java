package main.ashu.data_structure.binarytreesandbst.binarytrees;

//convert leaves of BT to doubly linked list
public class ConvertLeavesOfBTToDLL {
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}

	Node root;
	Node head; // will point to head of DLL
	Node prev; // temporary pointer

	// The main fuction that links the list list to be traversed
	public Node extractLeafList(Node root) {
		if (root == null)
			return null;
		if (root.left == null && root.right == null) {
			if (head == null) {
				head = root;
				prev = root;
			} else {
				prev.right = root;
				root.left = prev;
				prev = root;
			}
			return null;
		}
		root.left = extractLeafList(root.left);
		root.right = extractLeafList(root.right);
		return root;
	}
}
