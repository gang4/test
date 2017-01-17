package dynamic.programming;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import dynamic.programming.BellmanFordShortestPath.Edge;
import dynamic.programming.BellmanFordShortestPath.Vertex;

public class BellmanFordShortestPath2D {
	final Vertex[] vertices;
	int [][] weight = null;
	boolean negtive = false;
	Queue<Edge> frontEdges = new ArrayDeque<>();
	
	public BellmanFordShortestPath2D(Vertex[] vertices) {
		this.vertices = vertices;
	}

	private void init() {
		// Store the computed value, initialize it.
		// Weight[0] = 0, source vertex to itself, there is no distance.
		this.weight = new int[vertices.length][vertices.length];
		for (int i = 0; i < weight.length; i ++) {
			for (int j = 0; j < weight.length; j ++) {
				if (i == j) {
					weight[i][j] = 0;
				}
				else {
					weight[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		frontEdges.clear(); 
		for (int i = 0; i < vertices.length; i ++) {
			Arrays.asList(vertices[i].edges).stream().forEach(edge -> frontEdges.addAll(edge));
		}
	}

	/**
	 * 	Initialize D as the given cost matrix
	 * 	For k = 1, . . . , n;
	 * 		For all edge from i to j:
	 * 			dij := min(dij , dik + dkj)
	 * 	If dij + dji < 0 for some i and j, then the graph has a
	 * 	negative weight cycle
	 * 
	 * For each k, we can think it as number of hops from any
	 * vertex to any other vertex if there is a path.
	 * (1) 	We have to cache every for optimal value of dij, from vertex i to 
	 *  	vertex j that use k hops.
	 * (2) 	We need to know the all edge for next hop from exiting all
	 * 		ended edges.
	 *  
	 * @param k -- the number of edge from vertex i to vertex can use.
	 */
	/**
	 * 
	 * @param k
	 */
	public void build2DTable() {	
		if (this.weight == null) {
			init();
		}

		// Loop through all edges from zero vertex to vertices.length - 1 vertex.
		for (int k = 1; k < vertices.length; k ++) {
			int length = frontEdges.size();
			for (int i = 0; i < length; i ++) {
				Edge edge = frontEdges.poll();
				dumpEdge("current", edge);
				if (this.weight[edge.start.id][edge.end.id] == Integer.MAX_VALUE) {
					this.weight[edge.start.id][edge.end.id] = edge.weight;
				}
				int current = this.weight[edge.start.id][edge.end.id];
				for (Edge next : vertices[edge.end.id].edges) {
					dumpEdge("next", next);
					if (current + next.weight < this.weight[next.start.id][next.end.id]) {
						this.weight[next.start.id][next.end.id] = current + next.weight;
					}
					frontEdges.add(next);
				}
			}
		}		
	}
	
	private void dumpEdge(String msg, Edge e) {
		System.out.println(msg + " " + e.weight + " : " + e.start.id + " -> " + e.end.id);
	}
}
