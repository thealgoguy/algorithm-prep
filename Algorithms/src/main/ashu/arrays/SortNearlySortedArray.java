package main.ashu.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

//Given an array of n elements, where each element is at most k away
//from its target position, devise an algorithm that sort
//o(k) + (n-k)logk
public class SortNearlySortedArray {
     public static void main(String args []){
    	 int a[] = {10, 9, 8, 7, 4, 70, 60, 50};
    	 int k = 4;
    	 int n = a.length;
    	 int idx = 0;
    	 PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k+1);
    	 for(int i=0; i<=k; i++) pq.offer(a[i]);
    	 for(int i=k+1; i<n; i++) {
    		 a[idx++] = pq.poll();
    		 pq.offer(a[i]);
    	 }
    	 while(!pq.isEmpty()) a[idx++] = pq.poll();
    	 System.out.println("Sorted array is : "+Arrays.toString(a));
     }
}
