import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class PrintAllAnangrams {
	//this implementation uses sorted char sequence as keys to the hashmap. HashSet used to avoid duplicate storage of same anagrams
	public static void printAllAnagrams(String [] words) {
    	Map<String,HashSet<String>> dict = new HashMap<String,HashSet<String>>();
    	for(String s : words) {
    		char [] c = s.toCharArray();
    		Arrays.sort(c);
    		String k = new String(c);
    		if(dict.containsKey(k)) {
    			dict.get(k).add(s);
    		} else {
    			HashSet<String> list = new HashSet<String>();
    			list.add(s);
    			dict.put(k, list);
    		}
    	}
        Set<String> keys = dict.keySet();   
        System.out.print("Anangrams using sorted keys are : ");
        for(String key : keys) {      	
        	Iterator it = dict.get(key).iterator();
        	System.out.print("[");
        	while(it.hasNext()) {
        		System.out.print(it.next()+", ");	
        	}
        	System.out.print("] ");
        }      
	}
	
	public static void printAllAnagramsUsingPrimmeHashing(String words []) {
		//assuming alphabet size to be 26 all small letters...take as much primes
		Map<String,HashSet<String>> dict = new HashMap<String,HashSet<String>>();
		int prime [] = generatePrimes(26);
		for(String s : words) {
    		String hash = getHash(s,prime);
    		if(dict.containsKey(hash)) {
    			dict.get(hash).add(s);
    		} else {
    			HashSet<String> list = new HashSet<String>();
    			list.add(s);
    			dict.put(hash, list);
    		}
    	}	
		Set<String> keys = dict.keySet(); 
		System.out.println();
		System.out.print("Anangrams using prime hashing are : ");
        for(String key : keys) {      	
        	Iterator it = dict.get(key).iterator();
        	System.out.print("[");
        	while(it.hasNext()) {
        		System.out.print(it.next()+", ");	
        	}
        	System.out.print("] ");
        } 
	}
	public static int [] generatePrimes(int alphabetSize) {
		int primes [] = new int[alphabetSize];
		primes[0]=2; primes[1]=3; primes[2]=5;
		int count =3;
		for(int i=7; count<alphabetSize; i++) {
			boolean flag = true;
			//check divisibility by primes only till square root
			for(int j=0; j<=Math.sqrt(i); j++) {
				if(i%primes[j]==0) {
					flag = false; 
					break;
				}
			}
			if(flag) {
				primes[count] = i; count ++;
				
			}
		}
		return primes;
	}
	
	//note that generating hash using prime factorization guarantees unique hashes for anagrams of a single word
	//but may lead to integer overflow if word length is large. To deal with overflow, we can use modulo(a large enough prime) to restrict 
	//the results of integer multiplication but again this may result in collision. SO in case of collison, check for anagram manually.
	public static String getHash(String str, int [] primes) {
		int mul = 1;
		for(int i=0; i<str.length(); i++) {
			int x = str.charAt(i)-'a';  //mapping index of the alphabet onto the prime set
			mul *= primes[x];
		}
		return Integer.toString(mul);
	}
	
    public static void main(String args []) {
    	 
    	String [] words = {"abc", "bca", "cab", "jui"};
    	printAllAnagrams(words);
    	printAllAnagramsUsingPrimmeHashing(words);
    }
}
