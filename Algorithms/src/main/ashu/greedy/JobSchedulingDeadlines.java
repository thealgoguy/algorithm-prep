package main.ashu.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobSchedulingDeadlines {
	static class Job implements Comparable<Job>{
		char id;
		int deadline, profit;
		public Job(char id, int deadline, int profit) {
			this.id = id;
			this.deadline = deadline;
			this.profit = profit;
		}
		public int compareTo(Job x) {
			return Integer.compare(x.profit, this.profit);
		}
		@Override
		public String toString() {
			return "["+id +"]";
		}
	}
	public static void main(String args []) {
		Job jobs [] = new Job[5];
		int i=0;
		jobs[i++] = new Job('a', 2, 100);
		jobs[i++] = new Job('b', 1, 19);
		jobs[i++] = new Job('c', 2, 27);
		jobs[i++] = new Job('d', 1, 25);
		jobs[i++] = new Job('e', 3, 15);

		Arrays.sort(jobs);  //decreasing profit
		int slots = 0, profit = 0;
		List<Job> result = new ArrayList<Job>();
		for(i=0; i<5; i++) slots = Math.max(slots, jobs[i].deadline);
		int timeslot []= new int[slots+1];
		Arrays.fill(timeslot, -1);  //all slot sempty
		for(i=0; i<5; i++) slots = Math.max(slots, jobs[i].deadline);
		for(i=0; i<5; i++) {
			int deadline = jobs[i].deadline;
			for(int j=deadline; j>=1; j--) {
				if(timeslot[j] < 0) {
					timeslot[j] = i;
					result.add(jobs[i]);
					profit += jobs[i].profit;
					break;
				}
			}
		}
		System.out.println("Max profit earned = "+profit);
		System.out.print("Job sequence : "+result);

	}
}


