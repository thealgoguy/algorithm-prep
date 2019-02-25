package main.ashu.stack;

import java.util.ArrayList;
import java.util.List;

//Merge two sorted (ascending) lists of interval and return it as a new sorted list. 
//The new sorted list should be made by splicing together the intervals of the two lists and sorted in ascending order.
//Intervals within the same list don't overlap, but  they may if from different lists
//more concised verison at -
//https://github.com/jiadaizhao/LintCode/blob/master/0839-Merge%20Two%20Sorted%20Interval%20Lists/0839-Merge%20Two%20Sorted%20Interval%20Lists.cpp
//solution is lintcode accepted
public class MergeSortedIntervalLists {	
	static class Interval {
		      int start, end;
		      Interval(int start, int end) {
		          this.start = start;
		          this.end = end;
		      }
			@Override
			public String toString() {
				return "("+start + "," + end + ")";
			}	      
	}
    /**
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        if(list1 == null || list1.size() == 0) return list2;
        if(list2 == null || list2.size() == 0) return list1;
        List<Interval> result = new ArrayList();
        int i=0, j=0;
        int m = list1.size();
        int n = list2.size();
        while(i <m && j<n) {
            Interval first = list1.get(i);
            Interval second = list2.get(j);
            Interval prev = (result.isEmpty()) ? null : result.get(result.size()-1);
            //take the list with smaller start point and merge with the result set
            if (first.start <= second.start) {
            	//merge the first list with result
                if (prev != null && first.start <= prev.end) {
                    prev.end = Math.max(prev.end, first.end);
                }
                else {
                    result.add(first);
                }
                ++i;
            }
            else {
            	//merge the second lists with result
                if (prev != null && second.start <= prev.end) {
                    prev.end = Math.max(prev.end, second.end);
                }
                else {
                    result.add(second);
                }
                ++j;
            }
        }
        while(i<m) {
        	Interval first = list1.get(i);
        	Interval prev = (result.isEmpty()) ? null : result.get(result.size()-1);
        	if (prev != null && first.start <= prev.end) {
                prev.end = Math.max(prev.end, first.end);
            }
            else {
                result.add(first);
            }
            ++i;
        }
        
        while(j < n) {
        	Interval second = list2.get(j);
        	Interval prev = (result.isEmpty()) ? null : result.get(result.size()-1);
        	if (prev != null && second.start <= prev.end) {
                prev.end = Math.max(prev.end, second.end);
            }
            else {
                result.add(second);
            }
            ++j;
        }
          
        return result;
    }
    
    
    public static void main(String args []) {
    	List<Interval> list1 = new ArrayList();
    	List<Interval> list2 = new ArrayList();
    	list1.add(new Interval(1, 2));
    	list1.add(new Interval(3, 4));
    	
    	list2.add(new Interval(2, 3));
    	list2.add(new Interval(5, 6));
    	
    	MergeSortedIntervalLists mergeSorted = new MergeSortedIntervalLists();
    	
    	List<Interval> merged = mergeSorted.mergeTwoInterval(list1, list2);
    	System.out.println(merged);
    }
}
