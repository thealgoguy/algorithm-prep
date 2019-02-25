package main.ashu.data_structure.linkedlists;

//https://www.programcreek.com/2013/02/leetcode-partition-list-java/
//Given a linked list and a value x, partition it such that 
//all nodes less than x come before nodes greater than or equal to x.
//preserve the relative order of elements
public class PartitionLinkedList {
	private static class Node {
		int data;
		Node next=null;
		public Node(int data) {
			this.data = data;
		}
	}
	
	public static Node partition(Node head, int k) {
		if(head == null || head.next == null) return head;
		Node smaller = new Node(0); //dummy
		Node larger = new Node(0);  //dummy
		Node smallerHead = smaller; //copying for connection
		Node largerHead = larger;
		while(head != null) {
			if(head.data < k) {
				smaller.next = head;
				smaller = smaller.next;
			} else {
				larger.next = head;
				larger = larger.next;
			}
			head = head.next;
		}
		//split the two lists
		smaller.next = null; ;larger.next = null;
		//connect tail of smaller with head of larger
		smaller.next = largerHead.next;
		larger.next = null;  //VVI or will end in infinite loop
		return smallerHead.next;
	}
	public static void print(Node head) {
		   if(head == null) return;
		   System.out.print(head.data+" ");
		   print(head.next);
	   }
	
	public static void main(String args []) {
		Node head = new Node(1);
		head.next = new Node(4);
		head.next.next = new  Node(3);
		head.next.next.next = new  Node(2);
		head.next.next.next.next = new  Node(5);
		head.next.next.next.next.next = new  Node(2);
		System.out.print("Original linked list is : ");
		print(head);
		int k = 3;
		head = partition(head, k);
		System.out.print("\nlinked list partitioned aroiund key : "+k+" is : ");
		print(head);
		
	}
}
