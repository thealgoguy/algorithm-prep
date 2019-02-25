package main.ashu.data_structure.binarytreesandbst.binarytrees;

//https://leetcode.com/articles/all-nodes-distance-k-in-binary-tree/
public class NodesAtDistanceKFromTheTargetNode {
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	
	public static void nodesAtDistanceKFromTarget(Node root, Node target, int k) {
		int length = searchTarget(root, target);
		//System.out.println("Distance of target from root = "+length);
		if(length == -1) {
			System.out.println("target doesn't exist in the tree");
		}
		else {
			printNodesFromTarget(root, target, length, k);			
		}
	}
	
	public static boolean printNodesFromTarget(Node root, Node target, int len, int k) {
		if(root == null || target == null) return false;
		if(root.data == target.data) {
			printDescendants(root, k);
			return true;
		}
		boolean foundInLeft = printNodesFromTarget(root.left, target, len-1, k);
		if(foundInLeft) {
			//found in left subtree, distance of target from root.right = len+1
			//print descendants at distance = k-(len+1) in the right subtree
			printDescendants(root.right, k-len-1);
			return foundInLeft;
		}else {
			boolean foundInRight = printNodesFromTarget(root.right, target, len-1, k);
			//found in right subtree, distance of target from root.left = len+1
			//print descendants at distance = k-(len+1) in the left subtree
			if(foundInRight) {
				printDescendants(root.left, k-len-1);
				return foundInRight;
			}
		}
		return false;
	}
	
	public static void printDescendants(Node root, int down){
		if(root == null) return;
		if(down == 0) {
			System.out.print(root.data+" ");
			return;
		}
		printDescendants(root.left, down-1);
		printDescendants(root.right, down-1);
	}
	
	public static int searchTarget(Node root, Node target) {
		if(root == null) return -1;
		if(root.data == target.data) return 0;
		int left = searchTarget(root.left, target);
		if(left >=0) return left+1;
		int right = searchTarget(root.right, target);
		return (right >=0) ? right+1 : -1;
	}
	
	public static void main(String args []) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.left.left = new Node(9);
		root.left.right = new Node(5);
		root.left.right.left = new Node(6);
		root.left.right.right = new Node(7);
		root.left.right.right.right = new Node(10);
		root.left.right.right.right.left = new Node(11);
		root.right.right = new Node(8);
		
		Node target = root.left.right;
		int k = 3;
		System.out.print("Node at distance "+k+" from node "+target.data+" are :");
		nodesAtDistanceKFromTarget(root, target, k);
	}
}
