package graph.greedy;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import graph.GraphGenerator;
import graph.Vertex;
import graph.greedy.Dijkstra;
import graph.greedy.Dijkstra.Node;
import graph.greedy.Dijkstra.Node.Edge;

/**
 * *************************** NOT VERIFIED ********************
 * @author yyu
 *
 */
public class TestDijkstra {
	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		int len = new GraphGenerator().autoGenUnnsigned(r);
		Vertex [] vertice = GraphGenerator.autoGen(len);
		vertice = GraphGenerator.removeDup(vertice);
		Node [] nodes = toNode(vertice, r);
		int start = Math.abs(r.nextInt(len));
		int end = start;
		while (start == end ) {
			end = Math.abs(r.nextInt(len));
		}
		Dijkstra d = new Dijkstra(nodes[start], nodes[end], nodes);
		List<Edge> idxs = d.shortestPath();
		System.out.println("Path from : " + vertice[start].getId() + " to : " + vertice[end].getId());
		for (int j = 0; j < idxs.size(); j ++) {
			System.out.print("[" + idxs.get(j).startPoint + "] --> [" + idxs.get(j).endPoint + "] ");
		}
		System.out.println("\n");

		List<Edge> linked = new ArrayList<>();
		for (Edge node: idxs) {
			Iterator<Edge> it = linked.iterator();
			boolean removed = false;
			while (it.hasNext()) {
				Edge next = it.next();
				if (removed || next.startPoint == node.startPoint) {
					removed = true;
					it.remove();
				}
			}
			linked.add(node);
		}
		System.out.println("Path from : " + vertice[start].getId() + " to : " + vertice[end].getId());
		if (linked.size() > 0) {
			System.out.print("[" + linked.get(0).startPoint + "]");
			int distance = 0;
			for (int j = 0; j < linked.size(); j ++) {
				System.out.print(" --> [" + linked.get(j).endPoint + "] ");
				distance += linked.get(j).weight;
			}
			System.out.println("Distance: " + distance);
		}
		System.out.println("\n");
	} 

	static public Node[] toNode(Vertex[] vertice, Random r) {
		Node[] nodes = new Node[vertice.length];
		System.out.println("--------------------- Graph -------------------");
		for (int i = 0; i < nodes.length; i ++) {
			nodes[i] = new Node(i);
			System.out.println("[" + i + "]");
			System.out.print("Connect to: ");
			List<Edge> edges = nodes[i].getEdges();
			for (Vertex vertex: vertice[i].getConnected()) {
				Edge linked = new Edge();
				linked.endPoint = vertex.getId();
				linked.startPoint = i;
				linked.weight = Math.abs(r.nextInt(100));
				edges.add(linked);
				System.out.print(" [" + linked.endPoint + "]." + linked.weight);
			}
			System.out.println();
		}
		return nodes;
	}
}
