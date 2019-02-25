package main.ashu.data_structure.binarytreesandbst.binarytrees;

//similar to calculating diameter of tree
//a postorder walk will do
//similar style question - find largest sum subtree, count subtrees with sum = x
public class MaxDiffBetweenAncestorAndDescendant {
	static class Node {
		int key;
		Node left, right;

		public Node(int key) {
			this.key = key;
			left = right = null;
		}
	}
	//needed for updating global max diff so far at each level while walking up the tree
	//we need to find two nodes i and j such that level(i) < level(j) such that
	//abs(value(i)-value(j)) is maximum
	//return minimum element at each subtree in the recursive call 
	//while updating the max diff in some global object
	 static class MaxDiff {
		 int val;
		 MaxDiff() {
			 this.val = Integer.MIN_VALUE;
		 }
	 }
	public static int maxDiff(Node root) {
		MaxDiff max = new MaxDiff();
		maxDiffUtil(root, max);
		return max.val;
	}
	
	//return min at each level and update diff while walking up
	public static int maxDiffUtil(Node root, MaxDiff diff) {
		if(root == null) return Integer.MAX_VALUE;
		if(root.left == null && root.right == null) return root.key;
		int min = Math.min(maxDiffUtil(root.left, diff), maxDiffUtil(root.right, diff));
		diff.val = Math.max(diff.val, root.key - min);
		return Math.min(root.key, min);
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
		int maxDiff = maxDiff(root);
		System.out.println("Max diff between an ancestor and descendant = "+maxDiff);
	}
}
