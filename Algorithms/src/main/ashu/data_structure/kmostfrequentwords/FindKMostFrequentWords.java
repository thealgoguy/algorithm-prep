package main.ashu.data_structure.kmostfrequentwords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/

//Approach : Use tries to store words and their frequency. Use minheap to store k most frequent words in the file
//Since for every incoming word, we may need to updateKey on minheap, we need to store every key index for a word in the trie leaf node.
//Since during heap operations(add,remove,update) keys change their positions, so to update their indexes for the word in the tries, 
//we need to keep the reference of trie leaf node for the word in heap.
//For words not in the heap, trie leaf node will have -1 as index

public class FindKMostFrequentWords {
	private int K;
	private Trie trie;
	private MinHeapOfWords minHeap;

	private static final String FILENAME = "E:\\Ashu.txt";

	public FindKMostFrequentWords(int k){
		this.K = k;
		this.trie = new Trie();
		this.minHeap = new MinHeapOfWords(k);
	}
    
	public void processFile() {
		FileReader fr;
		BufferedReader br;
		String currentLine;
		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			while((currentLine=br.readLine()) != null) {
				String str [] = currentLine.split(" ");
				for(String s : str) {
					TrieNode leafNode = trie.insert(s);	
					Word word = new Word(s.trim(), leafNode.getFrequency(), leafNode);
					//if heap not full, insert/update the word
					if(!minHeap.isFull()) {
						//if key not present in heap, do insertKey operation
						if(leafNode.getMinHeapIndex()==-1) {
							int index = minHeap.insertKey(word);
							leafNode.setMinHeapIndex(index);
						}
						else {
							//if key already present in heap, do updateKey operation
							int index = minHeap.updateKey(leafNode.getMinHeapIndex(), leafNode.getFrequency());
							leafNode.setMinHeapIndex(index);
						}
					}
					else {  //heap full...compare with root of heap
						//check if word already there in heap
						if(leafNode.getMinHeapIndex() >= 0) {
							int index = minHeap.updateKey(leafNode.getMinHeapIndex(), leafNode.getFrequency());
							leafNode.setMinHeapIndex(index);
						}
						else { //do extractMin operation and then insertKey for new word
							Word currentMin = minHeap.getMinKey();
							if(currentMin.getFrequency() < leafNode.getFrequency()) {
								minHeap.extractMin();
								int index = minHeap.insertKey(word);
								leafNode.setMinHeapIndex(index);
							}
						}
						
					}
				}	
			}
			br.close();
			fr.close();
			//now dsiplay the words from the heap
			this.minHeap.displayKeys();
			
		}catch(IOException e) {
			System.out.println("Exception in reading the file"+e.getMessage());
		}
	}
	
	
	public static void main(String args []) {
		FindKMostFrequentWords kmw = new FindKMostFrequentWords(5);
		System.out.println(kmw.K+" most frequent words in the file are : \n");
		kmw.processFile();
	}
}
