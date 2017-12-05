package main.ashu.lrucache;

public interface LRUCache {
    public void put(int pageNumber, Page page);
    public Page get(int pageNumber);
    public void remove(int pageNumber);
}
