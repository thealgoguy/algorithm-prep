package main.ashu.data_structure.binarytreesandbst.binarytrees;

//Given an integer ‘K’ and a binary tree in string format. 
//Every node of a tree has value in range from 0 to 9.
//We need to find sum of elements at K-th level from root. The root is at level 0.
//Tree is given in the form: (node value(left subtree)(right subtree))
//tree = "(0(5(6()())(4()(9()())))(7(1()())(3()())))"

public class SumOfNodesAtKthLevelnStringTree {
    public static void main(String args []) {
    	String tree = "(0(5(6()())(4()(9()())))(7(1()())(3()())))";
    	int k = 2;
    	int sum = 0;
    	int level = -1;
    	for(int i=0; i < tree.length(); i++) {
    		if(tree.charAt(i) == '(') level++;
    		else if(tree.charAt(i) == ')') level--;
    		else if(level == k) {
    			sum += Integer.valueOf(Character.toString(tree.charAt(i)));
    		}
    	}
    	System.out.println("Sum of nodes at kth level = "+sum);
    }
}
