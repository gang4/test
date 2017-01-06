package graph.greedy;

import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import graph.GraphGenerator;
import graph.greedy.Union.Edge;
import graph.greedy.Union.Node;

/**
 * @author yyu
 *
 */
public class TestMSTKrushal1 {
	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		int len = new GraphGenerator().autoGenUnnsigned(r);
		Node [] nodes = new GraphGenerator().autoGen(len, r);
		TestUnionFind.makeConnected(nodes, r, len);
		Kruskal1 k = new Kruskal1(nodes);
		List<Edge> edges = k.mst();
		GraphGenerator.dumpGraph(nodes);
		System.out.println("Result:");
		edges.stream().forEach(edge -> System.out.println("[" + edge.getV1().getId() + "] -> [" + edge.getV2().getId() + "]." + edge.getWeight()));
		Assert.assertTrue(edges.size() + 1 == nodes.length);
		verify(edges, nodes);
	}
	
	@Test
	public void testWithCompress() {
		Random r = new Random(new Date().getTime());
		int len = 0;
		while (len < 2) {
			len = Math.abs(r.nextInt(128));
		}
		Node [] nodes = new GraphGenerator().autoGen(len, r);
		TestUnionFind.makeConnected(nodes, r, len);
		Kruskal1 k = new Kruskal1(nodes);
		List<Edge> edges = k.mstWithUnionCompress();
		GraphGenerator.dumpGraph(nodes);
		System.out.println("Result:");
		edges.stream().forEach(edge -> System.out.println("[" + edge.getV1().getId() + "] -> [" + edge.getV2().getId() + "]." + edge.getWeight()));
		Assert.assertTrue(edges.size() + 1 == nodes.length);
		verify(edges, nodes);
	}
	
	private void verify(List<Edge> edges, Node[] nodes) {
		final Hashtable<Integer, Node> table = new Hashtable<>();
		edges.stream().forEach(edge -> {
			if (table.get(edge.getV1().getId()) == null) {
				table.put(edge.getV1().getId(), edge.getV1());
			}
			if (table.get(edge.getV2().getId()) == null) {
				table.put(edge.getV2().getId(), edge.getV1());
			}
			});
		List<Integer> list = Collections.list(table.keys());
		Collections.sort(list);
		int length = list.get(list.size() - 1);
		Assert.assertTrue(length + 1 == nodes.length);
	}
}
