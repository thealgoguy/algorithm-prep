package main.ashu.data_structure.binarytreesandbst.binarytrees;

//check if each node is sum of all nodes in its left and right "subtrees"
//Similar - check for children sum property - each node sum of immediate left and right children
public class SumTreeCheck {
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	
	public static boolean isSumtree(Node root) {
		if(root == null) return true;
		if(root.left == null && root.right == null) return true;
		//instead to calulate sum of nodes at internal node using recursive call, we can leverage sum property
		// if left subtree is leaf, leftsum if leaf's data else left sum is double the left child's value
		int leftSum = (root.left == null) ? 0: (isLeaf(root.left) ?  root.left.data : 2*root.left.data);
		int rightSum = (root.right == null) ? 0 : (isLeaf(root.right)  ? root.right.data : 2 * root.right.data);
		return root.data == leftSum+rightSum && isSumtree(root.left) && isSumtree(root.right);
	}
	
	public static boolean isLeaf(Node root) {
		return root != null && root.left == null && root.right == null;
	}
	
	public static void main(String args []) {
        Node root = new Node(26);
        root.left = new Node(10);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(6);
        root.right.right = new Node(3);
       
        if(isSumtree(root)) {
        	System.out.println("Bianry tree is a sum tree");
        }
        else System.out.println("Binary tree is not a sum tree");
	}
}
