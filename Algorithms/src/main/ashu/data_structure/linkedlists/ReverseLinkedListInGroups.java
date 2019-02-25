package main.ashu.data_structure.linkedlists;

class Node {
	public int data;
	public Node next = null;	
	public Node(int data) {
		this.data = data;
	}
}

public class ReverseLinkedListInGroups {
	public static void main(String args []) {
		Node head = new Node(1);
		Node temp = head;
		for(int i=2; i<=2; i++) {
			temp.next = new Node(i);
			temp = temp.next;
		}
		for(int i=2; i>=1; i--) {
			temp.next = new Node(i);
			temp = temp.next;
		}
		System.out.println("Original list : ");
		display(head);
		System.out.println("Reversed list : ");
		int k = 3;
		/*Node reversed = reverseInGroup(head, k);
		display(reversed);*/
		boolean isPal = checkPalindrome(head);
		if(isPal) System.out.println("LL is palindrome");
		else System.out.println("LL is not a palindrome");
		isPal = isPalindrome(head);
		if(isPal) System.out.println("LL is palindrome");
		else System.out.println("LL is not a palindrome");
		display(head);
	}

	public static Node reverseInGroup(Node head, int k) {
		if(head==null) return null;
		Node temp = head;
		int i=0;
		for(; i<k; i++) {
			if(temp==null) break;
			temp = temp.next;
		}
		//if(i<k) return head;
		Node rem = reverseInGroup(temp, k);  //like postorder traversal
		Node newHead = reverse(head, k);
		head.next = rem;
		return newHead;
	}

	public static Node reverse(Node head, int k) {
		if(head==null) return null;
		int links = k-1;
		Node prev = head, curr = head.next, next = null;
		int i = 0;
		while(curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			i++;
			if(i==links) break;
		}
		return prev;
	}

	public static void display(Node head) {
		while(head != null) {
			System.out.print(head.data+" ");
			head = head.next;
		}
		System.out.println();
	}

	///
	public static boolean checkPalindrome(Node head) {
		if(head==null) return false; 
		Node slow = head, fast = head;  
		while(fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		Node reversed = reverse(slow.next);
		slow.next = reversed;
		
		Node temp = head;
		fast = slow.next;
		while(fast != null) {
			if(temp.data != fast.data) return false;
			temp = temp.next;
			fast = fast.next;
		}
		//success case...restore the list and return true
		reversed = reverse(slow.next);
		slow.next = reversed;
		return true;
	}

	public static Node reverse(Node head) {
		Node curr = head, next = head, prev=null; 
		while(curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}
	
	//checck palindrome recursive
	static Node start = null;
	public static boolean isPalindrome(Node head) {
		start = head;
		return checkPal(head);
	}
	
	public static boolean checkPal(Node head) {
		if(start==null || head==null) return true;
		boolean rem = checkPal(head.next);
		if(rem && start.data==head.data) {
			start = start.next;
			return true;
		}
		return false;
	}
}

