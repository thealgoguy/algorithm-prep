package main.ashu.matrix;

import java.util.HashMap;
import java.util.Map;

//https://www.geeksforgeeks.org/maximum-sub-matrix-area-having-count-of-1s-one-more-than-count-of-0s/
public class LargestSubmatrixWithCountOfOnesOneMoreThanZeroes {

	public static void main(String args[]) {
		
		int a[][] =  { { 1, 0, 0, 1 },
                { 0, 1, 1, 1 },
                { 1, 0, 0, 0 },
                { 0, 1, 0, 1 } };
		int m = a.length, n = a[0].length;
		int left=0, right=0, top=0, bottom=0;
		int maxarea = Integer.MIN_VALUE;
		for(int lc=0; lc<n; lc++) {
			int b [] = new int[m];
			for(int rc=lc; rc<n; rc++) {
				for(int row=0; row < m; row++) {
					b[row] += (a[row][rc] == 0) ? -1 : 1;
				}
				int res [] = evaluate(b);
				int len = res[0], l = res[1], r = res[2];
				if(len > 0 && maxarea < (rc-lc+1)*(r-l+1)) {
					maxarea = (rc-lc+1)*(r-l+1);
					left = lc; right = rc;
					top = l; bottom = r;
				}
			}
		}
		System.out.println("top, left = "+top+", "+left);
		System.out.println("bottom, right = "+bottom+", "+right);
		System.out.println("Max area is = "+maxarea);
		for(int i=top; i<=bottom; i++) {
			for(int j=left; j<=right; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	//here array contains -1 and 1
	//find longest subarray with sum=1
	public static int [] evaluate(int a[]) {
		int n = a.length;
		int maxlen = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int sum = 0;
		int start = 0, end = 0;
		for(int i=0; i<n; i++) {
			sum += a[i];
			//case 1: prefix ending at i may be longest
			if(sum == 1) {
				maxlen = i+1;
				end = i; start = 0;
			}
			//case 2: suffix ending at i(not starting at 0) may be longest
			if(map.containsKey(sum-1)) {
				if(maxlen < i-map.get(sum-1)) {
					maxlen = i-map.get(sum-1);
					start = map.get(sum-1)+1;
					end = i;
				}
			}
			//using "!" b/c we don't want to override the smallest i with this prefix sum
			//in search of longest subarray
			if(!map.containsKey(sum)) map.put(sum, i);  
		}
		int ans[] = {maxlen, start, end};
		//System.out.println("array : "+Arrays.toString(a)+" , start = "+start+", end = "+end+", max = "+maxlen);
		return ans;
	}
}
