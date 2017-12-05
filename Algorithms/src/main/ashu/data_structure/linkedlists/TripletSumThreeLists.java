package main.ashu.data_structure.linkedlists;

import java.util.Collections;
import java.util.LinkedList;

//Given three linked lists, print triplets with sum = k. Its LL version of 3-sum problem
//Sort 2 lists  and for every element of the third, using two finger approach look for pair with sum-x
//Hashing can also be used to reduce time complexity but will be costly if lists are large
//Also practise /sort-linked-list-0s-1s-2s-changing-links/  using three dummy nodes(to avoid null checks)
public class TripletSumThreeLists {
	
     public static void solve(LinkedList<Integer> l1, LinkedList<Integer> l2, LinkedList<Integer> l3, int k) {
    	 Collections.sort(l1);
    	 Collections.sort(l2, Collections.reverseOrder());
    	 /*Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	 for(Integer i : l3) {
    		 map.put(k-i, i);
    	 }*/
    	 
    	 for(Integer x : l3) {
    		 int i=0, j=0;
    		 while(i<l1.size() && j<l2.size()) {
    	    	   int val =  l1.get(i) + l2.get(j) +x;
    	    	   if(val == k) {
    	    		   System.out.println("Triplet with sum "+k+" is : "+l1.get(i)+", "+l2.get(j)+", "+x);
    	    		   i++; j++;
    	    		   //return;
    	    	   }
    	    	   else {
    	    		   if(val < k) i++;
    	    		   else j++;
    	    	   }
        	 }
    	 }
    	 
    	 
     }
     
     public static void main(String args []) {
    	 
    	 LinkedList<Integer> list1 = new LinkedList<Integer>();
    	 LinkedList<Integer> list2 = new LinkedList<Integer>();
    	 LinkedList<Integer> list3 = new LinkedList<Integer>();
         
    	 list1.add(20); list1.add(5); list1.add(15); list1.add(100);
    	 list2.add(10); list2.add(9); list2.add(4); list2.add(2);
    	 list3.add(1); list3.add(2); list3.add(4); list3.add(8);
    	 
    	 solve(list1, list2, list3, 25);  	 
     }
}
