package main.ashu.data_structure.tries.autocomplete.suggesttree;

/**
 * A weighted term.
 */
public class STEntry {  
    public final String term;
    public int weight;
    public int priority;
    
    public STEntry(String term, int weight) {
        this.term = term;
        this.weight = weight;
    }
    
    public String getTerm() {
        return term;
    }
    
    public int getWeight() {
        return weight;
    }

}
