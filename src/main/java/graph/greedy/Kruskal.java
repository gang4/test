package graph.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import graph.greedy.Dijkstra.Node;
import sort.heap.Heap;

public class Kruskal {
	static public class Union {
		final int leader;
		List<UnionNode> nodes = new ArrayList<>();
		public Union(int nodeId) {
			this.leader = nodeId;
		}
	}
	static public class UnionNode {
		Union union = null;
		final Node node;
		List<UnionEdge> edges = new ArrayList<>();
		public List<UnionEdge> getEdges() {
			return edges;
		}
		public UnionNode(Node node) {
			this.node = node;
		}
	}
	static public class UnionEdge {
		final UnionNode v1;
		final UnionNode v2;
		final int weight;
		public UnionEdge(UnionNode v1, UnionNode v2, int weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}
	}
	
	final UnionNode [] graph;
	final Heap<UnionEdge> heap;
	public Kruskal(UnionNode [] graph) {
		this.graph = graph;
		this.heap = new Heap<UnionEdge>(UnionEdge.class, new Comparator<UnionEdge>() {
			@Override
			public int compare(UnionEdge arg0, UnionEdge arg1) {
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
	
	public List<UnionEdge> mst() {
		List<UnionEdge> mst = new ArrayList<>();
		// init
		buildEdgeHeap();
		System.out.println("--------- MST ----------");
		do {
			UnionEdge edge = this.heap.extract();
			if (edge == null) {
				break;
			}
			if (edge.v1.union == null && edge.v2.union == null) {
				Union u = new Union(edge.v1.node.id);
				edge.v1.union = u;
				edge.v2.union = u;
				u.nodes.add(edge.v1);
				u.nodes.add(edge.v2);
			}
			else if (edge.v1.union == null) {
				edge.v1.union = edge.v2.union;
				edge.v2.union.nodes.add(edge.v1);
			}
			else if (edge.v2.union == null) {
				edge.v2.union = edge.v1.union;
				edge.v1.union.nodes.add(edge.v2);
			}
			else {
				// Check cycle
				if (edge.v2.union == edge.v1.union) {
					continue;
				}
				// merge two union, smaller to bigger.
				if (edge.v1.union.nodes.size() < edge.v2.union.nodes.size()) {
					edge.v2.union.nodes.addAll(edge.v1.union.nodes);
					edge.v1.union.nodes.stream().forEach((node) -> node.union = edge.v2.union);
				}
				else {
					edge.v1.union.nodes.addAll(edge.v2.union.nodes);
					edge.v2.union.nodes.stream().forEach((node) -> node.union = edge.v1.union);	
				}
			}
			
			mst.add(edge);
			System.out.println("From [" + edge.v1.node.getId() + "] to [" + edge.v2.node.getId() + "]" + ", weight: " + edge.weight);
		} while (true);
		
		return mst;
	}

	private void buildEdgeHeap() {
		for (UnionNode node: this.graph) {
			node.getEdges().stream().forEach((edge) -> this.heap.insert(edge));
		}
	}
}
