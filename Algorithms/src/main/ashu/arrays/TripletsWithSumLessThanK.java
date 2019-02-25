package main.ashu.arrays;

import java.util.Arrays;

//Given an aray, find triplets with sum less than K in O(n2) time
//Sort, use two pointer technique for pair sum
public class TripletsWithSumLessThanK {
	public static void main(String args[]) {
		int a[] = { 5, 1, 3, 4, 7 };
		int n = a.length;
		int sum = 12;
        Arrays.sort(a);
        //fix the first element in the outer loop
        for(int i=0; i<n-2; i++) {
        	//fix the second and third elements
        	int j=i+1, k=n-1;
        	while(j<k) {
        		int curr = a[i] + a[j] + a[k];
        		if(curr > sum) k--;
        		else {
        			for(int x=j+1; x<=k; x++) {
        				System.out.println("triplet is : "+a[i]+", "+a[j]+", "+a[k]);
        			}
        			j++;
        		}
        	}
        }
	}
}
