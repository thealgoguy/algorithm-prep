package main.ashu.data_structure.linkedlists;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedLists {
	private static class Node {
	     int val;
	     Node next;
	     Node(int x) { val = x; }
	  }
	
	public Node mergeKLists(List<Node> lists) {
        if (lists==null||lists.size()==0) return null;
        
        PriorityQueue<Node> queue= new PriorityQueue<Node>(lists.size(),new Comparator<Node>(){
            public int compare(Node o1,Node o2){
                if (o1.val<o2.val)
                    return -1;
                else if (o1.val==o2.val)
                    return 0;
                else 
                    return 1;
            }
        });
        
        Node dummy = new Node(0);
        Node tail=dummy;
        
        for (Node node:lists)
            if (node!=null) queue.add(node);
            
        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;
            //reinsert the next node of this min node in the PQ
            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }
}
