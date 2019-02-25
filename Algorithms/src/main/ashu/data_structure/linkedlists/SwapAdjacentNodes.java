package main.ashu.data_structure.linkedlists;

public class SwapAdjacentNodes {
	private static class Node {
    	int data;
    	Node next = null, random = null;
    	public Node(int x) { 
    		this.data = x;
    	}
    }
	
	//using dummy head node..always compare with next node
	public static Node swapPairs2(Node head) {
		if(head == null || head.next == null) return head;
		Node dummyHead = new Node(1);
		dummyHead.next = head;
		Node temp = dummyHead;
		while(temp.next != null && temp.next.next != null) {
			Node current = temp.next;
			Node fwd = current.next;
			current.next = fwd.next;
			fwd.next = current;
			temp.next = fwd;
			temp = current;
		}
		return dummyHead.next;
	}
	//wrong...correct it
	public static Node swapPairs1(Node head) {
		if(head == null || head.next == null) return head;
		Node prev = null, current = head, fwd = current.next;
		while(fwd != null) {
			Node next = fwd.next;
			if(prev == null) {
				head = fwd;
			}
			else {
				prev.next = fwd;
			}
			current.next = fwd.next;
			fwd.next = current;
			
			prev = current;
			current = next;
			fwd = current.next;
		}
		return head;
	}
	
	 public static void print(Node head) {
		   if(head == null) return;
		   System.out.print(head.data+" ");
		   print(head.next);
	   }
	public static void main(String args []) {
		Node head = new Node(1);
		Node temp = head;
		for(int i=2; i<=10; i++) {
			temp.next = new Node(i);
			temp = temp.next;
		}
		System.out.print("original linked list is : ");
		print(head);
		System.out.print("\nlinked list after swapping adjacent pairs is : ");
        Node newHead = swapPairs2(head);
		print(newHead);
	}
}
