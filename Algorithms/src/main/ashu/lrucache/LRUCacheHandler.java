package main.ashu.lrucache;

public class LRUCacheHandler {
     public static void main(String args []) {
    	 LRUCacheImpl lrucache = new LRUCacheImpl(4);
    	 lrucache.put(1, new Page(1,"Page1", "Page1 data"));
    	 lrucache.put(2, new Page(2,"Page2", "Page2 data"));
    	 lrucache.put(3, new Page(3,"Page3", "Page3 data"));
    	 lrucache.put(1, new Page(1,"Page1", "Page1 data"));
    	 lrucache.put(4, new Page(4,"Page4", "Page4 data"));
    	 lrucache.put(5, new Page(5,"Page5", "Page5 data"));
    	 lrucache.printCachedData();
     }
}
