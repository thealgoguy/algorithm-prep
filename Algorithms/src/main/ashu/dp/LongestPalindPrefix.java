package main.ashu.dp;

import java.util.Arrays;
import java.util.Scanner;


public class LongestPalindPrefix {
	static int [] makeZArray(String str) {
		int Z [] = new int[str.length()];
		Z[0] = 0;int l=0,r=0;
		for(int i=1; i<Z.length; i++) {
			if(i > r) {
				l=r=i;
				while(r < Z.length && str.charAt(r)==str.charAt(r-l)) r++;
				Z[i] = r-l; r--;
			}
			else {
				int k = i-l;
				if(Z[k] < r-i) Z[i] = Z[k];
				else { l = i;
					while(r < Z.length && str.charAt(r)==str.charAt(r-i)) r++;
					Z[i] = r-l; r--;
				}
			}
		}
		return Z;
	}
   public static void main(String args []) {
	   Scanner sc = new Scanner(System.in);
	   String s = sc.next();	   
	   StringBuffer st = new StringBuffer(s);
	   st.reverse();
	   String zStr = s+"#"+st;   //for longest palindromic prefix	   
	   int Z [] = makeZArray(zStr);
	   int max =-1;
	   for(int i=Z.length-1;i>=s.length()+1; i--) {
		   if(Z[i]==Z.length-i) {
			   max = Math.max(Z[i], max);
		   }
	   }	   
	   String ans1 = zStr.substring(0, max);
	   zStr = st+"#"+s;  // for longest palindromic suffix
	   Z  = makeZArray(zStr);
	   max =-1;
	   for(int i=Z.length-1;i>=s.length()+1; i--) {
		   if(Z[i]==Z.length-i) {
			   max = Math.max(Z[i], max);
		   }
	   }
	   ans1 = zStr.substring(0, max)+ans1;
	   System.out.println(ans1);
	   Z = makeZArray(ans1);
	   Z[0]=Z.length;
	   int p [] = new int[Z.length+1];
	   //now make an array p, where p[i] = count of pal prefix of length i;
	   for(int i=0; i<Z.length; i++) p[Z[i]] +=1;
	   //now p[i] = frequency of prefixes of length p[i] as per Z array...but every prefix of length p[i] is a prefix of p[i+j], i<j<n.
	   //Z array reflects only largest prefix so update the values of smaller p[i] values starting from p[n-1]
	   for(int i=p.length-2; i>=0; i--) {
		   p[i] = p[i+1] + p[i];
	   }
	   for(int i=1; i<p.length; i++)
		   System.out.print(p[i]+" ");
	   
   }
}
