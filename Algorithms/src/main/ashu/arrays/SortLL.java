package main.ashu.arrays;

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class SortLL {
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
    
    public static void main(String args []) {
    	SortLL ll =  new SortLL();
    	ListNode head = new ListNode(3);
    	head.next = new ListNode(2);
    	head.next.next= new ListNode(4);
    	head = ll.sortList(head);
        ListNode temp = head;
        while(temp != null){
        	System.out.println(temp.val);
        	temp = temp.next;
        }
    }
}
