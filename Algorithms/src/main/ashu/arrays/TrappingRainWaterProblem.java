package main.ashu.arrays;

//Given n non-negative integers representing an elevation map where the width of each bar is 1, 
//compute how much water it is able to trap after raining.
//It can be solve by scanning from both sides and then get the total.
//accepted - https://leetcode.com/problems/trapping-rain-water/submissions/
//2D version - https://leetcode.com/problems/trapping-rain-water-ii/
//other version - https://leetcode.com/problems/container-with-most-water/
public class TrappingRainWaterProblem {
	public int trap(int[] height) {
	    int result = 0;
	    if(height==null || height.length<=2)
	        return result;
	 
	    int left[] = new int[height.length];
	    int right[]= new int[height.length];
	 
	    //scan from left to right
	    int max = height[0];
	    left[0] = height[0];
	    for(int i=1; i<height.length; i++){
	    	max = Math.max(max, left[i]);
	    	left[i] = max;
	    }	 
	    //scan from right to left
	    max = height[height.length-1];
	    right[height.length-1]=height[height.length-1];
	    for(int i=height.length-2; i>=0; i--){
	    	max = Math.max(max, right[i]);
	    	right[i] = max;
	    }	 
	    //add water deposited on top of each bar
	    for(int i=0; i<height.length; i++){
	        result+= Math.min(left[i],right[i])-height[i];
	    } 
	    return result;
	}
}
