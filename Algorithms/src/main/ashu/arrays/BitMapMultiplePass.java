package main.ashu.arrays;

//memory = 2 bytes = 16 bits  range that we can capture (0...15)
//search range is in 1..100
//we may end up storing some extra bits...we can handle this explicitly that the missing number shouldn''t exceed the inpput range
// and if range is the entire integer set we got no problem
//Note : by increasing the memory we can decrease the number of passes made over the array

public class BitMapMultiplePass {
	public static void main(String args []) {
		int numBytes = 2;
		int [] a = {2,3,5,12,34,56,12,3,7,14,46,59,65,72,81,86,92,95,99,96,68,33};
		System.out.print("Input array  : ");
		for(int i=0; i<a.length; i++) System.out.print(a[i]+" ");
		byte [] bitmap = new byte[numBytes]; 
		int size = numBytes * 8;
		int low = 0, high = numBytes*8-1;
		System.out.println("\nMissing numbers in various passes are : ");
		int max = 100;
		int pass = 0;
		while(low<=max) {
			pass++;
			System.out.print("Pass "+pass+" : "+"(low="+low+", high="+high+") : ");
			//Phase1(setting phase) : iterate over the array and set the bit values in the bitmap
			for(int i=0; i<a.length; i++) {
				if(a[i] >=low && a[i] <= high) {
					/*int m = a[i]/8; //this will be valid for 1st pass only
    				int n = a[i]%8;*/
					int m = (a[i]/8) % numBytes;  //byte number may exceed the available count
					int n = a[i]%8;   //will always be in  0 to 7
					//check status of nth bit of mth byte
					int bitstatus = bitmap[m] & (1<<n); //0 or 1
					if(bitstatus ==0) bitmap[m] |= (1 << n);
				}
			}
			//Phase2(checking phase) : iterate over bitmap and look for bit positions not which are not set
			for(int i=0; i<numBytes; i++) {
				for(int j=0; j<8; j++) {
					//check if ith byte has jth bit set
					if((bitmap[i] & (1<<j)) ==0) {
						int missing = low + i*8+j;
						System.out.print(missing+" ");
					}
				}
			}
			//updating the range for next pass
			low = high+1;
			high=low+size-1;
			System.out.println();
		}
		System.out.println("\nTotal number of passes made on the input array = "+pass);
		int pass_cnt = (int) Math.ceil((double)max/(double)(bitmap.length*8));
		System.out.println("Number of passes using maths = "+pass_cnt);
	}
}
