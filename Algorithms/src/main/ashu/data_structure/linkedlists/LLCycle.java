package main.ashu.data_structure.linkedlists;

class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	  }

public class LLCycle {
	public ListNode detectCycle(ListNode head) {
        if(head==null || head.next==null) return null;
        ListNode slow = head, fast = head;
        while(fast!= null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }
        if(slow==fast) {
            slow = head;
            while(slow != fast){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        } else {
            return null;
        }
    }
	
	public static void main(String args []) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = head;
		LLCycle ll = new LLCycle();
		ListNode cycle = ll.detectCycle(head);
		if(cycle == null)
			System.out.println("No cycle");
		else System.out.println("Cycle begins at "+cycle.val);
	}
}
