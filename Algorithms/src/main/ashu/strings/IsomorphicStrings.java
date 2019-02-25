package main.ashu.strings;

import java.util.HashMap;
import java.util.Map;

//Given two strings s and t, determine if they are isomorphic.
//Two strings are isomorphic if the characters in s can be replaced to get t.
//"egg" and "add" are isomorphic, "foo" and "bar" are not.
//https://www.programcreek.com/2014/05/leetcode-isomorphic-strings-java/
public class IsomorphicStrings {
	
	public static boolean isomorphic(String s1, String s2) {
		if(s1 == null || s2 == null) return false;
		int l1 = s1.length();
		int l2 = s2.length();
		if(l1 != l2) return false;
		Map<Character, Character> map1 = new HashMap<Character, Character>();
		Map<Character, Boolean> map2 = new HashMap<Character, Boolean>();
		for(int i=0; i<l1; i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			if(map1.containsKey(c1)) {
				if(map1.get(c1) != c2) return false;
			} else{
				if(map2.containsKey(c2)) return false;
				map1.put(c1, c2);
				map2.put(c2, Boolean.TRUE);
			}
		}
		return true;
	}
    public static void main(String args []) {
    	String s1 = "egg";
    	String s2 = "add";
    	boolean ans = isomorphic(s1, s2);
    	if(ans) {
    		System.out.println("Both string are isomorphic");
    	} else {
    		System.out.println("Not isomorphic");
    	}
    }
}
