package graph.binary.tree;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import graph.binary.tree.Balanced.Node;

public class TestBinaryTree {
	@Test
	public void test10() {
		Balanced bst = new Balanced();
		bst.insert(new Balanced.Node(3));
		bst.insert(new Balanced.Node(2));
		bst.insert(new Balanced.Node(1));
		bst.balance(bst.getRoot(), 0);
		validate(bst.getRoot());
	}
	
	@Test
	public void test11() {
		Balanced bst = new Balanced();
		bst.insert(new Balanced.Node(5));
		bst.insert(new Balanced.Node(3));
		bst.insert(new Balanced.Node(2));
		bst.insert(new Balanced.Node(4));
		bst.insert(new Balanced.Node(5));
		bst.insert(new Balanced.Node(6));
		bst.insert(new Balanced.Node(1));
		bst.balance(bst.getRoot(), 0);
		Node root = bst.getRoot();
		Assert.assertTrue(root.key == 3);
		Assert.assertTrue(root.left.key == 2);
		Assert.assertTrue(root.left.right == null);
		Assert.assertTrue(root.left.left.key == 1);
		Assert.assertTrue(root.right.key == 5);
		Assert.assertTrue(root.right.right.key == 6);
		Assert.assertTrue(root.right.left.key == 4);
	}
	
	@Test
	public void test12() {
		Balanced bst = new Balanced();
		bst.insert(new Balanced.Node(6));
		bst.insert(new Balanced.Node(4));
		bst.insert(new Balanced.Node(2));
		bst.insert(new Balanced.Node(1));
		bst.insert(new Balanced.Node(5));
		bst.insert(new Balanced.Node(3));
		bst.insert(new Balanced.Node(7));
		bst.balance(bst.getRoot(), 0);
		Node root = bst.getRoot();
		Assert.assertTrue(root.key == 4);
		Assert.assertTrue(root.left.key == 2);
		Assert.assertTrue(root.left.right.key == 3);
		Assert.assertTrue(root.left.left.key == 1);
		Assert.assertTrue(root.right.key == 6);
		Assert.assertTrue(root.right.right.key == 7);
		Assert.assertTrue(root.right.left.key == 5);
		Assert.assertTrue(root.right.left.parent.key == 6);
	}
	
	@Test
	public void test20() {
		Balanced bst = new Balanced();
		bst.insert(new Balanced.Node(1));
		bst.insert(new Balanced.Node(2));
		bst.insert(new Balanced.Node(3));
		bst.balance(bst.getRoot(), 0);
		validate(bst.getRoot());
	}
	
	@Test
	public void test21() {
		Balanced bst = new Balanced();
		bst.insert(new Balanced.Node(2));
		bst.insert(new Balanced.Node(4));
		bst.insert(new Balanced.Node(5));
		bst.insert(new Balanced.Node(6));
		bst.insert(new Balanced.Node(1));
		bst.insert(new Balanced.Node(3));
		bst.balance(bst.getRoot(), 0);
		Node root = bst.getRoot();
		Assert.assertTrue(root.key == 4);
		Assert.assertTrue(root.left.key == 2);
		Assert.assertTrue(root.left.left.key == 1);
		Assert.assertTrue(root.left.right.key == 3);
		Assert.assertTrue(root.right.key == 5);
		Assert.assertTrue(root.right.left == null);
		Assert.assertTrue(root.right.right.key == 6);
	}
	
	@Test
	public void test22() {
		Balanced bst = new Balanced();
		bst.insert(new Balanced.Node(2));
		bst.insert(new Balanced.Node(4));
		bst.insert(new Balanced.Node(6));
		bst.insert(new Balanced.Node(7));
		bst.insert(new Balanced.Node(1));
		bst.insert(new Balanced.Node(3));
		bst.insert(new Balanced.Node(5));
		bst.balance(bst.getRoot(), 0);
		Node root = bst.getRoot();
		Assert.assertTrue(root.key == 4);
		Assert.assertTrue(root.left.key == 2);
		Assert.assertTrue(root.left.left.key == 1);
		Assert.assertTrue(root.left.right.key == 3);
		Assert.assertTrue(root.left.right.parent.key == 2);
		Assert.assertTrue(root.right.key == 6);
		Assert.assertTrue(root.right.left.key == 5);
		Assert.assertTrue(root.right.right.key == 7);
	}
	
	@Test
	public void test30() {
		Balanced bst = new Balanced();
		bst.insert(new Balanced.Node(3));
		bst.insert(new Balanced.Node(1));
		bst.insert(new Balanced.Node(2));
		bst.balance(bst.getRoot(), 0);
		validate(bst.getRoot());
	}

	@Test
	public void test31() {
		Balanced bst = new Balanced();
		bst.insert(new Balanced.Node(5));
		bst.insert(new Balanced.Node(2));
		bst.insert(new Balanced.Node(3));
		bst.insert(new Balanced.Node(4));
		bst.insert(new Balanced.Node(6));
		bst.insert(new Balanced.Node(1));
		bst.balance(bst.getRoot(), 0);
		Node root = bst.getRoot();
		Assert.assertTrue(root.key == 3);
		Assert.assertTrue(root.left.key == 2);
		Assert.assertTrue(root.left.left.key == 1);
		Assert.assertTrue(root.left.right == null);
		Assert.assertTrue(root.right.key == 5);
		Assert.assertTrue(root.right.left.key == 4);
		Assert.assertTrue(root.right.right.key == 6);
	}
	
	@Test
	public void test32() {
		Balanced bst = new Balanced();
		bst.insert(new Balanced.Node(7));
		bst.insert(new Balanced.Node(3));
		bst.insert(new Balanced.Node(5));
		bst.insert(new Balanced.Node(6));
		bst.insert(new Balanced.Node(1));
		bst.insert(new Balanced.Node(4));
		bst.insert(new Balanced.Node(8));
		bst.balance(bst.getRoot(), 0);
		Node root = bst.getRoot();
		Assert.assertTrue(root.key == 5);
		Assert.assertTrue(root.left.key == 3);
		Assert.assertTrue(root.left.parent.key == 5);
		Assert.assertTrue(root.left.left.key == 1);
		Assert.assertTrue(root.left.right.key == 4);
		Assert.assertTrue(root.left.right.parent.key == 3);
		Assert.assertTrue(root.right.key == 7);
		Assert.assertTrue(root.right.parent.key == 5);
		Assert.assertTrue(root.right.left.key == 6);
		Assert.assertTrue(root.right.left.parent.key == 7);
		Assert.assertTrue(root.right.right.key == 8);
	}

	@Test
	public void test40() {
		Balanced bst = new Balanced();
		bst.insert(new Balanced.Node(1));
		bst.insert(new Balanced.Node(3));
		bst.insert(new Balanced.Node(2));
		bst.balance(bst.getRoot(), 0);
		validate(bst.getRoot());
	}
	
	@Test
	public void test41() {
		Balanced bst = new Balanced();
		bst.insert(new Balanced.Node(2));
		bst.insert(new Balanced.Node(5));
		bst.insert(new Balanced.Node(6));
		bst.insert(new Balanced.Node(4));
		bst.insert(new Balanced.Node(3));
		bst.insert(new Balanced.Node(1));
		bst.balance(bst.getRoot(), 0);
		Node root = bst.getRoot();
		Assert.assertTrue(root.key == 4);
		Assert.assertTrue(root.left.key == 2);
		Assert.assertTrue(root.left.left.key == 1);
		Assert.assertTrue(root.left.right.key == 3);
		Assert.assertTrue(root.right.key == 5);
		Assert.assertTrue(root.right.left == null);
		Assert.assertTrue(root.right.right.key == 6);
	}
	
	@Test
	public void test42() {
		Balanced bst = new Balanced();
		bst.insert(new Balanced.Node(2));
		bst.insert(new Balanced.Node(6));
		bst.insert(new Balanced.Node(7));
		bst.insert(new Balanced.Node(4));
		bst.insert(new Balanced.Node(5));
		bst.insert(new Balanced.Node(3));
		bst.insert(new Balanced.Node(1));
		bst.balance(bst.getRoot(), 0);
		Node root = bst.getRoot();
		Assert.assertTrue(root.key == 4);
		Assert.assertTrue(root.left.key == 2);
		Assert.assertTrue(root.left.parent.key == 4);
		Assert.assertTrue(root.left.left.key == 1);
		Assert.assertTrue(root.left.right.key == 3);
		Assert.assertTrue(root.left.right.parent.key == 2);
		Assert.assertTrue(root.right.key == 6);
		Assert.assertTrue(root.right.parent.key == 4);
		Assert.assertTrue(root.right.left.key == 5);
		Assert.assertTrue(root.right.left.parent.key == 6);
		Assert.assertTrue(root.right.right.key == 7);
	}
	private void validate(Node root) {
		Assert.assertTrue(root.key == 2);
		Assert.assertTrue(root.parent == null);
		Assert.assertTrue(root.left.key == 1);
		Assert.assertTrue(root.right.key == 3);
		Node node = root.left;
		Assert.assertTrue(node.left == null);
		Assert.assertTrue(node.right == null);
		node = root.right;
		Assert.assertTrue(node.left == null);
		Assert.assertTrue(node.right == null);
	}
	
	@Test
	public void testRemove10() {
		int [] array = {3,2,1};
		Node [] nodes = generateNodesByInts(array);
		Node node = treeBuilder(nodes);
		Balanced tree = new Balanced();
		tree.setRoot(node);

		tree.remove(1);
		
		Assert.assertTrue(node.key == 3);
		Assert.assertTrue(node.left.key == 2);
		Assert.assertTrue(node.left.parent.key == 3);
		Assert.assertTrue(node.right == null);
		Assert.assertTrue(node.left.left == null);
		Assert.assertTrue(node.left.right == null);		
	}
	
	@Test
	public void testRemove11() {
		int [] array = {3,2,1};
		Node [] nodes = generateNodesByInts(array);
		Node node = treeBuilder(nodes);
		Balanced tree = new Balanced();
		tree.setRoot(node);

		tree.remove(2);
		
		Assert.assertTrue(node.key == 3);
		Assert.assertTrue(node.left.key == 1);
		Assert.assertTrue(node.left.parent.key == 3);
		Assert.assertTrue(node.right == null);
		Assert.assertTrue(node.left.left == null);
		Assert.assertTrue(node.left.right == null);		
	}
	
	@Test
	public void testRemove12() {
		int [] array = {3,2,1};
		Node [] nodes = generateNodesByInts(array);
		Node node = treeBuilder(nodes);
		Balanced tree = new Balanced();
		tree.setRoot(node);

		tree.remove(3);
		node = tree.getRoot();
		
		Assert.assertTrue(node.key == 2);
		Assert.assertTrue(node.left.key == 1);
		Assert.assertTrue(node.left.parent.key == 2);
		Assert.assertTrue(node.right == null);
		Assert.assertTrue(node.left.left == null);
		Assert.assertTrue(node.left.right == null);		
	}

	@Test
	public void testRemove40() {
		int [] array = {1,2,3};
		Node [] nodes = generateNodesByInts(array);
		Node node = treeBuilder(nodes);
		Balanced tree = new Balanced();
		tree.setRoot(node);

		tree.remove(3);
		
		Assert.assertTrue(node.key == 1);
		Assert.assertTrue(node.right.key == 2);
		Assert.assertTrue(node.right.parent.key == 1);
		Assert.assertTrue(node.left == null);
		Assert.assertTrue(node.right.left == null);
		Assert.assertTrue(node.right.right == null);		
	}
	
	@Test
	public void testRemove41() {
		int [] array = {1,2,3};
		Node [] nodes = generateNodesByInts(array);
		Node node = treeBuilder(nodes);
		Balanced tree = new Balanced();
		tree.setRoot(node);

		tree.remove(2);
		
		Assert.assertTrue(node.key == 1);
		Assert.assertTrue(node.right.key == 3);
		Assert.assertTrue(node.right.parent.key == 1);
		Assert.assertTrue(node.left == null);
		Assert.assertTrue(node.right.left == null);
		Assert.assertTrue(node.right.right == null);		
	}
	
	@Test
	public void testRemove42() {
		int [] array = {1,2,3};
		Node [] nodes = generateNodesByInts(array);
		Node node = treeBuilder(nodes);
		Balanced tree = new Balanced();
		tree.setRoot(node);

		tree.remove(1);
		node = tree.getRoot();
		
		Assert.assertTrue(node.key == 2);
		Assert.assertTrue(node.right.key == 3);
		Assert.assertTrue(node.right.parent.key == 2);
		Assert.assertTrue(node.left == null);
		Assert.assertTrue(node.right.left == null);
		Assert.assertTrue(node.right.right == null);		
	}
	
	@Test
	public void testRemoveBothNotNull1() {
		int [] array = {3,2,1,5,4,6};
		Node [] nodes = generateNodesByInts(array);
		Node node = treeBuilder(nodes);
		Balanced tree = new Balanced();
		tree.setRoot(node);

		tree.remove(3);
		node = tree.getRoot();
		
		Assert.assertTrue(node.key == 2);
		Assert.assertTrue(node.left.key == 1);
		Assert.assertTrue(node.left.parent.key == 2);
		Assert.assertTrue(node.right.key == 5);
		Assert.assertTrue(node.right.parent.key == 2);
	}
	
	@Test
	public void testRemoveBothNotNull2() {
		int [] array = {4,2,3,1,5,6};
		Node [] nodes = generateNodesByInts(array);
		Node node = treeBuilder(nodes);
		Balanced tree = new Balanced();
		tree.setRoot(node);

		tree.remove(4);
		node = tree.getRoot();
		
		Assert.assertTrue(node.key == 5);
		Assert.assertTrue(node.right.key == 6);
		Assert.assertTrue(node.left.key == 2);
		Assert.assertTrue(node.left.parent.key == 5);
		Assert.assertTrue(node.right.parent.key == 5);
	}
	
	@Test
	public void testRemoveBothNotNull3() {
		int [] array = {4,2,3,1,6,5,7};
		Node [] nodes = generateNodesByInts(array);
		Node node = treeBuilder(nodes);
		Balanced tree = new Balanced();
		tree.setRoot(node);

		tree.remove(4);
		node = tree.getRoot();
		
		Assert.assertTrue(node.key == 3);
		Assert.assertTrue(node.left.key == 2);
		Assert.assertTrue(node.left.parent.key == 3);
		Assert.assertTrue(node.left.right == null);
		Assert.assertTrue(node.right.key == 6);
		Assert.assertTrue(node.right.parent.key == 3);
		Assert.assertTrue(node.right.left.key == 5);
		Assert.assertTrue(node.right.right.key == 7);
	}
	
	@Test
	public void testRemoveBothNotNull4() {
		int [] tmp = {15, 18, 0, 10, 5, 17, 17, 21, 22, 14, 11, 5,17, 6, 14, 11, 10, 12, 11, 12, 22, 5, 3, 3, 0, 26, 21};
		Node [] array = generateNodesByInts(tmp);
		Node node = treeBuilder(array);
		Balanced tree = new Balanced();
		tree.setRoot(node);
		tree.balance(node);
		List<Integer> listB = new ArrayList<>();
		new Balanced().DFSGetSortedList(tree.getRoot(), listB);
		int len = 3;
		tree.remove(array[len].key);
		System.out.println("After removing");
		tree.BFSPrintTree(node);

		List<Integer> listA = new ArrayList<>();
		new Balanced().DFSGetSortedList(tree.getRoot(), listA);
		System.out.println("Sorted:");
		for (int i = 0; i < listA.size(); i ++) {
			System.out.print(" " + listA.get(i));
		}
		System.out.println("\nkey to delete: [" + len + "]" + array[len].key);
		Assert.assertTrue(listA.size() + 1 == listB.size());
		verify(listA);
	}
	
	@Test
	public void testTree() {
		Node [] node = generateNodes();
		treeBuilder(node);
	}
	
	@Test
	public void testSearch() {
		Balanced bst = new Balanced();
		bst.insert(new Balanced.Node(7));
		bst.insert(new Balanced.Node(3));
		bst.insert(new Balanced.Node(5));
		bst.insert(new Balanced.Node(6));
		bst.insert(new Balanced.Node(1));
		bst.insert(new Balanced.Node(4));
		bst.insert(new Balanced.Node(8));
		bst.balance(bst.getRoot(), 0);
		Node root = bst.getRoot();
		Assert.assertTrue(root.key == 5);
		Node node = bst.searchNode(8);
		Assert.assertTrue(node.key == 8);
		node = bst.searchNode(9);
		Assert.assertTrue(node == null);
	}
	
	@Test
	public void testBalance() {
		Node [] array = generateNodes();
		Node node = treeBuilder(array);
		Balanced tree = new Balanced();
		tree.setRoot(node);
		int treeHeight = tree.DFSFindTreeHeight();
		System.out.println("Before tree height: " + treeHeight);
		tree.BFSPrintTree(node);
		int treeHeight1 = tree.balance(node);
		treeHeight = tree.DFSFindTreeHeight();
		Assert.assertTrue(treeHeight1 == treeHeight);
		System.out.println("After tree height: " + treeHeight1);
		tree.BFSPrintTree(tree.getRoot());
		double h = Math.log(array.length + 1) / Math.log(2);
		System.out.printf("perfect height: %.02f", h);
	}
	
	@Test
	public void testRemoveNode() {
		for (int j = 0; j < 64; j ++) {
			Node [] array = generateNodes();
			int [] tmp = {15, 18, 0, 10, 5, 17, 17, 21, 22, 14, 11, 5,17, 6, 14, 11, 10, 12, 11, 12, 22, 5, 3, 3, 0, 26, 21};
			array = generateNodesByInts(tmp);
			Node node = treeBuilder(array);
			Balanced tree = new Balanced();
			tree.setRoot(node);
			tree.balance(node);
			List<Integer> listB = new ArrayList<>();
			new Balanced().DFSGetSortedList(tree.getRoot(), listB);

			Random r = new Random(new Date().getTime());
			int len = 0;
			while (len < 1) {
				len = Math.abs(r.nextInt(array.length - 1));
			}
			tree.remove(array[len].key);

			List<Integer> listA = new ArrayList<>();
			new Balanced().DFSGetSortedList(tree.getRoot(), listA);
			System.out.println("Sorted:");
			for (int i = 0; i < listA.size(); i ++) {
				System.out.print(" " + listA.get(i));
			}
			System.out.println("\nkey to delete: [" + len + "]" + array[len].key);
			Assert.assertTrue(listA.size() + 1 == listB.size());
			verify(listA);
		}
	}
	
	private Node treeBuilder(Node [] array) {
		Balanced bst = new Balanced();
		for (int i = 0; i < array.length; i ++) {
			bst.insert(array[i]);
		}
		List<Integer> list = new ArrayList<>();
		System.out.println("Sorted:");
		new Balanced().DFSGetSortedList(bst.getRoot(), list);
		for (int i = 0; i < array.length; i ++) {
			System.out.print(" " + list.get(i));
		}
		System.out.println("");
		verify(list);
		return bst.getRoot();
	}

	private void verify(List<Integer> list) {
		for (int i = 1; i < list.size(); i ++) {
			if (list.get(i - 1) > list.get(i)) {
				Assert.assertTrue(false);
			}
		}
	}
	
	private Node[] generateNodesByInts(int [] ins) {
		System.out.println("Input:");
		Node [] array = new Node[ins.length];
		for (int i = 0; i < array.length; i ++) {
			array[i] = new Balanced.Node(ins[i]);
			System.out.print(" " + array[i].key);
		}
		System.out.println();
		return array;
	}
	
	private Node[] generateNodes() {
		Random r = new Random(new Date().getTime());
		int len = generatePositiveNumber(r);
		Balanced.Node [] array = new Balanced.Node[len];
		System.out.println("Input: " + len);
		for (int i = 0; i < array.length; i ++) {
			array[i] = new Balanced.Node(Math.abs(r.nextInt(len)));
			System.out.print(" " + array[i].key);
		}
		System.out.println();
		return array;
	}

	private int generatePositiveNumber(Random r) {
		int len = 0;
		while (len < 5) {
			len = Math.abs(r.nextInt(32));
		}
		return len;
	}
	
	
}
