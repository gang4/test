package graph.greedy;

import java.util.ArrayList;
import java.util.List;

public class Union {
	public static class Node {
		public boolean visited = false; // for testing
		
		final List<Edge> edges = new ArrayList<>();
		Node parent;
		int rank = 0;
		final int id; // This is not necessary

		public Node getParent() {
			return parent;
		}
		public void setParent(Node parent) {
			this.parent = parent;
		}
		public int getRank() {
			return rank;
		}
		public void setRank(int rank) {
			this.rank = rank;
		}
		public int getId() {
			return id;
		}
		public List<Edge> getEdges() {
			return edges;
		}
		public Node(int id) {
			this.parent = this;
			this.id = id;
		}
	}
	
	static public class Edge {
		public int getWeight() {
			return weight;
		}
		public Node getV1() {
			return v1;
		}
		public Node getV2() {
			return v2;
		}
		final int weight;
		final Node v1;
		final Node v2;
		public Edge(Node v1, Node v2, int weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}
	}
	
	final List<Edge> edges = new ArrayList<>();
	public List<Edge> getEdges() {
		return edges;
	}

	public Node find(Node node) {
		if (node == node.parent) {
			return node;
		}
		return find(node.parent);
	}	
	
	public Node findCompress(Node node) {
		Node head = node;
		while (head != head.parent) {
			head = head.parent;
		}
		
		// Rewires all children
		Node tmp = node;
		while (tmp != tmp.parent) {
			Node next = node.parent;
			node.parent = head;
			tmp = next;
		}		
		return head;
	}	
	
	/**
	 * If you union an edge, the union1 will be v1 and union2 will be v2 of the edge
	 * 
	 * @param v1
	 * @param v2
	 */
	public void union(Edge edge) {
		Node head1 = find(edge.getV1());
		Node head2 = find(edge.getV2());
		if (head1 == head2) {
			return;
		}
		if (head1.getRank() < head2.getRank()) {
			head1.parent = head2;
		}
		else if (head1.getRank() == head2.getRank()) {
			head2.parent = head1;	
			head1.rank ++;
		}
		else {
			head2.parent = head1;
		}
		this.edges.add(edge);
	}
	
	/**
	 * If you union an edge, the union1 will be v1 and union2 will be v2 of the edge
	 * 
	 * @param v1
	 * @param v2
	 */
	public void unionWithCompress(Edge edge) {
		Node head1 = findCompress(edge.getV1());
		Node head2 = findCompress(edge.getV2());
		if (head1 == head2) {
			return;
		}
		if (head1.getRank() < head2.getRank()) {
			head1.parent = head2;
		}
		else if (head1.getRank() == head2.getRank()) {
			head2.parent = head1;	
			head1.rank ++;
		}
		else {
			head2.parent = head1;
		}
		this.edges.add(edge);
	}
}
