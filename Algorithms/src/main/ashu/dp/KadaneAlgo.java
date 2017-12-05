package main.ashu.dp;

public class KadaneAlgo {
   
	public static void main(String args []) {
		int a[] = {-2, -3, 4, -1, -2, 1, 5, -3};
		
		int n = a.length;
		int l=0, r=0;
		int max = a[0];
		int prev = a[0];
		for(int i=1; i<n; i++) {
			int curr = prev + a[i];			
			if(curr >= a[i]) {
				r++;
				prev = curr;
			}
			else {
				l=i; r=i;
				prev = a[i];
			}
			max = Math.max(max, curr);
		}
		
		System.out.println("Maxim subarray sum = "+max);
	}
}
