package main.ashu.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//Given an array of n distinct elements, find the minimum number of swaps required to sort the array.
//ans = sum(i) = 1k(cycle_size – 1), where k is the number of cycles
public class MinimumSwapsToSortArray {
	public static void main(String args []) {
		int []arr = {1, 5, 4, 3, 2}; 
		int n = arr.length; 

		// Create two arrays and use as pairs where first 
		// array is element and second array 
		// is position of first element 
		ArrayList<Pair> arrpos = 
			new ArrayList <Pair> (); 
		for (int i = 0; i < n; i++) 
			arrpos.add(new Pair(arr[i], i)); 

		// Sort the array by array element values to 
		// get right position of every element as the 
		// elements of second array. 
	Collections.sort(arrpos, new Comparator<Pair>() 
			{ 
		public int compare(Pair o1, 
				Pair  o2) 
		{ 
			if (o1.getKey() > o2.getKey()) 
				return -1; 
			// We can change this to make it then look at the 
			// words alphabetical order 
			else if (o1.getKey().equals(o2.getKey())) 
				return 0; 

			else
				return 1; 
		} 
			}); 

		// To keep track of visited elements. Initialize 
		// all elements as not visited or false. 
		Boolean[] vis = new Boolean[n]; 
		Arrays.fill(vis, false); 

		// Initialize result 
		int ans = 0; 

		// Traverse array elements 
		for (int i = 0; i < n; i++) 
		{ 
			// already swapped and corrected or 
			// already present at correct pos 
			if (vis[i] || arrpos.get(i).getValue() == i) 
				continue; 

			// find out the number of  node in 
			// this cycle and add in ans 
			int cycle_size = 0; 
			int j = i; 
			while (!vis[j]) 
			{ 
				vis[j] = true; 

				// move to next node 
				j = arrpos.get(j).getValue(); 
				cycle_size++; 
			} 

			// Update answer by adding current cycle. 
			if(cycle_size > 0) 
			{ 
				ans += (cycle_size - 1); 
			} 
		} 

		System.out.println("Minimum swaps to sort = "+ans); 
	} 
	
	private static class Pair {
		Integer key, value;
		public Pair(Integer key, Integer value) {
			super();
			this.key = key;
			this.value = value;
		}
		public Integer getKey() {
			return key;
		}
		public void setKey(Integer key) {
			this.key = key;
		}
		public Integer getValue() {
			return value;
		}
		public void setValue(Integer value) {
			this.value = value;
		}
	}
}
