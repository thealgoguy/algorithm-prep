import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Given a string, find the longest substring without repeated characters.

public class LongestSubstrUniqueChars {
	public int lengthOfLongestSubstring(String s) {
        if(s==null) return 0;
        char ch [] = s.toCharArray();
        int n = ch.length;
        if(n==0) return 0;
        //count array of ASCII character set as hashset
        int p [] = new int[256];
        Arrays.fill(p, 0);
        //initialize the window
        int start=0, end = 0;
        int max = 0;
       // if the window can be extended by taking the next char without violating the uniqueness constraint include it 
        //else try to shrink the current window from start till constraint is met or the window collapses
        
        while(end < n) {
            if(p[ch[end]]==0) {
                p[ch[end]]++;
                end++;
            }
            else {
                while(start <= end && p[ch[end]]>0) {
                    p[ch[start]]--;
                    start++;
                    if(p[ch[end]]==0) break;
                }
            }
            max = Math.max(max, end-start);
        }
        //System.out.println(s.substring(start, end+1));
        return max;
    
	}
	
	public int lengthOfLongestSubstring2(String s) {
	    int i = 0, j = 0, max = 0;
	    Set<Character> set = new HashSet<>();	    
	    while (j < s.length()) {
	        if (!set.contains(s.charAt(j))) {
	            set.add(s.charAt(j++));
	            max = Math.max(max, set.size());
	        } else {
	            set.remove(s.charAt(i++));
	        }
	    }
	    
	    return max;
	}
	
	public static void main(String arg []) {
		LongestSubstrUniqueChars sol = new LongestSubstrUniqueChars();
		String s = "pwwkew";
		int len = sol.lengthOfLongestSubstring(s);
		System.out.println("Length of the longest substring without repeated chars = "+len);
		len = sol.lengthOfLongestSubstring2(s);
		System.out.println("Length of the longest substring without repeated chars = "+len);
	}
	
}