package main.ashu.data_structure.tries.autocomplete.suggesttree;

public class STNode {
	/**
     * A list of autocomplete suggestions, ordered from highest weight to lowest
     * weight.
     */ 
    public STEntry[] list;
    public STEntry entry;
    public char firstChar;
    public short charEnd;
    public int priority;
    public STNode left, mid, right;
    public STNode up; // parent in the ternary search tree
    
    public STNode(String term, int weight, int charStart, STNode up) {
        entry = new STEntry(term, weight);
        firstChar = term.charAt(charStart);
        charEnd = (short) term.length();
        left = mid = right = null;
        this.up = up;
    }
    
    public STNode(STNode n, int charEnd) {
        entry = null;
        firstChar = n.firstChar;
        this.charEnd = (short) charEnd;
        priority = n.priority;
        left = n.left;
        mid = n;
        right = n.right;
        up = n.up;
    }
    
    /**
     * Returns the suggestion at the specified index in the list. The first
     * suggestion is at index 0, the second at index 1, and so on.
     */
    public STEntry getSuggestion(int index) {
        return list[index];
    }

    /**
     * Returns the number of suggestions in the list.
     */
    public int listLength() {
        return list.length;
    }
    
    public char charAt(int index) {
        if(entry != null)
            return entry.getTerm().charAt(index);
        else
            return list[0].getTerm().charAt(index);
    }
    
    public int listIndexOf(STEntry e) {
        for(int i = 0; i < list.length; i++) {
            if(list[i] == e)
                return i;
        }
        return -1;
    }

	public STEntry[] getList() {
		return list;
	}

	public void setList(STEntry[] list) {
		this.list = list;
	}

	public STEntry getEntry() {
		return entry;
	}

	public void setEntry(STEntry entry) {
		this.entry = entry;
	}

	public char getFirstChar() {
		return firstChar;
	}

	public void setFirstChar(char firstChar) {
		this.firstChar = firstChar;
	}

	public short getCharEnd() {
		return charEnd;
	}

	public void setCharEnd(short charEnd) {
		this.charEnd = charEnd;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public STNode getLeft() {
		return left;
	}

	public void setLeft(STNode left) {
		this.left = left;
	}

	public STNode getMid() {
		return mid;
	}

	public void setMid(STNode mid) {
		this.mid = mid;
	}

	public STNode getRight() {
		return right;
	}

	public void setRight(STNode right) {
		this.right = right;
	}

	public STNode getUp() {
		return up;
	}

	public void setUp(STNode up) {
		this.up = up;
	}

}
