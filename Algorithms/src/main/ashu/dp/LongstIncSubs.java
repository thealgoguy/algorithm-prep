import java.util.*;


public class LongstIncSubs {
	public static void main(String args []) {
		Scanner sc  = new Scanner(System.in);
		String s = sc.nextLine();
		String sp [] = s.split(" ");
		int n = sp.length;
		int a [] = new int[n];
		int dp [] = new int[n];
		LinkedList<Integer> list [] = new LinkedList[n];
		for(int i=0; i<n; i++) {
			a[i] = Integer.parseInt(sp[i]); dp[i] = 1;
			list[i] = new LinkedList(); list[i].add(a[i]);
		}
		//dp[i] = length of longest inc subse ending at i, smallest such subse = a[i] whose length is 1.;
		for(int i=1; i<n; i++) {
			int max = -1, idx = -1; //finding best subseq for extension of curr subs
			for(int j=i-1; j>=0; j--) {
				if(a[i] > a[j] && max < dp[j]) {
					max = dp[j]; idx = j;
				} 
			}
			if(max >0) { 
				dp[i] = max + dp[i];  //extend dp[i]
				LinkedList lst = new LinkedList();lst.addAll(list[idx]);
				 lst.addAll(list[i]); list[i] = lst;
			}
		}
		//now find the global max....could have been done in the prev loop itself.
		int idx=0, max = dp[0];
		for(int i=1; i<n; i++){  
			if(dp[i] > max) {
				max = dp[i]; idx = i; 
			} 
		}
		System.out.print("Longest increasing subsequence = ");
		for(int i=0; i<list[idx].size(); i++) System.out.print(list[idx].get(i)+" ");
		System.out.println("length = "+max);
		//printing the LIS using backward lookup
		int l=max-1; String lis = a[idx]+" ";
		System.out.print("LIS using backward lookup = ");
		for(int i=idx-1; i>=0; i--) {
			if(dp[i]==l) { lis = a[i]+" "+lis; l--;}
		}
		System.out.println(lis);
		//Ques - how to print multiple subsequences. http://www.edufyme.com/code/?id=66f041e16a60928b05a7e228a89c3799
}
}
