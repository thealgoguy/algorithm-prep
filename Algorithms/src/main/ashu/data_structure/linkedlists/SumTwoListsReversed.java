package main.ashu.data_structure.linkedlists;

//adding two linked lists. LLs are given in reversed form
//Traversal on both LLs is same as in merging two LLs
//now do this without modification. Use recursion. Just remember to align the numbers before making recursive sum call.I solved on leetcode.
public class SumTwoListsReversed {
	private static class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; }
		  }
	//iteration is similar to merging of linked lists
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummyHead = new ListNode(0);  
        ListNode current = dummyHead; //return ans.next
        int carry = 0;
        while(l1 != null && l2 != null) {
        	int sum = l1.val + l2.val + carry;
        	current.next = new ListNode(sum%10);
            carry = sum/10;
            l1 = l1.next; l2 = l2.next;
        }
        while(l1 != null) {
        	current.next = new ListNode((l1.val + carry)%10);
            carry = (l1.val +carry)/10;
           l1 = l1.next;
        }
        while(l2 != null) {
        	current.next = new ListNode((l2.val+carry)%10);
            carry = (l2.val +carry) /10;
             l2 = l2.next;
        }
        //append the leftover carry
        if(carry >0) current.next = new ListNode(1);
        current = current.next;
        return dummyHead.next;
    }
	
	public static void main(String args []) {
		ListNode l1 = new ListNode(2); l1.next = new ListNode(4); l1.next.next = new ListNode(3);
		ListNode l2 = new ListNode(5); l2.next = new ListNode(6); l2.next.next = new ListNode(4);
		ListNode sum = addTwoNumbers(l1, l2);
		System.out.println();
	}
}
