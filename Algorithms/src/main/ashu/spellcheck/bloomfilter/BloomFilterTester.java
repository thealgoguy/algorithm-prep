package main.ashu.spellcheck.bloomfilter;

import java.io.IOException;
import java.util.Scanner;

public class BloomFilterTester {
	public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bloom Filter Test\n");   
 
        //Set capacity and key size. See the formula for optimal capacity and keysize
        BloomFilter bf = new BloomFilter(5 , 2);
       // BloomFIlter1 bf = new BloomFIlter1(10, 5, 2);
        try {
			//bf.loadData("C:\\Workspace\\Algorithm\\src\\spellcheck\\bloomfilter\\bloominput.txt");
        	bf.loadData("C:\\Workspace\\Algorithm\\src\\spellcheck\\big.txt");
		} catch (IOException e){
			e.printStackTrace();
		}                                           
		System.out.println("Enter key to search");
		System.out.println("Search result : "+ bf.contains( scan.next()));
		//System.out.println("\nSize = "+ bf.getSize() );
    }
}
