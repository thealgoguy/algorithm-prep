package main.ashu.arrays;

import java.util.PriorityQueue;

public class KthSmallestElementInArray {
   //using max heap
	//using quick select(partitioning using pivot, recurse to appropriate half, stop when pivot gets at k-1)
	//https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/
	
	public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue(k);
        for(int i=0; i<k; i++) pq.offer(nums[i]);
        for(int i=k; i<nums.length; i++) {
            int top = pq.peek();
            if(top < nums[i]) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }
        return pq.poll();
    }
	
	public static void main(String args[]) {
		int a[] = {2, 1};
		int k = 1;
		System.out.println("kth largest = "+findKthLargest(a, k));
	}
}
