package main.ashu.arrays;

public class ZAlgo {
     public static void main(String args []) {
    	 String text = "BACDGABCDAPABCD";
    	 String pat = "ABCD";
    	 char arr[]  = (pat + text).toCharArray();
    	 int z [] = new int[arr.length];
    	 z[0] = 0;
    	 int l=0, r=0;
    	 for(int i=1; i<z.length; i++) {
    		 z[i] = (i<r) ? Math.min(z[i-l], r-i+1) : 0;
    		 while(i+z[i] < z.length && arr[i+z[i]] == arr[z[i]]) z[i]++;
    		 if(i+z[i]>r) {
    			 l = i; 
    			 r = i+z[i];
    		 }
    	 }
    	 for(int i=pat.length(); i<z.length; i++) {
    		 if(z[i]==pat.length()) System.out.println("Pattern occurs at index "+(i-pat.length()));
    	 }
     }
}
