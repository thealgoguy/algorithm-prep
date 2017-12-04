package kmostfrequentwords;

public class Word implements Comparable<Word>{
    private String word;
    private int frequency;
    TrieNode leafNode;  //leaf node of trie where word ends
    
    public Word(String word, int freq, TrieNode leaf) {
    	this.word = word;
    	this.frequency = freq;
    	this.leafNode = leaf;
    }

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public TrieNode getLeafNode() {
		return leafNode;
	}

	public void setLeafNode(TrieNode leafNode) {
		this.leafNode = leafNode;
	}

	@Override
	public int compareTo(Word o) {
		return Integer.compare(this.frequency, o.frequency);
	}

	@Override
	public String toString() {
		return word+" : frequency = "+frequency;
	}
    
	
}
