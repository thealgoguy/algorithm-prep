package main.ashu.data_structure.binarytreesandbst;

import java.util.Arrays;

//A full binary tree with n leaves and n-1 internal nodes..total 2*n-1 nodes. 
//Array Representation : Root node represents the entire array, leaves represent single array element.
//Internal nodes represent merging of leaf nodes(merging may differ a/c to use case, sum,min,max,etc). 
//for node at index i, left child is at 2*i+1 and right child at 2*i+2 and parent at (i-1)/2.
//create...O(n). Query..O(logn), update..O(logn)....to update we process one node at every level and no of levels..O(logn)
//Every operation in segtree has to start with largest segment at the root and them narrowing down the segment size till leaf node
public class SegmentTree {
  public int height;
  public int noOfLeafNodes;
  public int maxSize;
  public int tree[];
  public SegmentTree(int arr []) {
	  int n = arr.length;
	  this.noOfLeafNodes = n;
	  this.height = (int)(Math.ceil(Math.log(n) /  Math.log(2)));
	  this.maxSize = 2 * (int)Math.pow(2, height) - 1;
	  tree = new int[maxSize];
	  buildSegmentTree(arr,0,arr.length-1,0); //pass end points of input array as starting segment and 0 as index of root
  }
  public int leftChild(int x) {
	  return 2*x + 1;
  }
  public int rightChild(int x) {
	  return 2*x + 2;
  }
  //build segment tree recursively starting from root node..in recursive preorder fashion.
  //use iterative version to save stack space
  public int buildSegmentTree(int a[], int ss, int se, int segIndex) {
	  if(ss == se) {   //recursion terminate condition...segment has single element
		  tree[segIndex] = a[ss];
		  return tree[segIndex];
	  }
	  //in case of more than one element, split the segment, solve for smaller segments and combine the result(divide and conquer)
	  int mid = ss + (se-ss)/2;
	  tree[segIndex] = buildSegmentTree(a, ss, mid, leftChild(segIndex)) + buildSegmentTree(a, mid+1, se, rightChild(segIndex));
	  return tree[segIndex];
  }
  //find the sum in the range start to end in the array a[]
  public int findRangeSum(int a [], int qs, int qe) {
	  if(qs < 0 || qe >= a.length || qs > qe) {   //validate the query range
		  System.out.println("Invalid range, returning -1");
		  return -1;
	  }
	  return findRangeSumUtil(0, a.length-1, qs, qe, 0); //passing end points of input array as starting segment, query range and index of root
  }
  //There can be three case :
  //1. Query range completely contains the current tree segment...return the segment index
  //2. Query range outside the segment.....return failure
  //3. Query range partially overlaps with the segment...solve using divide and conquer
  public int findRangeSumUtil(int ss, int se, int qs, int qe, int segIndex) {
	  if(qs <= ss && qe >= se) return tree[segIndex]; //current segment falls in the query range...return the current segment index
	  if(se < qs || ss > qe) return 0;  // terminate if segment outside the query range	  
	  //partial overlap....split the search space and solve using divide and conquer
	  int mid = ss + (se - ss)/2;
	  int leftSum = findRangeSumUtil(ss, mid, qs, qe, leftChild(segIndex)) ;
	  int rightSum =  findRangeSumUtil(mid+1,se, qs, qe, rightChild(segIndex));	  
	  return leftSum + rightSum;
  }
  public void update(int a[], int pos, int newVal) {
	  if(pos < 0 || pos >= a.length) {
		  System.out.println("Invalid index for update");
		  return;
	  }
	  int diff = newVal - a[pos];
	  a[pos] = newVal;
	  updateUtil(0, a.length-1, pos, diff, 0);
  }
  //takes O(h) time because one node at each level will be updated till the leaf node to adjust to the updated value in the array
  public void updateUtil(int ss, int se, int pos, int diff, int segIndex) {
	  if(pos <ss || pos >se) return;   //new position outside the segment....return
	  
	  //if the position lies inside the current seg, update the node value
		  tree[segIndex] += diff;
	  
	  //if segment length is greater than one, split and recurse...just divide the segment...no combine step(combine step will be required if updating a range)
		  if(ss != se) {
			  int mid = ss + (se - ss)/2;
			  updateUtil(ss, mid,pos, diff, leftChild(segIndex));
			  updateUtil(mid+1, se, pos, diff, rightChild(segIndex));  
		  }	    
  }
  //this method adds a value incVal to all array elements from index us to ue and updates the segtree accordingly
  public void updateRange(int a[], int us, int ue, int incVal) {
	  if(us < 0 || us >= a.length || ue < 0 || ue >= a.length || us > ue) {
		  System.out.println("Invalid update range");
		  return;
	  } 
	  updateRangeUtil(a, 0, a.length-1, us,ue, incVal, 0);
  }
  public void updateRangeUtil(int a[], int ss, int se, int us, int ue, int diff, int segIndex) {
	  if(ss > se || ss > ue || se < us) return;  //segment out of range
	  if(ss == se) {
		  tree[ss] += diff;
		  return;
	  }
	  //overlap case...split and solve
	  int mid = ss + (se - ss)/2;
	  updateRangeUtil(a, ss, mid, us, ue, diff, leftChild(segIndex));
	  updateRangeUtil(a, mid+1, se, us, ue, diff, rightChild(segIndex));
	  tree[segIndex] = tree[leftChild(segIndex)] + tree[rightChild(segIndex)];
  }
  
   public static void main(String args []) {
       int[] elements = {1,3,5,7,9,11};
       SegmentTree segmentTree = new SegmentTree(elements);
       System.out.println("Array Representation of seg tree : "+Arrays.toString(segmentTree.tree));
       //segmentTree.buildSegmentTree(elements);
       int sum = segmentTree.findRangeSum(elements,1,3);
       System.out.println("Sum of elemnts in the range 1 to 3 = "+sum);
       segmentTree.update(elements,1,10);   //elements[5] = 10
       sum = segmentTree.findRangeSum(elements,1,3);
       System.out.println("Sum of elemnts in the range 1 to 3 = "+sum);
   }
}
