package main.ashu.data_structure.tries.autocomplete;

import java.util.Arrays;
import java.util.Random;

import main.ashu.data_structure.tries.autocomplete.suggesttree.STEntry;
import main.ashu.data_structure.tries.autocomplete.suggesttree.STIterator;
import main.ashu.data_structure.tries.autocomplete.suggesttree.STNode;

/**
 * An autocomplete data structure that enables you to quickly look up the top k
 * terms with a given prefix in a set of weighted terms. The structure is based
 * on a compressed trie of the terms (implemented as a randomized ternary search
 * tree) in which each node holds a weight-ordered list of the k
 * highest-weighted terms in its subtree.
 * <p>Note that this implementation is not synchronized. If multiple threads
 * access a Suggest Tree concurrently, and at least one of the threads modifies
 * the tree, it must be synchronized externally.
 */
public class SuggestTree {
	private final Random random = new Random();
    private final int k;
    private STNode root;
    private int size;   
    /**
     * Creates a Suggest Tree with the specified k-value
     * @param k the maximum number of autocomplete suggestions to return for a*/
    public SuggestTree(int k) {
        if(k < 1)
            throw new IllegalArgumentException();
        this.k = k;
        root = null;
        size = 0;
    }
    
    /**
     * Returns the k highest-weighted terms in the tree that start with the
     * specified prefix, or null if there is no such term. */
    public STNode getAutocompleteSuggestions(String prefix) {
        return search(prefix);
    }   
    /**
     * Returns the tree entry for the specified term, or null if there is no
     * such entry.*/
    public STEntry getEntry(String term) {
        STNode n = search(term);
        if(n == null || n.getCharEnd() > term.length())
            return null;
        else
            return n.getEntry();
    }
    
    /**
     * Returns an iterator over the terms in the tree.
     */
    public STIterator iterator() {
        return new STIterator(root);
    }
    
    /**
     * Returns the number of terms in the tree.
     */
    public int size() {
        return size;
    }
    
    /**
     * Inserts the specified term with the specified weight into the tree, or
     * reweights the term if it is already present. 
     */
    public void put(String term, int weight) {
        if(term.isEmpty() || term.length() > Short.MAX_VALUE)
            throw new IllegalArgumentException();
        if(root == null) {
            root = new STNode(term, weight, 0, null);
            finishInsertion(root);
            return;
        }
        int i = 0;
        STNode n = root;
        while(true) {
            if(term.charAt(i) < n.firstChar) {
                if(n.left == null) {
                    n.left = new STNode(term, weight, i, n);
                    finishInsertion(n.left);
                    return;
                }else
                    n = n.left;
            }else if(term.charAt(i) > n.firstChar) {
                if(n.right == null) {
                    n.right = new STNode(term, weight, i, n);
                    finishInsertion(n.right);
                    return;
                }else
                    n = n.right;
            }else{
                while(++i < n.charEnd) {
                    if(i == term.length() || term.charAt(i) != n.charAt(i)) {
                        n = split(n, i);
                        break;
                    }
                }
                if(i < term.length()) {
                    if(n.mid == null) {
                        n.mid = new STNode(term, weight, i, n);
                        finishInsertion(n.mid);
                        return;
                    }else
                        n = n.mid;
                }else{
                    if(n.entry == null) {
                        n.entry = new STEntry(term, weight);
                        finishInsertion(n);
                    }else if(n.entry.weight < weight)
                        increaseWeight(n, weight);
                    else if(n.entry.weight > weight)
                        reduceWeight(n, weight);
                    return;
                }
            }
        }
    }
    
    /**
     * Removes the specified term from the tree.
     */
    public void remove(String term) {
        STNode n = search(term);
        if(n == null || n.entry == null || n.charEnd > term.length())
            return;
        randomizeDeletion(n);
        STEntry e = n.entry;
        n.entry = null;
        if(n.mid == null) {
            STNode p = parent(n);
            delete(n);
            n = p;
        }
        if(n != null && n.entry == null && n.mid.left == null && n.mid.right == null) {
            STNode p = parent(n);
            merge(n, n.mid);
            n = p;
        }
        removeFromLists(e, n);
        size--;
    }
    
    private STNode search(String s) {
        if(s.isEmpty())
            throw new IllegalArgumentException();
        int i = 0;
        STNode n = root;
        while(n != null) {
            if(s.charAt(i) < n.firstChar)
                n = n.left;
            else if(s.charAt(i) > n.firstChar)
                n = n.right;
            else{
                while(++i < n.charEnd) {
                    if(i == s.length())
                        return n;
                    else if(s.charAt(i) != n.charAt(i))
                        return null;
                }
                if(i == s.length())
                    return n;
                else
                    n = n.mid;
            }
        }
        return null;
    }
    
    private STNode split(STNode n, int position) {
        STNode s = new STNode(n, position);
        if(n.list.length == k)
            s.list = Arrays.copyOf(n.list, k);
        else // the list is copied in insertIntoLists()
            s.list = n.list;
        if(n.left != null)
            n.left.up = s;
        if(n.right != null)
            n.right.up = s;
        if(n == root)
            root = s;
        else if(n == n.up.left)
            n.up.left = s;
        else if(n == n.up.right)
            n.up.right = s;
        else
            n.up.mid = s;
        n.firstChar = n.charAt(position);
        n.left = n.right = null;
        n.up = s;
        return s;
    }

    private void merge(STNode n, STNode m) {
        m.firstChar = n.firstChar;
        m.left = n.left;
        m.right = n.right;
        m.up = n.up;
        if(n.left != null)
            n.left.up = m;
        if(n.right != null)
            n.right.up = m;
        if(n == root)
            root = m;
        else if(n == n.up.left)
            n.up.left = m;
        else if(n == n.up.right)
            n.up.right = m;
        else
            n.up.mid = m;
    }

    private void delete(STNode n) {
        if(n == root)
            root = null;
        else if(n == n.up.left)
            n.up.left = null;
        else if(n == n.up.right)
            n.up.right = null;
        else
            n.up.mid = null;
    }
    
    private void finishInsertion(STNode n) {
        randomizeInsertion(n);
        insertIntoLists(n);
        size++;
    }
    
    private void randomizeInsertion(STNode n) {
        n.entry.priority = random.nextInt();
        n.priority = higherPriority(n.entry, n.mid);
        while(n != root && n.up.priority < n.priority) {
            if(n == n.up.left)
                rotateRight(n.up);
            else if(n == n.up.right)
                rotateLeft(n.up);
            else{
                n.up.priority = n.priority;
                n = n.up;
            }
        }
    }
    
    private void randomizeDeletion(STNode n) {
        int p = n.entry.priority;
        n.entry.priority = Integer.MIN_VALUE;
        while(n != null && n.priority == p) {
            n.priority = higherPriority(n.entry, n.mid);
            STNode h = higherPriorityNode(n.left, n.right);
            while(h != null && h.priority >= n.priority) {
                if(h == n.left)
                    rotateRight(n);
                else
                    rotateLeft(n);
                h = higherPriorityNode(n.left, n.right);
            }
            n = parent(n);
        }
    }
    
    private int higherPriority(STEntry e, STNode n) {
        if(e == null)
            return n.priority;
        else if(n == null)
            return e.priority;
        else if(e.priority < n.priority)
            return n.priority;
        else
            return e.priority;
    }
    
    private STNode higherPriorityNode(STNode n, STNode m) {
        if(n == null)
            return m;
        else if(m == null)
            return n;
        else if(n.priority < m.priority)
            return m;
        else
            return n;
    }
    
    private void rotateLeft(STNode n) {
        STNode r = n.right;
        n.right = r.left;
        if(r.left != null)
            r.left.up = n;
        r.up = n.up;
        if(n == root)
            root = r;
        else if(n == n.up.left)
            n.up.left = r;
        else if(n == n.up.right)
            n.up.right = r;
        else
            n.up.mid = r;
        r.left = n;
        n.up = r;
    }
    
    private void rotateRight(STNode n) {
        STNode l = n.left;
        n.left = l.right;
        if(l.right != null)
            l.right.up = n;
        l.up = n.up;
        if(n == root)
            root = l;
        else if(n == n.up.left)
            n.up.left = l;
        else if(n == n.up.right)
            n.up.right = l;
        else
            n.up.mid = l;
        l.right = n;
        n.up = l;
    }
    
    private void insertIntoLists(STNode n) {
        STEntry e = n.entry;
        for( ; n != null; n = parent(n)) {
            if(n.mid == null)
                n.list = new STEntry[1];
            else if(n.list.length < k)
                n.list = Arrays.copyOf(n.list, n.list.length + 1);
            else if(e.weight <= n.list[k - 1].weight)
                return;
            int i = n.list.length - 1;
            while(i > 0 && e.weight > n.list[i - 1].weight) {
                n.list[i] = n.list[i - 1];
                i--;
            }
            n.list[i] = e;
        }
    }
    
    private void increaseWeight(STNode n, int newWeight) {
        STEntry e = n.entry;
        e.weight = newWeight;
        for( ; n != null; n = parent(n)) {
            int i = n.listIndexOf(e);
            if(i == -1) {
                if(e.weight <= n.list[k - 1].weight)
                    return;
                else
                    i = k - 1;
            }
            while(i > 0 && e.weight > n.list[i - 1].weight) {
                n.list[i] = n.list[i - 1];
                i--;
            }
            n.list[i] = e;
        }
    }
    
    private void reduceWeight(STNode n, int newWeight) {
        STEntry e = n.entry;
        e.weight = newWeight;
        for( ; n != null; n = parent(n)) {
            int i = n.listIndexOf(e);
            if(i == -1)
                return;
            while(i < n.list.length - 1 && e.weight < n.list[i + 1].weight) {
                n.list[i] = n.list[i + 1];
                i++;
            }
            n.list[i] = e;
            if(i == k - 1) {
                STEntry t = topUnlistedTerm(n);
                if(t != null && t.weight > e.weight)
                    n.list[i] = t;
            }
        }
    }
    
    private void removeFromLists(STEntry e, STNode n) {
        for( ; n != null; n = parent(n)) {
            int i = n.listIndexOf(e);
            if(i == -1)
                return;
            while(i < n.list.length - 1) {
                n.list[i] = n.list[i + 1];
                i++;
            }
            n.list[i] = e;
            if(n.list.length < k)
                n.list = Arrays.copyOf(n.list, n.list.length - 1);
            else{
                STEntry t = topUnlistedTerm(n);
                if(t == null)
                    n.list = Arrays.copyOf(n.list, k - 1);
                else
                    n.list[i] = t;
            }
        }
    }

    private STEntry topUnlistedTerm(STNode n) {
        STEntry t = null;
        if(n.entry != null && n.listIndexOf(n.entry) == -1)
            t = n.entry;
        for(STNode c = leftmostChild(n); c != null; c = rightSibling(c)) {
            for(STEntry e : c.list) {
                if(n.listIndexOf(e) == -1) {
                    if(t == null || t.weight < e.weight)
                        t = e;
                    break;
                }
            }
        }
        return t;
    }

    private STNode leftmostChild(STNode n) {
        n = n.mid;
        if(n != null) {
            while(n.left != null)
                n = n.left;
        }
        return n;
    }

    private STNode rightSibling(STNode n) {
        if(n.right != null) {
            n = n.right;
            while(n.left != null)
                n = n.left;
            return n;
        }else{
            while(n == n.up.right)
                n = n.up;
            if(n == n.up.left)
                return n.up;
            else
                return null;
        }
    }
    
    private STNode parent(STNode n) {
        while(n != root && n != n.up.mid)
            n = n.up;
        return n.up;
    }
}
