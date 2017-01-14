package dynamic.programming;

import org.junit.Assert;
import org.junit.Test;

import dynamic.programming.BellmanFordShortestPath.Edge;
import dynamic.programming.BellmanFordShortestPath.Vertex;

public class TestBellmanFordShortestPath {
	@Test
	public void test() {
		Vertex [] vertice = new Vertex[8];
		for (int i = 0; i < vertice.length; i ++) {
			vertice[i] = new Vertex(i);
		}
		
		vertice[0].edges.add(new Edge(vertice[0], vertice[1], 5));
		vertice[0].edges.add(new Edge(vertice[0], vertice[4], 9));
		vertice[0].edges.add(new Edge(vertice[0], vertice[7], 8));

		vertice[1].edges.add(new Edge(vertice[1], vertice[2], 12));
		vertice[1].edges.add(new Edge(vertice[1], vertice[3], 15));
		vertice[1].edges.add(new Edge(vertice[1], vertice[7], 4));

		vertice[2].edges.add(new Edge(vertice[2], vertice[3], 3));
		vertice[2].edges.add(new Edge(vertice[2], vertice[6], 11));

		vertice[3].edges.add(new Edge(vertice[3], vertice[6], 9));
		
		vertice[4].edges.add(new Edge(vertice[4], vertice[5], 4));
		vertice[4].edges.add(new Edge(vertice[4], vertice[6], 20));
		vertice[4].edges.add(new Edge(vertice[4], vertice[7], 5));
		
		vertice[5].edges.add(new Edge(vertice[5], vertice[2], 1));
		vertice[5].edges.add(new Edge(vertice[5], vertice[6], 13));

		vertice[7].edges.add(new Edge(vertice[7], vertice[5], 6));
		vertice[7].edges.add(new Edge(vertice[7], vertice[2], 7));

		BellmanFordShortestPath shortest = new BellmanFordShortestPath(vertice);
		shortest.buildPass();
		int [] result = {0, 5, 14, 20, 9, 13, 26, 8};
		for (int i = 0; i < result.length; i ++) {
			Assert.assertTrue(result[i] == shortest.getWeight()[i]);
		}
		Assert.assertTrue(!shortest.hasNegtiveCycle());
	}
}
