package main.ashu.data_structure.linkedlists;

public class DetectRemoveCycle {
	private static class Node {
		int data;
		Node next = null;
		public Node(int val) {
			this.data = val;
		}
	}
	//also called tortoise and hair algorithm or slow-fast pointer approach
	public static boolean detectAndRemoveCycle(Node head) {
		if(head == null) return false;
		boolean cycle = false;
		Node slow = head, fast = head;
		while(fast != null && fast.next != null && slow != fast) {
			slow = slow.next;
			fast = fast.next.next;
		}
		if(slow == fast) {
			System.out.println("Linked list has cycle");
			cycle = true;
			slow = head;
		    while(slow.next != fast.next) {
		    	slow = slow.next;
		    	fast = fast.next;
		    }
		    System.out.println("The node at which cycle exists is : "+slow.data);
		    System.out.println("Removing the cycle....");
		    slow.next = null;
		}
		return cycle;
	}
	
	public static void main(String s []) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = head;
		System.out.println("Linked list with cycle");
		DetectRemoveCycle.detectAndRemoveCycle(head);
	}
}
