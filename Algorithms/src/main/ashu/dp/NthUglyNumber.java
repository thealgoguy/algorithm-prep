package main.ashu.dp;

public class NthUglyNumber {

	//An ugly number has only 2, 3 or 5 as its prime factors

	public static void main(String args[]) {
		int n = 150;
		int next = nthUgly(n);
		System.out.println(n + "th ugly number is : " + next);
	}

	public static int nthUgly(int n) {
		int ugly[] =  new int[n];
		ugly[0] = 1;  
		int x = 2;  //next multiple of 2
		int y = 3;  // next multiple of 3
		int z = 5;  //next multiple of 5
		int twos=0, threes=0, fives=0;
		if (n == 1)
			return n;
		int c = 1;
		
		// merge three sorted sequences
		while (c < n) {
			ugly[c] = Math.min(x, Math.min(y, z));
			//System.out.println("c = "+c+" next = "+ugly[c]);
			if (ugly[c] == x){
				twos++; x = 2 * ugly[twos];    //tricky step....?
			}
			if (ugly[c] == y){
				threes++; y = 3 * ugly[threes];
			}			
			if(ugly[c]==z){
				fives++; z = 5 * ugly[fives];
			}				
			c++;
		}
		return ugly[n-1];
	}

}
