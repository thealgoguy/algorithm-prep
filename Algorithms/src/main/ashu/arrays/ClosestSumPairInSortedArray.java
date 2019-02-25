package main.ashu.arrays;

//Application of two-pointer technique
//2 pointer technique has two steps :
//condition evaluation for updating answer,
//condition evaluation for moving pointers
public class ClosestSumPairInSortedArray {
   public static void main(String args []) {
	   int a[] = {10, 22, 28, 29, 30, 40};
	   int n = a.length;
	   int x = 54;
	   int i=0, j=n-1;
	   int ans = Integer.MAX_VALUE;
	   int n1 = 0, n2=0;
	   while(i < j) {
		   int sum = a[i]+ a[j];
		   //condition evaluation for updating answer
		   if(sum == x) {
			   n1 = a[i]; n2 =a[j];
			   ans = x;
			   break;
		   }
		   if( Math.abs(x-ans) > Math.abs(sum-x)) {
			   n1 = a[i]; n2 =a[j];
			   ans = sum;
		   }
		   //condition evaluation for moving pointers
		   if(sum < x) i++;
		   else j--;
	   }
	   System.out.println("Closest pair sum = "+ans);
	   System.out.println("Pair is "+n1+", "+n2);;
   }
}
