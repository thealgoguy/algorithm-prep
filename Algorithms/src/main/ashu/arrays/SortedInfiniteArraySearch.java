package main.ashu.arrays;

//https://www.geeksforgeeks.org/find-position-element-sorted-array-infinite-numbers/
public class SortedInfiniteArraySearch {
    public static void main(String args []) {
    	int a [] = {3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170};
    	int key = 10;
    	int pos= search(a, key);
    	System.out.println("Position of the key is : "+pos);
              
    }
    
    //since array is infinite, first need to get the index range where the key may possibly be found
    //and then do a binary search over the range
    public static int search(int a [], int key) {
    	int l=0, h=1;
    	while(a[h] < key) {
    		l = h;
    		h *= 2;
    	}
    	return binSearch(a, l, h, key);
    }
    
    public static int binSearch(int a[], int l, int h, int key){
    	if(l >h) return -1;
    	if(l == h) return a[l] == key ? l : -1;
    	int mid = l + (h-l)/2;
    	 return a[mid] > key ? binSearch(a, l, mid-1, key) : binSearch(a, mid+1, h, key);
    }
}
