package main.ashu.data_structure;

//http://www.javamadesoeasy.com/2015/02/hashmap-custom-implementation.html
//read about rehashing and distributed hash

public class CustomHashMap<K, V> {
	static class Entry<K, V> {  //an entry is a linked list
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	private Entry<K, V> bucket[]; // like adjacency list
	private static int capacity = 8;

	public CustomHashMap() {
		this.bucket = new Entry[capacity];
	}

	public int hash(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}

	public V get(K key) {
		int hash = hash(key);
		if (bucket[hash] == null)
			return null;
		else {
			// get the head of the list and search for the key
			Entry<K, V> node = bucket[hash];
			while (node != null) {
				if (node.key.equals(key))
					return node.value; // equals needs to be overridden
				node = node.next;
			}
			return null;
		}
	}

	public void put(K newKey, V val) {
		int hash = hash(newKey);
		Entry<K, V> newEntry = new Entry(newKey, val, null);
		if (bucket[hash] == null) {
			bucket[hash] = newEntry;
		} else {
			Entry<K, V> curr = bucket[hash];
			Entry<K, V> prev = null;
			// note that we have to minimize insertion time, so insert in the
			// beginning
			while (curr != null) {
				if (curr.key.equals(newKey)) {
					break;
				}
				prev = curr;
				curr = curr.next;
			}
			if (prev == null) {
				newEntry.next = curr;
				bucket[hash] = newEntry;
			} else {
				newEntry.next = prev.next;
				prev.next = newEntry;
			}
		}
	}

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
		for (int i = 0; i < capacity; i++) {
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
