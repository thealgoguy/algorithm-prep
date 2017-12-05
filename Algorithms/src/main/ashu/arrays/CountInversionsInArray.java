package main.ashu.arrays;

//http://www.geeksforgeeks.org/counting-inversions/

public class CountInversionsInArray {
	
	public static int  countInversions(int a[]) {
		int n = a.length;
		return mergeSort(a, 0, n-1);
	}
	
	public static int  mergeSort(int a[], int l, int r) {
		if(l >=r) return 0;
		int mid = l + (r-l)/2;
		int left = mergeSort(a, l, mid);
		int right = mergeSort(a, mid+1, r);
		return left + right + merge(a, l, mid, r);
	}
	
	public static int merge(int a[], int l, int mid, int r){
		int i=l, j = mid+1;
		int temp [] = new int[r-l+1];  //not required
		int k = 0;
		int inv = 0;
		while(i<=mid && j<=r) {
			if(a[i] <= a[j]) {
				temp[k++] = a[i++];
			}
			else {
				inv += (mid-i+1);
				temp[k++] = a[j++];
			}
		}
		return inv;
	}
	
    public static void main(String args []) {
    	int a [] = {1, 20, 6, 4, 5};
    	int inversions = countInversions(a);
    	System.out.println("Number of inversions = "+inversions);
    }
}
