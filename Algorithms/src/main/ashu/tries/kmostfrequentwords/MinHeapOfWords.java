package kmostfrequentwords;

public class MinHeapOfWords {
	private int capacity;
	private Word [] arr;
	private int size;
	public MinHeapOfWords(int capacity) {
		this.capacity = capacity;
		this.arr = new Word[this.capacity];
		this.size=0;
	}
	public boolean isFull(){
		return (size==capacity);
	}

	public int insertKey(Word word) {
		if(size==capacity) {
			System.out.println("Max heap capacity reached, can't insert the key");
			return -1;
		}
		else {
			arr[size] = word;
			int finalIdx = bubbleUp(size);
			size++;
			return finalIdx;
		}
	}

	public int updateKey(int i, int val) {
		if(i>=0 && i<capacity) {
			swap(i,0);
			arr[0].setFrequency(val);
			return heapify(0);
		}
		return -1;
	}

	public Word extractMin() {
		Word removed = arr[0];
		//change pointers in trie leaf node
		arr[0].leafNode.setMinHeapIndex(-1);
		size--;
		arr[0] = arr[size];
		arr[size].getLeafNode().setMinHeapIndex(0);
		heapify(0);
		return removed;
	}

	public Word getMinKey() {
		return arr[0];
	}

	//bubble down operation
	public int heapify(int i) {
		if(i>=0 && i<capacity) {
			int left = 2*i+1, right = 2*i+2;
			int min1 = (left<capacity && arr[left]!= null) ? arr[left].getFrequency() : Integer.MAX_VALUE;
			int min2 = (right<capacity && arr[right]!=null) ? arr[right].getFrequency() : Integer.MAX_VALUE;
			if(arr[i].getFrequency() < min1 && arr[i].getFrequency() < min2) return i;
			int min = Math.min(arr[i].getFrequency(), Math.min(min1,  min2));
			if(min == min1) {
				swap(i,left);
				return heapify(left);
			}
			else {
				swap(i,right);
				return heapify(right);
			} 		
		}
		return -1;
	}

	public int bubbleUp(int i) {
		if(i>=0 && i<capacity) {
			int parent = (i-1)/2;
			if((arr[parent].compareTo(arr[i]) > 0)) {
				swap(i, parent);
				return bubbleUp(parent);
			}
			else return i;
		}
		return -1;
	}

	public void swap(int i, int j) {
		//swap pointers in corresponding trie node
		arr[i].getLeafNode().setMinHeapIndex(j);
		arr[j].getLeafNode().setMinHeapIndex(i);
		//now swap indexes in heap
		Word temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public void displayKeys() {
		for(int i=0; i<capacity; i++) {
			System.out.println(arr[i]);
		}
	}
}
