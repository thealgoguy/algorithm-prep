package geeksforgeeks;

public class LongestBitonicSubsequence {
  public static void main(String args []) {
	  int arr[] = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5,13, 3, 11, 7, 15};
	  int n = arr.length;
	  int lis [] = new int[n];
	  int lds [] = new int[n];
	  for(int i=0; i<n; i++) {
		  lis[i] = lds[i] = 1;
	  }
	  for(int i=1; i<n; i++) {
		  for(int j=0; j<i; j++) {
			  lis[i] = (arr[j] < arr[i]) ? Math.max(lis[i], lis[j]+1) : lis[i];			  
		  }
	  }
	  for(int i=n-2; i>=0; i--) {
		  for(int j=i+1; j<n; j++) {
			  lds[i] = (arr[j] < arr[i]) ? Math.max(lds[i], lds[j]+1) : lds[i];
		  }
	  }
	  int lbs = 0;
	  for(int i=0; i<n; i++) {
		  lbs = Math.max(lbs, lis[i]+lds[i]-1);
	  }
	  System.out.println("Length of LBS is "+lbs);
  }
}
