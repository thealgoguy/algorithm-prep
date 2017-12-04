package DS;

import java.util.Comparator;
import java.util.PriorityQueue;

//A priority queue is an abstract data type where each ele­ment has a “priority” assigned to it. 
//So the element with the higher priority is served before the other elements. 
//While creating a PQ, we need to pass suitable comparator for ordering of priority. If no comparator is passed,
//java assumes natural ordering.
//Min and Max Heap can be implemented using this class

public class MinHeapUsingPQ {
	public static void main(String args[]) {
		int a [] = {3,2,1,7,8,4,10,16,12};
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(a.length); //no comparator passed for min heap
		for(int i=0; i<a.length; i++) minHeap.offer(a[i]); //insert elements in PQ
		System.out.println("Min heap using PQ is : "+minHeap);
		System.out.print("Deleting elements from minHeap : ");
		for(int i=0; i<a.length; i++) { 
			System.out.print(minHeap.peek()+" ");
			minHeap.poll();
		}
	}
}
