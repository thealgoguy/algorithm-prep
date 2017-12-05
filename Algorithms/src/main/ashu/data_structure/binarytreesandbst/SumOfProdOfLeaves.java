package main.ashu.data_structure.binarytreesandbst;

import java.util.LinkedList;
import java.util.Queue;

public class SumOfProdOfLeaves {
   private static class Node {
	   public int data;
	   public Node left=null, right=null;
	   public Node(int data) {
		   this.data = data;
	   }
   }
   
   public static int findProduct(Node root) {
	   if(root==null) return 0;
	   Queue<Node> q = new LinkedList<Node>();
	   q.offer(root);
	   int prod = 1;
	   while(!q.isEmpty()) {
		   int sum = 0;
		   int n = q.size();
		   while(n-- > 0) {
			   Node next = q.poll();
			   if(next.left != null) q.offer(next.left);
			   if(next.right != null) q.offer(next.right);
			   if(next.left == null && next.right == null) sum += next.data;
		   }
		  if(sum >0)  prod *= sum;
		   System.out.println("sum = "+sum+" prod = "+prod);
	   }
	   return prod;
   }
   
   //sum of left leaves
   public static int leftLeafSum(Node root) {
	   if(root==null) return 0;
	   return findSum(root, null);
	 }
	 public static int findSum(Node root, Node parent) {
	   if(root==null) return 0;	   
	   if(root.left==null && root.right==null) {
	      if(parent.left==root) return root.data;
	 	 else return 0;
	   }
	   return findSum(root.left, root) + findSum(root.right, root);
	 }
   
   public static void main(String args []) {
	   Node root = new Node(20);
	   root.left = new Node(9);
       root.right = new Node(49);
       root.left.right = new Node(12);
       root.left.left = new Node(5);
       root.right.left = new Node(23);
       root.right.right = new Node(52);
       root.left.right.right = new Node(12);
       root.right.right.left = new Node(50);
	   int product = findProduct(root);
       System.out.println("The final product value : "+product);
       System.out.println("Sum of left leaves = "+leftLeafSum(root));
   }
}
