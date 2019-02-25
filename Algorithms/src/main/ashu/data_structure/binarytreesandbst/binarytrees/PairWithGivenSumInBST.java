package main.ashu.data_structure.binarytreesandbst.binarytrees;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

//Since the inorder traversal gives sorted set, 
//do inorder and reverse inorder trvaersal at the same time
//stack version has not passed on leetcode
public class PairWithGivenSumInBST {
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	
	//do inorder, store elements in set
	//if set.containsKey()
	public static void findPairUisnHashing(Node root, int target) {
		Set<Integer> set = new HashSet<Integer>();
		findPairUtil(root, target, set);
	}
	
	public static boolean findPairUtil(Node root, int target, Set<Integer> set) {
		if(root == null) return false;
		if(findPairUtil(root.left, target, set)) return true;
		if(set.contains(target - root.data)) {
			System.out.println("pair with given sum  = "+root.data+" , "+(target- root.data));
		} else {
			set.add(root.data);
		}
		return findPairUtil(root.right, target, set);
	}
	
	public static void findPair(Node root,int  target) {
		if(root == null) return;
		Stack<Node> s1 = new Stack<Node>();
		Stack<Node> s2 = new Stack<Node>();
		Node temp = root;
		while(temp != null) {
			s1.push(temp);
			temp = temp.left;
		}
		temp = root;
		while(temp != null) {
			s2.push(temp);
			temp = temp.right;
		}
        boolean found = false;
        int x = 0, y=0;
		while(! s1.isEmpty() && !s2.isEmpty()) {
			if(s1.peek() == s2.peek()) break;
			x = s1.peek().data; 
			y = s2.peek().data;
			int sum = x + y;
			if(sum == target) {
				found = true;
				s1.pop();
				s2.pop();
				break;
			} else if(sum < target) {
				Node next = s1.pop();
				if(next.right != null) {
					next = next.right;
					s1.push(next);
					while(next.left != null){
						s1.push(next.left);
						next = next.left;
					}
				}
			} else {
				Node next = s2.pop();
				if(next.left != null) {
					next = next.left;
					s1.push(next);
					while(next.right != null){
						s1.push(next.right);
						next = next.right;
					}
				}
			}
		}
		if(found) {
			System.out.println("Pairs in the BST with sum "+target+" are : "+x +" and "+y);
		} else {
			System.out.println("Pait with sum "+target+" doesn't exist in the BST");
		}
	}
	
	public static void main(String args []) {
		Node root = new Node(4);
		root.left = new Node(2);
		root.left.left = new Node(1);
		root.left.right = new Node(3);
		root.right = new Node(6);
		root.right.left = new Node(5);
		root.right.right = new Node(7);
		int sum = 10;
		findPair(root, sum);
	}
}
