package main.ashu.spellcheck.bloomfilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

//http://www.javamex.com/tutorials/collections/bloom_filter_java.shtml
//The optimal number of hash functions used is determined according to the ration between 
//expected elements (n) and bloom filter size (m). 
//k = m/n * ln(2)

public class BloomFIlter1 {
	private static final int MAX_HASHES = 8;
	private static final long[] byteTable;
	private static final long HSTART = 0xBB40E64DA205B064L;
	private static final long HMULT = 7664345821815920749L;

	private final BitSet data;
	private final int noHashes;
	private final int hashMask;

	static {
		byteTable = new long[256 * MAX_HASHES];
		long h = 0x544B2FBACAAF1684L;
		for (int i = 0; i < byteTable.length; i++) {
			for (int j = 0; j < 31; j++)
				h = (h >>> 7) ^ h; h = (h << 11) ^ h; h = (h >>> 10) ^ h;
				byteTable[i] = h;
		}
	}

	private long hashCode(String s, int hcNo) {
		long h = HSTART;
		final long hmult = HMULT;
		final long[] ht = byteTable;
		int startIx = 256 * hcNo;
		for (int len = s.length(), i = 0; i < len; i++) {
			char ch = s.charAt(i);
			h = (h * hmult) ^ ht[startIx + (ch & 0xff)];
			h = (h * hmult) ^ ht[startIx + ((ch >>> 8) & 0xff)];
		}
		return h;
	}

	public BloomFIlter1(int log2noBits, int noHashes) {
		if (log2noBits < 1 || log2noBits > 31)
			throw new IllegalArgumentException("Invalid number of bits");
		if (noHashes < 1 || noHashes > MAX_HASHES)
			throw new IllegalArgumentException("Invalid number of hashes");

		this.data = new BitSet(1 << log2noBits);
		this.noHashes = noHashes;
		this.hashMask = (1 << log2noBits) - 1;
	}

	public BloomFIlter1(int noItems, int bitsPerItem, int noHashes) {
		int bitsRequired = noItems * bitsPerItem;
		if (bitsRequired >= Integer.MAX_VALUE) {
			throw new IllegalArgumentException("Bloom filter would be too big");
		}
		int logBits = 4;
		while ((1 << logBits) < bitsRequired)
			logBits++;
		if (noHashes < 1 || noHashes > MAX_HASHES)
			throw new IllegalArgumentException("Invalid number of hashes");
		this.data = new BitSet(1 << logBits);
		this.noHashes = noHashes;
		this.hashMask = (1 << logBits) - 1;
	}

	public void add(String s) {
		for (int n = 0; n < noHashes; n++) {
			long hc = hashCode(s, n);
			int bitNo = (int) (hc) & this.hashMask;
			data.set(bitNo);
		}
	}

	public boolean contains(String s) {
		for (int n = 0; n < noHashes; n++) {
			long hc = hashCode(s, n);
			int bitNo = (int) (hc) & this.hashMask;
			if (!data.get(bitNo)) return false;
		}
		return true;
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
