package graph.greedy;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Repeat;

import graph.GraphGenerator;
import graph.Vertex;
import graph.bfs.MinDistance;
import graph.greedy.PrimesMST;
import graph.greedy.DijkstraShortestDistance.Node;
import graph.greedy.DijkstraShortestDistance.Node.Edge;

/**
 * *************************** NOT VERIFIED ********************
 * @author yyu
 *
 */
public class TestMSTPrimes1 {
	static int total = 0;
	
	@Test
	@Repeat(value = 10)
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
		makeUndirectedGraph(nodes);
		dumpNode(nodes);
		PrimesMST primes = new PrimesMST(nodes);
		List<Edge> edges = primes.mst();
		edges.stream().forEach((edge) -> TestMSTPrimes1.total += edge.getWeight());
		System.out.println("\nTotal Nodes: " + vertice.length + ", mst size: " + edges.size() + ", total weight: " + TestMSTPrimes1.total);
		Assert.assertTrue(edges.size() + 1 == nodes.length);
	}

	static public void dumpNode(Node[] nodes) {
		System.out.println("--------------------- Graph -------------------");
		for (int i = 0; i < nodes.length; i ++) {
			System.out.println("[" + i + "]");
			System.out.print("Connect to: ");
			nodes[i].getEdges().stream().forEach((edge) -> 
				System.out.print(" [" + edge.getEndPoint() + "]." + edge.getWeight())
				);
			System.out.println();
		}
	}
	
	static public void makeUndirectedGraph(Node [] graph) {
		Hashtable<Integer, Node> table = new Hashtable<>();
		for (Node n : graph) {
			table.put(n.getId(), n);
		}
		
		for (Node n : graph) {
			//System.out.println("n : " + n.getId());
			for (Edge edge: n.getEdges()) {
				boolean add = true;
				Node end = table.get(edge.getEndPoint());
				//System.out.println("end : " + end.getId());
				for (Edge desEdge: end.getEdges()) {
					if (desEdge.getEndPoint() != edge.getStartPoint()) {
						continue;
					}
					add = false;
					break;
				}
				if (add) {
					Edge desEdge = new Edge();
					desEdge.setEndPoint(edge.getStartPoint());
					desEdge.setStartPoint(edge.getEndPoint());
					desEdge.setWeight(edge.getWeight());
					end.getEdges().add(desEdge);
				}
			}
		}
	}
}
