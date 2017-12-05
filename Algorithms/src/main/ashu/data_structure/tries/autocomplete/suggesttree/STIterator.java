package main.ashu.data_structure.tries.autocomplete.suggesttree;

import java.util.NoSuchElementException;
/**
 * An iterator over the terms in the tree. The iterator returns the terms in
 * alphabetical order and allows the caller to remove returned terms from
 * the tree during the iteration.
 */
public class STIterator {
	
	private STNode root;
    private STNode next;
    
    public STIterator(STNode root) {
    	this.root = root;
        if(root == null)
            next = null;
        else
            next = firstEntry(root);
    }
    
    public boolean hasNext() {
        return (next != null);
    }
    
    /**
     * Returns the next term in the iteration.
     */
    public STEntry next() {
        if(next == null)
            throw new NoSuchElementException();
        STEntry e = next.getEntry();
        next = nextEntry(next);
        return e;
    }
    
    private STNode firstEntry(STNode n) {
        while(true) {
            while(n.getLeft() != null)
                n = n.getLeft();
            if(n.getEntry() == null)
                n = n.getMid();
            else
                return n;
        }
    }
    
    private STNode nextEntry(STNode n) {
        if(n.getMid() != null)
            return firstEntry(n.getMid());
        else if(n.getRight() != null)
            return firstEntry(n.getRight());
        else{
            while(n.getUp() != null) {
                if(n == n.getUp().getLeft()) {
                    if(n.getUp().getEntry() != null)
                        return n.getUp();
                    else
                        return firstEntry(n.getUp().getMid());
                }else if(n == n.getUp().getMid() && n.getUp().getRight() != null)
                    return firstEntry(n.getUp().getRight());
                else
                    n = n.getUp();
            }
            return null;
        }
    }

}
