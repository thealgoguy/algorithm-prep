package main.ashu.data_structure.linkedlists;

import java.util.HashMap;
import java.util.Map;
//clone a linked list with next and random pointers
//Approach 1 : Hashing , http://www.geeksforgeeks.org/clone-linked-list-next-arbit-pointer-set-2/
//Approach 2 : Inserting cloned into original, http://www.geeksforgeeks.org/clone-linked-list-next-random-pointer-o1-space/

public class CloneLinkedListWithRandomPointer {
    private static class Node {
    	int data;
    	Node next = null, random = null;
    	public Node(int x) { 
    		this.data = x;
    	}
    }
    
    public Node cloneUsingHashing(Node head) {
    	Map<Node, Node> map = new HashMap<Node, Node>();
    	Node orig = head, copy = null;
    	while(orig != null) {
    		copy = new Node(orig.data);
    		map.put(orig, copy);
    		orig = orig.next;
    	}
    	orig = head;
    	while(orig != null) {
    		copy = map.get(orig);
    		copy.next = map.get(orig.next);
    		copy.random = map.get(orig.random);
    		orig = orig.next;
    	}
    	return map.get(head);
    }
    
    public Node cloneConstantSpace(Node head) {
    	Node orig = head;
    	//1. insert copies in front of every original node
    	while(orig != null) {
    		Node next = orig.next;
    		orig.next = new Node(orig.data);
    		orig.next.next = next;
    		orig = next;
    	}
    	Node temp = head.next;  //save head of cloned LL
    	//2. change random links of cloned LL
    	orig = head;
    	while(orig != null) {
    		orig.next.random = orig.random.next;
    		orig = (orig.next != null) ? orig.next.next : orig.next;
    	}
    	//3. split original and cloned LL
        orig = head; 
        Node copy = temp;
        while(orig != null && copy != null) {
        	//change links
        	orig.next = (orig.next != null) ? orig.next.next : orig.next;
        	copy.next = (copy.next != null) ? copy.next.next : copy.next;
        	//move pointers
        	orig = orig.next;
        	copy = copy.next;
        }
        //orig.next = null;
        return copy;
    }
}
