package main.ashu.computationalgeometry.quadtree;

/******************************************************************************
 *  Compilation:  javac QuadTree.java
 *  Execution:    java QuadTree M N
 *
 *  Quad tree.
 * Source : http://algs4.cs.princeton.edu/92search/QuadTree.java.html
 * Watc a quadtree in action : http://ericandrewlewis.github.io/how-a-quadtree-works/
 ******************************************************************************/

public class QuadTree<Key extends Comparable<Key>, Value>  {
    private Node root;

    // helper node data type
    private class Node {
        Key x, y;              // x- and y- coordinates
        Node NW, NE, SE, SW;   // four subtrees
        Value value;           // associated data

        Node(Key x, Key y, Value value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

  /***********************************************************************
    *  Insert (x, y) into appropriate quadrant
    ***************************************************************************/
    public void insert(Key x, Key y, Value value) {
        root = insert(root, x, y, value);
    }

    private Node insert(Node h, Key x, Key y, Value value) {
        if (h == null) return new Node(x, y, value);
        //// if (eq(x, h.x) && eq(y, h.y)) h.value = value;  // duplicate
        else if ( less(x, h.x) &&  less(y, h.y)) h.SW = insert(h.SW, x, y, value);
        else if ( less(x, h.x) && !less(y, h.y)) h.NW = insert(h.NW, x, y, value);
        else if (!less(x, h.x) &&  less(y, h.y)) h.SE = insert(h.SE, x, y, value);
        else if (!less(x, h.x) && !less(y, h.y)) h.NE = insert(h.NE, x, y, value);
        return h;
    }


  /***********************************************************************
    *  Range search.  Search for points in the plane that lie inside a given rectangle
    ***************************************************************************/

    public void query2D(Interval2D<Key> rect) {
        query2D(root, rect);
    }

    private void query2D(Node node, Interval2D<Key> rect) {
        if (node == null) return;
        Key xmin = rect.intervalX.min;
        Key ymin = rect.intervalY.min;
        Key xmax = rect.intervalX.max;
        Key ymax = rect.intervalY.max;
        if (rect.contains(node.x, node.y))
            System.out.println("Found :    (" + node.x + ", " + node.y + ") " + node.value);
        if ( less(xmin, node.x) &&  less(ymin, node.y)) query2D(node.SW, rect);
        if ( less(xmin, node.x) && !less(ymax, node.y)) query2D(node.NW, rect);
        if (!less(xmax, node.x) &&  less(ymin, node.y)) query2D(node.SE, rect);
        if (!less(xmax, node.x) && !less(ymax, node.y)) query2D(node.NE, rect);
    }


   /***************************************************************************
    *  helper comparison functions
    ***************************************************************************/

    private boolean less(Key k1, Key k2) { return k1.compareTo(k2) <  0; }
    private boolean eq  (Key k1, Key k2) { return k1.compareTo(k2) == 0; }


   /***************************************************************************
    *  test client
    ***************************************************************************/
    public static void main(String[] args) {
        /*int M = Integer.parseInt(args[0]);   // queries
        int N = Integer.parseInt(args[1]); */  // points
    	
    	int M = 2, N = 10;

        QuadTree<Integer, String> st = new QuadTree<Integer, String>();

        // insert N random points in the unit square
        for (int i = 0; i < N; i++) {
            Integer x = (int) (100 * Math.random());
            Integer y = (int) (100 * Math.random());
            System.out.println("(" + x + ", " + y + ")");
            st.insert(x, y, "P" + i);
        }
        System.out.println("Inserted " + N + " points in Quadtree");

        // do some range searches.  
        //Since points are random, you may not get overlaps in some runs, so do multiple runs to see the results
        for (int i = 0; i < M; i++) {
            Integer xmin = (int) (100 * Math.random());
            Integer ymin = (int) (100 * Math.random());
            Integer xmax = xmin + (int) (100 * Math.random());
            Integer ymax = ymin + (int) (200 * Math.random());
            Interval<Integer> intX = new Interval<Integer>(xmin, xmax);
            Interval<Integer> intY = new Interval<Integer>(ymin, ymax);
            Interval2D<Integer> rect = new Interval2D<Integer>(intX, intY);
            System.out.println("Searching for points inside rectangle : "+rect);
            st.query2D(rect);
        }
    }

}

