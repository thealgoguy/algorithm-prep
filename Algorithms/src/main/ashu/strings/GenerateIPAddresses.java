package main.ashu.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Given a numeric string, generate all valid IP addresses
//Idea : place 3 dots in the string and check for valid IPs

public class GenerateIPAddresses {
	public static void main(String args[]) {
		String s = "25525511135";
		List<String> list = generate(s);
		System.out.println("All valid IP addresses are : ");
		for (String str : list) {
			System.out.println(str);
		}
	}

	private static List<String> generate(String s) {
		List<String> list = new ArrayList<String>();
		if (s == null)
			return list;
		// length validation
		if (s.length() < 3 || s.length() > 12)
			return list;
		int n = s.length();
		// place 3 dots at n-1 places....place one by one
		for (int i = 1; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				for (int k = j + 1; k < n; k++) {
					String IP = s.substring(0, i) + "." + s.substring(i, j)
							+ "." + s.substring(j, k) + "." + s.substring(k);
					if (isValidIP(IP))
						list.add(IP);
				}
			}
		}
		Collections.sort(list, new Comparator<String>() {
			public int compare(String o1, String o2) {
				String a1[] = o1.split("[.]");
				String a2[] = o2.split("[.]");

				int result = -1;
				for (int i = 0; i < 4 && result != 0; i++) {
					result = a1[i].compareTo(a2[i]);
				}
				return result;
			}
		});
		return list;
	}

	private static boolean isValidIP(String IP) {
		if (IP == null)
			return false;
		String str[] = IP.split("\\.");
		for (String s : str) {
			if (s.length() > 3 || Integer.parseInt(s) < 0
					|| Integer.parseInt(s) > 255)
				return false;
			if (s.length() > 1 && Integer.parseInt(s) == 0)
				return false;
			if (s.length() > 1 && Integer.parseInt(s) != 0
					&& s.charAt(0) == '0')
				return false;
		}
		return true;
	}
}
