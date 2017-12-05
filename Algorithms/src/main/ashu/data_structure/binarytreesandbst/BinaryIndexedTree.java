package main.ashu.data_structure.binarytreesandbst;

//Reference - https://www.youtube.com/watch?v=v_wj_mOAlig
//useful for getting prefix sum while updating elements in the array(logn)
//The only trick is :
//1. is u update the array u will have to update bit for max logn indexes
//2. if u find sum, u will have to get it from max logn indexes
//to get next index, last set bit is flipped and added/subtracted from current index
//2's complement of x has every bit flipped except the last set bit and is -x
//(x & -x) gives last set bit in x

public class BinaryIndexedTree {
   public int bit [];
   
   public BinaryIndexedTree(int arr []) {
	  bit = new int[arr.length+1];
	  initialize(arr);
   }
   private void initialize(int [] arr) {
	   for(int i=0; i<arr.length; i++) {
		   //runs max logn time for each index value
		   add(i, arr[i]);
	   }
   }
   
   //add val at index and all ancestors that include this index as their subset
   //this is upward - right movement
   public void add(int index, int val) {
	   index++;
	   while(index < bit.length) {
		   bit[index] += val;
		 //next index = current index + last set bit of current index
		   index = index + (index & -index); 
	   }
   }
   
   //return array sum till index
   //to get the previous ranges, subtract last set bit form its bin representation
   //this is upward-left movement
   public int getSum(int index) {
	   if(index <0 || index >=bit.length) {
		   System.out.println("Invalid index for getSum()");
		   return -1;
	   }
	   index++;
	   int res = 0;
	   while(index >=1) {
		   res += bit[index];
		   index = index - (index & -index); //subtract the last set bit
	   }
	   return res;
   }
   
   public static void main(String args []) {
	   int arr [] = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
	   BinaryIndexedTree bit = new BinaryIndexedTree(arr);
	   int sum = bit.getSum(5);	   
	   System.out.println("Sum of elements in arr[0..5] is "+sum);
	   arr[3] += 6;
	   bit.add(3,6);
	   sum = bit.getSum(5);
	   System.out.println("After adding 6 to index 5, sum in arr[0..5] is "+ sum);
   }
   
}
