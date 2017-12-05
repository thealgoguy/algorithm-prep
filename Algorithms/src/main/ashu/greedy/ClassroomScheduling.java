package main.ashu.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

//Since we have to find rooms for all the intervals, sort them based on starting time
//Now process the sorted intervals : Compare the start time of next interval with the intervals already processed
//the interval with minimum end time should be checked for availability, if end time < start of next interval re-use the room
//else allot a new room for this interval. To efficiently check for availability from already allotted rooms
//we can insert them in priority queue sorted by finish time. Then the max size of the PQ will be the minimum
//rooms required for scheduling all the intervals

public class ClassroomScheduling {	
	static class Interval implements Comparable<Interval>{
		int start, end;
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
		public int compareTo(Interval i) {
			return Integer.compare(this.end, i.end);
		}
	}
	public static void main(String args []) {
		int arrl[] = {1, 2, 10, 5, 5};
        int exit[] = {4, 5, 12, 9, 12};
        Interval interval [] = new Interval[arrl.length];
        for(int i=0; i<arrl.length; i++) {
        	interval[i] = new Interval(arrl[i], exit[i]);
        }
        Arrays.sort(arrl);  //process them as they arrive, i.e. increasing start time
        PriorityQueue<Interval> pq = new PriorityQueue();
        pq.offer(interval[0]);
        int depth = 0;
        for(int i=1; i<arrl.length; i++) {
        	Interval firstToFinish = pq.peek();
        	if(firstToFinish.end < arrl[i]) {
        	   pq.poll();
        	   pq.offer(interval[i]);
        	}
        	else {
        		pq.offer(interval[i]);
        		depth = Math.max(depth, pq.size());
        	}
        }
        System.out.println("Minimum number of classrooms/platforms required = "+depth);
	}
}
