package dynamic.programming;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * https://www.coursera.org/learn/algorithm-design-analysis-2/lecture/x0YZd/single-source-shortest-paths-revisted
 * https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm
 * https://web.stanford.edu/class/cs97si/07-shortest-path-algorithms.pdf
 * http://algs4.cs.princeton.edu/lectures/44DemoBellmanFord.pdf
 */
public class BellmanFordShortestPath {
	static public class Edge {
		final Vertex start;
		final Vertex end;
		final int weight;
		public Edge(Vertex s, Vertex e, int w) {
			this.start = s;
			this.end = e;
			this.weight = w;
		}
	}
	static public class Vertex {
		final int id;
		List<Edge> edges = new ArrayList<>();
		public Vertex(int id) {
			this.id = id;
		}
	}
	
	public Vertex[] getVertices() {
		return vertices;
	}

	public int[] getWeight() {
		return weight;
	}

	public Edge[] getPath() {
		return path;
	}

	public boolean isNegtive() {
		return negtive;
	}

	final Vertex[] vertices;
	int [] weight = null;
	Edge [] path = null;
	boolean negtive = false;
	
	public BellmanFordShortestPath(Vertex[] vertices) {
		this.vertices = vertices;
	}
	
	public void buildPass() {
		if (this.weight == null) {
			init();
		}
		buildPassInternal();
	}
	
	private void init() {
		// Store the computed value, initialize it.
		// Weight[0] = 0, source vertex to itself, there is no distance.
		this.weight = new int[vertices.length];
		for (int i = 1; i < weight.length; i ++) {
			weight[i] = Integer.MAX_VALUE;
		}
		this.path = new Edge[vertices.length];		
	}
	
	/**
	 * 	Initialize D as the given cost matrix
	 * 	For k = 1, . . . , n;
	 * 		For all edge from i to j:
	 * 			dij := min(dij , dik + dkj)
	 * 	If dij + dji < 0 for some i and j, then the graph has a
	 * 	negative weight cycle
	 */
	private void buildPassInternal() {		
		// Loop through all edges from zero vertex to vertices.length - 1 vertex.
		for (int i = 0; i < vertices.length - 1; i ++) {
			List<Edge> edges = vertices[i].edges;
			for (Edge edge: edges) {
				// w := dik + dkj
				int w = edge.weight + weight[edge.start.id];
				// min := min { dij, w }
				// dij is the one cached in this weight. The default
				// is Integer.MAX_VALUE, except the source.
				if (w < weight[edge.end.id]) {
					weight[edge.end.id] = w;
					path[edge.end.id] = edge;
				}
			}
			dump();
		}		
	}
	
	private void dump() {
		System.out.println();
		for (int k = 0; k < path.length; k ++) {
			System.out.print("" + k + " 	" + this.weight[k] + "	");
			if (path[k] != null) {
				System.out.println(" " + path[k].start.id + " -> " + path[k].end.id);
			}
			else {
				System.out.println();
			}
		}		
	}
	public boolean hasNegtiveCycle() {
		// If there is non negative cycle, one more iteration should not change path.
		List<Edge> edges = vertices[vertices.length - 1].edges;
		for (Edge edge: edges) {
			int w = edge.weight + weight[edge.start.id];
			if (w < weight[edge.end.id]) {
				return true;
			}
		}
		return false;
	}
}
