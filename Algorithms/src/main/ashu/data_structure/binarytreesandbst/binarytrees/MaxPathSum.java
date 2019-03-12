package main.ashu.data_structure.binarytreesandbst.binarytrees;

//a path is defined as any sequence of nodes from some starting node to any node
//in the tree along the parent-child connections.
//The path must contain at least one node and does not need to go through the root.
//accepted - https://leetcode.com/problems/binary-tree-maximum-path-sum/submissions/
public class MaxPathSum {
	private class Sum {
		int val = 0;
	}

	public int maxPathSum(TreeNode root) {
		Sum s = new Sum();
		s.val = Integer.MIN_VALUE;
		getMax(root, s);
		return s.val;
	}

	private int getMax(TreeNode root, Sum s) {
		if (root == null)
			return 0;
		int left = getMax(root.left, s);
		int right = getMax(root.right, s);
		int single = Math.max(root.val, root.val + Math.max(left, right));
		int max = Math.max(single, root.val + left + right);
		s.val = Math.max(s.val, max);
		return single;
	}
}
