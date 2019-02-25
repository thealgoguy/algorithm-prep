package main.ashu.data_structure.binarytreesandbst.binarytrees;

import java.util.Stack;

public class FlattenBTtoLL {
	
	private class Node {
		int key;
		Node left, right;
		Node(int item) {
			key = item;
			left = right = null;
		}
	}
	public class Solution {
	    public void flatten(Node root) {
	        Stack<Node> stack = new Stack<Node>();
	        Node p = root;
	 
	        while(p != null || !stack.empty()){
	 
	            if(p.right != null){
	                stack.push(p.right);
	            }
	 
	            if(p.left != null){
	                p.right = p.left;
	                p.left = null;
	            }else if(!stack.empty()){
	                Node temp = stack.pop();
	                p.right=temp;
	            }
	 
	            p = p.right;
	        }
	    }
	}
}
