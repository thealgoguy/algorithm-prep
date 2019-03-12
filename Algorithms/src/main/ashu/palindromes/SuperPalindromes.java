package main.ashu.palindromes;

//https://leetcode.com/problems/super-palindromes/
//a positive integer is a superpalindrome if it is a palindrome, and it is also the square of a palindrome.
//Now, given two positive integers L and R (represented as strings), return the number of superpalindromes in the inclusive range [L, R].
//1 <= len(L|R) <= 18, i.e. L and R are strings representing integers in the range [1, 10^18)

public class SuperPalindromes {

	public int superpalindromesInRange(String sL, String sR) {
		long L = Long.valueOf(sL);
		long R = Long.valueOf(sR);
		int MAGIC = 100000; // we will iterate upon this range only to generate
		// palindromes in range [L/2, R/2] and check if
		// square of it is a superpalindrome
		int ans = 0;
		// count odd length;
		for (int k = 1; k < MAGIC; ++k) {
			StringBuilder sb = new StringBuilder(Integer.toString(k));
			for (int i = sb.length() - 2; i >= 0; --i) {
				sb.append(sb.charAt(i));
			}
			long v = Long.valueOf(sb.toString());
			v *= v;
			if (v > R) break;
			if (v >= L && isPalindrome(v)) {
				ans++;
			}
		}
		// count even length;
		for (int k = 1; k < MAGIC; ++k) {
			StringBuilder sb = new StringBuilder(Integer.toString(k));
			for (int i = sb.length() - 1; i >= 0; --i) {
				sb.append(sb.charAt(i));
			}
			long v = Long.valueOf(sb.toString());
			v *= v;
			if (v > R)
				break;
			if (v >= L && isPalindrome(v))
				ans++;
		}
		return ans;
	}

	public boolean isPalindrome(long x) {
		return x == reverse(x);
	}

	public long reverse(long x) {
		long ans = 0;
		while (x > 0) {
			ans = 10 * ans + x % 10;
			x /= 10;
		}
		return ans;
	}
}
