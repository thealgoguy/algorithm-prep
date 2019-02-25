package main.ashu.data_structure.binaryheap;

import java.util.ArrayList;
import java.util.List;
//A min heap is a complete binary tree, in which root<=left and right children(ordering property)
//and elements are filled from left to right(shape property)
//for a heap of size n, last n/2 nodes are leaf nodes.
//operations : extract_min(the root is the min element), insert(at the end),create(inserting n times)
//After every insert, bubbleUp() must be called to preserve order of heap
//After every extract_min, bubbleDown() must be called to preserve heap order. 
//For this,root is replaced with the last node and last node is deleted. Root is now bubbled down for ordering.

class MaxHeap {
	private List<Integer> list = null;
	private int heapSize =0; //index of the last element in heap, 1-based	
	
	MaxHeap() {
		list = new ArrayList<Integer>();
		list.add(0); //not incrementing size as we will use 1-based indexing for ease of child calculation
	}
	private boolean isEmpty() {return list.size()==0; }
	private int getLeftChildIndex(int x) {
		return (2*x <=heapSize) ? 2*x : -1;
	}
	private int getRightChildIndex(int x) {
		return (2*x+1 <=heapSize) ? 2*x+1 : -1;
	}
	private int getParentIndex(int x) {
		return (x==1) ? -1 : x/2;
	}
	private void swap(int idx1, int idx2) {
		int temp = list.get(idx1);
		list.set(idx1, list.get(idx2));
		list.set(idx2, temp);
	}
	private void bubbleUp(int index) {
		int parent = getParentIndex(index);
		if(parent==-1) return; //root node
		if(list.get(index)>list.get(parent)) {
			swap(parent,index);
			bubbleUp(parent);
		}		
	}
	private void bubbleDown(int root)  {
		if(heapSize==0) return; //empty heap case
		if(root > heapSize/2) return; //no bubbleDown on leaf nodes
		int left = getLeftChildIndex(root);
		int right = getRightChildIndex(root);
		if(right==-1) { //only left child node exists
			int max = Math.max(list.get(root), list.get(left));
			if(list.get(root)!=max) {
				swap(root,left);
				bubbleDown(left);
			}
		}
		else { //both child nodes exist
			int max = Math.max(list.get(root), list.get(left));
			max = Math.max(max,list.get(right));
			if(list.get(root)!=max) {
				if(max==list.get(left)) {
					swap(left,root);
					bubbleDown(left);
				}
				else {
					swap(right,root);
					bubbleDown(right);

				}
			}
		}
	}
	public void insert(int x) {
		heapSize++;
		list.add(x);
		bubbleUp(heapSize);
	}
	public int extractMax() {
		int max = list.get(1);
		list.set(1,list.get(heapSize));
		heapSize--;
		bubbleDown(1);
		return max;
	}
	public void dispay() {
		System.out.print("Max heap is : ");
		for(int i=1; i<=heapSize; i++) System.out.print(list.get(i)+" ");
	}
	public int peek() {
		return (heapSize==0) ? -1 : list.get(1); 
	}
}

public class MaxHeapImpl {
	   public static void main(String args []) {
		   int a [] = {3,2,1,7,8,4,10,16,12};
		   MaxHeap mh = new MaxHeap();
		   for(int i=0; i<a.length; i++) mh.insert(a[i]);
		   mh.dispay();
		   int minElement = mh.peek();
		   if(minElement==-1) System.out.println("No elemnt in the heap");
		   else System.out.println("\nLargest element is "+ minElement);
		   System.out.println("Deleting elements one by one for heapsort(descending)");
		   for(int i=0; i<a.length; i++) {
			   System.out.print(mh.extractMax()+" ");
			   /*System.out.print("\nDeleted = "+mh.extractMax()+" ");
			   mh.dispay();*/
		   }
	   }

}
