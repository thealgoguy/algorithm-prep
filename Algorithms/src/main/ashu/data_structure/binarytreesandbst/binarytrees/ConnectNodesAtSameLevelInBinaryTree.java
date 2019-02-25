package main.ashu.data_structure.binarytreesandbst.binarytrees;

//https://www.geeksforgeeks.org/connect-nodes-at-same-level-with-o1-extra-space/
public class ConnectNodesAtSameLevelInBinaryTree {
	private static class Node {
		int data;
		Node left, right, nextRight;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	
	public void connectLinearSpace(Node root){
		if(root == null) return;
		if(root.left != null){
			root.left.nextRight = root.right;
		}
		if(root.right != null) {
			root.right.nextRight = (root.nextRight != null) ? root.nextRight.left : null;
		}
		connectLinearSpace(root.left);
		connectLinearSpace(root.right);
	}
	
	//set next right of level i before setting for i+1
	//if we traverse the nextRight node before the left and right children (root, nextRight, left),
	//then we can make sure that all nodes at level i have the nextRight set, before the level i+1 nodes
	//visit next-right, left, right
	//runs in constant space..normal level/preorder will use O(n) space
	public void connect(Node root) {
		if(root == null) return;
		if(root.nextRight != null) connect(root.nextRight);
		if(root.left != null) {
			if(root.right != null) {
				root.left.nextRight = root.right;
				root.right.nextRight = getNextRightOfRightChild(root);
			} else {
				root.left.nextRight = getNextRightOfRightChild(root);
			}
			connect(root.left);
		}
		else if(root.right != null) {
			root.right.nextRight = getNextRightOfRightChild(root);
			connect(root.right);
		}
		else {//at leaf node
			connect(getNextRightOfRightChild(root));
		}
	}
	
	public Node getNextRightOfRightChild(Node root) {
		Node temp = root.nextRight;
		while(temp != null) {
			if(temp.left != null) return temp.left;
			if(temp.right != null) return temp.right;
			temp = temp.nextRight;
		}
		return temp;
	}
	
	public static void main(String args []) {
		
	}
}
