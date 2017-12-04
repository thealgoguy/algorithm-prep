package arrays;


public class Print1To100 {
	static int i=1;
	public static void main(String[] args) {	
		a(); a();a();a();a();  //n=5
		/*String set = new java.util.BitSet() {{ set(1, 100+1); }}.toString();
		 System.out.append(set, 1, set.length()-1);*/
	}	
	public static void b(){
		System.out.print(i+++" ");  //invoked 1oo times
	}
	public static void c(){
		b();b();b();b();b(); //n=20*5=100
	}
	public static void a(){
		c();c();c();c();  //n= 5*4=20
	}
}
