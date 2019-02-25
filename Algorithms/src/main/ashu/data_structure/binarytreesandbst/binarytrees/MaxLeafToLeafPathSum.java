package main.ashu.data_structure.binarytreesandbst.binarytrees;

public class MaxLeafToLeafPathSum {
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	
	static class MaxVal {
		int val;
	}
	public static int getMaxPathSum(Node root) {
		MaxVal max = new MaxVal();
		max.val = Integer.MIN_VALUE;
		getMaxPathSumUtil(root, max);
		return max.val;
	}
	
	//post order walk
	//why ? to compute root-leaf path sum for level i, we would use info from i-1
	public static int getMaxPathSumUtil(Node root, MaxVal max) {
		if(root == null) return Integer.MIN_VALUE;
		if(root.left == null && root.right == null) {
			return root.data;
		}
		
		int leftSum = getMaxPathSumUtil(root.left, max);
		int rightSum = getMaxPathSumUtil(root.right, max);
		//if at non-leaf node with two children return greater of the two root-leaf paths
		//also update the global max
		if(root.left != null && root.right != null) {
			max.val = Math.max(max.val, root.data + leftSum + rightSum);
			return root.data + Math.max(leftSum, rightSum);
		}
		//one child case
		return root.left == null ? root.data + rightSum : root.data + leftSum;
	}

	public static void main(String args[]) {
		Node root = new Node(-15);
		root.left = new Node(5);
		root.right = new Node(6);
		root.left.left = new Node(-8);
		root.left.right = new Node(1);
		root.left.left.left = new Node(2);
		root.left.left.right = new Node(6);
		root.right.left = new Node(3);
		root.right.right = new Node(9);
		root.right.right.right = new Node(0);
		root.right.right.right.left = new Node(4);
		root.right.right.right.right = new Node(-1);
		root.right.right.right.right.left = new Node(10);
		int maxPathSum = getMaxPathSum(root);
		System.out.println("max path sum between two leaves = "+maxPathSum);
	}
}
