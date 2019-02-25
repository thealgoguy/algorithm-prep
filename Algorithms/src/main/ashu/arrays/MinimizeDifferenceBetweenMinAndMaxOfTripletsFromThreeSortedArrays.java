package main.ashu.arrays;
//https://www.geeksforgeeks.org/minimize-maxai-bj-ck-minai-bj-ck-three-different-sorted-arrays/
//Given three sorted arrays, minimize the absolute difference between max and min of triplets a[i], b[j], c[k]
//application of merging
//minimize (max(A[i], B[j], C[k]) – min(A[i], B[j], C[k]))
//For minimizing diff, the max and min of the triplets should be closest possible
public class MinimizeDifferenceBetweenMinAndMaxOfTripletsFromThreeSortedArrays {
    public static void main(String args []) {
    	int a[] = {1, 4, 5, 8, 10};
		int b[] = {6, 9, 15};
		int c [] = {2, 3, 6, 6};
		int min = Integer.MAX_VALUE;
		int i=0, j=0, k=0;
		while(i<a.length && j<b.length && k<c.length) {
			int tripletMax = Math.max(Math.max(a[i], b[j]), c[k]);
			int tripletMin = Math.min(Math.min(a[i], b[j]), c[k]);
			int diff = Math.abs(tripletMax - tripletMin);
			min = Math.min(diff, min);
			//increment the counter of the min array so that the min approaches the max of the triplet
			if(tripletMin == a[i]) i++;
			else if(tripletMin == b[j]) j++;
			else if(tripletMin == c[k]) k++;
			else break;
		}
		System.out.println(min);
    }
}
