package main.ashu.javacollections;

import java.util.LinkedList;

//http://www.javamadesoeasy.com/2015/02/hashmap-custom-implementation.html
//read about rehashing and distributed hash
//put, get, remove, resize
//for a threshold size, linked list is used, after which it gets converted to red black tree
//https://dzone.com/articles/custom-hashmap-implementation-in-java

public class CustomHashMap<K, V> {
	static class Entry<K, V> { // an entry is a linked list
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	// so the hashmap is basically an array of linked lists
	//has following instance variables internally
	private Entry<K, V> bucket[]; // like adjacency list
	//try using java's inbuilt LL class too, no need to reinvent the wheel
	//private LinkedList<Entry<K, V>> bucket[]; 
	private static final int INITIAL_CAPACITY = 1 << 4; // 16
	private int size = 0;

	public CustomHashMap() {
		this.bucket = new Entry[INITIAL_CAPACITY];
		//this.bucket = new LinkedList[INITIAL_CAPACITY];
	}

	public int hash(K key) {
		return Math.abs(key.hashCode()) % INITIAL_CAPACITY;
	}

	// inserts a key-value pair in the hashmap
	//creates a new entry node(key-value pair) and inserts in the appropriate bucket location
	public void put(K key, V value) {
		Entry<K, V> entry = new Entry(key, value, null);
		int hash = hash(key);
		 Entry<K, V> existing = bucket[hash];
	        if (existing == null) {
	            bucket[hash] = entry;
	            size++;
	        } else {
	            // compare the keys see if key already exists
	        	//use of equals method to compare keys
	            while (existing.next != null) {
	                if (existing.key.equals(key)) {
	                    existing.value = value;
	                    return;
	                }
	                existing = existing.next;
	            }
	            if (existing.key.equals(key)) {
	                existing.value = value;
	            } else {
	                existing.next = entry;
	                size++;
	            }
	        }
	}

	// returns the value against the particular key
	public V get(K key) {
		int hash = hash(key);
		Entry<K, V> current = bucket[hash];
	    while (current != null) {
	        if (current.key.equals(key)) {
	            return current.value;
	        }
	        current = current.next;
	    }
	    return null;
	}

	// deletes a key from the map
	public boolean delete(K key) {
		int hash = hash(key);
		if (bucket[hash] == null)
			return false;
		Entry<K, V> curr = bucket[hash];
		Entry<K, V> prev = null;
		boolean found = false;
		while (curr != null) {
			if (curr.key.equals(key)) {
				found = true;
				break;
			}
			prev = curr;
			curr = curr.next;
		}
		if (!found)
			return false;
		if (prev == null) {
			bucket[hash] = curr.next;
		} else {
			curr.next = curr.next.next;
		}
		return true;
	}

	public void display() {
		for (int i = 0; i < INITIAL_CAPACITY; i++) {
			Entry<K, V> entry = bucket[i];
			if (entry != null) {
				while (entry != null) {
					System.out.print("key = " + entry.key + " , value = "
							+ entry.value);
					entry = entry.next;
				}
				System.out.println();
			}
		}
	}

}
