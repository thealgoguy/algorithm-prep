package main.ashu.data_structure.linkedlists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddFloatingPointLLs {
	private static Node ll1 = null,  ll2 = null;
   private static class Node {
	   private String val;
	   private Node next;
	   public Node(String x) {
		   this.val = x;
		   this.next = null;
	   }
   }
  private static class Carry {
	  private int val;
	  public Carry(int x) {
		  this.val = x;
	  }
  }
   public static Node convert(String s) {
	   Node ll = new Node(s.charAt(0)+""), tail = ll;
	   for(int i=1; i<s.length(); i++) {
		   tail.next = new Node(s.charAt(i)+"");
		   tail = tail.next;
	   }
	   return ll;
   }
   
   public static void align() {
	   int i1=0, f1=0, i2=0, f2=0;
	   //count integral and fractional parts in the two LLs
	   Node temp = ll1;
	   while(temp != null && !temp.val.equals(".")) {
		   i1++; temp = temp.next;
	   }
	   if(temp != null) {
		   temp = temp.next;
		   while(temp != null) {
			   f1++; temp = temp.next;
		   }
	   }
	   temp = ll2;
	   while(temp != null && !temp.val.equals(".")) {
		   i2++; temp = temp.next;
	   }
	   if(temp != null) {
		   temp = temp.next;
		   while(temp != null) {
			   f2++; temp = temp.next;
		   }
	   }
	   System.out.println("i1="+i1+" f1="+f1+", i2="+i2+" f2="+f2);
	   //align integral parts by adding zeros at the beginning
	   if(i1 <i2) {
		   for(int i=1; i<=i2-i1; i++) {
			   Node head = new Node("0");
			   head.next = ll1; ll1 = head;
		   }
	   }
	   else if(i2 < i1) {
		   for(int i=1; i<=i1-i2; i++) {
			   Node head = new Node("0");
			   head.next = ll2; ll2 = head;
		   }
	   }
	   //align fractional parts
	   if(f1 < f2) {
		   temp = ll1;
		   while(temp.next != null) temp = temp.next;
		   for(int i=1; i<=f2-f1; i++) {
			   Node tail = new Node("0");
			   temp.next = tail; temp = tail;
		   }
	   }
	   if(f2 < f1) {
		   temp = ll2;
		   while(temp.next != null) temp = temp.next;
		   for(int i=1; i<=f1-f2; i++) {
			   Node tail = new Node("0");
			   temp.next = tail; temp = tail;
		   }
	   }
   }
   
  
   public static Node add() {
	   align();
	   System.out.println("After alignments, the linked lists are :");
	   display(ll1); display(ll2);
	   Carry c = new Carry(0);
	   Node sum = addRecursively(ll1, ll2, c);
	   if(c.val > 0) {
		   Node head = new Node(1+"");
		   head.next = sum; sum = head;
	   }
	   return sum;
   }
   
   public static Node addRecursively(Node l1, Node l2, Carry c) {
	   if(l1 == null) return l2;
	   if(l2 == null) return l1;
	   Node rightSum = addRecursively(l1.next, l2.next, c);
	   //in the return call, check for decimal point
	   Node head = null;
	   if(l1.val.equals(".")) {
		  head = new Node(".");
	   }
	   else {
		   int curr = Integer.parseInt(l1.val) + Integer.parseInt(l2.val) + c.val;
		   head = new Node((curr % 10)+"");
		   c.val = curr/10;
	   }
	   head.next = rightSum;
	   return head;
	   
   }
   
   public static void display(Node list) {
	   while(list != null) {
		   System.out.print(list.val);
		   list = list.next;
	   }
	   System.out.println();
   }
   public static void main(String args []) throws IOException {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   System.out.println("Enter the floating point number");
	   String s = br.readLine();
	   ll1 = convert(s);
	   System.out.println("Enter the second floating point number");
	   s = br.readLine();
	   ll2 = convert(s);
	   System.out.println("The linked lists are :");
	   display(ll1); display(ll2);
	   Node sum = add();
	   System.out.println("The sum of the two linked lists is : ");
	   display(sum);
   }
}
