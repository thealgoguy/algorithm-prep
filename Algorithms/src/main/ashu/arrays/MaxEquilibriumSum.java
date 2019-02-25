package main.ashu.arrays;

public class MaxEquilibriumSum {
	public static void main(String args[]) {
		int a[] = { -2, 5, 3, 1, 2, 6, -4, 2 };
		int n = a.length;
		int prefixsum[] = new int[n];
		int suffixsum[] = new int[n];
		// we can avoid calculating the extra arrays  by calculating total sum
		// and then calculating prefix-suffix sums on the fly in one pass
		prefixsum[0] = a[0];
		for (int i = 1; i < n; i++) {
			prefixsum[i] = prefixsum[i - 1] + a[i];
		}
		suffixsum[n - 1] = a[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			suffixsum[i] = suffixsum[i + 1] + a[i];
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (prefixsum[i] == suffixsum[i]) {
				max = Math.max(prefixsum[i], max);
			}
		}
		System.out.println("Max equilibrium sum = " + max);
	}
}
