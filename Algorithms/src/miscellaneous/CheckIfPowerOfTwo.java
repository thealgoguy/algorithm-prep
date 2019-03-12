package miscellaneous;

public class CheckIfPowerOfTwo {
	// All power of two numbers have only one bit set.
	// If we subtract a power of 2 numbers by 1 then all unset bits after the
	// only set bit become set;
	// and the set bit become unset.
	static boolean isPowerOfTwo(int x) {
		/*
		 * First x in the below expression is for the case when x is 0
		 */
		return x != 0 && ((x & (x - 1)) == 0);
	}
	
	// take the log of the number on base 2 and if you get an integer then number is power of 2.
	static boolean isPowerOfTwo2(int n) {
		return (int) (Math.ceil((Math.log(n) / Math.log(2)))) == (int) (Math
				.floor(((Math.log(n) / Math.log(2)))));
	} 
	
	static boolean isPowerOfTwo3(int n) 
    { 
        if (n == 0) 
            return false;          
        while (n != 1) 
        { 
            if (n % 2 != 0) 
                return false; 
            n = n / 2; 
        } 
        return true; 
    } 

}
