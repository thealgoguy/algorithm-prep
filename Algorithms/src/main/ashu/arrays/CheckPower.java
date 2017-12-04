package threadpool;

import java.util.Arrays;


public class CheckPower {
	//time = O(loga base b)
	public static boolean isPower(int a, int b) {
		int pow = 1;
		while(pow < a) {
			if(pow == a) break;
			pow *= b;
		}
		return (pow==a) ? true : false; 		
	}

	//O(log loga)
	//idea : find a range in which power may lie and bin search in that range only
	public static boolean isPowerOpt(int a, int b) {
		int pow = b, i =1;
		int pre = pow;
		while(pow <a) {
			pre = pow;
			pow = pow * pow;
			i *= 2;
		}
		if(pow == a) return true;
		//create a sorted array of powers from b(i/2...i) and bin search the array for a
		int p [] = new int[i/2];		
		for(int j=0; j<p.length; j++) {
			p[j] = pre *b;
		}
		int idx = Arrays.binarySearch(p, a);
		return idx != -1;
	}

    //check if a is power of two	
	//Observ : if a is a power of 2, then a-1 has all its bits flipped wrt a, so ANDing will give null. 
	public boolean isPowerOfTwo(int a) {
		return (a!=0) && ((a & (a-1))==0);
	}
	
	public static void main(String args []) {
		int a = 128, b = 4;
		//boolean res = isPower(a, b);
		boolean res = isPowerOpt(a, b);
		if(res) System.out.println(a +" is a power of "+b);
		else System.out.println(a +" is not a power of "+b);
		
	}
}
