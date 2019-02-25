package main.ashu.data_structure.linkedlists;

import java.util.Stack;

public class SinglyLLToBBST {
	//current node of linked list
	static LLNode current = null;
	
    private static class LLNode {
    	public int data;
    	public LLNode next = null;
    	public LLNode(int d){
    		this.data = d;
    	}
    }    
    private static class BTNode {
    	public int data;
    	public BTNode left=null, right=null;
    	public BTNode(int d){
    		this.data = d;
    	}
    }
    
    private void add(LLNode head, int data) {
    	LLNode newNode = new LLNode(data);
    	if(head==null){
    		head = newNode;
    	}
    	else {
    		while(head.next != null) head = head.next;
    		head.next  = newNode;
    	}
    }
    
    public BTNode createBBST(LLNode head) {
    	if(head==null) return null;
    	int n = 0;
        current = head;
    	while(head != null) {
    		n++; head= head.next;
    	}
    	return makeBBST(n);
    }
    
    //creating in inorder fashion since list is sorted
    public BTNode makeBBST(int size) {
    	if(size<=0) return null;
    	BTNode left = makeBBST(size/2);
    	BTNode root = new BTNode(current.data);
    	root.left = left;
    	current = current.next;
    	root.right = makeBBST(size-size/2-1);
    	return root;
    }
    
    public void inorder(BTNode root) {
    	if(root==null) return;
    	inorder(root.left);
    	System.out.print(root.data+" ");
    	inorder(root.right);
    }
    
    //return true if a pair with sum = key exists in the bbst
    public boolean sumExists(BTNode root, int key) {
    	if(root==null) return false;
    	Stack<BTNode> s1 = new Stack<BTNode>();
    	Stack<BTNode> s2 = new Stack<BTNode>();
    	/*s1.push(root);
    	s2.push(root);*/
    	BTNode temp = root;
    	while(temp != null) {
    		s1.push(temp);
    		temp = temp.left;
    	}
    	temp = root;
    	while(temp != null) {
    		s2.push(temp);
    		temp = temp.right;
    	}
    	
    	while(!s1.isEmpty() && !s2.isEmpty()) {
    		int left = s1.peek().data;
    		int right = s2.peek().data;
    		if(left + right == key) return true;
    		if(left + right < key){
    			temp = s1.pop();
    			if(temp.right != null) {
    				temp = temp.right;
    				//s1.push(temp);
    				while(temp.left != null){
    					s1.push(temp);
    					temp = temp.left;
    				}
    			}
    		}
    		else {
    			temp = s2.pop();
    			if(temp.left != null) {
    				temp = temp.left;
    				//s2.push(temp);
    				while(temp.right != null){
    					s2.push(temp);
    					temp = temp.right;
    				}
    			}
    		}
    	}
    	return false;
    }
    
    
    public static void main(String args []) {
    	SinglyLLToBBST sl = new SinglyLLToBBST();    	
    	LLNode head = new LLNode(1);
        for(int i=2; i<=7; i++) sl.add(head, i);
        current = head;
        BTNode root = sl.createBBST(head);
        System.out.print("Inorder traversal of BBST : ");
        sl.inorder(root);
        System.out.println();
        int sum = 13;
        boolean sumExists = sl.sumExists(root, sum);
        if(sumExists) System.out.println("Sum = "+sum+" exists in the tree");
        else System.out.println("Sum = "+sum+" doesn't exist in the tree");
    }
}
