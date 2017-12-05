package main.ashu.arrays;

public class AllSubsetsBF {
   
	public static void main(String args []) {
		int a [] = {1,2,3,4};
		int n = a.length;
		//there will be (1<<n) such sets. count from 0 till (1<<n)-1 and 
		//print elements in the array corresponding to positions of set bits in the counter.
		for(int cnt =0; cnt<(1<<n); cnt++) {
			String str ="";
			//check which all out of n bits are set in the counter.
			for(int i=0; i<n; i++) {
				if((cnt & (1<<i)) > 0) str+=a[i]+" ";
			}
			System.out.println("subset no. "+(cnt+1)+" = "+str);
		}
	}
}
