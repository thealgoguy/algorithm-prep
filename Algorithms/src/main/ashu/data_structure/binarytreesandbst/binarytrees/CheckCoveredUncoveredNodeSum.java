package main.ashu.data_structure.binarytreesandbst.binarytrees;

//https://www.geeksforgeeks.org/check-sum-covered-uncovered-nodes-binary-tree/
public class CheckCoveredUncoveredNodeSum {
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	
	public static boolean checkEqualSum(Node root) {
		if(root == null) return true;
		int total = totalSum(root);
		int leftBoundarySum = leftBoundarySum(root.left);
		int rightBoundarySum = rightBoundarySum(root.right);
		int uncoveredSum = leftBoundarySum + rightBoundarySum + root.data;
		int coveredSum = total - uncoveredSum;
		return coveredSum == uncoveredSum;	
	}
	
	public static int leftBoundarySum(Node root) {
		if(root == null) return 0;
		if(root.left == null && root.right == null) return root.data;
		if(root.left != null) return root.data + leftBoundarySum(root.left);
		else return root.data + leftBoundarySum(root.right);
	}
	
	public static int rightBoundarySum(Node root) {
		if(root == null) return 0;
		if(root.left == null && root.right == null) return root.data;
		if(root.right != null) return root.data + leftBoundarySum(root.right);
		else return root.data+ leftBoundarySum(root.left);
	}
	
	public static int totalSum(Node root) {
		if(root == null) return 0;
		return totalSum(root.left) + totalSum(root.right) + root.data;
	}

	public static void main(String args[]) {
		Node root = new Node(8);
		root.left = new Node(3);
		root.left.left = new Node(1);
		root.left.right = new Node(6);
		root.left.right.left = new Node(4);
		root.left.right.right = new Node(7);

		root.right = new Node(10);
		root.right.right = new Node(14);
		root.right.right.left = new Node(13);
		boolean equalSum = checkEqualSum(root);
		if(equalSum) {
			System.out.println("Sums of covered and uncovered nodes are same");
		}
		else {
			System.out.println("Sums of covered and uncovered nodes are not same");
		}
	}
}
