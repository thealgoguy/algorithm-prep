package main.ashu.data_structure.linkedlists;

//merge sort on a linked list
public class MergeSortLinkedList {
	private static class ListNode {
   	 int val;
   	ListNode next = null;
   	 public ListNode(int val) {
   		 this.val = val;
   	 }
    }
	public ListNode sortList(ListNode head) {
        if(head == null || head.next==null) return head;
        ListNode slow=head, fast = head.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }       
        ListNode left = head;
        ListNode right = slow.next;
        slow.next = null;
        left = sortList(left);
        right = sortList(right);
        head = merge(left, right);
        return head;
    }
    //merge two sorted linked lists
    public ListNode merge(ListNode left, ListNode right) {
        if(left == null) return right;
        if(right == null) return left;
        if(left.val < right.val) {
            left.next = merge(left.next, right);
            return left;
        }
        else {
            right.next = merge(left, right.next);
            return right;
        }
    }
}
