package main.ashu.arrays;

import java.util.Collections;
import java.util.PriorityQueue;

//online algorithm
//approach 1: augmented self balancing BST
//approach 2: using max and min heaps
public class MedianInStreamOfIntegers {
	PriorityQueue<Integer> maxHeap;//lower half
    PriorityQueue<Integer> minHeap;//higher half
 
    public MedianInStreamOfIntegers(){
        maxHeap = new PriorityQueue(Collections.reverseOrder());
        minHeap = new PriorityQueue<Integer>();
    }
 
    // Adds a number into the data structure.
    public void addNum(int num) {
    	//add new elemets to the left heap and then shift the root to the right heap
        maxHeap.offer(num);  
        minHeap.offer(maxHeap.poll());
        if(maxHeap.size() < minHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
    }
    // Returns the median of current data stream
    public double findMedian() {
        if(maxHeap.size()==minHeap.size()){
            return (double)(maxHeap.peek()+(minHeap.peek()))/2;
        }else{
            return maxHeap.peek();
        }
    }
}
