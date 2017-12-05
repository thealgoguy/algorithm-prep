package main.ashu.arrays;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//https://stackoverflow.com/questions/8031939/finding-maximum-for-every-window-of-size-k-in-an-array
//http://www.programcreek.com/2014/05/leetcode-sliding-window-maximum-java/

//There are 2 approaches : 1. Deque approach. 2. DP approach(in above link) 3. BST/heap approach.  1 and 2 are best
//IN the implementation, the deque contains INDEXES of useful elements(elements that greater than current element and are in the window)
//Extension of the problem : http://www.geeksforgeeks.org/sum-minimum-maximum-elements-subarrays-size-k/

public class SlidingWindowMax {

	public static Integer[] maxsInEveryWindows(int[] arr, int k) {
	    Deque<Integer> deque = new ArrayDeque<Integer>();
	    /* Process first k (or first window) elements of array */
	    for (int i = 0; i < k; i++) {
	        // For very element, the previous smaller elements are useless so
	        // remove them from deque
	        while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]) {
	            deque.removeLast(); // Remove from rear
	        }
	        // Add new element at rear of queue
	        deque.addLast(i);
	    }
	    List<Integer> result = new ArrayList<Integer>();
	    // Process rest of the elements, i.e., from arr[k] to arr[n-1]
	    for (int i = k; i < arr.length; i++) {
	        // The element at the front of the queue is the largest element of
	        // previous window, so add to result.
	        result.add(arr[deque.getFirst()]);
	        // Remove all elements smaller than the currently
	        // being added element (remove useless elements)
	        while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]) {
	            deque.removeLast();
	        }
	        // Remove the elements which are out of this window
	        while (!deque.isEmpty() && deque.getFirst() <= i - k) {
	            deque.removeFirst();
	        }
	        // Add current element at the rear of deque
	        deque.addLast(i);
	    }
	    // Print the maximum element of last window
	    result.add(arr[deque.getFirst()]);
	    return result.toArray(new Integer[0]);
	}
	
	public static void main(String args []){
		int a [] = {12, 1, 78, 90, 57, 89, 56};
		Integer[] window = maxsInEveryWindows(a, 3);
		System.out.print("Using deque : ");
		for(Integer i : window) {
			System.out.print(i+" ");
		}
	}
}
