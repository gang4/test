package graph.greedy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import graph.GraphGenerator;
import graph.Vertex;
import graph.bfs.MinDistance;
import graph.greedy.Dijkstra.Node;
import graph.greedy.Dijkstra.Node.Edge;
import graph.greedy.Kruskal.UnionNode;
import graph.greedy.Kruskal.UnionEdge;

public class TestMSTKrushal {
	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		Vertex [] vertice;
		int len = new GraphGenerator().autoGenUnnsigned(r);
		vertice = GraphGenerator.autoGen(len);
		vertice = GraphGenerator.removeDup(vertice);
		int index = Math.abs(r.nextInt(len));
		// get the most node connected
		new MinDistance(vertice[index], new Vertex(Integer.MAX_VALUE)).search();
		// Connect rest of nodes that are not connected
		List<Vertex> localTree = new ArrayList<>();
		for (Vertex v: vertice) {
			if (!v.isVisited()) {
				localTree.add(v);
			}
		}
		vertice[index].getConnected().addAll(localTree);
		
		Node [] nodes = TestDijkstra.toNode(vertice, r);
		TestMSTPrimes1.makeUndirectedGraph(nodes);
		TestMSTPrimes1.dumpNode(nodes);
		Kruskal k = new Kruskal(toUnionNode(nodes));
		List<UnionEdge> edges = k.mst();
		edges.stream().forEach((edge) -> TestMSTPrimes1.total += edge.weight);
		System.out.println("\nTotal Nodes: " + vertice.length + ", mst size: " + edges.size() + ", total weight: " + TestMSTPrimes1.total);
		Assert.assertTrue(edges.size() + 1 == nodes.length);
		verify(edges);
	}
	
	private void verify(List<UnionEdge> edges) {
		TestMSTPrimes1.total = -1;
		edges.stream().forEach(edge -> {
			Assert.assertTrue(edge.weight >= TestMSTPrimes1.total); 
			TestMSTPrimes1.total = edge.weight;
		});
	}

	private UnionNode [] toUnionNode(Node [] nodes) {
		UnionNode [] unions = new UnionNode [nodes.length];
		for (int i = 0; i < nodes.length; i ++) {
			unions[i] = new UnionNode(nodes[i]);
		}
		
		for (int i = 0; i < nodes.length; i ++) {
			List<Edge> edges = nodes[i].getEdges();
			for (Edge e: edges) {
				UnionEdge uEdge = new UnionEdge(unions[e.getStartPoint()], unions[e.getEndPoint()], e.getWeight());
				unions[i].edges.add(uEdge);
			}
		}
		return unions;
	}
}
