package main.ashu.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Find the largest set of pairwie non-overlapping intervals
//sort by finish time and count non-overlaps
//Note : sort by start will not be optimal
//Job scheduling with same profit
public class MaxPairwiseNonOverlapping {
	//sorted by finish time
	static class Activity implements Comparable<Activity>{
		int start, finish;
		public Activity(int start, int finish) {
			this.start = start;
			this.finish = finish;
		}
		public int compareTo(Activity x) {
			return Integer.compare(this.finish, x.finish);
		}
		@Override
		public String toString() {
			return "["+start + ", " + finish + "]";
		}
	}
	public static void main(String args []) {
		int start [] = {5, 1, 3, 0, 5, 8};
		int finish [] = {9, 2, 4, 6, 7, 9};
		int n = start.length;
		Activity arr[] = new Activity[n];
		for(int i=0; i<n; i++) arr[i] = new Activity(start[i], finish[i]);
		Arrays.sort(arr);
		List<Activity> nonOverlapping = new ArrayList<Activity>();
		nonOverlapping.add(arr[0]);
		int count = 1;
		int lastFinish = arr[0].finish;
		for(int i=1; i<n; i++) {
			if(arr[i].start > lastFinish) {
				count++;
				lastFinish = arr[i].finish;
				nonOverlapping.add(arr[i]);
			}
		}
		System.out.println("Maximium number of pairwise non-overlapping intervals : "+count);
		System.out.print("Non-overlapping intervals are : "+nonOverlapping);
	}
}
