package main.ashu.data_structure.binarytreesandbst.binarytrees;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree, print the longest leaf to leaf path
//Basically, print the nodes that constitute the diameter
//Diameter is the max(lh+rh+1) for all modes
//so once we have the length of diameter and the subtree,
//we just have to print leaf-root and root-leaf paths of respective lengths
public class LongestLeafToLeafPath {
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}

	static class Path {
		int lh, rh;
		Node diameterNode;
	}
    //iterate over all the possible paths and keep updating the golabl max as well
	public static void diameter(Node root) {
		if (root == null)
			return;
		Path path = new Path();
		diameterUtil(root, path);
		System.out.println("Length of dialmeter is : "
				+ (path.lh + path.rh + 1));
		List<Node> nodes = new ArrayList<Node>();
		printLeafToRootPath(path.diameterNode.left, path.lh, nodes);
		System.out.print(path.diameterNode.data + " ");
		nodes.clear();
		printRootToLeafPath(path.diameterNode.right, path.rh, nodes);
	}

	//in recursive call return the longest root-leaf path
	//update the global leaf-leaf path length in gloabl object
	public static int diameterUtil(Node root, Path path) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null) {
			if (path.lh + path.rh <= 0) {
				path.lh = path.rh = 0;
				path.diameterNode = root;
			}
			return 1;
		}
		int lh = diameterUtil(root.left, path);
		int rh = diameterUtil(root.right, path);
		//update if the current path passing through this root node is optimal
		if (path.lh + path.rh < lh + rh) {
			path.lh = lh;
			path.rh = rh;
			path.diameterNode = root;
		}
		return Math.max(lh, rh) + 1;
	}
  //print leaf to root path of length len for the root node
	public static boolean printLeafToRootPath(Node root, int len,
			List<Node> nodes) {
		if (root == null)
			return len == 0;
		nodes.add(root);
		if (len == 1) {
			if (root.left == null && root.right == null) {
				for (int i = nodes.size() - 1; i >= 0; i--) {
					System.out.print(nodes.get(i).data + " ");
				}
			}
			return root.left == null && root.right == null;
		}
		if (printLeafToRootPath(root.left, len - 1, nodes))
			return true;
		boolean x = printLeafToRootPath(root.right, len - 1, nodes);
		nodes.remove(root); //remove nodes while travelling up the tree
		return x;
	}

	public static boolean printRootToLeafPath(Node root, int len,
			List<Node> nodes) {
		if (root == null)
			return len == 0;
		nodes.add(root);
		if (len == 1) {
			if (root.left == null && root.right == null) {
				for (int i = 0; i < nodes.size(); i++) {
					System.out.print(nodes.get(i).data + " ");
				}
			}
			return root.left == null && root.right == null;
		}
		if (printRootToLeafPath(root.left, len - 1, nodes))
			return true;
		boolean x = printRootToLeafPath(root.right, len - 1, nodes);
		nodes.remove(root);
		return x;
	}

	public static void main(String args[]) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.left.right.left = new Node(6);
		root.left.right.right = new Node(7);
		root.left.left.right = new Node(8);
		root.left.left.right.left = new Node(9);
		diameter(root);
	}
}
