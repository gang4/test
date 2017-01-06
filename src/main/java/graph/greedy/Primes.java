package graph.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import graph.greedy.Dijkstra.Node;
import graph.greedy.Dijkstra.Node.Edge;
import sort.heap.Heap;

public class Primes {
	final Node [] graph;
	final Heap<Edge> heap;
	public Primes(Node [] graph) {
		this.graph = graph;
		this.heap = new Heap<Edge>(Edge.class, new Comparator<Edge>() {
			@Override
			public int compare(Edge arg0, Edge arg1) {
				if (arg0.getWeight() > arg1.getWeight()) {
					return 1;
				}
				else if (arg0.getWeight() < arg1.getWeight()) {
					return -1;
				}
				else {
					return 0;
				}
			}});
	}
	
	public List<Edge> mst() {
		List<Edge> mst = new ArrayList<>();
		// init
		System.out.println("--------- MST ----------");
		Node node = this.graph[0];
		for (; node != null; ) {
			node.setVisited(true);
			insertAll(node);
			Edge edge;
			do {
				edge = heap.extract();
			} while (edge != null && this.graph[edge.getEndPoint()].isVisited());
			if (edge == null) {
				break;
			}
			this.graph[edge.getEndPoint()].setVisited(true);
			
			mst.add(edge);
			System.out.println("From [" + edge.getStartPoint() + "] to [" + edge.getEndPoint() + "]" + ", weight: " + edge.getWeight());
			node = this.graph[edge.getEndPoint()];
		}
		
		return mst;
	}

	private void insertAll(Node node) {
		for (Edge edge : node.getEdges()) {
			if (this.graph[edge.getStartPoint()].isVisited() && this.graph[edge.getEndPoint()].isVisited()) {
				continue;
			}
			this.heap.insert(edge);
		}
	}
}
