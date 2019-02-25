package main.ashu.arrays;

//Given a sorted and rotated array, find if pair with a given sum exists
public class PairSumInCyclicArray {
    public static void main(String args []) {
    	int a[] = {11, 15, 6, 7, 9, 10};
    	int sum = 16;
    	int n = a.length;
    	//pivot is  the largest element in the array
    	int pivotIndex = getPivotIndex(a, 0, n-1);
    	System.out.println("pivot index is : "+pivotIndex);
    	int l = (pivotIndex + 1) % n;
    	int r = pivotIndex;
    	pairSum(a, l, r, sum);
    }
    
    //pair sum using two pointers in corcular fashion
    private static void pairSum(int a[], int l, int r, int sum) {
    	int n = a.length;
    	while(l != r) {
    		int curr = a[l]  +a[r];
    		if(curr == sum) {
    			System.out.println("Pair is : ("+a[l]+" , "+a[r]+")");
    			break;
    		}else if(curr < sum) l = (l+1)% n;
    		else r = (r-1 + n) % n;
    	}
    }
    
    private static int getPivotIndex(int a[], int l, int r) {
    	int index = -1;
    	while(l <= r) {
    		int mid = l + (r-l)/2;
    		if(a[mid] > a[mid+1]) return mid;
    		if(a[l] <= a[mid]) l = mid+1;
    		else r = mid-1;
    	}
    	return index;
    }
}
