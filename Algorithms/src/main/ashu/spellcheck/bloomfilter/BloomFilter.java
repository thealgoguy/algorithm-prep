package main.ashu.spellcheck.bloomfilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//http://www.sanfoundry.com/java-program-implement-bloom-filter/

public class BloomFilter {
	private byte[] set;
    private int keySize, setSize, size;
    private MessageDigest md;
 
    public BloomFilter(int capacity, int k)
    {
        setSize = capacity;
        set = new byte[setSize];
        keySize = k;
        size = 0;
        try 
        {
            md = MessageDigest.getInstance("MD5");
        } 
        catch (NoSuchAlgorithmException e) 
        {
            throw new IllegalArgumentException("Error : MD5 Hash not found");
        }
    }
 
    public int getSize()
    {
        return size;
    }
 
    /* Method to get hash - MD5 */
    private int getHash(int i)
    {
        md.reset();
        byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();
        md.update(bytes, 0, bytes.length);
        return Math.abs(new BigInteger(1, md.digest()).intValue()) % (set.length - 1);
    }
	
    /* Method to add an object */
    public void add(Object obj)
    {
        int[] tmpset = getHashArray(obj);
        for (int i : tmpset)
            set[i] = 1;
        size++;
    }
    
	/* Method to check is an object is present */
    public boolean contains(Object obj) 
    {
        int[] tmpset = getHashArray(obj);
        for (int i : tmpset){
        	
            if (set[i] != 1)
                return false;
        }
        return true;
    }
    
	/* Method to get set array for an object */
    private int[] getHashArray(Object obj)
    {
        int[] tmpset = new int[keySize];
        tmpset[0] = getHash(obj.hashCode());
        for (int i = 1; i < keySize; i++){
            tmpset[i] = (getHash(tmpset[i - 1]));
        }
        return tmpset;
    }  
    
    public void loadData(String filePath) throws IOException {
    	BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
			MessageDigest m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        String line = br.readLine();
        while (line != null) {
        	String words [] = line.split("\\s+");
        	for(String word : words) {
        		add(word);
        	}
            line = br.readLine();
        }
        br.close();
    }
}
