package main.ashu.sorting;

//http://www.opendatastructures.org/ods-java/11_2_Counting_Sort_Radix_So.html
public class CountingSort {
	
	public static int [] countSort(int a[]) {
		int max = Integer.MIN_VALUE;
		for(int i=0; i<a.length; i++) max = Math.max(max,  a[i]);
		int count [] = new int[max+1];
		for(int i=0; i<a.length; i++)  {  //storing frequency of individual element
			count[a[i]]++;
		}
		for(int i=1; i<count.length; i++) count[i] += count[i-1]; //counting how many elements are <= a given element.
		//now there are count[i] elements <= i. Use this fact for sorting in linear time for all a[i] 
		//by traversing from right to left.
		int sorted [] = new int[a.length];   //to store final result
		for(int i=sorted.length-1; i>=0; i--) {  //looping on the original array since some num maybe in the range[low,high] but not in the array a [].
			sorted[count[a[i]]-1] = a[i];
			count[a[i]]--;   //now a[i] is at its place in the sorted array. So, decrement its count so as to count no less than equal to other a[i]s in the array.
		}
		return sorted;
	}
  public static void main(String args []) {
	  int a [] = {2,5,6,45,34,12,33};
	  System.out.print("Input is : ");
	  for(int i=0; i<a.length; i++) System.out.print(a[i]+" ");
	  int sorted [] = countSort(a);
	  System.out.print("\nSorted array is : ");
	  for(int i=0; i<a.length; i++) System.out.print(sorted[i]+" ");
  }
}
