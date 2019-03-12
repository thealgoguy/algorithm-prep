package main.ashu.strings;

public class PermutationsOfString {
	public static void main(String args[]) {
		String str = "hello";
		int n = str.length();
		permute(str.toCharArray(), 0);
	}

	public static void permutation(String str) {
		permutation("", str);
	}

	private static void permutation(String prefix, String str) {
		int n = str.length();
		if (n == 0)
			System.out.println(prefix);
		else {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i),
						str.substring(0, i) + str.substring(i + 1, n));
		}
	}

	private static void permute(char c[], int l) {
		if (l == c.length - 1) {
			System.out.println(new String(c));
		} else {
			for (int i = l; i < c.length; i++) {
				swap(c, l, i);
				permute(c, l + 1);
				swap(c, l, i);
			}
		}
	}

	private static void swap(char c[], int l, int r) {
		while (l < r) {
			char x = c[l];
			c[l] = c[r];
			c[r] = x;
			l++;
			r--;
		}
	}
}
