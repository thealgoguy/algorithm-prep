package main.ashu.data_structure.binarytreesandbst.binarytrees;

//Given a binary tree with a value associated with each node, 
//we need to choose a subset of these nodes such that sum of chosen nodes is maximum
//under a constraint that no two chosen nodes in the subset should be directly connected
//that is, if we have taken a node in our sum
//then we can’t take its any children in consideration and vice versa.
//Similar - maximum-sum-tree-adjacent-levels-not-allowed
public class MaxNonAdjacentNodesSumInBinaryTree {
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	
	static class TwoSum {
		int sum1;  //including root node
		int sum2;  //excluding root node
		public TwoSum(int i, int j) {
			this.sum1 = i;
			this.sum2 = j;
		}
	}
	public static int maxNonAdjacentSum(Node root) {
		TwoSum twoSum = maxNonAdjacentSumUtil(root);
		return Math.max(twoSum.sum1, twoSum.sum2);
	}
	
	public static TwoSum maxNonAdjacentSumUtil(Node root) {
		if(root == null) return new TwoSum(0, 0);
		if(root.left == null && root.right == null) {
			return new TwoSum(root.data,0);
		}
		TwoSum sumLeft = maxNonAdjacentSumUtil(root.left);
		TwoSum sumRight = maxNonAdjacentSumUtil(root.right);
		//if root included, don't consider left and right children in the sum
		int includeRoot = root.data + sumLeft.sum2 + sumRight.sum2;
		//if root not included, consider the best of inclusion/exclusion from both children and add
		int excludeRoot = Math.max(sumLeft.sum1, sumLeft.sum2) + Math.max(sumRight.sum1, sumRight.sum2);
		return new TwoSum(includeRoot, excludeRoot);
	}
	
	public static void main(String args []) {
	    Node root= new Node(10);
	    root.left= new Node(1);
	    root.left.left= new Node(2);
	    root.left.left.left= new Node(1);
	    root.left.right= new Node(3);
	    root.left.right.left= new Node(4);
	    root.left.right.right= new Node(5);
	    int maxSum = maxNonAdjacentSum(root);
	    System.out.println("Maximum sum in BT such that no two nodes are adjacent = "+maxSum);
	}
}
