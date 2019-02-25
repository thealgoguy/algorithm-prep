package main.ashu.bitmanipulation;

//https://www.programcreek.com/2014/05/leetcode-add-binary-java/
public class AddBinaryStrings {   
	public static void main(String args []) {
		String s1 = "111";
		String s2 = "101001";
		String sum = addBinary(s1, s2);
		System.out.println("Binary sum = "+sum);
	}
	private static String addBinary(String a, String b) {
		StringBuilder sb = new StringBuilder();
		int i = a.length() - 1;
	    int j = b.length() - 1;

		int carry = 0;
		while (i >= 0 || j >= 0) {
			int sum = 0;

			if (i >= 0 && a.charAt(i) == '1') {
				sum++;
			}

			if (j >= 0 && b.charAt(j) == '1') {
				sum++;
			}

			sum += carry;

			if (sum >= 2) {
				carry = 1;
			} else {
				carry = 0;
			}

			sb.insert(0, (char) ((sum % 2) + '0'));

			i--;
			j--;
		}

		if (carry == 1)
			sb.insert(0, '1');

		return sb.toString();
	}
}
