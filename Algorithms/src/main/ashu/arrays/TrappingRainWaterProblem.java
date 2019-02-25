package main.ashu.arrays;

//Given n non-negative integers representing an elevation map where the width of each bar is 1, 
//compute how much water it is able to trap after raining.
//It can be solve by scanning from both sides and then get the total.
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
	        if(height[i]<max){
	            left[i]=max;
	        }else{
	            left[i]=height[i];
	            max = height[i];
	        }
	    }	 
	    //scan from right to left
	    max = height[height.length-1];
	    right[height.length-1]=height[height.length-1];
	    for(int i=height.length-2; i>=0; i--){
	        if(height[i]<max){
	            right[i]=max;
	        }else{
	            right[i]=height[i];
	            max = height[i];
	        }
	    }	 
	    //add water deposited on top of each bar
	    for(int i=0; i<height.length; i++){
	        result+= Math.min(left[i],right[i])-height[i];
	    } 
	    return result;
	}
}
