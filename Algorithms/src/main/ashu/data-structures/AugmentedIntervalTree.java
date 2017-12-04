package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;


//member variables have been made public to do away with getters/setters
class Interval implements Comparable<Interval>{
	public long start;
	public long end;
	public long max;
	public Interval left, right;
	
	public Interval(long start, long end) {
		this.start = start;
		this.end = end;
		this.max = this.end;
		this.left = this.right = null;
	}	
	public int compareTo(Interval i) {
		if(this.start < i.start) return -1;
		if(this.start == this.end) {
			return (this.end <= i.end) ? -1 : 1;
		}
		return 1;
	}
	public String toString() {
		return "["+this.start+","+this.end+",max="+this.max+"]";				
	}
	
}

public class AugmentedIntervalTree {
  private static Interval root;
  

  public void insert(Interval newNode) {
  	this.root = insertUtil(this.root, newNode);
  }
  //recursive method to insert a new node in the interval tree
  private Interval insertUtil(Interval current, Interval newNode) {
  	if(current == null) {
  		current = newNode;
  		return current;
  	}
  	//update the max case if possible
  	if(current.max < newNode.end) current.max = newNode.end;
  	if(current.compareTo(newNode) < 0) {
  		current.right = insertUtil(current.right, newNode);
  	}
  	else {
  		current.left = insertUtil(current.left, newNode);
  	}
  	return current;
  }
  public static List<Interval> findOverlappingIntervals (Interval i, ArrayList<Interval> over) {
	  findOverlapUtil(root,i, over);
	 return over;
  }
  
  public static void findOverlapUtil(Interval current, Interval qi, ArrayList<Interval> over) {
	 if(qi==null || current==null) return;
	  if(qi.start >= current.start && qi.start <=current.end || qi.end >=current.start && qi.end <=current.end) {
		  over.add(current);
	  }
	  if(current.left!= null && current.left.max >=qi.start) findOverlapUtil(current.left, qi, over);
		  findOverlapUtil(current.right, qi, over);
	  
  }
  public void inorder() {
  	inorderUtil(this.root);
  }
  public void inorderUtil(Interval current) {
  	if(current != null) {
  		if(current.left != null) inorderUtil(current.left);
  		System.out.print(current+", ");
  		if(current.right != null) inorderUtil(current.right);
  	}
  }
  
  public static void main(String args []) {
  	AugmentedIntervalTree tree = new AugmentedIntervalTree();
  	tree.insert(new Interval(5, 10));
  	tree.insert(new Interval(15, 25));
  	tree.insert(new Interval(1, 12));
  	tree.insert(new Interval(8, 16));
  	tree.insert(new Interval(14, 20));
  	tree.insert(new Interval(18, 21));
  	tree.insert(new Interval(2, 8));
  	System.out.println("Inorder traversal of the Interval tree : ");
  	tree.inorder();
  	System.out.println();
  	List<Interval> queryIntervals = new ArrayList<Interval>();
  	queryIntervals.add(new Interval(8, 10));
  	queryIntervals.add(new Interval(20, 122));
  	ArrayList<Interval> overlaps = new ArrayList<Interval>();
  	 	for(Interval i : queryIntervals) {
  	 		findOverlappingIntervals(i, overlaps);
  	 		System.out.print("Interval "+i+" overlaps with : ");
  	 		for(Interval itr : overlaps) {
  	 			System.out.print(itr+" ");
  	 		}
  	 		overlaps.clear();
  	 		System.out.println();	
  	 	}
  }
}
