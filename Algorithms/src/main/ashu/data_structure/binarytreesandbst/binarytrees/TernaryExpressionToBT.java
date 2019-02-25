package main.ashu.data_structure.binarytreesandbst.binarytrees;

public class TernaryExpressionToBT {
	static int i=0;
	private static class Node {
		char data;
		Node left=null, right=null;
		public Node(char data){
			this.data = data;
		}
	}	
	public static Node create(char c[], int n) {
		
		if(i >=n){
			return null;
		}
		Node root = new Node(c[i]);
		i++;
		if(i<n) {
			if(c[i]=='?') {
				i++;
				 root.left = create(c, n);
			}
			else {
				i++;
				root.right = create(c, n);
			}
		}
		return root;
	}

	public static void inorder(Node root){
		if(root==null) return;		
		inorder(root.left);
		System.out.print(root.data+" ");
		inorder(root.right);
	}

	public static void main(String args []){
		String s = "a?b?c?p:d:e:f";
		System.out.println("Expresion is : "+s);
		Node root = create(s.toCharArray(), s.length());
		System.out.print("Inorder traversal : ");
		inorder(root);
	}
}
