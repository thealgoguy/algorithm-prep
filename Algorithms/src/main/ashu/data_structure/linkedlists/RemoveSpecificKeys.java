package main.ashu.data_structure.linkedlists;

//Remove all elements from a linked list of integers that have value val.
//Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
//Return: 1 --> 2 --> 3 --> 4 --> 5
//https://www.programcreek.com/2014/04/leetcode-remove-linked-list-elements-java/
public class RemoveSpecificKeys {
	private static class Node {
		int data;
		Node next=null;
		public Node(int data) {
			this.data = data;
		}
	}
	
	//using dummy head node..
	//initialise temp with dummy head and compare with next of temp
	public static Node removeKeys1(Node head, int key) {
		if(head == null) return head;
		Node dummyHead = new Node(0);
		dummyHead.next = head;
		Node temp = dummyHead;
		while(temp.next != null) {
			if(temp.next.data == key) {
				temp.next = temp.next.next;
			}
			else temp = temp.next;
		}
		return dummyHead.next;
	}
	
	//without using dummy nodes...use prev pointer
	public static Node removeKeys2(Node head, int key) {
		Node temp = head;
		Node prev = null;
		while(temp != null) {
			if(temp.data == key) {
				if(prev == null) { //key found at head also
					head = head.next;
				} else {
					prev.next = temp.next;
				}
			} 
			prev = temp;
			temp = temp.next;
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
		head.next = new Node(2);;
		head.next.next = new Node(6);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(4);
		head.next.next.next.next.next = new Node(5);
		head.next.next.next.next.next.next = new Node(6);
		System.out.print("original : ");
		print(head);
		int k = 6;
		Node newHead = removeKeys2(head, k);
		System.out.print("\nwith keys removed : ");
		print(newHead);
	}
	
}
