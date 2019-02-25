package main.ashu.dp;

public class MaxProductSubarray {

	public static void main(String args []) {
		int a [] = {-2, -3, 0, -2, -40};
		int n = a.length;
		int min=a[0], max = a[0];
		int ans = a[0];
		for(int i=1; i<n; i++) {
			max = Math.max(Math.max(a[i], a[i]*max), a[i] * min);
			min = Math.min(Math.min(a[i], a[i]*min), a[i]*max);
			ans = Math.max(max, ans);
		}
		System.out.println("Max product subarray : "+ans);
	}
	
	public static int findPeak(int a[]) {
		int n = a.length;
		int i=0, j=n-1;
		while(i<=j) {
			int mid = i + (j-i)/2;
			if(a[mid] > a[mid-1] && a[mid] > a[mid+1]) return mid;
			else if(a[i] <= a[mid]) i=mid;
			else j=mid;
		}
		return -1;
	}

}
