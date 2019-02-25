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
public class MergeOvelappingIntervals {

	private class Interval {
		int start, end;
		public Interval(int start2, int max) {
			// TODO Auto-generated constructor stub
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
		for (int i = 1; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);
			if (pre.end < curr.start) {
				result.add(pre);
				pre = curr;
			} else if(pre.end < curr.end){
				pre.end = curr.end;
				/*Interval merged = new Interval(pre.start, Math.max(pre.end,
						curr.end));
				pre = merged;*/
			}
		}
		result.add(pre);

		return result;
	}
}
