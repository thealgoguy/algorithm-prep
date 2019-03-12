package main.ashu.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//sort intervals by start time. Then use stack, keep updating top in case of overlap
//how to do in place without stack ? see geeks
//how to merge k sorted intervals ? heap approach
//how to check if two intervals overlap - min of end-points >= max of star-points
//(min(a.end, b.end) >= max(a.start, b.start)
//accepted - https://leetcode.com/problems/merge-intervals/submissions/
public class MergeOvelappingIntervals {

	private class Interval {
		int start, end;
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> result = new ArrayList<Interval>();

		if (intervals == null || intervals.size() == 0)
			return result;
        //sort in increasing start time
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				if (i1.start != i2.start)
					return i1.start - i2.start;
				else
					return i1.end - i2.end; //end part doesn't matter
			}
		});

		Interval pre = intervals.get(0);
		result.add(intervals.get(0));
		//keep including the non-overlapping ones in the answer set
		//in case of overlap, update the end point of the last interval
        for(int i=1; i<intervals.size(); i++) {
            Interval current = intervals.get(i);
            Interval prev = result.get(result.size()-1);
            if(prev.end >= current.start) {
                prev.end = Math.max(prev.end, current.end);
            }else {
            	result.add(current);
            }
        }
		result.add(pre);

		return result;
	}
}
