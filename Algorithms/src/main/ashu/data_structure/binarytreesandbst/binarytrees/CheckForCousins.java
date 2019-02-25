package main.ashu.data_structure.binarytreesandbst.binarytrees;

import java.util.LinkedList;
import java.util.Queue;

//Enhance to print cousins of a given node in BT - store parents in map during traversal and do level order traversal
public class CheckForCousins {
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	 static class Cousin {
		 int level1 = -1, level2 = -1;
		 Node p1=null, p2 =null;  //parents
	 }
	public static boolean areCousins(Node root, Node n1, Node n2) {
		if(root == null) return false;
		Cousin cousin = new Cousin();
		//can use queue for traversal in place of recursion
		traverse(root, n1, n2, 0, cousin);
		return cousin.level1 >0 && cousin.level2 >0  && cousin.level1 == cousin.level2 && cousin.p1 != cousin.p2;
	}
	
	public static void traverse(Node root, Node n1, Node n2, int level, Cousin cousin) {
		if(root == null) return;
		if(root.left == null || root.right == null) return;
		//if both nodes found, return
		if(cousin.level1 >0 && cousin.level2 > 0) return;
		if(root.left == n1) {
			cousin.level1 = level+1;
			cousin.p1 = root;
		}
		if(root.right == n2) {
			cousin.level2 = level+1;
			cousin.p2 = root;
		}
		traverse(root.left, n1, n2, level+1,cousin);
		traverse(root.right, n1, n2, level+1,cousin);
	}
	
	//print all cousins of a given node
	public static void printCousins(Node root, Node node) {
		if(root == null || root.left == null && root.right == null) return;
		if(node == root || node == null) return;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		int level = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			int cousinLevel = -1;
			while(size-- >0) {
				Node current = queue.poll();
				//don't push siblings of the cousin node
				if(current.left == node || current.right == node) {
					cousinLevel = level; continue; //or cousinLevel = level + 1 ?
				}
				if(current.left != null) queue.offer(current.left);
				if(current.right != null) queue.offer(current.right);
			}
			//if reached the cousin level, print the cousins
			if(cousinLevel == level) {
				while(!queue.isEmpty()) {
					Node next = queue.poll();
					System.out.print(next.data+" ");
				}
				break;
			}
			level++;
			
		}
	}
	public static void main(String args []) {
	    Node root = new Node(1);
	    root.left = new Node(2);
	    root.right = new Node(3);
	    root.left.left = new Node(4);
	    root.left.right = new Node(5);
	    root.left.right.right = new Node(15);
	    root.right.left = new Node(6);
	    root.right.right = new Node(7);
	    root.right.left.right = new Node(8);
	    
	    
	    Node n1 = root.left.left;
	    Node n2 = root.right.right;
	    if(areCousins(root, n1, n2)) {
	    	System.out.println("nodes are cousins");
	    }
	    else{
	    	System.out.println("nodes are not cousins");
	    }
	    
	    System.out.print("cousins of "+n1.data+" are : ");
	    printCousins(root, n1);
	    System.out.print("\ncousins of "+n2.data+" are : ");
	    printCousins(root, n2);
	    System.out.print("\ncousins of "+root.left.data+" are : ");
	    printCousins(root, root.left);
	}
}
