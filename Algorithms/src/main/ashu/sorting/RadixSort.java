
//Counting sort is a linear time sorting algorithm that sort in O(n+k) time when elements are in range from 1 to k.
//But if the the range(k) becomes larger than n, maybe n*n, counting sort is not a good choice. Radix sort solves this p[roblem
//It uses counting sort as the subroutine. 
// O((n+b) * logb(k)) where b is the base representing numbers.

public class RadixSort {
	//this approach is based on decimal digits...also learn how to sort based on individual bits in binary representation of the numbers
	public static int [] radix(int [] a) {
		int max = a[0];
		for(int i=1; i<a.length; i++) max = Math.max(max, a[i]);
		for(int digPlace = 1; max/digPlace>0; digPlace *= 10) countingSort(a,digPlace);
		return a;
	}
	//sorting based on kth digit O(n+k) time
	public static void countingSort(int []a, int k) {
		int count [] = new int[10];
		int ans [] = new int[a.length];
		for(int i=0; i<a.length; i++) count[(a[i]/k)%10]++;
		for(int i=1; i<count.length; i++) count[i] += count[i-1];  //running sum
		for(int i=a.length-1; i>=0; i--) {
			ans[count[(a[i]/k)%10] - 1] = a[i];
			count[(a[i]/k)%10]--; //why ??
		}
		for(int i=0; i<a.length; i++) a[i] = ans[i];
	}
    public static void main(String args []) {
    	int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};   	
    	System.out.print("Input array is : ");
    	for(int i=0; i<arr.length; i++) System.out.print(arr[i]+" ");
    	System.out.println();
    	arr = radix(arr);
    	System.out.print("Sorted array is : ");
    	for(int i=0; i<arr.length; i++) System.out.print(arr[i]+" ");
    }
}
