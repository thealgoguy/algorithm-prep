package main.ashu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

//here window techniqe won't work
//use hash map with prefix sum...change 0 to -1
public class AshuPractice extends Thread{
	private static class A {
		int a = 5;
	}

	public static void main(String args[]) {
		A a = new A();
		System.out.println(" a = "+a.a);
		add(a);
		System.out.println(" a = "+a.a);
	}
	
	private static void add(A a) {
		a.a++;		
	}
	
}
