package main.ashu.arrays;

//generate all subsets of a set S
//Follow up - Generate subsets of size k

public class PowerSet {
    public static void main(String args []) {
    	char set[] = {'a','b','c'};
    	int n = set.length;  	
    	int max = 1<<n;   //size of power set = pow(2,n)
    	//in binary representations form zero to max, if ith bit in count is set include the ith array element in the subset else ignore
    	for(int count =0; count<max; count++) {
    		for(int i=0; i<set.length; i++) {
    			if((count &(1<<i)) >0) {
    				System.out.print(set[i]);
    			}
    			
    		}
    		System.out.println();
    	}
    }
}
