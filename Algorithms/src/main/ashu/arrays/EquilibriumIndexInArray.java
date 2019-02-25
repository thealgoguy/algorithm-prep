package main.ashu.arrays;

public class EquilibriumIndexInArray {
   public static void main(String args []) {
	   int a[] = {-7, 1, 5, 2, -4, 3, 6};
	   int n = a.length;
	   int sum = 0;
	   for(int i=0; i<n; i++) sum += a[i];
	   int prefixsum = a[0];
	   int index = -1;
	   for(int i=1; i<n; i++) {
           prefixsum += a[i]; 
           if(prefixsum-a[i] == sum-prefixsum) {
        	   index = i;
           }
	   }
	   if(index >0 && index<n-1) {
		   System.out.println("Equilibrium index = "+index);
	   }
	   else System.out.println("Equilibrium index doesn't exist");
   }
}
