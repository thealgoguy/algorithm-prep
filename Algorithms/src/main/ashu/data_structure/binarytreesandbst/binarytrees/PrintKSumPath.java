package main.ashu.data_structure.binarytreesandbst.binarytrees;

import java.util.ArrayList;
import java.util.List;

//A binary tree and a number k are given. 
//Print every path in the tree with sum of the nodes in the path as k.
//A path can start from any node and end at any node and must be downward only, 
//i.e. they need not be root node and leaf node; 
public class PrintKSumPath {
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	//not necessarily root-leaf path, it can be any partial/whole path
	//a path can end at any node, so check of revery case
	public static void printkSumPath(Node root, int k) {
		if(root == null) return;
		List<Integer> path = new ArrayList<Integer>();
		printkSumPathUtil(root, path, k);
	}
	
	//backtracking step
	public static void printkSumPathUtil(Node root, List<Integer> path, int k) {
		if(root == null) return;
		path.add(root.data);
		int sum = 0;
		for(int i=path.size()-1; i>=0; i--) {
			sum += path.get(i);
			if(sum == k) printPath(path, i);
		}
		printkSumPathUtil(root.left, path, k);
		printkSumPathUtil(root.right, path, k);
		path.remove(path.size()-1);  //remove while going up the tree, don't do path.removde(root.data) b/c remove method takes index or object but both maynot delete the current root 
	}
	
	public static void printPath(List<Integer> path, int i) {
		for(;i<path.size(); i++) {
			System.out.print(path.get(i)+" ");
		}
		System.out.println();
	}
	
	public static void main(String args []) {
		 Node root = new Node(1);
		    root.left = new Node(3);
		    root.left.left = new Node(2);
		    root.left.right = new Node(1);
		    root.left.right.left = new Node(1);
		    root.right = new Node(-1);
		    root.right.left = new Node(4);
		    root.right.left.left = new Node(1);
		    root.right.left.right = new Node(2);
		    root.right.right = new Node(5);
		    root.right.right.right = new Node(2);
		    
		    int k = 5;
		    System.out.println("Paths with sum = "+k+" are : ");
		    printkSumPath(root, k);
	}
}
