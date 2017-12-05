package main.ashu.greedy;

import java.util.Arrays;

//Given arrival and exit times of various guests in a party, determine the time at which maximum number of guests were present.
//Approach 1 : sorting + merging. Approach 2: Using priority queue.
//This is b/c we wanna find the minimum chairs that we must arrange so no one has to wait
public class MaxGuestsInParty {	
	public static void main(String args []) {
		int arrl[] = {1, 2, 10, 5, 5};
        int exit[] = {4, 5, 12, 9, 12};
        Arrays.sort(arrl);
        Arrays.sort(exit);
        int count=0, max = 0, time=0;
        int i=0, j=0;
        //similar to merging two sorted arrays
        while(i<arrl.length && j<arrl.length) {
        	if(arrl[i] <= exit[j]) {
        		count++;
        		if(max < count) {
        			max = count;
        			time = arrl[i];
        		}
        		i++;
        	}
        	else {
        		count--;
        		j++;
        	}
        }
        System.out.println(max+" people were present at time = "+time);
	}  

}
