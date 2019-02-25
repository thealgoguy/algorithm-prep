package main.ashu.arrays;

import java.util.Arrays;

public class SortByFrequency {
   public static void main(String args []) {
	   int a[] = {2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12}; 
	   int n = a.length;
	   
	   sort(a);
	   System.out.println("After frequency sorting : "+Arrays.toString(a));
   }
   
   private static void sort(int a[]) {
	   int n = a.length;
	   //store counts in self balancing BST and then do inorder traversal
	   //can also be solved using hashing
   }
}
