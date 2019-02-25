package main.ashu.arrays;

import java.util.Arrays;

public class MergeSortedArraysInPlace {
  public static void main(String args []) {
	  int a [] = {1, 5, 9, 10, 15, 20};
	  int b [] = {2, 3, 8, 13};  
	  merge(a, b);
	  System.out.println("After merging, arrays are : "+Arrays.toString(a)+" "+Arrays.toString(b));
	  
  }
  
  private static void merge(int a[], int b[]) {
	  int m = a.length;
	  int n = b.length;
	  for(int i=n-1; i>=0; i--) {
		  /* Find the smallest element greater than ar2[i]. Move all 
          elements one position ahead till the smallest greater 
          element is not found ...insertion sort like*/
		  int j, last = a[m-1];
		  for(j=m-2; j>=0 && a[j]>b[i]; j--) {
			  a[j+1] = a[j];
		  }
		  // If there was a greater element 
          if (j != m-2 || last > b[i]) 
          { 
              a[j+1] = b[i]; 
              b[i] = last; 
          }
	  }
  }
}
