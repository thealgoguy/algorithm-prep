package main.ashu.data_structure.binarytreesandbst.binarytrees;

import java.util.HashSet;
import java.util.Set;

//Give a binary tree, check if there is a node pair on root-leaf path with sum=root data
public class PairInRootToLeafPathWithGivenSum {	
	static class Node {
		int key;
		Node left, right;

		Node(int item) {
			key = item;
			left = right = null;
		}
	}
	
	public static boolean pairWithSumInPath(Node root) {
		Set<Integer> set = new HashSet<Integer>();
		return pairWithSumInPathUtil(root, set, root.key);
	}
	
	public static boolean pairWithSumInPathUtil(Node root, Set<Integer> set, int sum) {
		if(root == null) return false;
		if(root.left == null && root.right == null) {
			return set.contains(sum-root.key);
		}
		//check for left right and backtrack while moving up the tree
		set.add(root.key);
		boolean exists = pairWithSumInPathUtil(root.left, set, sum) ||  pairWithSumInPathUtil(root.right, set, sum);
		set.remove(root.key);  //remove while going up the tree, this node can't be considered in paths not going through it
		return exists;
	}
   public static void main(String args []) {
	    Node root = new Node(8);
	    root.left    = new Node(5);
	    root.right   = new Node(4);
	    root.left.left = new Node(9);
	    root.left.right = new Node(7);
	    root.left.right.left = new Node(1);
	    root.left.right.right = new Node(12);
	    root.left.right.right.right = new Node(2);
	    root.right.right = new Node(11);
	    root.right.right.left = new Node(3);
	    boolean exists = pairWithSumInPath(root);
	    if(exists) System.out.println("Exists");
	    else System.out.println("Doesn't esis");
   }
}
