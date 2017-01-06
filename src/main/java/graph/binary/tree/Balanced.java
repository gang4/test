package graph.binary.tree;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Balanced {
	static enum UnBalancedCase {
		CASE1,
		CASE2,
		CASE3,
		CASE4,
		NONCASE
	}
	static public class Node {
		final int key;
		public Node left = null;
		public Node right = null;
		public Node parent = null;
		int height = 0;
		public Node (int key) {
			this.key = key;
		}
	}
	
	private Node root = null; 
	public void setRoot(Node root) {
		this.root = root;
	}
	public Node getRoot() {
		return root;
	}
	
	public Balanced() {
	}
	
	public void insert(Node newNode) {
		if (root == null) {
			root = newNode;
			return;
		}
		
		Node node = this.root;
		do {
			if (node.key < newNode.key) {
				if (node.right == null) {
					node.right = newNode;
					break;
				}
				else {
					node = node.right;
				}
			}
			else {
				if (node.left == null) {
					node.left = newNode;
					break;
				}
				else {
					node = node.left;
				}
				
			}
		} while (true);
		newNode.parent = node;
	}

	public Node searchNode(int key) {		
		Node node = this.root;
		do {
			if (node.key < key) {
				node = node.right;
			}
			else if (node.key > key) {
				node = node.left;				
			}
			else {
				return node;
			}
		} while (node != null);
		return null;
	}

	public Node remove(int key) {	
		Node node = searchNode(key);
		if (node.left == null && node.right == null) {
			remove0(node);
		}
		else if (node.left == null || node.right == null) {
			remove1(node);
		}
		else {
			remove2(node);
		}
		cleanup(node);
		return node;
	}
	
	/**
	 * 			n1               
	 *        /   \
	 *       n2    n3
	 *      / \   / \
	 *     a   b c   d
	 *     if both b and c is not null
	 * 			b               
	 *        /   \
	 *       n2    n3
	 *      /     / \
	 *     a     c   d
	 *      
	 * @param node
	 */
	private void remove2(Node n1) {
		Node n2 = n1.left;
		Node n3 = n1.right;
		Node b = n2.right;
		Node c = n3.left;
		if (b == null) {
			n2.right = n3;
			n3.parent = n2;
			reassignParent(n1, n2);
		}
		else if (c == null) {
			n3.left = n2;
			n2.parent = n3;
			reassignParent(n1, n3);			
		}
		else {
			reassignParent(n1, b);
			Node bl = b.left;
			Node br = b.right;
			if (bl == null) {
				b.left = n2;
				n2.parent = b;
			}
			else {
				Node next = bl;
				Node tmp = next.left;
				while (tmp != null) {
					next = tmp;
					tmp = next.left;
				}
				next.left = n2;
				n2.parent = next.left;
			}
			n2.right = null;

			if (br == null) {
				b.right = n3;
				n3.parent = b;
			}
			else {
				Node next = br;
				Node tmp = next.right;
				while (tmp != null) {
					next = tmp;
					tmp = next.right;
				}
				next.right = n3;
				n3.parent = next.right;
			}
		}
	}
	
	/**
	 * Both lkef and right is null
	 * @param node
	 */
	private void remove0(Node node) {
		if (node.parent != null) {
			reassignParent(node, null);
		}
		else {
			this.root = null;
		}
	}
	
	/**
	 * Either left or right is not null
	 * @param node
	 * @return
	 */
	private void remove1(Node node) {
		Node child = node.left;
		if (child == null) {
			child = node.right;
		}
		if (node.parent != null) {
			reassignParent(node, child);
		}
		else {
			this.root = child;
			child.parent = root;
		}
	}

	private void reassignParent(Node node, Node child) {
		Node parent = node.parent;
		if (parent == null) {
			this.root = child;
			return;
		}
		if (parent.left == node) {
			parent.left = child;
		}
		else {
			parent.right = child;
		}
		if (child != null) {
			child.parent = parent;
		}
	}
	
	private void cleanup(Node node) {
		node.parent = null;
		node.left = null;
		node.right = null;
	}

	public int DFSFindTreeHeight() {
		int max = -1;
		Queue<Node> queue = new ArrayDeque<Node>();
		root.height = 1;
		queue.add(root);
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (current.left == null && current.right == null) {
				//int height = this.getHeight(current);
				if (max < current.height) {
					max = current.height;
				}
			}
			else {
				if (current.left != null) {
					current.left.height = current.left.parent.height + 1;
					queue.add(current.left);
				}
				if (current.right != null) {
					current.right.height = current.right.parent.height + 1;
					queue.add(current.right);
				}
			}
		}
		return max;
	}
	/**
	 * Sorted order
	 * 
	 * @param node
	 * @param list
	 */
	public void DFSGetSortedList(Node node, List<Integer> list) {
		if (node == null) {
			return;
		}
		if (node.left != null) {
			DFSGetSortedList(node.left, list);
			list.add(node.key);
		}
		else {
			list.add(node.key);
		}
		DFSGetSortedList(node.right, list);
	}
	
	public void BFSPrintTree(Node node) {
		Queue<Node> q = new ArrayDeque<Node>();
		if (node == null) {
			return;
		}
		int currentLevelHeight = 0;
		node.height = currentLevelHeight;
		q.add(node);
		while (!q.isEmpty()) {
			Node head = q.poll();
			if (head.height > currentLevelHeight) {
				System.out.println();
				currentLevelHeight = head.height;
			}
			System.out.print("	" + head.key);

			if (head.left != null) {
				head.left.height = head.height + 1;
				q.add(head.left);
			}
			if (head.right != null) {
				head.right.height = head.height + 1;
				q.add(head.right);
			}
		}
		System.out.println();
	}

	/**
	 * (4)   n1			  n1
	 *      /  \          / \
	 *     a    n2 	 	 a  new
	 *         / \          /  \
	 *       new  b	   	   d	n2
	 *      /  \               / \
	 *     d    c             c   b
	 * @param newNode
	 * @return 
	 */
	Node rotate4(Node newNode) {
		Node n2 =  newNode.parent;
		Node n1 = n2.parent;
		n1.right = newNode;
		newNode.parent = n1;
		Node c = newNode.right;
		newNode.right = n2;
		n2.parent = newNode;
		n2.left = c;
		if (c != null) {
			c.parent = n2;
		}
		return this.rotate2(n2);
	}
	/**
	 * (3)    n1		 	n1
	 *       / \           /  \
	 *     n2   a       new   a
	 *    /  \         /  \
	 *   b    new     n2   d 
	 *        / \    / \
	 *       c   d  b   c
	 * @param newNode
	 * @return 
	 */
	Node rotate3(Node newNode) {
		Node n2 = newNode.parent;
		Node n1 = n2.parent;
		n1.left = newNode;
		newNode.parent = n1;
		Node c = newNode.left;
		newNode.left = n2;
		n2.parent = newNode;
		n2.right = c;
		if (c != null) {
			c.parent = n2;
		}
		return this.rotate1(n2);
	}
	/**
	 * (2) n1				 n2
	 *    /  \          	/  \
	 *   a    n2	      n1    new
	 *       /  \        / \    /  \
	 *      b    new    a   b  c    d 
	 *           / \
	 *          c   d
	 * Rotate left
	 * @param newNode
	 * @return 
	 */
	Node rotate2(Node newNode) {
		Node n1 = newNode.parent.parent; // save n1
		Node n2 = newNode.parent;
		
		Node b = n2.left; 
		n2.left = n1;
		n1.right = b;
		if (b != null) {
			b.parent = n1;
		}
		
		if (n1.parent != null) {
			if (n1.parent.left == n1) {
				n1.parent.left = n2;
			}
			else {
				n1.parent.right = n2;
			}
		}
		else {
			this.root = n2;
		}
		Node tmp = n1.parent;
		n1.parent = n2;
		n2.parent = tmp;
		return n2;
	}
	/**
	 * (1)        n1           n2
	 *           / \          /  \
	 *          n2  a      new    n1
	 *         / \        /  \   / \
	 *      new   b      d    c b   a  
	 *      / \            
	 *     d   c
	 *  Rotate right.
	 * @param newNode
	 * @return 
	 */
	Node rotate1(Node newNode) {
		Node n1 = newNode.parent.parent;
		Node n2 = newNode.parent;
		
		Node b = n2.right; 
		n2.right = n1;
		n1.left = b;
		if (b != null) {
			b.parent = n1;
		}
		
		if (n1.parent != null) {
			if (n1.parent.left == n1) {
				n1.parent.left = n2;
			}
			else {
				n1.parent.right = n2;
			}
		}
		else {
			this.root = n2;
		}
		Node tmp = n1.parent;
		n1.parent = n2;
		n2.parent = tmp;
		return n2;
	}

	int balance(Node node) {
		return balance(node, 0);
	}
	
	int balance(Node node, int i) {
		if (node == null) {
			return i;
		}
		
		int left = balance(node.left, i + 1);
		int right = balance(node.right, i + 1);
		
		node.height = Math.max(left, right);
		if (Math.abs(left - right) > 1) {
			Node root = rotate(node);
			return balance(root, i);
		}
		else {
			return node.height;			
		}
	}

	private Node rotate(Node node) {
		if (isCase1(node)) {
			return rotate1(node.left.left);
		}
		else if (isCase2(node)) {
			return rotate2(node.right.right);
		}
		else if (isCase3(node)) {
			return rotate3(node.left.right);
		}
		else if (isCase4(node)) {
			return rotate4(node.right.left);
		}
		
		return null;
	}

	/**
	 * (4)   n1			  n1
	 *      /  \          / \
	 *     a    n2 	 	 a  new
	 *         / \          /  \
	 *       new  b	   	   d	n2
	 *      /  \               / \
	 *     d    c             c   b
	 * @param newNode
	 */
	private boolean isCase4(Node n1) {
		// Definition
		// (1) n1.height = x + 2;
		// (2) n2.height = x + 1, a.height = x - 1;
		// (3) new.height = x, b.height < x
		Node a = n1.left;
		Node n2 = n1.right;
		if (n2 == null) {
			return false;
		}
		if (a != null && !((n2.height - a.height) > 1)) {
			return false;
		}
		Node b = n2.right;
		Node neW = n2.left;
		if (neW == null) {
			return false;
		}
		if (b != null && b.height >= neW.height) { // if b.height <= neW.height (4)
			return false;
		}
		return true;
	}
	/**
	 * (3)    n1		 	n1
	 *       / \           /  \
	 *     n2   a       new   a
	 *    /  \         /  \
	 *   b    new     n2   d 
	 *        / \    / \
	 *       c   d  b   c
	 * @param newNode
	 */
	private boolean isCase3(Node n1) {
		// Definition
		// (1) n1.height = x + 2;
		// (2) n2.height = x + 1, a.height = x - 1;
		// (3) new.height = x, b.height < x
		Node a = n1.right;
		Node n2 = n1.left;
		if (n2 == null) {
			return false;
		}
		if (a != null && !((n2.height - a.height) > 1)) {
			return false;
		}
		Node b = n2.left;
		Node neW = n2.right;
		if (neW == null) {
			return false;
		}
		if (b != null && b.height >= neW.height) { // if b.height <= neW.height (3)
			return false;
		}
		return true;
	}
	/**
	 * (2) n1				 n2
	 *    /  \          	/  \
	 *   a    n2	      n1    new
	 *       /  \        / \    /  \
	 *      b    new    a   b  c    d 
	 *           / \
	 *          c   d
	 * Rotate left
	 * @param newNode
	 */
	private boolean isCase2(Node n1) {
		// Definition
		// (1) n1.height = x + 2;
		// (2) n2.height = x + 1, a.height = x - 1;
		// (3) new.height = x, b.height < x
		if (n1.right == null) {
			return false;
		}

		Node a = n1.left;
		Node n2 = n1.right;
		if (a != null && !((n2.height - a.height) > 1)) {
			return false;
		}
		Node b = n2.left;
		Node neW = n2.right;
		if (neW == null) {
			return false;
		}

		if (b != null && b.height > neW.height) { // if b.height <= neW.height (4)
			return false;
		}
		return true;
	}
	/**
	 * (1)        n1           n2
	 *           / \          /  \
	 *          n2  a      new    n1
	 *         / \        /  \   / \
	 *      new   b      d    c b   a  
	 *      / \            
	 *     d   c
	 *  Rotate right.
	 * @param newNode
	 */
	private boolean isCase1(Node n1) {
		// Definition
		// (1) n1.height = x + 2;
		// (2) n2.height = x + 1, a.height = x - 1;
		// (3) new.height = x, b.height < x
		if (n1.left == null) {
			return false;
		}
		
		Node a = n1.right;
		Node n2 = n1.left;
		if (a != null && !((n2.height - a.height) > 1)) {
			return false;
		}
		Node b = n2.right;
		Node neW = n2.left;
		if (neW == null) {
			return false;
		}
		if (b != null && b.height > neW.height) { // if b.height <= neW.height (3)
			return false;
		}
		return true;
	}

	static public void dump(Node node) {
		if (node == null) {
			return;
		}
		if (node.left != null) {
			dump(node.left);
			System.out.print(" " + node.key);
		}
		else {
			System.out.print(" " + node.key);
		}
		dump(node.right);
	}
}
