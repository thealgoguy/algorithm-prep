package main.ashu.data_structure.binarytreesandbst.binarytrees;

public class LongestRootToLeafPathSum {
	static boolean f1 = false, f2 = false;
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	//we need two things at reaching the leaf node - path length and path sum
	//both can be kept in the recursion stack but for comparison we need two global variables
	//can also pass an object reference comprising the two
	static int maxPathLength = 0;
	static int maxSum  = 0;
	public static int longestPathSum(Node root) {
		 longestPathSumUtil(root, 0, 0);
		 return maxSum;
	}
	
	public static void longestPathSumUtil(Node root, int len, int sum) {
		if(root == null) return;
		if(root.left == null && root.right == null) {
			maxPathLength = Math.max(maxPathLength, len);
			//needs correction I think
			maxSum = Math.max(maxSum, sum+root.data);
			return;
		}
		longestPathSumUtil(root.left, len+1, sum + root.data);
		longestPathSumUtil(root.right, len+1, sum + root.data);
	}
	public static void main(String args []) {
		Node root = new Node(4);         /*        4        */
        root.left = new Node(2);         /*       / \       */
        root.right = new Node(5);        /*      2   5      */
        root.left.left = new Node(7);    /*     / \ / \     */
        root.left.right = new Node(1);   /*    7  1 2  3    */
        root.right.left = new Node(2);   /*      /          */
        root.right.right = new Node(3);  /*     6           */
        root.left.right.left = new Node(6);
       int sum = longestPathSum(root);
       System.out.println("Longets path sum = "+sum);
	}
}
