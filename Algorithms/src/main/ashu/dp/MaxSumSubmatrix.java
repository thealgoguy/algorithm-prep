package main.ashu.dp;

//Given a matrix, find the submatrix with largest sum
//Uses the idea of Kadane's algo to find max sum subarray
//This algo works with at least +ve element
public class MaxSumSubmatrix {
    public static void main(String args []) {
    	int a[][] = {{1, 2, -1, -4, -20},
                    {-8, -3, 4, 2, 1},
                    {3, 8, 10, 1, 3},
                    {-4, -1, 1, 7, -6}};
    	//fix left and right cols
    	int r = a.length;
    	int c = a[0].length;
    	int top=0, bottom=0, left=0, right=0;
    	int globalMax = 0;
    	for(int lc=0; lc<c; lc++) {
    		int sum [] = new int[r];
    		for(int rc = lc; rc<c; rc++) {
    			for(int i=0; i<r; i++) {
    				sum[i] += a[i][rc];
    			}
    			int [] result = kadane(sum);
    			int max = result[0];
    			if(max > globalMax) {
    				globalMax = max;
    				left = lc; right = rc;
    				top = result[1];
    				bottom = result[2];
    			}
    		}
    	}
    	System.out.println("Maximum sum rectangle = "+globalMax);
    	System.out.println("Coordinates are : "+"("+top+","+left+"), ("+bottom+","+right+")");
    	System.out.println("The max sum rectangle : ");
    	for(int i=top; i<=bottom; i++) {
    		for(int j=left; j<=right; j++) {
    			System.out.print(a[i][j]+" ");
    		}
    		System.out.println();
    	}
    }
    
    public static int [] kadane(int a[]) {
    	int n = a.length;
    	int l=0, r=0;
    	int prev = a[0], max = a[0];
    	for(int i=1; i<n; i++) {
    		int curr = Math.max(a[i], a[i]+prev);
    		if(curr > max) {
    			max = curr;
    			r=i;
    			if(max==a[i]) l=i;
    		}
    		prev = curr;
    	}
    	int x [] = {max, l, r};
    	return x;
    }
}
