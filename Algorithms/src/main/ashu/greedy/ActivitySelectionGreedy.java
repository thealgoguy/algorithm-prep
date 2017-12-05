package main.ashu.greedy;

import java.util.ArrayList;
import java.util.Arrays;

//N activities with start and finish times
//Find the largest set of non-overlapping activities, each activity has same priority
//Similar to Interval Scheduling Problem(ISP) :  Given start and finish times of n processes, find the maximum
//no. of non-conflicting processes that the processor can be allocated to. The processor can entertain only one task at a time.
//Approach-sort on the basis of finish  times, greedily select the activity that finishes first, 
//subject to the constraint that it doesn't conflict with the latest activity added in the solution 

public class ActivitySelectionGreedy {
	static class Task implements Comparable<Task> {
		int start, finish;
		Task(int s, int f) {
			this.start=s;
			this.finish=f;
		}
		public int compareTo(Task t) {     //sorting based on finish time, ascending order
			return Integer.compare(this.finish, t.finish);
		}
	}
   public static void main(String args []) {
	   Task arr [] = {new Task(5,9),new Task(8,9),new Task(0,6), new Task(3,4), new Task(1,2)};
	   Arrays.sort(arr);
	   int count =1;
	   int lastFinish = arr[0].finish;
	   ArrayList<Task> opted = new ArrayList<Task>();
	   opted.add(arr[0]);
	   for(int i=1; i<arr.length; i++) {
		   if(arr[i].start >=lastFinish) { //check for compatibility of ith task with that of the already selected tasks.
			   opted.add(arr[i]);
			   lastFinish = arr[i].finish;
			   count++;
		   }
	   }
	   System.out.println("Maximum no. of activities = "+count);
	   //print the activities form the list - opted.
   }
}
