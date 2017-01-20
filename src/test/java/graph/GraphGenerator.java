package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import graph.Vertex;
import graph.greedy.Union.Edge;
import graph.greedy.Union.Node;

public class GraphGenerator {
	public int autoGenUnnsigned(Random r) {
		return autoGenUnnsigned(r, 64);
	}
	
	public int autoGenUnnsigned(Random r, int limit) {
		int len = 0;
		while (len < 2) {
			len = Math.abs(r.nextInt(limit));
		}
		return len;
	}
	
	public Node[] autoGen(int len, Random r) {
		// Nodes
		Node [] nodes = new Node[len];
		for (int i = 0; i < len; i ++) {
			nodes[i] = new Node(i);
		}
		// Edges
		int subLen = Math.abs(r.nextInt(len * len));
		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < subLen; i ++) {
			int v1 = 0;
			int v2 = 0;
			while (v1 == v2) {
				v1 = Math.abs(r.nextInt(len));
				v2 = Math.abs(r.nextInt(len));
			}
			int weight = Math.abs(r.nextInt(len * 2));
			Edge e = new Edge(nodes[v1], nodes[v2], weight);
			addTo(edges, e);
		}
		edges.stream().forEach(edge -> nodes[edge.getV1().getId()].getEdges().add(edge));
		return nodes;
	}
	
	private void addTo(List<Edge> edges, Edge e) {
		boolean exist = false;
		for (Edge edge: edges) {
			if ((edge.getV1() == e.getV1() && edge.getV2() == e.getV2()) ||
					(edge.getV1() == e.getV2() && edge.getV2() == e.getV1())) {
				exist = true;
			}
		}
		if (!exist) {
			edges.add(e);
		}
	}

	static public void dumpNode(Node [] nodes) {
		System.out.println("---------------------Deleted dup Graph -------------------");
		for (int i = 0; i < nodes.length; i ++) {
			System.out.print("[" + i + "] Connect to:");
			for (Edge e : nodes[i].getEdges()) {
				System.out.print(" [" + e.getV2().getId() + "]." + e.getWeight());
			}
			System.out.println();
		}		
	}
	
	static public void dumpGraph(Node [] nodes) {
		// Build
		for (int i = 0; i < nodes.length; i ++) {
			for (Edge e : nodes[i].getEdges()) {
				List<Edge> edges = nodes[e.getV2().getId()].getEdges();
				boolean exist = false;
				for (Edge v2: edges) {
					if (v2.getV2() == e.getV1()) {
						exist = true;
						break;
					}
				}
				if (!exist) {
					edges.add(new Edge(nodes[e.getV2().getId()], nodes[e.getV1().getId()], e.getWeight()));
				}
			}
		}		

		System.out.println("Graph:");
		for (int i = 0; i < nodes.length; i ++) {
			System.out.print("[" + i + "] Connect to:");
			for (Edge e : nodes[i].getEdges()) {
				System.out.print(" [" + e.getV2().getId() + "]." + e.getWeight());
			}
			System.out.println();
		}		
	}
	
	static public Vertex[] autoGen(int len) {
		Random r = new Random(new Date().getTime());
		Vertex [] vertice = new Vertex [len];
		for (int i = 0; i < len; i ++) {
			vertice[i] = new Vertex(i);
		}
		
		for (int i = 0; i < len; i ++) {
			List<Vertex> list = vertice[i].getConnected();
			int subLen = Math.abs(r.nextInt(len / 2));
			for (int j = 0; j < subLen; j ++) {
				int index = Math.abs(r.nextInt(len));
				list.add(vertice[index]);
			}
		}
		System.out.println("----------------- aoto-gen -------------------");
		dump(vertice);
		return vertice;
	}

	static public Vertex[] removeDup(Vertex[] vertices) {
		for (Vertex vertex: vertices) {
			if (vertex.getConnected() == null || vertex.getConnected().isEmpty()) {
				continue;
			}
			Iterator<Vertex> it = vertex.getConnected().iterator();
			while (it.hasNext()) {
				if (it.next().getId() == vertex.getId()) {
					it.remove();
				}
			}
			if (vertex.getConnected().isEmpty()) {
				continue;
			}
			
			Collections.sort(vertex.getConnected(), new Comparator<Vertex>() {
				@Override
				public int compare(Vertex arg0, Vertex arg1) {
					if (arg0.getId() > arg1.getId()) {
						return 1;
					}
					else if (arg0.getId() < arg1.getId()) {
						return -1;
					}
					else {
						return 0;
					}
				}
			});
			List<Vertex> list = vertex.getConnected();
			List<Vertex> tmp = new ArrayList<>();
			for (int i = 0; i < list.size() - 1; i ++) {
				if (list.get(i).getId() != list.get(i + 1).getId()) {
					tmp.add(list.get(i));
				}
			}
			if (tmp.isEmpty() || tmp.get(tmp.size() - 1).getId() != list.get(list.size() - 1).getId()) {
				tmp.add(list.get(list.size() - 1));
			}
			vertex.setConnected(tmp);
		}
		System.out.println("----------------- clean up -------------------");
		dump(vertices);
		return vertices;
	}

	public static void dump(Vertex[] vertice) {
		for (int i = 0; i < vertice.length; i ++) {
			System.out.println("vertice[" + i + "]");
			System.out.print("connected: ");
			for (int j = 0; j < vertice[i].connected.size(); j ++) {
				System.out.print("[" + vertice[i].connected.get(j).getId() + "]");
			}
			System.out.println("\n");
		}
	}
}
