package main.ashu.anagrams;

import java.util.Arrays;
import java.util.Scanner;

public class IsAnagram {
public static void main(String args[]) {
	Scanner sc = new Scanner(System.in);
	int T = sc.nextInt();
	while(sc.hasNext()) {
		String txt = sc.next();
		if(valid(txt.split("\\s+"))) System.out.println("YES");
		else System.out.println("NO");
	}
}

public static boolean valid(String[] arr) {
	String s1 = arr[0], s2 = arr[1];
char [] c1 = s1.toCharArray();
char [] c2 = s2.toCharArray();
Arrays.sort(c1); Arrays.sort(c2);
if(c1.equals(c2)) return true;
return false;
}
}
