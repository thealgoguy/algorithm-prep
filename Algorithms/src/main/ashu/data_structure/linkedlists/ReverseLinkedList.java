package main.ashu.data_structure.linkedlists;

public class ReverseLinkedList {
     private static class Node {
    	 int data;
    	 Node next = null;
    	 public Node(int val) {
    		 this.data = val;
    	 }
     }
     
     public Node reverseIterative(Node head) {
    	 if(head == null || head.next == null) return head;
    	 Node prev = null, temp = head;
    	 while(temp != null) {
    		 Node next = temp.next;
    		 temp.next = prev;
    		 prev = temp;
    		 temp = next;
    	 }
    	 return prev;
     }
     
     public Node reverseRecursively(Node head){
    	 if(head == null || head.next == null) return head;
    	 Node p = reverseRecursively(head.next);
    	 head.next.next = head;
    	 head.next = null;
    	 return p;
     }
     
     //reverse in groups of k
     public Node reverseKGroup(Node head, int k) {
         if(head == null || k<=0) return head;
         Node prev = head, temp = head;
         int c = 0;
         Node next = null;
         if(less(head, k)) return head;
         while(temp != null) {
             if(c==k) break;
             c++;
             next = temp.next;
             temp.next = prev;
             prev = temp;
             temp = next;
         }
         head.next = reverseKGroup(next, k);    
         return prev;
     }
     
     public boolean less(Node node, int k) {
         while(node != null) {
             node = node.next;
             k--;
         }
         return k > 0;
     }
}
