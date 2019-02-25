package main.ashu.strings;

import java.util.Arrays;

public class AshuPractice {
    public static void main(String args []) {
    	String A = "13.0";
    	String B = "13.0.8";
    	int x = compareVersion(A,B);
    	System.out.println(x);
    }
    
    public static int compareVersion(String A, String B) {
        if(A == null && B == null) return 0;
        if(B == null) return 1;
        if(A == null) return -1;
        String a [] = A.split("\\.");
        String b [] = B.split("\\.");
           int i=0;
            while(i<a.length && i<b.length) {
                int x = Integer.parseInt(a[i]) - Integer.parseInt(b[i]);
                if(x == 0) {
                    i++; continue;
                }
                else{
                    return (x >0) ? 1 : -1;
                }
            }
        if(i == a.length && i==b.length) return 0;
        else if(i== a.length) return -1;
        else return 1;
    }
}
