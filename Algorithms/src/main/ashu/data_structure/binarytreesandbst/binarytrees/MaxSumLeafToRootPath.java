package main.ashu.data_structure.binarytreesandbst.binarytrees;

public class MaxSumLeafToRootPath {
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	
	//there can be many root-leaf path and sums
	//we need to keep updating the max sum as we go down and reach the leaf nodes
	//propagate a max object in the downward walk
	//to identify leaf while updating max sum, preorder walk will help
	static class MaxPath{
		Node leaf;
	    int sum;
	    public MaxPath(){
	    	this.sum = Integer.MIN_VALUE;
	    }
	}
	public static void getMaxPathSumLeaf(Node root) {
		MaxPath path = new MaxPath();
		getMaxPathSumLeafUtil(root, path, 0);
		System.out.println("max leaf root path sum = "+path.sum);
		printLeafRootPath(root, path.leaf);
	}
	
	//propagate current sum down the tree, update max sum in MaxPath instance
	public static void getMaxPathSumLeafUtil(Node root, MaxPath path, int sum){
		if(root == null) return;
		if(root.left == null && root.right == null) {
			sum += root.data;
			if(path.sum < sum) {
				path.sum = sum;
				path.leaf = root;
			}
			return;
		}
		getMaxPathSumLeafUtil(root.left, path, sum+root.data);
		getMaxPathSumLeafUtil(root.right, path, sum+root.data);
	}
	
	public static boolean printLeafRootPath(Node root, Node leaf) {
		if(root == null) return false;
		//if atleaf node check if on the path
		if(root.left == null && root.right == null) {
			//call equals method root.equals(leaf)
			if(root.data == leaf.data) {
				System.out.print(root.data+" ");
				return true;
			}
			return false;
		}
		boolean found =  printLeafRootPath(root.left, leaf);
		//if found on the left, current root is on the path, record it
		if(found) {
			System.out.print(root.data+" ");
		}
		//if not found on the left, lok right
		if(!found) found = printLeafRootPath(root.right, leaf);
		//if found on the right, current root is on the path, record it
		if(found) {
			System.out.print(root.data+" ");
		}
		//return the status of found to be used by the internal nodes while traversing up the tree
		return found;
		
	}

	public static void main(String args[]) {
		Node root = new Node(10);
		root.left = new Node(-2);
		root.right = new Node(7);
		root.left.left = new Node(8);
		root.left.right = new Node(-4);
		
		getMaxPathSumLeaf(root);
		
	}
}
