package main.ashu.data_structure.binarytreesandbst.binarytrees;

import java.util.LinkedList;
import java.util.Queue;

//check if binary tree is symmetric or not
class Node {
	int key;
	Node left, right;
	Node(int item) {
		key = item;
		left = right = null;
	}
}

public class CheckMirrorIterative {
	
	public static boolean isMirrorV1(Node root1, Node root2) {
		   if(root1 == null && root2 == null) return true;
		   if(root1 == null || root2 == null) return false;
		   return root1.key == root2.key && isMirrorV1(root1.left, root2.right) && isMirrorV1(root1.right, root2.left);  
	}

	public static boolean isMirrorV2(Node root1, Node root2) {
		if(root1 == null && root2 == null) return true;
		if(root1 == null || root2 == null) return false;
		Queue<Node> queue = new LinkedList<Node>();  //let's add null nodes too
		queue.offer(root1.left);
		queue.offer(root2.right);
		while(!queue.isEmpty()) {
			Node leftChild = queue.poll();
			Node rightChild = queue.poll();
			if(leftChild == null && rightChild == null) continue;
			if(leftChild == null || rightChild == null) return false;
			if(leftChild.key != rightChild.key) return false;
		    //enqueing in the order we would like to compare for mirror
			queue.offer(leftChild.left);
			queue.offer(rightChild.right);
			queue.offer(leftChild.right);
			queue.offer(rightChild.left);
		}
		return true;
	}
	public static void main(String args[]) {
		Node root = new Node(1);
		root.left = new Node(2);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(4);
        root.right.right = new Node(3);
        boolean recursively = isMirrorV1(root, root);
        boolean iteratively = isMirrorV2(root, root);
        System.out.println("Mirror check recursively : "+recursively);
        System.out.println("Mirror check iteratively : "+iteratively);
	}
}
