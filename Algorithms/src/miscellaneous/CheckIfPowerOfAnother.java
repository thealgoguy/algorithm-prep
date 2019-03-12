package miscellaneous;

public class CheckIfPowerOfAnother {
	static boolean isPower(int x, int y) {
		// logarithm function to
		// calculate value
		int res1 = (int) Math.log(y) / (int) Math.log(x);
		// Note : this is double
		double res2 = Math.log(y) / Math.log(x);
		// compare to the result1 or
		// result2 both are equal
		return (res1 == res2);
	}
	
	//can be optimized by binary search
	public static boolean isPower2(int x, int y) 
    { 
        // The only power of 1 is 1 itself 
        if (x == 1) 
            return (y == 1);  
        // Repeatedly compute power of x 
        int pow = 1; 
        while (pow < y) 
            pow = pow * x; 
        // Check if power of x becomes y 
        return (pow == y); 
    } 

	// Driver Code
	public static void main(String args[]) {
		if (isPower(27, 729))
			System.out.println("true");
		else
			System.out.println("false");
	}
}
