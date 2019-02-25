package main.ashu.data_structure.binarytreesandbst.binarytrees;

import java.util.Stack;

//Wrong program...correct it
//https://www.geeksforgeeks.org/construct-binary-tree-string-bracket-representation/
public class BracaketedStringToBinaryTree {
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	
	public static Node constructTree(String str) {
		if(str == null || str.isEmpty()) return null;
		int n = str.length();
		return constructTreeUtil(str, 0, n-1);
	}
	
	public static Node constructTreeUtil(String str, int start, int end){
		if(start > end) return null;
		Node root = new Node(str.charAt(start) - '0');
		int bracketStart = -1, bracketEnd = -1;
		//find index of start parenthesis - '('
		if(start+1 <=end && str.charAt(start+1) == '(') {
			bracketStart = start+1;
			//find index of matching parenthesis - ')' for this subtree
			findMathcingBracket(str, bracketStart, end);
		}
		if(bracketStart != -1) { 
			//recurse for constructing left and right subtrees
			root.left = constructTreeUtil(str, bracketStart+1, bracketEnd-1);
			root.right = constructTreeUtil(str, bracketEnd+1, end-1);
		}
		return root;
	}
	
	public static int findMathcingBracket(String str, int s, int e) {
		if(s>e) return -1;
		Stack<Integer> stack = new Stack();
		for(int i=s; i<=e; i++) {
			if(str.charAt(i) == '('){
				stack.push(i);
			} else if(str.charAt(i) == ')'){
				if(str.charAt(stack.peek()) == '(') {
					stack.pop();
					if(stack.isEmpty()) return i;
					else stack.push(i);
				}
			}
		}
		return -1;
	}
	
	public static void preOrderWalk(Node root) {
		if(root == null) return;
		System.out.print(root.data+" ");
		preOrderWalk(root.left);
		preOrderWalk(root.right);
	}
   public static void main(String args []) {
	   String str = "4(2(3)(1))(6(5))";
	   Node root = constructTree(str);
	   System.out.print("Pre-order tarversal of the tree is : ");
	   preOrderWalk(root);
   }
}
