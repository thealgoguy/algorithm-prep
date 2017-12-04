package geeksforgeeks;

//http://algorithms.tutorialhorizon.com/print-all-the-nodes-which-are-x-distance-from-the-given-node/

class BTNode {
	public int data;
	public BTNode left=null, right=null;
	public BTNode(int data) {
		this.data = data;
	}
}

public class NodesAtKDistFromTarget {
	
	public void printNodesAtDistK(BTNode root, BTNode target, int k) {
		if(root==null || target==null || k<0) return;
		int len = getDistanceFromRoot(root, target, 0);
		System.out.println("Distance of target from root : "+len);
		if(len==-1) return;
		System.out.print("Nodes at distance '3' from Node '5' are : ");
		walkAndPrint(root, target, len, k);
	}
	// a preorder walk over the tree
	//nodes being printed while walking down and climbing up	
	public BTNode walkAndPrint(BTNode root, BTNode target, int len, int k){
		if(root==null || target==null || k<0 || len <0) return null;
		if(root == target) {
			printDown(root, k);  //walk down
			return root;
		}
		else {
			BTNode left = walkAndPrint(root.left, target, len-1, k);
			//if came up from the left and found the target, look into the right direction
			if(left != null) {
				printDown(root.right, k-len-1);  //walk down
				return root;
			} 
			BTNode right = walkAndPrint(root.right, target, len-1, k);
			//if came up from the right and found the target, look into the left direction
			if(right != null) {
				printDown(root.left, k-len-1);  //walk down
				return root;
			}
		}
		return null;
	}
	//print nodes at distance k from the root node(only downward)
	public void printDown(BTNode root, int k) {
		if(root==null) return;
		if(k==0) System.out.print(root.data+" ");
		printDown(root.left, k-1);
		printDown(root.right, k-1);
	}
	
	//distance of the node target from the root
	public int getDistanceFromRoot(BTNode root, BTNode target, int len){
		if(root==null || target==null) return -1;
		if(root==target) return len;
		int left = getDistanceFromRoot(root.left, target, len+1);
		if(left > 0) return left;
		int right = getDistanceFromRoot(root.right, target, len+1);
		return (right > 0) ? right : -1;
	}
		
     public static void main(String args []) {
    	 BTNode root = new BTNode(1);
 		root.left = new BTNode(2);
 		root.right = new BTNode(3);
 		root.left.left = new BTNode(4);
 		root.left.left.left = new BTNode(9);
 		root.left.right = new BTNode(5);
 		root.left.right.left = new BTNode(6);
 		root.left.right.right = new BTNode(7);
 		root.left.right.right.right = new BTNode(10);
 		root.left.right.right.right.left = new BTNode(11);
 		root.right.right = new BTNode(8);
 		NodesAtKDistFromTarget printer = new NodesAtKDistFromTarget();
 		printer.printNodesAtDistK(root, root.left.right, 3);
     }
}
