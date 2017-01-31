package graph.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import graph.greedy.DijkstraShortestDistance.Node.Edge;
import sort.heap.Heap;

public class DijkstraShortestDistance {
	static public class Node {
		static public class Edge {
			public int getStartPoint() {
				return startPoint;
			}
			public void setStartPoint(int owner) {
				this.startPoint = owner;
			}
			public int getEndPoint() {
				return endPoint;
			}
			public void setEndPoint(int des) {
				this.endPoint = des;
			}
			public int getWeight() {
				return weight;
			}
			public void setWeight(int weight) {
				this.weight = weight;
			}
			int startPoint;
			int endPoint;
			int weight;
		}
		
		public boolean isVisited() {
			return visited;
		}
		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		public int getId() {
			return id;
		}
		public List<Edge> getEdges() {
			return edges;
		}
		public Node(int idx) {
			this.id = idx;
		}
		final int id; 
		boolean visited = false;
		final List<Edge> edges = new ArrayList<>();
	}
	
	final private Node start;
	final private Node des;
	final Node [] graph;
	final Heap<Edge> heap;
	public DijkstraShortestDistance(Node start, Node des, Node[] graph) {
		this.start = start;
		this.des = des;
		this.graph = graph;
		this.heap = new Heap<Edge>(Edge.class, new Comparator<Edge>() {
			@Override
			public int compare(Edge arg0, Edge arg1) {
				if (arg0.weight > arg1.weight) {
					return 1;
				}
				else if (arg0.weight < arg1.weight) {
					return -1;
				}
				else {
					return 0;
				}
			}});
	}

	public List<Edge> shortestPath() {
		List<Edge> list = new ArrayList<>();
		Edge node = new Edge();
		node.endPoint = this.start.getId();
		this.start.visited = true;
		do {
			node = search(this.graph[node.endPoint].getEdges());
			if (node == null) {
				break;
			}
			this.graph[node.endPoint].visited = true;
			list.add(node);
			if (this.graph[node.endPoint].getId() == this.des.getId()) {
				break;
			}
		} while (true);
		
		return list;
	}

	private Edge search(List<Edge> nodes) {
		for (Edge node: nodes) {
			heap.insert(node);
		}
		Edge node;
		do {
			node = heap.extract();
			if (node == null || !graph[node.endPoint].visited) {
				break;
			}
		} while (true);
		return node;
	}

//	public List<Integer> shortestPath() {
//		List<Integer> list = new ArrayList<>();
//		list.add(this.start.getId());
//		Node node = this.start;
//		do {
//			node = search(node.getEdges());
//			if (node == null || node.getId() == this.des.getId()) {
//				if (node != null) {
//					list.add(node.getId());
//				}
//				break;
//			}
//			node.visited = true;
//			list.add(node.getId());
//		} while (true);
//		
//		return list;
//	}
//
//	private Node search(List<LinkedNode> nodes) {
//		for (LinkedNode node: nodes) {
//			heap.insert(node);
//		}
//		LinkedNode node;
//		do {
//			node = heap.extract();
//			if (node == null || !graph[node.des].visited) {
//				break;
//			}
//		} while (true);
//		if (node != null) {
//			return graph[node.des];
//		}
//		else {
//			return null;
//		}
//	}
}
