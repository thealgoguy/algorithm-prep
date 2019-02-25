package main.ashu.data_structure.linkedlists;

//not working
public class RemoveDuplicates {
	private static class Node {
		int data;
		Node next=null;
		public Node(int data) {
			this.data = data;
		}
	}
	
	//write a recursive version using post-order traversal
	public static Node removeDuplicates(Node head) {
		// take a dummy node so we don't have to handle null for head node
		Node dummy = new Node(0);
		dummy.next = head;
		Node temp = dummy;
		// compare next 2 keys in loop[, of duplicate found remove all such
		// duplicates
		// and set links else increment pointer by one
		while (temp.next != null && temp.next.next != null) {
			if (temp.next.data == temp.next.next.data) {
				int duplicate = temp.next.data;
				while (temp.next != null && temp.next.data == duplicate) {
					temp.next = temp.next.next;
				}
			} else {
				temp = temp.next;
			}
		}
		return dummy.next;
	}
	public static void print(Node head) {
		   if(head == null) return;
		   System.out.print(head.data+" ");
		   print(head.next);
	   }
	public static void main(String args []) {
		Node head = new Node(1);
		head.next = new Node(1);;
		head.next.next = new Node(1);;
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(3);
		System.out.print("original : ");
		print(head);
		Node newHead = removeDuplicates(head);
		System.out.print("\nwith duplicates removed : ");
		print(newHead);
	}
}
