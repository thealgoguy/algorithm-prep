package arrays;

 //Best : https://codessnippets.wordpress.com/2015/10/05/mediankth-element-of-two-sorted-arrays/
//http://stackoverflow.com/questions/34103511/understanding-the-algorithm-of-median-of-two-sorted-arrays
//k is zero based index  O(log(m+n)).....merge sort will take O(k) space
//returns element at kth index in the merged array....no actual merging done
//2 versions of bSearch based on k is 0-based or 1-based
public class MedianSortedArraysGeneric {
 
    public static double findMedianZeroBased(int a[], int b[]) {
    	int m = a.length;
    	int n = b.length;
    	int count = m+n;
    	int k = count/2;
    	//if m+n is odd, there will be two centre elements , so take average. if count is odd, count/2 th is the only centre
    	if(count %2 == 0) return (kthSmallestZeroBased(k-1, a, b, 0, m-1, 0, n-1) + kthSmallestZeroBased(k, a, b, 0, m-1, 0, n-1)) / 2.0;
    	else return kthSmallestZeroBased(k, a, b, 0, m-1, 0, n-1);
    }
    private static int kthSmallestZeroBased(int k, int a[], int b[], int s1, int e1, int s2, int e2) {
    	int m = e1-s1+1;
    	int n = e2-s2+1;
    	if(m > n) kthSmallestZeroBased(k, b,a,s2,e2,s1,e1);
    	//base cases
    	if(k >= m+n-1) {
    		System.out.println("Error, kth smalles element doesn't exist");
    		return -1;
    	}
    	if(m==0) return b[s2+k];
    	if(n==0) return a[s1+k];
    	if(k==0) return Math.min(a[s1], b[s2]);
    	
    	/*int m1 = s1+k/2; 
    	int m2 = s2+k/2;*/
    	//don't blindly split like above, split in ratio of lengths....think why ?
    	int m1 = (int)((double)m/(double)(m+n)*k);
    	int m2 = k - (m1+1);  //m1+m2 = k-1
    	//map m1,m2 to corresponding array indexes
    	m1 = s1 + m1;
    	m2 = s2 + m2;
    	if(a[m1] < b[m2]) {
    		//m1-s1+1 elements will be in first k elements
    		k = k-(m1-s1+1);
    		s1 = m1+1;
    		e2 = m2;
    	} else {
    		//m2-s2+1 elements will be in first k elements
    		k = k-(m2-s2+1);
    		e1 = m1;
    		s2 = m2+1;
    	}
    	return kthSmallestZeroBased(k, a, b, s1, e1, s2, e2);   	
    }
    public static double findMedianLengthBased(int a[], int b[]) {
    	int m = a.length;
    	int n = b.length;
    	int count = m+n;
    	int k = count/2+1;
    	//if m+n is odd, there will be two centre elements , so take average. if count is odd, count/2 th is the only centre
    	if(count %2 == 0) return (kthSmallestLengthBased(k-1, a, b, 0, 0) + kthSmallestLengthBased(k, a, b, 0, 0)) / 2.0;
    	else return kthSmallestLengthBased(k, a, b, 0, 0);
    }
    //here k is 1-based so kth element be at k-1 th index
    public static double kthSmallestLengthBased(int k, int a[], int b[], int s1, int s2) {
    	if(s1 >= a.length) return b[s2+k-1];
    	if(s2>= b.length) return b[s1+k-1];
    	if(k==1) return Math.min(a[s1], b[s2]);
    	//if any of the array has less than k/2 elements, set the mid to MAX so that it will always move to the other array due to base check.
    	int m1 = (s1+k/2-1 < a.length) ? a[s1+k/2-1] : Integer.MAX_VALUE;
    	int m2 = (s2+k/2-1 < b.length) ? b[s2+k/2-1] : Integer.MAX_VALUE;
    	if(m1 < m2) 
    		return kthSmallestLengthBased(k-k/2, a, b, s1+k/2, s2);
    	else 
    		return kthSmallestLengthBased(k-k/2, a, b, s1, s2+k/2);
    }
    public static void main(String args []) {
    	int ar1[] = {3};
        int ar2[] = {1, 2, 4, 5};
        double median = findMedianZeroBased(ar1, ar2);
        System.out.println("Median is "+median);
        median = findMedianLengthBased(ar1, ar2);
        System.out.println("Median is "+median);
    }
}
