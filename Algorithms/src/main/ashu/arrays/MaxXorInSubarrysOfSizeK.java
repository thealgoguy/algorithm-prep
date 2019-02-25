package main.ashu.arrays;

//application of fixed sliding window technique
//similar - find the max sum in all windows of size k in a n array
//how would you improve to find subarray with max xor(can be of any size)
public class MaxXorInSubarrysOfSizeK {
   public static void main(String args []) {
	   int a [] = {2, 5, 8 ,1 , 1 ,3} ;
	   int k = 3;
	   int n = a.length;
	   int max = 0, curr = 0;
	   //initialize curr  with first window
	   for(int i=0; i<k; i++) {
		   curr ^= a[i];
	   }
	   max = curr;
       for(int i=k; i<n; i++) {
    	   max = Math.max(max, curr);
    	   //to discard an element from window, xor with it again
    	   curr = curr ^ a[i-k];
    	   curr = curr ^ a[i];
       }
       max = Math.max(max, curr);
       System.out.println("Max xor in all subarrys of size k = "+max);
   }
}
