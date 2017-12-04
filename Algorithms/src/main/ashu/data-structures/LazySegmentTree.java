
import java.util.Arrays;

public class LazySegmentTree {  
   int height;
   int maxSize;
   int tree [];
   int lazy [];
   public LazySegmentTree(int arr[]) {
	   int n = arr.length;
	   int height = (int)(Math.ceil((Math.log(n))/Math.log(2)));  //remember this expr..ceil taken after division
	   int maxSize = 2 * (int)Math.pow(2, height) - 1;
	   tree = new int[maxSize];
	   lazy = new int[maxSize];
	   Arrays.fill(lazy, 0);
	   constructSegTree(arr, 0, n-1, 0);
   }
   
   public int constructSegTree(int a[], int ss, int se, int segIndex) {
	   if(ss == se) {
		   tree[segIndex] = a[ss];
		   return a[ss];
	   }
	   int mid = ss +(se-ss)/2;
	   int leftSum = constructSegTree(a, ss, mid, 2*segIndex + 1);
	   int rightSum = constructSegTree(a, mid+1, se, 2*segIndex + 2);
	   tree[segIndex] = leftSum + rightSum;
	   return tree[segIndex];
   }
   
   public int getSum(int arr[], int qs, int qe) {
	   //validate the query range
	   if(qs <0 || qs >=arr.length || qe<0 || qe>= arr.length || qs > qe) {
		   System.out.println("invalid range for getSum()");
		   return 0;
	   }
	   return getSumUtil(arr, 0, arr.length-1, qs, qe, 0);
   }
   
   public int getSumUtil(int a[], int ss, int se, int qs, int qe, int segIndex) {	   
	   //current segment outside the query range
	   if(ss > se || ss > qe || se < qs) return 0;   	   
	   //check for pending carry
	   if(lazy[segIndex] !=0) {		
		   System.out.println("Adding the lazy sum to the tree node and its children");
		 //add the carry sum to the current node value and its children if they exist
		   tree[segIndex] += (se-ss+1) * lazy[segIndex];
		   if(ss != se) {
			   tree[2*segIndex+1] += lazy[segIndex];
			   tree[2*segIndex+2] += lazy[segIndex];
		   }
		   lazy[segIndex] = 0;	//unmark the lazy array for the current node
	   }
	 //segment lies completely inside the query range...return the tree node value
	   if(ss>=qs && se <=qe) {  
		   return tree[segIndex];
	   }
	   //split in case of partial overlap
	   int mid = ss + (se-ss)/2;
	   return getSumUtil(a, ss, mid, qs, qe, 2*segIndex +1) + getSumUtil(a, mid+1, se, qs, qe, 2*segIndex + 2);
   }
   public void updateRange(int arr[], int us, int  ue, int value) {
	   if(us <0 || ue<0 || us >=arr.length || ue>=arr.length || us>ue) {
		   System.out.println("Invalid range for update");
		   return;
	   }
	   updateRangeUtil(arr, 0, arr.length-1, us, ue, value, 0);
   }
   public void updateRangeUtil(int a[], int ss, int se, int us, int ue, int diff, int segIndex) {
	   //check for pending carry
	   if(lazy[segIndex] != 0) {   
		   tree[segIndex] += (se-ss+1) * lazy[segIndex];
		   if(ss != se) {
			   tree[2*segIndex+1] += lazy[segIndex];
			   tree[2*segIndex+2] += lazy[segIndex];   
		   }
		   lazy[segIndex] = 0;
	   }
	 //segment outside the range..return back
	   if(ss>se || ss>ue || se <us) return;  
	   //if segment completely inside the range..use the lazy concept
	   //instead of recurring for left and right children..simply store their carry in the lazy array and return
	   if(ss >= us && se <= ue) {  	   
		   tree[segIndex] += (se-ss+1) * diff;
		   if(ss != se) {
			   System.out.println("Adding diff to the lazy array while update");
			   lazy[2*segIndex+1] += diff;
			   lazy[2*segIndex+2] += diff;
		   }		   
	   } else {   //segment partially in range....recurse for children and solve 					
		   int mid = ss +(se-ss)/2;
		   updateRangeUtil(a, ss, mid, us, ue, diff, 2*segIndex+1);
		   updateRangeUtil(a, mid+1, se, us, ue, diff, 2*segIndex+2);
		   tree[segIndex] = tree[2*segIndex+1] + tree[2*segIndex+2];
	   }
   }
   public static void main(String args []) {
       int[] elements = {1,3,5,7,9,11};
       LazySegmentTree segmentTree = new LazySegmentTree(elements);
       System.out.println("Array Representation of seg tree : "+Arrays.toString(segmentTree.tree));
       //segmentTree.buildSegmentTree(elements);
       int sum = segmentTree.getSum(elements,1,3);
       System.out.println("Sum of elemnts in the range 1 to 3 = "+sum);
       segmentTree.updateRange(elements, 1, 5, 10);
       System.out.println("Array Representation of seg tree : "+Arrays.toString(segmentTree.tree));
       sum = segmentTree.getSum(elements,1,3);
       System.out.println("Updated sum of values in the range 1 to 3 = "+sum);
   }
}
