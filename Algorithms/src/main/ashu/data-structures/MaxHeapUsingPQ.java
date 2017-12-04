package DS;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxHeapUsingPQ {

	public static void main(String args[]) {
		int a [] = {3,2,1,7,8,4,10,16,12};
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return b-a;
			}
		};
		 PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(a.length, comparator);
		for(int i=0; i<a.length; i++) maxHeap.offer(a[i]); //insert elements in PQ
		System.out.println("Max heap using PQ is : "+maxHeap);
		System.out.print("Deleting elements from maxHeap : ");
		for(int i=0; i<a.length; i++) { 
			System.out.print(maxHeap.peek()+" ");
			maxHeap.poll();
		}
	}

}
