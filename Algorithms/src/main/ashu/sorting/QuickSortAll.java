package threadpool;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

//http://www.techiedelight.com/quicksort/
//crux : the choice of pivot decides how efficient "partition" will be.Once pivot is decided, partition rearranges
//the array elements so that the smaller ones come before the larger ones, leaving pivot at its correct place as in sorted array. 
//Pivot can be - first/last element, middle element, random element, median of first, middle and last or median of 9...etc
//Read wikipedia

public class QuickSortAll {
	public static void swap(int a [], int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	//Lomuto partition scheme....single index used. select last element as pivot and do reordering of elements
	//bad O(n*) when array sorted or has equal elements
	public static int partition1(int a[], int i, int j) {
		int pivot = a[j]; //selecting last element as pivot or middle or first
		int pi=i;
		for(int k=i; k<j; k++) {
			if(a[k] <= pivot){
				swap(a, k, pi);
				pi++;
			}
		}
		swap(a, pi, j); //now make the pivot guy at j go to its rightful index pi
		return pi;
	} 
	//Hoare partition scheme...good in case of equal elements but bad if sorted
	//take 2 pointers approaching each other. If inversion detected, swap them
	//does three times fewer swaps on avg
	public static int partition2(int a [], int low, int high) {
		int pivot = a[low+(high-low+1)/2];   //or a[low] or a[high]
		int i = low, j = high;
		while(i <=j) {
			while(a[i] < pivot) i++;
			while(a[j] > pivot) j--;
			if(i <= j)  {
				swap(a, i, j);
				i++; j--;
			}
		}
		return i;       
	}
	
	//particulary designed for handling duplicate keys....but worst case is O(n*n)
	public static int [] threeWayPartitionSinglePivot(int a[], int low, int high) {
		int [] ans = new int[2];
		if(low < high) {
			int pivot = a[low];  //first element as pivot
			int lt = low;
			int gt = high;
			int i = low+1;  //low is pivot
			
			while(i <=gt) {
				if(a[i] < pivot) {
					swap(a, i, lt);
					lt++; i++;
				}
				else if(a[i] > pivot) {
					swap(a, i, gt);						
					gt--;
				}
				else i++;
			}
			ans[0] = lt;
			ans[1] = gt;
		}
		return ans;
	}
	public static int [] threeWayPartitionDoublePivot(int a[], int low, int high) {
		int [] ans = new int[2];
		if(low < high) {
			//ensure pivot1 is smaller
			//if pivot1 and pivot2 are equal, fast forward pivot1 so that it becomes different
			int pivot1 = a[low];
			int  pivot2 = a[high];
			if(pivot1 > pivot2) {
				swap(a,low,high);
				pivot1 = a[low];
				pivot2 = a[high];
			}
			else if(pivot1==pivot2) {
				while(pivot1==pivot2 && low < high) {
					low++; pivot1=a[low];
				}
			}
			int lt = low + 1;
			int gt = high - 1;
			int i = low+1;  //low is pivot
			
			while(i <= gt) {
				if(a[i] < pivot1) {
					swap(a, i, lt);
					lt++; i++;
				}
				else if(a[i] > pivot2) {
					swap(a, i, gt);						
					gt--;
				}
				else i++;
			}
			swap(a, low, --lt);
		    swap(a, high, ++gt);
			ans[0] = lt;
			ans[1] = gt;
		}
		return ans;
	}
	
	//nlogn best and avg case, n*n in worst case when array already sorted
	//select leftmost or rightmost element as pivot
    public static void qsortPoor(int a[], int low, int high) {
    	if(low < high) {
    		int pi = partition1(a, low, high);
    		//System.out.println("partition index = "+pi);
    		qsortPoor(a, low, pi-1);
    		qsortPoor(a, pi+1, high);	
    	}   	
    }
    //select random element from array as pivot
    public static void qsortrandomized(int a[], int low, int high) {
    	if(low < high) {
    		//choose an random index and swap it with last index and do partitioning with last index as earlier
    		int range = high-low+1;
    		//int r = new Random().nextInt(range); this is also true
    		int r =  (int)(Math.random()) % range;
    		int randPi = r + low;
    		//System.out.println("random index = "+randPi);
    		swap(a,randPi, high);
    		int pi = partition1(a, low, high);
    		qsortrandomized(a, low, pi-1);
    		qsortrandomized(a, pi+1, high);	
    	}   	
    }
    //counters already sorted case but poor in equal case
    //An even stronger pivoting rule, for larger arrays, is to pick the ninther as pivot, a recursive median-of-three (Mo3), defined as
    //ninther(a) = median(Mo3(first ? of a), Mo3(middle ? of a), Mo3(final ? of a))
    public static void qsortUsingMedian(int a[], int low, int high) {
    	if(low < high) {
    		int n = high-low+1;
    		if(n <= 5) insertionSort(a, low, high);
    		else {
    			int median = medianOfThree(a, low, high);
    			int pi = partition2(a,low, high);
    			qsortUsingMedian(a, low, pi-1);
    			qsortUsingMedian(a, pi+1, high);
    		}
    	}
    }
    //https://dzone.com/articles/algorithm-week-quicksort-three
    //also called Dutch National Flag algorithm
    public static void qsortUsingSinglePivot3WayPartition(int [] a, int low, int high) {
    	if(low < high) {
    		int n= high-low+1;
    		int pi[] = threeWayPartitionSinglePivot(a, low, high);
    		int lt = pi[0], gt = pi[1];
    		qsortUsingSinglePivot3WayPartition(a, low, lt-1);
    		qsortUsingSinglePivot3WayPartition(a,gt+1,high);
    	}
    }
    //used by Java7 in Arrays.sort() api
    public static void qsortUsingDualPivot3WayPartition(int []a, int low, int high) {
    	if(low >= high) return;
    	int pi[] = threeWayPartitionDoublePivot(a, low, high);
    	int lt = pi[0], gt = pi[1];
    	qsortUsingDualPivot3WayPartition(a, low, lt-1);
    	qsortUsingDualPivot3WayPartition(a, lt+1, gt-1);
    	qsortUsingDualPivot3WayPartition(a, gt+1, high);
    	
    }
    //returns median of left, middle and right. also places median in the middle position
    public static int medianOfThree(int a [], int low, int high) {
    	if(low > high) return -1;
    	if(low==high) return a[low];
    	int mid = low + (high-low)/2;
    	if(a[low] < a[mid]) swap(a,low, mid);
    	if(a[mid] < a[high]) swap(a, mid, high);
    	if(a[low] < a[mid]) swap(a,low, mid);
    	return a[mid];
    }
    private static void insertionSort(int[] a, int low, int high ) {
        for( int i = low + 1; i <= high; i++ ) {
          for(int j=i; j>low; j--) {
             if(a[j] < a[j-1]) swap(a, i, j-1);	  
          }
        }
    }
    
    public static void qsortIterative(int a[], int low, int high) {
    	if(low < high) {
    		Stack<Integer> stack = new Stack();
    		stack.push(high); 
    		stack.push(low);
    		while(!stack.isEmpty()) {
    			int l = stack.pop();
    			int h = stack.pop();
    			int pi = partition1(a, l, h);
    			//push left recursive call
    			if(l < pi-1) {
    				stack.push(pi-1);
    				stack.push(l);
    			}
    			//push right recursive call
    			if(pi+1 < h) {
    				stack.push(h);
    				stack.push(pi+1);
    			}
    		}
    	}   
    }
    
    public static void main(String args []) {
    	int a[] = {3,9,2,23,45,7,11,11,9,34,90,24,0,5,18,5};
    	qsortPoor(a, 0, a.length-1);
    	System.out.println("Quick sort single pivot 2-way partition    : "+Arrays.toString(a));
    	qsortrandomized(a, 0, a.length-1);
    	System.out.println("Quick sort random pivot 2-way partition    : "+Arrays.toString(a));
    	qsortUsingMedian(a, 0, a.length-1);
    	System.out.println("Quick sort median-of-three 2-way partition : "+Arrays.toString(a));
    	qsortUsingSinglePivot3WayPartition(a, 0, a.length-1);
    	System.out.println("Quick sort single pivot 3-way partition    : "+Arrays.toString(a));
    	qsortUsingDualPivot3WayPartition(a, 0, a.length-1);
    	System.out.println("Quick sort dual pivot 3-way partition      : "+Arrays.toString(a));
    	qsortIterative(a, 0, a.length-1);
    	System.out.println("Quick sort iterative version using stack   : "+Arrays.toString(a));
    }
}

