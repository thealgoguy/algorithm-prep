package geeksforgeeks;
//A complete binary tree with n leaves and n-1 internal nodes..total 2*n-1 nodes. 
//Array Representation : Leaf nodes are elements of the input array, internal nodes represent merging of leaf nodes(merging may differ
// a/c to use case, sum,min,max,etc). for node at index i, left child is at 2*i+1 and right child at 2*i+2 and parent at (i-1)/2.
//create...O(n). Query..O(logn), update..O(logn)....to update we process one node at every level and no of levels..O(logn)
public class SegmentTree {
  public int height;
  public int noOfLeafNodes;
  public int maxSize;
  public int tree[];
  public SegmentTree(int arr []) {
	  this.noOfLeafNodes = arr.length;
	  this.height = (int)(Math.ceil(Math.log(noOfLeafNodes) /  Math.log(2)));
	  this.maxSize = 2 * (int)Math.pow(2, height) - 1;
	  tree = new int[maxSize];
	  buildSegmentTree(arr,0,arr.length-1,0); //pass end points of input array as starting segment and 0 as index of root
  }
  public int leftChild(int position) {
	  return 2*position+1;
  }
  public int rightChild(int position) {
	  return 2*position+2;
  }
  //build segment tree recursively starting from root node..in recursive preorder fashion.
  public int buildSegmentTree(int a[], int start, int end, int current) {
	  if(start == end) {   //recursion terminate condition
		  tree[current] = a[start];
		  return tree[current];
	  }
	  int mid = start + (end-start)/2;
	  tree[current] = buildSegmentTree(a, start, mid, leftChild(current)) + buildSegmentTree(a, mid+1, end, rightChild(current));
	  return tree[current];
  }
  //find the sum in the range start to end in the array a[]
  public int findRangeSum(int a [], int qs, int qe) {
	  if(qs < 0 || qe >= a.length || qs > qe) {
		  System.out.println("Invalid range, returning -1");
		  return -1;
	  }
	  return findRangeSumUtil(0, a.length-1, qs, qe, 0); //passing end points of input array as starting segment, query range and index of root
  }
  public int findRangeSumUtil(int ss, int se, int qs, int qe, int segIndex) {
	  if(qs <= ss && qe >= se) return tree[segIndex]; //current segment falls in the query range...return the current segment index
	  if(se < qs || ss > qe) return 0;  // terminate if segment outside the query range	  
	  //partial overlap....split the search space
	  int mid = ss + (se - ss)/2;
	  tree[segIndex] = findRangeSumUtil(ss, mid, qs, qe, leftChild(segIndex)) + findRangeSumUtil(mid+1,se, qs, qe, rightChild(segIndex));
	  return tree[segIndex];	  
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
  public void updateUtil(int ss, int se, int pos, int diff, int segIndex) {
	  if(pos <ss || pos >se) return;   //new position outside the segment....return
	  //if inside the segment..update the value of node and its children in preorder fashion
	  tree[segIndex] += diff;
	  //split and recurse
	  if(ss != se) {
		  int mid = ss + (se - ss)/2;
		  updateUtil(ss, mid,pos, diff, leftChild(segIndex));
		  updateUtil(mid+1, se, pos, diff, rightChild(segIndex));
	  }	  
  }
  
   public static void main(String args []) {
       int[] elements = {1,3,5,7,9,11};
       SegmentTree segmentTree = new SegmentTree(elements);
       //segmentTree.buildSegmentTree(elements);
       int sum = segmentTree.findRangeSum(elements,1,3);
       System.out.println("Sum of elemnts in the range 1 to 3 = "+sum);
       segmentTree.update(elements,1,10);   //elements[5] = 10
       sum = segmentTree.findRangeSum(elements,1,3);
       System.out.println("Sum of elemnts in the range 1 to 3 = "+sum);
   }
}
