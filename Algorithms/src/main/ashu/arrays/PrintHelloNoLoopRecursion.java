package main.ashu.arrays;
 //print 100 times
public class PrintHelloNoLoopRecursion {
   public static void main(String args []) {
	   String s = "Hello ";
	   s += s+s+s+s; //n=5
	   s+= s+s+s+s;  //n =25
	   s+= s+s+s;    //n = 25*4 = 100
	   System.out.println(s);
   }
}
