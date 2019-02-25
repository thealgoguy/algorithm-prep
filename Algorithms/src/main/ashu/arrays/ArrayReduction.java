package main.ashu.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

//Application of heap
public class ArrayReduction {
	public static void main(String args []) {
		Integer a[] = {1, 1, 3, 3};
		ArrayReduction ar = new ArrayReduction();
		int cost = ar.sumAndCost(a);
		System.out.println(cost);
	}
	
	 public int sumAndCost(Integer [] arr) {
	        PriorityQueue<Integer> queue = new PriorityQueue(Arrays.asList(arr));
	        //for(int i=0; i<arr.length; i++) queue.offer(arr[i]);
	        int cost = 0;
	        while (queue.size() > 1) {
	            int curr =queue.poll() + queue.poll();
	            cost += curr;
	            queue.offer(curr);
	        }

	        return cost;
	    }
}
