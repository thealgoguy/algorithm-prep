package main.ashu.arrays;

import java.util.Arrays;

//extra positive or negative should go to the end
public class RearrangeAlternatePositiveNegative {
   public static void main(String args []) {
	   int a[] = {2, 3, -4, -1, 6, -9};  
	   int n = a.length;
	   System.out.println("original : "+Arrays.toString(a));
	   preservingOrder(a);
	   System.out.println("rearranged preserving order: "+Arrays.toString(a));
	   int b [] = {2, 3, -4, -1, 6, -9};
	   System.out.println("original : "+Arrays.toString(b));
	   withoutPreservingOrder(b);
	   System.out.println("rearranged without order preserved: "+Arrays.toString(b));
   }
   
   private static void withoutPreservingOrder(int a[]){
	   int n = a.length;
	   int i=0, j = a.length - 1;
	   //select zero as pivot of quick sort and do partitioning
	   while(i < j){
		   while(a[i] > 0) i++;;
		   while(a[j] < 0) j--;
		   if(i < j) swap(a, i, j);  //important
	   }
	   System.out.println("after pivoting : "+Arrays.toString(a));
	   int k = 1;
	   while(k<n && i<n){
		   swap(a, k, i);
		   k += 2;
		   i++;
	   }
   }
   
   private static void preservingOrder(int arr[]){
	   int outofplace = -1; 
	   int n = arr.length;
       for (int index = 0; index < n; index++)  
       { 
           if (outofplace >= 0)  
           { 
               // find the item which must be moved into the out-of-place 
               // entry if out-of-place entry is positive and current 
               // entry is negative OR if out-of-place entry is negative 
               // and current entry is negative then right rotate 
               // 
               // [...-3, -4, -5, 6...] -->   [...6, -3, -4, -5...] 
               //      ^                          ^ 
               //      |                          | 
               //     outofplace      -->      outofplace 
               // 
               if (((arr[index] >= 0) && (arr[outofplace] < 0)) 
                       || ((arr[index] < 0) && (arr[outofplace] >= 0)))  
               { 
                   rightrotate(arr, n, outofplace, index); 
 
                   // the new out-of-place entry is now 2 steps ahead 
                   if (index - outofplace > 2)  
                       outofplace = outofplace + 2; 
                   else
                       outofplace = -1; 
               } 
           } 
 
           // if no entry has been flagged out-of-place 
           if (outofplace == -1)  
           { 
               // check if current entry is out-of-place 
               if (((arr[index] >= 0) && ((index & 0x01)==0)) 
                       || ((arr[index] < 0) && (index & 0x01)==1)) 
                   outofplace = index; 
           } 
       } 
   }
   
   private static void rightrotate(int arr[], int n, int outofplace, int cur)  
   { 
       int tmp = arr[cur]; 
       for (int i = cur; i > outofplace; i--) 
           arr[i] = arr[i - 1]; 
       arr[outofplace] = tmp; 
   }
   
   private static void swap(int a[], int i, int j){
	   int t = a[i];
	   a[i] = a[j];
	   a[j] = t;
   }
}
