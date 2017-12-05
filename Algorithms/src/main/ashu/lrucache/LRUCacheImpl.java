package main.ashu.lrucache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCacheImpl implements LRUCache{
	private int CAPACITY;                //max no of pages the cache can hold	
	private Map<Integer, Page> keyMap;   //to locate a page in O(1) time, if it exists in the cache.
	//a FIFO queue containing the actual pages. The keyMap points to these pages in the memory.
	//LRU pages are in the beginning while newer pages are in the end. If cache full, front pages will be removed.
	private LinkedList<Page> pageList;     //real Page objects stored in this list
	LRUCacheImpl(int capacity) {
		this.CAPACITY = capacity;
		this.keyMap = new HashMap();       //hashmap for quick lookup
		pageList = new LinkedList<Page>(); //doubly linked list
	}
	public void put(int pageNumber, Page newPage) {
		//if already present(cache hit), replace
		if(keyMap.containsKey(pageNumber)) {
			Page oldPage = keyMap.get(pageNumber); //reference of old page
			keyMap.put(pageNumber, newPage); //update the map
			pageList.remove(oldPage);      //remove the old page
			pageList.addLast(newPage);   //add the page to the end of the queue
		}
		else {
			//if not present(cache miss) then
			//if cache not full, simply add else evict the lru page and then add
			if(pageList.size() <CAPACITY) {
				pageList.addLast(newPage);
				keyMap.put(pageNumber, newPage);	
			}
			else { //evict lru page and add new page			
				Page lruPage = pageList.get(0);
				keyMap.remove(lruPage.getPageNumber());
				pageList.remove(0);
				keyMap.put(pageNumber, newPage);
				pageList.addLast(newPage);
			}
			
		}
		
	}
	public Page get(int pageNumber) {
		//cache hit case, found
		if(keyMap.containsKey(pageNumber)) {
			return keyMap.get(pageNumber);
		}
		//cache miss case, not found
		return null;
	}
	public void remove(int pageNumber) {
		Page oldPage = keyMap.get(pageNumber);
		keyMap.remove(pageNumber);
		pageList.remove(oldPage);
	}
	//pages from newest to oldest
	public void printCachedData() {
		for(int i=pageList.size()-1;i>=0; i--) {
			System.out.println(pageList.get(i));
		}
	}	
}
