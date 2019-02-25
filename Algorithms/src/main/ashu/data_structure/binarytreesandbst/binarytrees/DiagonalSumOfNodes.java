package main.ashu.data_structure.binarytreesandbst.binarytrees;

import java.util.HashMap;
import java.util.Map;

//Similar - find vertical sum of nodes in BT, see - https://www.geeksforgeeks.org/vertical-sum-in-binary-tree-set-space-optimized/
//For space-efficiency, use DLL in place of map, see - 
public class DiagonalSumOfNodes {
	private static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	
	public static void getDiagonalSum(Node root) {
		if(root == null) return;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		getDiagonalSumUtil(root, map, 0);
		for(int i=0; i > -(map.keySet().size()); i--) {
			System.out.print(map.get(i)+" ");
		}
	}
	
	public static void getDiagonalSumUtil(Node root, Map<Integer, Integer> map, int slope) {
		if(root == null) return;
		if(map.containsKey(slope)) {
			map.put(slope, map.get(slope)+root.data);
		} else {
			map.put(slope, root.data);
		}
		getDiagonalSumUtil(root.left, map, slope-1);
		getDiagonalSumUtil(root.right, map, slope);
	}

	public static void main(String args[]) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(9);
		root.left.right = new Node(6);
		root.right.left = new Node(4);
		root.right.right = new Node(5);
		root.right.left.left = new Node(12);
		root.right.left.right = new Node(7);
		root.left.right.left = new Node(11);
		root.left.left.right = new Node(10);
		System.out.println("Diagonal sum of nodes with slope -1 are :");
		getDiagonalSum(root);
	}
}
