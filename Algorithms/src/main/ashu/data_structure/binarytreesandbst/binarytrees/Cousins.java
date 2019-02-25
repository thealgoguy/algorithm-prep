package main.ashu.data_structure.binarytreesandbst.binarytrees;

import java.util.LinkedList;
import java.util.Queue;

public class Cousins {
	private static class Node {
		   public int data;
		   public Node left=null, right=null;
		   public Node(int data) {
			   this.data = data;
		   }
	   }
	
	public static boolean cousins(Node root, Node a, Node b) {
		if(root == null) return false;
		Queue<Node> q = new LinkedList<Node>();
		q.offer(root);
		int l1=-1, l2=-1;
		Node p1=null, p2=null;
		int l =0;
		while(!q.isEmpty()){
			l++;
			int n = q.size();
			while(n-- > 0) {
				Node next = q.poll();
				if(next.left != null) {
					q.offer(next.left);
					if(next.left == a) {
						l1 = l+1; p1=next;
					}
					else if(next.left == b){
						l2 = l+1; p2 = next;
					}
				}
				if(next.right != null){
					q.offer(next.right);
					if(next.right == a) {
						l1 = l+1; p1=next;
					}
					else if(next.right == b){
						l2 = l+1; p2 = next;
					}
				}
			}
		}
		if(l1 != l2 || p1==p2) return false;
		return true;
	}
	
	public static void main(String args []){
		Node root = new Node(1);
		root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.right = new Node(15);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);
        
        Node a = root.left.right;
        Node b = root.right.left;
        boolean cousins = cousins(root, a, b);
        if(cousins) System.out.println("Cousins");
        else System.out.println("Not cousins");
	}
}
