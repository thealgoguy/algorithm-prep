package miscellaneous;

public class CelebrityProblem {
	static void generateNextPalindromeUtil(int num[], int n)  
    { 
        int mid = n / 2;  
        // end of left side is always 'mid -1' 
        int i = mid - 1;     //left middle index      
        int j = (n % 2 == 0) ? mid : mid + 1; //right middle index
        // Initially, ignore the central palindromic parts
        while (i >= 0 && num[i] == num[j])  
        { 
            i--; 
            j++; 
        } 
//increment middle only if given number is palindrome or left is smaller
        boolean incrementMiddle = (i < 0 || num[i] < num[j]);        
        if(!incrementMiddle) { 
        	// Copy the mirror of left to tight
        	while (i >= 0)  
            { 
                num[j++] = num[i--]; 
            }
        	return;
        }else { 
        	//increment the middle element, propagate carry to left and mirror left to right      	 
            int carry = 0;           
            if (n % 2 == 1) { 
                num[mid] += 1; 
                carry = num[mid] / 10; 
                num[mid] %= 10; 
            } else{
            	carry = 1;
            }
            i = mid - 1; 
            j = (n % 2 == 0 ? mid : mid + 1);              
            // Add 1 to the rightmost digit of the left  
            // side, propagate the carry towards MSB digit  
            // and simultaneously copying mirror of the  
            // left side to the right side. 
            while (i >= 0)  
            { 
                num[i] = num[i] + carry; 
                carry = num[i] / 10; 
                num[i] %= 10; 
                num[j] = num[i];// copy mirror to right 
                i--; 
                j++; 
            }  
        
        }
    } 
  
    // The function that prints next palindrome  
    // of a given number num[] with n digits. 
    static void generateNextPalindrome(int num[], int n)  
    { 
        System.out.println("Next Palindrome is:"); 
          
        // Input type 1: All the digits are 9,  
        // simply o/p 1 followed by n-1 0's  
        // followed by 1. 
        if (isAll9(num, n)) { 
            System.out.print("1"); 
            for (int i = 0; i < n - 1; i++) 
                System.out.print("0"); 
            System.out.println("1"); 
  
        } 
      
        // Input type 2 and 3 
        else { 
            generateNextPalindromeUtil(num, n); 
            printarray(num); 
        } 
    } 
  
    // A utility function to check if num has all 9s 
    static boolean isAll9(int num[], int n) { 
        for (int i = 0; i < n; i++) 
            if (num[i] != 9) 
                return false; 
        return true; 
    } 
  
    /* Utility that prints out an array on a line */
    static void printarray(int num[]) { 
        for (int i = 0; i < num.length; i++) 
            System.out.print(num[i]); 
        System.out.println(); 
    } 
  
    public static void main(String[] args)  
    { 
        int num[] = { 9, 4, 1, 8, 7, 9, 7, 8, 3, 2, 2 }; 
        generateNextPalindrome(num, num.length); 
    } 
}
