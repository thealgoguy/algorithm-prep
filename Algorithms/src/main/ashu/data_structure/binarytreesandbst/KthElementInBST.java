package main.ashu.data_structure.binarytreesandbst;

import java.util.Stack;

public class KthElementInBST {
	
	Node root = null;
    static private class Node {
    	public int data;
    	public Node left=null, right=null;
    	public Node(int data) {
    		this.data = data;
    	}
    }
    
    static private class Count {
    	int value=0;
    	int count=-1;
		public Count(int count) {
			this.count = count;
		}
    	
    }
    
    public static void  kthLargest(Node root, int k, Count cnt) {
    	if(root==null || k <0 || cnt.count > k) return;
    	kthLargest(root.right, k, cnt);
    	cnt.count++;
    	if(k==cnt.count){
           cnt.value = root.data;
    		return;
    	}
    	//k--;
      kthLargest(root.left, k, cnt);

    }
    
    public static int kthSmallestIterative(Node root, int x) {
    	if(root==null || x<0) return -1;
    	Stack<Node> s = new Stack<Node>();
    	s.push(root);
    	Node temp = root;
    	while(temp != null) {
    		s.push(temp);
    		temp = temp.left;
    	}
    	while(!s.isEmpty()) {
    		Node next = s.pop();
    		if(x==1) return next.data;
    		x--;
    		if(next.right != null) {
    			Node t = next.right;
    			s.push(t);
    			while(t.left != null) {
    				s.push(t);
    				t= t.left;
    			}
    		}
    	}
    	return -1;
    }

    public static void main(String args []) {
    	Node root = new Node(50);
    	root.left = new Node(30);
    	root.left.left = new Node(20);
    	root.left.right = new Node(40);
    	root.right = new Node(70);
    	root.right.left = new Node(60);
    	root.right.right = new Node(80);
    	
    	/*k = 4;
    	System.out.print("kth smallest element in bst, k="+k+" is : ");
    	kthSmallest(root);
    	System.out.println(kth);
    	
    	k=4;
    	System.out.println("kth smallest using stacks, k="+k+" is : "+kthSmallestIterative(root, 4));*/
    	
    	for(int i=1; i<=7; i++){
    		Count cnt = new Count(0);
    		System.out.print("kth largest element in bst, k="+i+" is : "); 
    		kthLargest(root, i, cnt);
        	System.out.println(cnt.value);
    	}
    	
    	
    	
    	
    }
}
