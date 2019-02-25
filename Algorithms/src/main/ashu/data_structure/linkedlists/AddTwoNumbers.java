package main.ashu.data_structure.linkedlists;

public class AddTwoNumbers {
   private static class Node {
	   int data;
	   Node next=null;
	   public Node(int data) {
		   this.data = data;
	   }
   }
   
   //lists are not given in reversed form
   //find the point of alignment   
   private static class Carry {
       public int val;
       public Carry(int val) {
           this.val = val;
       }
   }
   //do padding for smaller list by adding dummy nodes in the beginning
   //then reverse and add or add recursively
   public static Node addTwoNumbers(Node l1, Node l2) {
       Carry c = new Carry(0);
       int len1 = 0, len2 = 0;
       Node l = l1;
       while(l != null) {
           len1++; l = l.next;
       }
       l = l2;
       while(l != null) {
           len2++; l = l.next;
       }
       if(len1 < len2) {
           for(int i=1; i<=len2-len1; i++) {
               Node newNode = new Node(0);
               newNode.next = l1; l1 = newNode;
           }
       }
       else if(len2 < len1) {
           for(int i=1; i<=len1-len2; i++) {
               Node newNode = new Node(0);
               newNode.next = l2; l2 = newNode;
           }
       }
       Node ans = getSum(l1, l2, c);
       if(c.val > 0) {
          Node  newNode = new Node(1);
           newNode.next =ans;
           ans = newNode;
       }
       return ans;
  }
public static Node getSum(Node l1, Node l2, Carry c) {
	if(l1 == null && l2 == null) return null;
     if(l1 == null) return l2;
   if(l2 == null) return l1;
   Node right = getSum(l1.next, l2.next, c);
   int sum = l1.data + l2.data + c.val;
   Node curr = new Node(sum % 10);
   c.val = sum/10;
   curr.next = right;
   return curr;
}

private static class Visit {
	   int carry = 0;
	   int len1 = 0;
	   int len2 = 0;
}
   /*public static Node addNumbers(Node list1, Node list2) {
	   int len1 = length(list1);
	   int len2 = length(list2);
	   Visit visit = new Visit();
	   visit.carry = 0;
	   visit.len1 = len1;
	   visit.len2 = len2;
	   Node head = getSum(list1, list2, visit);
	   if(visit.carry >0) {
		   Node temp = new Node(visit.carry%10);
		   temp.next = head;
		   head = temp;
	   }
	    return head;
   }*/
   
   //in class of large lists creating new node every time is costly
   //use space of larger list
   /*public static Node getSum(Node large, Node  small, Visit visit) {
	   if(large == null && small == null) return null;
	   if(visit.len1 < visit.len2) return getSum(small, large, visit);
	   if(visit.len1 > visit.len2) {
		   return getSum(large.next, small, l1-1, l2, carry);
	   } 
	   Node rightSum = getSum(large.next, small.next, l1-1, l2-1, carry);
	   int sum = 0;
	   if(l1 == l2) {
		   sum = rightSum.data + large.data + small.data+ carry.value; 
	   } else {
		   sum = rightSum.data + large.data + carry.value;
	   }	    
	   large.data = sum % 10;
	   carry.value = sum/10;
	   return large;
   }*/
   
   public static int length(Node node) {
	   if(node == null) return 0;
	   return 1 + length(node.next);
   }
   
   public static void print(Node head) {
	   if(head == null) return;
	   System.out.print(head.data+" ");
	   print(head.next);
   }
   public static void main(String args []) {
	   Node num1 = new Node(1);
	   Node t = num1;
	   for(int i=2; i<=5; i++) {
		   t.next = new Node(i);
		   t = t.next;
	   }
	   Node num2 = new Node(1);
	   t = num2;
	   for(int i=2; i<=3; i++) {
		   t.next = new Node(i);
		   t = t.next;
	   }
	   System.out.println("num1 is : ");
	   print(num1);
	   System.out.println("num2 is : ");
	   print(num2);
	   Node sum = addTwoNumbers(num1, num2);
	   System.out.println("Sum list is : ");
	   print(sum);
   }
}
