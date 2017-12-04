package arrays;

public class MedianSortedArraysEqual {
     int a [], b[];
     int n;
     public MedianSortedArraysEqual(int a[], int b[]) {
	     this.a = a;
	     this.b = b;
	     this.n = a.length;
	}
     
     public double medianOfSingle(int a[]) {
    	 int n = a.length;
    	 return (n%2==0) ? (a[n/2-1]+a[n/2])/2 : a[n/2];
     }
    public double findMedian() {
    	if(a==null) medianOfSingle(b);
    	if(b== null) medianOfSingle(a);
    	return findMedianUtil(0, n-1, 0, n-1);
    }
    
    public double findMedianUtil(int l1, int h1, int l2, int h2) {
    	//base cases
    	int n = h1-l1+1;
    	if(n<=0) return -1;
    	if(n==1) return (a[l1]+b[l2])/2;
    	if(n==2) {
    		return( Math.max(a[l1], b[l2]) + Math.min(a[h1], b[h2]))/2;
    	}
    	int m1, m2;
    	
    	if(n%2 ==0) {
    		m1 = (a[l1+n/2 - 1] + a[l1+n/2])/2;
    		m2 = (b[l2+n/2 - 1] + b[l2+n/2])/2;
    	} else {
    		m1 = a[l1+n/2-1];
    		m2 = b[l2+n/2-1];
    	}
    	if(m1==m2) return m1;
    	else if(m1 < m2) {
    		if(n%2 == 0) {
    			return findMedianUtil(l1+n/2-1, h1, l2, l2+n/2);
    		}
    		else return findMedianUtil(l1+n/2, h1, l2, l2+n/2);
    	} else {
    		if(n%2==0) {
    			return findMedianUtil(l1,l1+n/2 , l2+n/2-1, h2);
    		}
    		else return findMedianUtil(l1, l1+n/2, l2+n/2-1, h2);
    	}
    }
    
    public static void main(String args []) {
    	int ar1[] = {1, 2, 3, 6};
        int ar2[] = {4, 6, 8, 10};
        MedianSortedArraysEqual med = new MedianSortedArraysEqual(ar1, ar2);
        if (ar1.length == ar2.length)
            System.out.println("Median is "+ med.findMedian());
        else
        	System.out.println("Doesn't work for arrays of unequal size");
    }
}
