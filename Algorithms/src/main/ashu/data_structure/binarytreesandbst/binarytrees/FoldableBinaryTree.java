package main.ashu.data_structure.binarytreesandbst.binarytrees;

//A tree can be folded if left and right subtrees of the tree are structure wise mirror image of each other.
//An empty tree is considered as foldable.
public class FoldableBinaryTree {
	private static class Node {
		int key;
		Node left, right;
		Node(int item) {
			key = item;
			left = right = null;
		}
	} 
	
	public static boolean foldable(Node root) {
		if(root == null) return true;
		return foldableUtil(root, root);
	}
	
	//check for mirror with no key-comparison
	public static boolean foldableUtil(Node root1, Node root2) {
		if(root1 == null && root2 == null) return true;
		if(root1 == null || root2 == null) return false;
		return foldableUtil(root1.left, root2.right) && foldableUtil(root1.right, root2.left);
	}
	
	public static void main(String args []) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.left.right = new Node(5);
        boolean foldable = foldable(root);
        if(foldable) {
        	System.out.println("Foldable");
        }
        else {
        	System.out.println("Not foldable");
        }
	}
}
