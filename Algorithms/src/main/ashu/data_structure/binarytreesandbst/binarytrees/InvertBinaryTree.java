package main.ashu.data_structure.binarytreesandbst.binarytrees;

//same as mirror a BT
//https://leetcode.com/problems/invert-binary-tree/description/
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) {
		val = x;
	}
}

public class InvertBinaryTree {
	public TreeNode invertTree(TreeNode root) {
		if (root == null)
			return root;
		if (root.left == null && root.right == null)
			return root;
		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		root.left = right;
		root.right = left;
		return root;
	}
}
