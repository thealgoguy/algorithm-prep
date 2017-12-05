package main.ashu.arrays;

import java.util.*;

public class ThreeSum {
    Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
    public List<List<Integer>> threeSum(int[] a) {
        int n = a.length;
        List<List<Integer>> list = new ArrayList();
        for(int i=0; i<n; i++){
        	int j=i+1;
            while(j<n){
            	List<Integer> l = sum2(a, j++, n-1, -a[i]);
            	if(l.size()<2) break;
                l.add(a[i]);              
                if(isUnique(l.get(0), l.get(1), a[i])) {
                	list.add(l);
                	hm.put(l.get(0), l.get(0));
                    hm.put(l.get(1), l.get(1));
                    hm.put(l.get(2), l.get(2));   
                }
            } 
        }
        return list;
    }
    public boolean isUnique(int a, int b, int c){
    	return (hm.containsKey(a) && hm.containsKey(b) && hm.containsKey(c)) ? false : true;
    }
    
    public List<Integer> sum2(int [] a, int s, int end, int sum){
        List<Integer> list = new ArrayList();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=s; i<=end; i++) {
            map.put(a[i], i);
        }
        for(int i=s; i<=end; i++) {
            if(map.containsKey(sum-a[i])) {
                if(i < map.get(sum-a[i])) {
                    list.add(a[i]);
                    list.add(sum-a[i]);
                    break;
                }
            }
        }
        return list;
    }
    
    public static void main(String args []) {
    	ThreeSum tsum = new ThreeSum();
    	int a [] = {-1,0,1,2,-1,-4};
    	System.out.println(tsum.threeSum(a));
    }

}
