package main.ashu.data_structure.binarytreesandbst.binarytrees;

//there can be many BTs possible, return any
//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/discuss/244921/Java-recursive-solution-7ms-beats-100
public class ConstructBTFromPreAndPostOrder {
	int index = 0;

	public TreeNode constructFromPrePost(int[] pre, int[] post) {
		return buildTree(pre, post, 0, post.length - 1);
	}

	private TreeNode buildTree(int[] pre, int[] post, int left, int right) {
		if (left > right)
			return null;

		index++;
		TreeNode root = new TreeNode(post[right]);
		if (left == right)
			return root;

		int i = left;
		while (i <= right && post[i] != pre[index])
			i++;

		root.left = buildTree(pre, post, left, i);
		root.right = buildTree(pre, post, i + 1, right - 1);
		return root;
	}

}
