package main.ashu.data_structure.linkedlists;

//https://www.programcreek.com/2015/03/leetcode-odd-even-linked-list-java/
//do it in -place without creating new nodes each time
public class OddEvenLinkedList {
	private static class Node {
		int data;
		Node next=null;
		public Node(int data) {
			this.data = data;
		}
	}
	
	//does in-place, if u do it by creating copies of nodes, 
	//u won't have to bother about links but the question is to do in-place
	public static Node oddEven1(Node head) {
		if(head == null || head.next == null || head.next.next == null) return head;
		Node odd = head;
		Node even = head.next;
		Node evenHead = even; //saving for connecting odd nodes with head of even nodes
		//even will always be one step ahead of odd
		while(even != null && even.next != null) {
			odd.next = even.next;      //set odd's next
			even.next = odd.next.next; //set even's next
			//increment odd and even for next loop
			odd = odd.next;
			even = even.next;
		}
	  odd.next = evenHead; //connect tail of odd with head of even
		return head;
	}
	
	//simpler version than oddEven1
	public static Node oddEven2(Node head) {
		if(head == null || head.next == null || head.next.next == null) return head;
		Node odd = new Node(0);
		Node even = new Node(0);
		Node oddHead = odd;
		Node evenHead = even;
		int count = 1;
		while(head != null) {
			if(count %2 ==0) {
				even.next = head;
				even = even.next;
			} else {
				odd.next = head;
				odd = odd.next;
			}
			count++;
			head = head.next;
		}
		odd.next = null; even.next = null;
		System.out.print("\nodd : ");
		print(oddHead.next);
		System.out.print("\neven : ");
		print(evenHead.next);
		odd.next = evenHead.next;
		return oddHead.next;
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
		//oddEven1(head);
		System.out.print("\nlinked list after odd even ordering is : ");
		oddEven2(head);
		print(head);
	}
}
