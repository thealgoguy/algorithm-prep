import java.util.Arrays;

//using Meet in the Middle technique...
public class MaxSubsetSumLessThanS {
public static void main(String args []) {
	int a [] = {45, 34, 4, 12, 5, 2};
	int n = a.length;
	int sum = 42;
	int max = Integer.MIN_VALUE;
	int x  [] = new int[(1<<n/2)];
	int y [] = new int[1<<(n-n/2)];
	for(int i=0; i<(1<<n/2); i++) {
		for(int j=0; j<n/2; j++) {
			if((i &(1<<j)) > 0) x[i]+=a[j];
		}
	}
	for(int i=0; i<(1<<(n-n/2)); i++) {
		for(int j=0; j<(n-n/2); j++) {
			if((i &(1<<j)) > 0) y[i]+=a[n/2+j];
		}
	}
	
	Arrays.sort(y);
	for(int i=0; i<(1<<n/2); i++) {
		int j=0, k=1<<(n-n/2)-1;
		if(x[i] > sum) continue;
		while(j<k) {
			int mid = j+(k-j)/2;
			if(x[i]+y[mid] <= sum) j=mid+1;
			else k=mid-1;
		}
		if(x[i]+y[j] > sum) j--;
		if(x[i]+y[j] > max) max = x[i]+y[j];
	}
	
	System.out.println("The largest subset sum less than or equal to given sum is "+ max);
}
}
