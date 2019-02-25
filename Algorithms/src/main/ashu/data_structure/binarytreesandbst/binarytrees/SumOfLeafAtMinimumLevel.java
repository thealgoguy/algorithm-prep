package main.ashu.data_structure.binarytreesandbst.binarytrees;

import java.util.LinkedList;
import java.util.Queue;

public class SumOfLeafAtMinimumLevel {
	static class Node {
		int key;
		Node left, right;

		Node(int item) {
			key = item;
			left = right = null;
		}
	}
   
	public static int getMinLevelLeafSum(Node root) {
		if(root == null) return 0;
		if(root.left == null && root.right == null) return root.key;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int sum = 0;
			int levelSize = queue.size();
			boolean leafFound = false;
			while(levelSize >0) {
				Node n = queue.poll();
				if(n.left == null && n.right == null) {
					sum += n.key;
					leafFound = true;
				}
				if(n.left != null) queue.offer(n.left);
				if(n.right != null) queue.offer(n.right);
				levelSize--;
			}
			if(leafFound) return sum;
			else sum = 0;
		}
		return -1;
	}
	
	public static void main(String args[]) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		root.left.right.left = new Node(8);
		root.right.left.right = new Node(9);
		int sum = getMinLevelLeafSum(root);
		System.out.println("Sum of leaf nodes at minimum depth = "+sum);
	}
}
