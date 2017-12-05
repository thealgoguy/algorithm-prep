package main.ashu.backtracking;
//Given a number K and string S of digits denoting a positive integer, build the largest number possible by performing swap operations on the digits of S atmost K times.
//Concept : Backtracking or DFS
public class LargestNumberKSwaps {
	static String max = "-1";	
	public static void main(String args []) {
		int k = 4;
	    String s = "3435335";
	    search(s.toCharArray(), 0, k);
	    System.out.println("Greatest number formed = "+max);
	}
	public static void search(char c [], int i, int k) {
	    int n = c.length;
	    //base case
	    if(n==1) {
	        max = String.valueOf(c);
	        return;
	    }
	    //base case
	    if(k==0 || i>=n-1) {
	        String x = String.valueOf(c);
	        greater(max.toCharArray(), x.toCharArray());
	        return;
	    }
	    //considering current number
	    String x = String.valueOf(c);
	    greater(max.toCharArray(), x.toCharArray());
	    //skip swap for c[i]
	    search(c, i+1, k); 
	    //considering all possible swaps for c[i]
	    for(int j=i+1; j<n; j++){
	        swap(c, i, j);    //swap
	        search(c, i+1, k-1); //search after swap
	        swap(c, i, j);    //backtrack
	    }
	}
	
	public static void greater(char c1[], char c2[]) {
	    int n1 = c1.length;
	    int n2 = c2.length;
	    if(n1 > n2) {
	        max = String.valueOf(c1);
	        return;
	    }
	    else if(n2>n1) {
	        max = String.valueOf(c2);
	        return;
	    }
	    boolean greater = true; 
	    for(int i=0; i<n1; i++) {
	        int x = Integer.parseInt(Character.toString(c1[i]));
	        int y = Integer.parseInt(Character.toString(c2[i]));
	        if(x>y){
	            max = String.valueOf(c1);
	            return;
	        }
	        else if(x<y) {
	            max = String.valueOf(c2);;
	            return;
	        }
	    }
	    
	}
	public static void swap(char c[], int i, int j){
	    char ch = c[i];
	    c[i] = c[j];
	    c[j] = ch;
	}
}
