package main.ashu.data_structure.binarytreesandbst.binarytrees;

import java.util.ArrayList;
import java.util.List;

//Applications of LCA :
//1. Distance between two nodes
//2. print the path common to the two paths from the root to the two given nodes

public class LowestCommonAncestorInBinaryTree {
	static boolean f1 = false, f2 = false;
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	
	//find candidate lca assuming both nodes exist, then double-check to ensure its really the lca
	public static  Node lcaUsingSinglePreorderWalk(Node root, int n1, int n2) {
		if(root == null) return null;
		Node lcaCandidate = lcaUtil(root, n1, n2);
		//now check if it's really the lca
		boolean exists = (f1 && f2) && nodeOnPath(lcaCandidate, n1) && nodeOnPath(lcaCandidate, n2);
		return exists ? lcaCandidate : null;
	}
	
	//this method assumes n1 and n2 exists
	public static Node lcaUtil(Node root, int n1, int n2) {
		if(root == null) return null;
		if(root.data == n1) {
			f1 = true; return root;
		}
		if(root.data == n2) {
			f2 = true; return root;
		}
		//recurse
		Node left = lcaUtil(root.left, n1, n2);
		Node right = lcaUtil(root.right, n1, n2);
		return (left != null && right != null) ? root : (left != null ? left : right);
	}
	
	public static boolean nodeOnPath(Node root, int key) {
		if(root == null) return false;
		if(root.data == key) return true;
		return nodeOnPath(root.left, key) || nodeOnPath(root.right, key);
	}
	
	public static Node lcaUsingRootToLeafPaths(Node root, int n1, int n2) {
		if(root == null) return null;
		List<Node> path1 = new ArrayList<Node>();
		List<Node> path2 = new ArrayList<Node>();
		boolean search1 = getPath(root, n1, path1);
		boolean search2 = getPath(root, n2, path2);
		if(!search1 ||  !search2) return null;
		Node lca = null;
		for(int i=0; i<path1.size(); i++) {
			if(path1.get(i).data != path2.get(i).data) {
				lca = path1.get(i-1);
			}
		}
		return lca;
	}	
	//like backtracking algo...
	//add to the path, recurse in hope to find, backtrack if couldn't
	public static boolean getPath(Node root, int n, List<Node> path) {
		if(root == null) return false;
		path.add(root);
		if(root.data == n) return true;
		boolean found =getPath(root.left, n, path);
		if(found) return true;
		found =getPath(root.right, n, path);
		if(found) return true;
		path.remove(root);
		return false;
	}
	
	public static void main(String args []) {
	    Node root = new Node(1);
	    root.left = new Node(2);
	    root.right = new Node(3);
	    root.left.left = new Node(4);
	    root.left.right = new Node(5);
	    root.right.left = new Node(6);
	    root.right.right = new Node(7);
	    int n1 = 4, n2 = 5;
	    Node lca = lcaUsingRootToLeafPaths(root, n1, n2);
	    if(lca != null) {
	    	System.out.println("LCA("+n1+","+n2+") is : "+lca.data);
	    }
	    lca = lcaUsingSinglePreorderWalk(root, n1, n2);
	    if(lca != null) {
	    	System.out.println("LCA("+n1+","+n2+") is : "+lca.data);
	    }
	    //Note : if we are given parent pointers as well, we can find depths of the nodes, 
	    //then go traverse diff up the tree from deeper node and once at same level,
	    //walk up the tree looking for common parent
	    //Note : Can also be done RMQ**** optimal in case of multiple queries for lca
	}

}
