package main.ashu.data_structure.tries.autocomplete.usingternarysearchtree;

public class TSTNode {
	 char character;
	 TSTNode left, middle, right;
	 boolean isEndOfWord;
	
	public TSTNode(char ch,	boolean isEndOfWord) {
		this.character = ch;
		this.left = null;
		this.middle = null;
		this.right = null;
		this.isEndOfWord = isEndOfWord;
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char ch) {
		this.character = ch;
	}

	public TSTNode getLeft() {
		return left;
	}

	public void setLeft(TSTNode left) {
		this.left = left;
	}

	public TSTNode getMidddle() {
		return middle;
	}

	public void setMidddle(TSTNode midddle) {
		this.middle = midddle;
	}

	public TSTNode getRight() {
		return right;
	}

	public void setRight(TSTNode right) {
		this.right = right;
	}

	public boolean isEndOfWord() {
		return isEndOfWord;
	}

	public void setEndOfWord(boolean isEndOfWord) {
		this.isEndOfWord = isEndOfWord;
	}
}
