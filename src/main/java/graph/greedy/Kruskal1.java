package graph.greedy;

import java.util.Comparator;
import java.util.List;

import graph.greedy.Union.Edge;
import graph.greedy.Union.Node;
import sort.heap.Heap;

public class Kruskal1 {
	final Node [] graph;
	final Heap<Edge> heap;
	public Kruskal1(Node [] graph) {
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
	
	public List<Edge> mst() {
		// init
		buildEdgeHeap();
		System.out.println("--------- MST ----------");
		Union union = new Union();
		do {
			Edge edge = this.heap.extract();
			if (edge == null) {
				break;
			}
			union.union(edge);
		} while (true);
		
		return union.getEdges();
	}

	public List<Edge> mstWithUnionCompress() {
		// init
		buildEdgeHeap();
		System.out.println("--------- MST ----------");
		Union union = new Union();
		do {
			Edge edge = this.heap.extract();
			if (edge == null) {
				break;
			}
			union.unionWithCompress(edge);
		} while (true);
		
		return union.getEdges();
	}
	
	private void buildEdgeHeap() {
		for (Node node: this.graph) {
			node.getEdges().stream().forEach((edge) -> this.heap.insert(edge));
		}
	}
}
