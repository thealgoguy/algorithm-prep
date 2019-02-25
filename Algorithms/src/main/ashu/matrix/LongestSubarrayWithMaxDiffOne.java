package main.ashu.matrix;

import java.util.HashMap;
import java.util.Map;

//Given an array of size n containing 0’s and 1’s only.
//The problem is to find the length of the longest subarray having count of 1’s one more than count of 0’s.
//here window technique won't work
//use hash map with prefix sum...change 0 to -1
//https://www.geeksforgeeks.org/longest-subarray-count-1s-one-count-0s/
public class LongestSubarrayWithMaxDiffOne {

	public static void main(String args[]) {
		int a[] = {0, 1, 0, 0}; //{0, 1, 1, 0, 0, 1};
		int n = a.length;
		int max = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int sum = 0;
		int start = 0, end = 0;
		for(int i=0; i<n; i++) {
			sum += (a[i] == 0) ? -1 : 1;	
			//case 1: prefix ending at i may be longest
			if(sum == 1) {
				max = i+1;
				start = i; end = i;
			}
			//case 2: suffix ending at i(not starting at 0) may be longest
			if(map.containsKey(sum-1)) {
				//max = Math.max(max, i-map.get(sum-1));
				if(max < i-map.get(sum-1)) {
					max = i-map.get(sum-1);
					start = map.get(sum-1)+1;
					end = i;
				}
			}
			//using ! b/c we don't want to override the smaller i with this prefix sum
			//in search of longest subarray
			if(!map.containsKey(sum)) map.put(sum, i);
		}
		System.out.println(max);
		System.out.print("Subarray is : ");
		for(;start<=end; start++) System.out.print(a[start]+" ");
	}
}
