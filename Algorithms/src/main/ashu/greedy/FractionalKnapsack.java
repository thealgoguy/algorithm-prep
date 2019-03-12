package main.ashu.greedy;

import java.util.Arrays;

//O(nlogn) time complexity greedy algorithm
public class FractionalKnapsack {
	public static void main(String args[]) {
		int W = 50; // Weight of knapsack
		Item arr[] = { new Item(10, 60), new Item(20, 100), new Item(30, 120) };
		Arrays.sort(arr); // sorted in decreasing order
		double profit = 0;
		int curr_weight = 0;
		for (Item i : arr) {
			if (curr_weight + i.weight <= W) { // check if ith item can be taken
												// wholly
				curr_weight += i.weight;
				profit += i.value;
			} else { // take only the required portion from the ith item
				int remaining = W - curr_weight;
				if (remaining > 0) {
					curr_weight += remaining;
					profit += (remaining) * (i.value / i.weight);
				}
			}
		}
		System.out.println("Max possible for the knapsack = " + profit);
	}
}

class Item implements Comparable<Item> {
	int weight;
	int value;

	Item(int w, int v) {
		this.weight = w;
		this.value = v;
	}

	public int compareTo(Item x) { // sorting in decreasing order of
									// value/weight
		return Double.compare((double) x.value / x.weight, (double) this.value
				/ this.weight);
	}
}
