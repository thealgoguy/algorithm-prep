package main.ashu.stack;

import java.util.ArrayList;
import java.util.List;

//https://www.geeksforgeeks.org/insert-in-sorted-and-non-overlapping-interval-array/
//Given a set of non-overlapping & sorted intervals, insert a new interval into the intervals (merge if necessary)
//https://www.programcreek.com/2012/12/leetcode-insert-interval/
//accepted - https://leetcode.com/problems/insert-interval/submissions/
public class InsertAndMergeInSortedInterval {
	static class Interval {
		int start, end;
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "(" + start + "," + end + ")";
		}
	}
	
	public List<Interval> insert(List<Interval> intervals, Interval newInterval){
		int n = intervals.size();
		List<Interval> result = new ArrayList();
		for(Interval interval: intervals){
            if(interval.end < newInterval.start){ //new interval greater than current interval
                result.add(interval);
            }else if(interval.start > newInterval.end){ //new interval smaller than current interval
                result.add(newInterval);
                //the current interval will come later in the sorted list
                newInterval = interval;        
            }else if(interval.end >= newInterval.start || interval.start <= newInterval.end){ 
            	//new and current intervals overlap, update the boundaries of the neeInterval
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(newInterval.end, interval.end);
            }
        }
		//the value in the newInterval holds the greatest interval in the sorted order, 
		//add the same to the result list
        result.add(newInterval); 
		return result;
	}
	
	//If the intervals list is an ArrayList, we can use binary search to make the best time complexity O(n).
	// best time is O(log(n)) and worst case time is O(n).
	public List<Interval> insertUsiinfBinarySearch(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new ArrayList();		 
	    if (intervals.size() == 0) {
	        result.add(newInterval);
	        return result;
	    }	 
	    int p = helper(intervals, newInterval);
	    result.addAll(intervals.subList(0, p));
	 
	    for (int i = p; i < intervals.size(); i++) {
	        Interval interval = intervals.get(i);
	        if (interval.end < newInterval.start) {
	            result.add(interval);
	        } else if (interval.start > newInterval.end) {
	            result.add(newInterval);
	            newInterval = interval;
	        } else if (interval.end >= newInterval.start || interval.start <= newInterval.end) {
	            newInterval = new Interval(Math.min(interval.start, newInterval.start), Math.max(newInterval.end, interval.end));
	        }
	    }
	 
	    result.add(newInterval); 
	    return result;
	}	
	public int helper(List<Interval> intervals, Interval newInterval) {
	    int low = 0;
	    int high = intervals.size() - 1;
	 
	    while (low < high) {
	        int mid = low + (high - low) / 2;
	 
	        if (newInterval.start <= intervals.get(mid).start) {
	            high = mid;
	        } else {
	            low = mid + 1;
	        }
	    }
	 
	    return high == 0 ? 0 : high - 1;
	}

}
