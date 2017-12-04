package geeksforgeeks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class TreeNode {
	public int data;
	public TreeNode left, right;
	TreeNode(int data) {
		this.data = data;
		this.left = this.right = null;
	}
	TreeNode () {		
	}
}
//to be used for finding largest BST
class MinMax {
	int min;
	int max;
	boolean isBST;
	int size;
	MinMax(){
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        isBST = true;
        size = 0;
    }
}

class BST {
   public TreeNode root;
   public boolean f1=false, f2=false;
   public BST() {
	   root = new TreeNode();
	   root.left=root.right = null;
   }
   public void insert(int key) {
	   TreeNode newNode = new TreeNode();
	   newNode.data = key;
	   newNode.left = newNode.right = null;
	   if(root ==null) root = newNode;
	   else {
		   TreeNode temp = root, parent = root;
		   while(temp != null) {
			   parent = temp;
			   if(key < temp.data) temp = temp.left;
			   else temp = temp.right;
		   }
		   if(key < parent.data) parent.left = newNode;
		   else parent.right = newNode;
	   }
   }
   public boolean isBST() {
	   return isBSTUtil(this.root,Integer.MIN_VALUE, Integer.MAX_VALUE);
   }
   public boolean isBSTUtil(TreeNode root, int min, int max) {
	   if(root == null) return true;
	   if(root.data < min || root.data > max) return false;
	   return isBSTUtil(root.left, min, root.data-1) && isBSTUtil(root.right,root.data, max);
   }
   public int largestBST() {
	   MinMax m =  largestBSTUtil(this.root);
	   return m.size;
   }
   //traverse in post order fashion for efficiency
   public MinMax largestBSTUtil(TreeNode root) {
	   MinMax m = new MinMax();
	   if(root == null) {
		   return m;
	   } else {
		   MinMax left = largestBSTUtil(root.left);
		   MinMax right = largestBSTUtil(root.right);
		   if(!left.isBST || !right.isBST || root.data < left.max || root.data >= right.min) { //failure case
			   m.isBST = false;
			   m.size = Math.max(left.size, right.size);
			   return m;
		   } else { //success case ..valid BST
			   m.isBST = true;
			   m.size = left.size + right.size + 1;
			   m.min = (root.left != null) ? left.min : root.data;
			   m.max = (root.right != null) ? right.max : root.data;
			   return m;
		   }
	   }
   }
   public void preorderRecursive() {
	   System.out.print("Preorder Recurdive : ");
	   preorderRecursiveUtil(this.root);
	   System.out.println();
   }
   public void preorderRecursiveUtil(TreeNode root) {
	   if(this.root == null) return;
	   System.out.print(root.data+ " ");
	   if(root.left != null) preorderRecursiveUtil(root.left);
	   if(root.right != null) preorderRecursiveUtil(root.right);
   }
   public void preorderIterative() {
	   if(root==null) return;
	   System.out.print("Preorder Iterative : ");
	   Stack<TreeNode> stack = new Stack();
	   stack.push(root);  //push right and then left so that on popping order becomes root, left, right
	   while(!stack.isEmpty()) {
		   TreeNode temp = stack.pop();
		   System.out.print(temp.data+" ");
		   if(temp.right != null) stack.push(temp.right);
		   if(temp.left != null)  stack.push(temp.left);
	   }
	   System.out.println();
   }
   public void MorrisPreorderTraversal() {
	   if(root == null) return;
	   System.out.print("Morris Preorder  :   ");
	   TreeNode current = this.root;
	   while(current != null) {
		   //System.out.print(current.data+" ");  //visit the root no printing here b/c root gets visited twice due threading 
		   if(current.left != null) {
			   TreeNode temp = current.left;
			   while(temp.right != null && temp.right != current) temp = temp.right;
			   if(temp.right == null) {   //create the thread 
				   System.out.print(current.data+" ");  //visit the root first time
				   temp.right = current;
				   current = current.left;
			   } else { //visiting current second time don't print rather unthread it and go to the right			   
				   current = current.right;	
				   temp.right = null;  //remove the thread
			   }
		   } else  {
			   System.out.print(current.data+" ");  //visit the root
			   current = current.right;			   
		   }
	   }
	   System.out.println();
   }
   public void inorderRecursive() {
	   System.out.print("Inorder Recursive : ");
	   inorderRecursiveUtil(root);
	   System.out.println();
   }
   public void inorderRecursiveUtil(TreeNode root) {
	   if(root == null) return;
	   inorderRecursiveUtil(root.left);
	   System.out.print(root.data+" ");
	   inorderRecursiveUtil(root.right);
   }
   public void inorderIterative() {
	   System.out.print("Inorder Iterative : ");
	   Stack<TreeNode> stack = new Stack();
	   TreeNode temp = root;
	   stack.push(root);
	   //go to the leftmost node while pushing nodes on the stack
	   while(temp.left != null)   { 
		   stack.push(temp);
		   temp = temp.left;
	   }
	   //now start popping and pushing the right subtrees if not null
	   while(!stack.isEmpty()) {
		   temp = stack.pop();
		   System.out.print(temp.data+" ");
		   if(temp.right != null) {
			   temp = temp.right;
			   stack.push(temp);
			   while(temp.left != null) {
				   temp = temp.left;
				   stack.push(temp);
			   }
		   }
	   }
	   System.out.println();
   }
   public void MorrisInorderTraversal() {
	   System.out.print("Morris Inorder   :  ");
	   if(root==null) return;
	   TreeNode temp = this.root;
	   while(temp != null) {
		   if(temp.left == null){
			   System.out.print(temp.data+" ");
			   temp = temp.right;
		   }
		   else {
			   //find the inorder predecessor and make it point to the current node
			   TreeNode pre = temp.left;
			   while(pre.right != null && pre.right != temp) pre = pre.right;  
			   if(pre.right == null) { //visiting this root for the first time
				   pre.right = temp;  //creating thread to the inorder successor of the subtree rooted at temp like we do using stack
				   temp = temp.left;
			   } else {               //visiting pred second time...revert back the changes and visit the current
				   pre.right = null;
				   System.out.print(temp.data+" ");
				   temp = temp.right;
			   }

		   } 
	   }
	   System.out.println();
   }
   public void postorderRecursive() {
	   if(root==null) return;
	   System.out.print("Postorder Recursive : ");
	   postorderRecursiveUtil(this.root);
	   System.out.println();
   }
   public void postorderRecursiveUtil(TreeNode root) {
	   if(root == null) return;
	   if(root.left != null) postorderRecursiveUtil(root.left);
	   if(root.right != null) postorderRecursiveUtil(root.right);
	   System.out.print(root.data+" ");
   }
   public void postorderIterativeTwoStacks() {
	   if(root==null) return;
	   System.out.print("Postorder iterative using two stacks : ");
	   Stack<TreeNode> s1 = new Stack(); //to keep track of left and right subtrees
	   Stack<TreeNode> s2 = new Stack();  //to contain roots in postorder traversal
	   s1.push(this.root);
	   //pop, push the root to s2, push left and right to s1....till s1 becomes empty
	   while(!s1.isEmpty()) {
		   TreeNode temp = s1.pop();
		   s2.push(temp);
		   if(temp.left != null) s1.push(temp.left);
		   if(temp.right != null) s1.push(temp.right);
	   }
	   while(!s2.isEmpty()) {
		   System.out.print(s2.pop().data+" ");
	   }
	   System.out.println();
   }
   //http://articles.leetcode.com/binary-tree-post-order-traversal
   //We would process the node and pop it off the stack in these 3 cases:
  // 1. The node is a leaf node (no children)
  // 2. We just traverse up the tree from the left and no right child exist.
  // 3. We just traverse up the tree from the right.
   public void postorderIterativeSingleStack() {
	   if(root==null) return;
	   System.out.print("Postorder iterative using single stacks : ");
	   Stack<TreeNode> stack = new Stack();
	   stack.push(this.root);
	   TreeNode previous = null;
	   while(!stack.isEmpty()) {
		   TreeNode current = stack.peek();
		   //if traversing down tree...
		   if(previous == null || previous.left==current | previous.right==current) {
			   if(current.left != null) stack.push(current.left);
			   else if(current.right != null) stack.push(current.right);
			   else {
				   TreeNode visited = stack.pop();
				   System.out.print(visited.data+" ");
			   }
		   } else if(current.left==previous) {   //traversing up from left
			   if(current.right != null) stack.push(current.right);
			   else { //pop the leaf node
				   TreeNode visited = stack.pop();
				   System.out.print(visited.data+" ");
			   }
		   } else { //traversing up from right 
			   //pop since both left and right of this subtree has been visited now
			   TreeNode visited = stack.pop();
			   System.out.print(visited.data+" ");
		   }
		   previous = current;
	   }
	   System.out.println();
   }
   //https://www.quora.com/What-is-a-good-way-to-implement-stackless-recursion-less-post-order-traversal-for-a-non-threaded-binary-tree-using-Morris-method
   public void MorrisPostorderTraversal() {
	   if(root == null) return;
	   System.out.print("Morris Postorder Traversal : ");
	   TreeNode dummyRoot = new TreeNode();
	   dummyRoot.left = this.root;
	   dummyRoot.right = null;
	   TreeNode current = dummyRoot, pred, first, middle, last;
	   
	   while(current != null) {
		   if(current.left == null){
				current = current.right;
			} else{
				/* current has a left child => it also has a predeccessor
				   make current as right child predeccessor of p	
				*/
				//Note : when a predecessor if found for the first time, make it point to the current node. 
				//When it's found the second time, reverse the link as you move down till the current node.
				//Next move up while printing the visited nodes and restoring the link till the current node.
				//Now move right and do the same shit again....
				//Note that storing the parent while moving to right is not required b/c. Making  whole tree the left child of the 
				//dummy node solves this problem by creating a thread from rightmost node to the dummy node, 
				//this makes visiting the parent nodes possible through link reversal. Make the diagram and see.
				
				pred = current.left;
				while(pred.right!=null && pred.right != current){
					pred = pred.right;
				}
				
				if(pred.right == null){ 	 
					// predeccessor found for first time
					// modify the tree					
					pred.right = current;	
					current = current.left;				
				}else { 						 				
				   // predeccessor found second time
				   // Step 1(link reversal downwards) : reverse the right references in between current and pred
					first = current;
					middle = current.left;  			
					while(middle!=current){			
						last = middle.right;
						middle.right = first;
						first = middle;
						middle = last;
					}				
					// Step 2: visiting the nodes in postorder way and restoring the links on the go.	
					first = current;
					middle = pred;
					while(middle!=current){						
						System.out.print(middle.data+" ");  
						last = middle.right;	  	
						middle.right = first;
						first = middle;
						middle = last;
					}					
					// remove the pred to node reference to restore the tree structure
					pred.right = null;	
					current = current. right;
				}
			}
	   }
	   System.out.println();
   }
   public void levelOrderTraversal() {
	   if(root==null) return;
	   System.out.println("Level order traveral :");
	   Queue<TreeNode> q = new LinkedList();
	   q.offer(root);
	   int level = 0;
	   while(!q.isEmpty()) {
		   int levelSize = q.size();
		   System.out.print("Nodes at level "+level+" : ");
		   while(levelSize-- > 0) {
			   TreeNode temp = q.poll();
			   System.out.print(temp.data+" ");
			   if(temp.left != null) q.offer(temp.left);
			   if(temp.right != null) q.offer(temp.right);
		   }
		   System.out.println();
		   level ++;
	   }
   }
   public void reverseLevelOrderTraversal() {
	   if(root==null) return;
	   System.out.println("Reverse Level order traveral :");
	   Queue<TreeNode> q = new LinkedList();
	   q.offer(root);
	   int level = 0;
	   while(!q.isEmpty()) {
		   int levelSize = q.size();
		   System.out.print("Nodes at level "+level+" : ");
		   while(levelSize-- > 0) {
			   TreeNode temp = q.poll();
			   System.out.print(temp.data+" ");
			   //enqueue right child and then left child
			   if(temp.right != null) q.offer(temp.right);
			   if(temp.left != null) q.offer(temp.left);
		   }
		   System.out.println();
		   level ++;
	   }
   }
   //can also be done using Deque or by recursively printing nodes of a level by passing level info and directions of printing(left/right).
   public void spiralLevelOrdeTwoStacks() {
	   if(root==null) return;
	   System.out.println("Spiral Level order traveral using two stacks:");
	   Stack<TreeNode> s1 = new Stack();
	   Stack<TreeNode> s2 = new Stack();
	   s1.push(root);
	   int level = 0;
	   while(!s1.isEmpty() || !s2.isEmpty()) {
		   System.out.print("Level "+level+" : ");
		   if(level%2 ==0) {
			   while(!s1.isEmpty()) {
				   TreeNode temp = s1.pop();
				   System.out.print(temp.data+" ");
				   if(temp.left != null) s2.push(temp.left);
				   if(temp.right != null) s2.push(temp.right);
			   }

		   } else {
			   while(!s2.isEmpty()) {
				   TreeNode temp = s2.pop();
				   System.out.print(temp.data+" ");
				   if(temp.right != null) s1.push(temp.right);
				   if(temp.left != null) s1.push(temp.left);
			   }
		   }
		   level++;
		   System.out.println();
	   }
   }
   
   //diameter is the length(no of nodes) of largest path between any two nodes, not necessarily passing through the root
   //d = max(diam of left, diam of right, 1+left height+right height)
   public void diameterAndHeight() {
	   int dh [] = diameterUtil(this.root);
	   System.out.println("Height of BST is : "+dh[1]);
	   System.out.println("Diameter of BST is : "+dh[0]);
   }
   //O(n) time....finding height and diameter separately will be O(n2)
   public int [] diameterUtil(TreeNode root) {
	   int d [] = {0,0};
	   if (root == null) return d;
	   if(root.left==null && root.right==null) {
		   d[0] = 1; d[1] = 1; return d;
	   }
	   d = diameterUtil(root.left);
	   int ld = d[0];
	   int lh = d[1];
	   d = diameterUtil(root.right);
	   int rd = d[0];	   
	   int rh = d[1];
	   d[0] = Math.max(Math.max(ld, rd), lh+rh+1); //computing final diameter
	   d[1] = Math.max(lh, rh)+1;                  //final height
	   return d;
   }
   public boolean search(int key) {
	   if(root==null) return false;
	   return searchUtil(key, root);
   }
   //using recursive preorder traversal
   public boolean searchUtil(int key, TreeNode root) {
	   if(key == root.data) return true;
	   else if(root.left != null && key < root.left.data) return searchUtil(key, root.left);
	   else if(root.right != null) return searchUtil(key, root.right);
	   return false;
   }
   public void rootToLeafPathWithSum(int sum) {
	   ArrayList<Integer> path = new ArrayList();
	   boolean exists = pathWithSumUtil(this.root, sum, path);
	   if(!exists) System.out.println("No path with sum "+sum+" exists in the tree");
	   System.out.println();
   }
   //using preorder traversal to find path..for a general binary tree
   //what strategy would you choose if its a BST....pruning of search space maybe using range check before going left or right 
   public boolean pathWithSumUtil(TreeNode root, int sum, ArrayList<Integer> path) {
	   if(root == null) return false;
	   if(root.left == null && root.right == null) { //leaf node...base case
		   if(root.data == sum) {
			   path.add(root.data);
			   System.out.print("Path with given sum : ");
			   for(Integer i : path) System.out.print(i+ " ");
			   System.out.println();
			   path.remove(path.size()-1); //backtracking for storing other paths
			   return true;
		   } else return false;
	   } else {
		   boolean searchLeft = false, searchRight = false;
		   if(root.left != null) {
			   path.add(root.data);
			   searchLeft = pathWithSumUtil(root.left, sum-root.data, path);
			   path.remove(path.size()-1);	//backtrack	for storing other paths	   			   
		   }
		   if(root.right != null) {
			   path.add(root.data);
			   searchRight = pathWithSumUtil(root.right, sum-root.data, path);
			   path.remove(path.size()-1); //backtrack for storing other paths
		   }
		   return searchLeft || searchRight;
	   }
   }
   //this soln is for BT.. doesn't handle duplicates
   public TreeNode lowestCommonAncestorBT(TreeNode n1, TreeNode n2) {
	   f1 = false;
	   f2 = false;
	   TreeNode lca = lowestCommonAncestorBTUtil(root, n1, n2);
	  return (f1 && f2) ? lca : null;  //return null if keys not present
   }
   public TreeNode lowestCommonAncestorBTUtil(TreeNode root, TreeNode n1, TreeNode n2) {
	   if(root == null) return root;  //base case
	   if(root.data==n1.data || root.data==n2.data){
		   if(root.data==n1.data) f1 = true;
		   else if(root.data == n2.data) f2 = true;
		   return root;  //comparison at root
	   }
	   TreeNode left = lowestCommonAncestorBTUtil(root.left, n1,n2);
	   TreeNode right = lowestCommonAncestorBTUtil(root.left, n1,n2);
	   if(left != null & right != null) return root;
	   return (left != null) ? left : right;
   }
 //this soln is for BST ..doesn't handle duplicates
   public TreeNode lowestCommonAncestorBST(TreeNode n1, TreeNode n2) {
	   f1 = false;
	   f2 = false;
	   TreeNode lca = lowestCommonAncestorBSTUtil(root, n1, n2); //return null if keys not present
	   return (f1 && f2) ? lca : null;
   }
   //using predorder traversal....write iterative version too
   public TreeNode lowestCommonAncestorBSTUtil(TreeNode root, TreeNode n1, TreeNode n2) {
	   if(root == null) return root;
	   if(root.data == n1.data || root.data == n2.data) { // visiting the root and comparing
		   if(root.data==n1.data) f1 = true;
		   else if(root.data == n2.data) f2 = true;
		   return root;     
	   }
	   if(root.data >n1.data && root.data >n2.data) return lowestCommonAncestorBSTUtil(root.left, n1, n2);
	   else if(root.data <n1.data && root.data <n2.data) lowestCommonAncestorBSTUtil(root.right, n1, n2);
	   return root;	   
   }
}


//Test class for BT/BST
public class Trees {
	public static void main(String arg []) {
		BST bst = new BST();
		   //int a [] = {2,12,8,5,9,13,7,30};
		   /*int a [] = {10,8,2,3,5,2};
		   
		   System.out.print("Inserting into the BST : ");
		   for(int i=0; i<a.length; i++){  
			   System.out.print(a[i]+" ");
			   bst.insert(a[i]);
		   }*/
		   bst.root.data = 1;
		   bst.root.left = new TreeNode(2);
		   bst.root.right = new TreeNode(3);
		   bst.root.left.left = new TreeNode(7);
		   bst.root.left.right = new TreeNode(5);
		   bst.root.right.left = new TreeNode(6);
		   bst.root.right.right = new TreeNode(7);
		   System.out.println();
		   boolean isBST = bst.isBST();
		   System.out.println("Given binary tree is "+(isBST==true ? "a BST" : "not a BST" ));
		   System.out.println("Size of largest BST in the given BT is : "+bst.largestBST());
		   bst.diameterAndHeight();
		   bst.preorderRecursive();
		   bst.preorderIterative();
		   bst.MorrisPreorderTraversal();
		   bst.inorderRecursive();
		   bst.inorderIterative();
		   bst.MorrisInorderTraversal();
		   bst.postorderRecursive();
		   bst.postorderIterativeTwoStacks();
		   bst.postorderIterativeSingleStack();
		   bst.MorrisPostorderTraversal();
		   bst.levelOrderTraversal();
		   bst.reverseLevelOrderTraversal();
		   bst.spiralLevelOrdeTwoStacks();
		   bst.rootToLeafPathWithSum(28);
		   //bst.rootToLeafPathWithSum(10);

		   /*System.out.println("Enter an element to search");
		   Scanner sc = new Scanner(System.in);
		   int key = Integer.parseInt(sc.nextLine());
		   if(bst.search(key)) System.out.println("The key "+key+" exists in the BST");
		   else System.out.println("The key "+key+" doesn't exists in the BST");*/
	   }
}