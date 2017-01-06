package graph.greedy;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

import graph.GraphGenerator;
import graph.greedy.Union.Edge;
import graph.greedy.Union.Node;

public class TestUnionFind {
	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		int len = new GraphGenerator().autoGenUnnsigned(r);
		Node [] nodes = new GraphGenerator().autoGen(len, r);
		makeConnected(nodes, r, len);
		GraphGenerator.dumpNode(nodes);
		
		Union u = new Union();
		for (Node n: nodes) {
			n.getEdges().stream().forEach(edge -> u.union(edge));
		}
		GraphGenerator.dumpGraph(nodes);
		System.out.println("Result:");
		dumpUnion(nodes);
		verify(nodes);
	}
	
	static public void makeConnected(Node[] nodes, Random r, int len) {
		int index = Math.abs(r.nextInt(len));
		// BFS by given an random node to start with
		Queue<Node> q = new ArrayDeque<>();
		q.add(nodes[index]);
		while (!q.isEmpty()) {
			Node n = q.poll();
			n.visited = true;
			for (Edge e : n.getEdges()) {
				if (e.getV2().visited) {
					continue;
				}
				q.add(nodes[e.getV2().getId()]);
			}
		}
		for (Node n : nodes) {
			if (!n.visited) {
				nodes[index].getEdges().add(new Edge(nodes[index], n, Math.abs(r.nextInt(len * 2))));
			}
		}
	}

	private void verify(Node[] nodes) {
		Node last = null;
		for (Node n: nodes) {
			Node head = find(n);
			if (last != null) {
				Assert.assertTrue(last == head);
				last = head;
			}
		}
	}

	private Node find(Node n) {
		if (n == n.parent) {
			return n;
		}
		for (Edge edge : n.getEdges()) {
			if (edge.v2 == n.parent) {
				break;
			}
		}
		//Assert.assertTrue(exist);
		return find(n.parent);
	}

	private void dumpUnion(Node[] nodes) {
		for (Node n: nodes) {
			System.out.print("[" + n.id + "]." + n.rank);
			dumpInternal(n.parent);
			System.out.println();
		}
	}

	private void dumpInternal(Node n) {
		System.out.print(" [" + n.id + "]." + n.rank);
		if (n != n.parent) {
			dumpInternal(n.parent);
		}
	}
}
