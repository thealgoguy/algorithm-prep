package main.ashu.arrays;

//Find min/max/sum value in a given range(l,r) in sqrt(n) time(per query) and space
//This is called square root decomposition method.

public class RMQ {
	public int a[];
	public int b [];
	public int blockSize;

	public RMQ(int a[]) {
		this.a = a;
		this.b = preprocess(a);
		blockSize = (int)Math.sqrt(a.length);
	}

	public static void main(String args []) {
		int a [] = {1, 5, 2, 4, 6, 1, 3, 5, 7, 10};
		RMQ  rmq = new RMQ(a);

		for(int i=0; i<a.length; i++) {
			for(int j=i; j<a.length; j++) {
				int min = rmq.findMin(i, j);
				System.out.println("Minimum value in the range ("+i+", "+j+") : "+ +min);
			}
		}    	   	 
	}

	public int[] preprocess(int a[]) {
		int n = a.length;
		int blockSize = (int)Math.sqrt(n);
		int noOfBlocks = (int)Math.ceil((double)n/blockSize);
		int b [] = new int[noOfBlocks];
		for(int i=0; i<b.length; i++) {
			b[i] = Integer.MAX_VALUE;
		}
		int index=0;
		for(int i=0; i<n; i++) {
			b[index] = Math.min(b[index], a[i]);
			if(index >0 && index % blockSize == 0) index++;
		}
		return b;
	}

	public int findMin(int l, int r) {
		if(l>r) return Integer.MAX_VALUE;
		int min = Integer.MAX_VALUE;
		int n = a.length;
		//considering the leading partial block
		while(l<r && l%blockSize != 0) {
			min = Math.min(a[l], min);
			l++;
		}
		//considering full blocks
		while(l+blockSize <= r) {
			min = Math.min(min, b[l/blockSize]);
			l += blockSize;
		}
		//considering trailing partial block
		while(l<=r) {
			min = Math.min(min, a[l]);
			l++;
		}
		return min;
	}
}
