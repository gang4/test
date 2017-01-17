package dynamic.programming;

import org.junit.Assert;
import org.junit.Test;

import dynamic.programming.BellmanFordShortestPath.Edge;
import dynamic.programming.BellmanFordShortestPath.Vertex;

public class TestBellmanFordShortestPath2D {
	@Test
	public void test() {
		Vertex [] vertice = new Vertex[3];
		for (int i = 0; i < vertice.length; i ++) {
			vertice[i] = new Vertex(i);
		}
		
		vertice[0].edges.add(new Edge(vertice[0], vertice[1], 4));
		vertice[0].edges.add(new Edge(vertice[0], vertice[2], 3));

		vertice[1].edges.add(new Edge(vertice[1], vertice[2], -2));
		BellmanFordShortestPath2D s = new BellmanFordShortestPath2D(vertice);
		s.build2DTable();
		Util.dump(s.weight);
		Assert.assertTrue(s.weight[1][2] == 2);
	}
	
	@Test
	public void test1() {
		Vertex [] vertice = gen();
		BellmanFordShortestPath2D s = new BellmanFordShortestPath2D(vertice);
		s.build2DTable();
		Util.dump(s.weight);
		
		Assert.assertTrue(s.weight[0][1] == 5);
		Assert.assertTrue(s.weight[5][2] == 14);
		Assert.assertTrue(s.weight[2][3] == 17);
		Assert.assertTrue(s.weight[0][4] == 9);
		Assert.assertTrue(s.weight[4][5] == 13);
		Assert.assertTrue(s.weight[2][6] == 25);
		Assert.assertTrue(s.weight[0][7] == 8);
	}

	/**
	 * https://www.cs.cmu.edu/afs/cs/academic/class/15210-s13/www/lectures/lecture13.pdf
	 */
	@Test
	public void test2() {
		Vertex [] vertice = new Vertex[5];
		for (int i = 0; i < vertice.length; i ++) {
			vertice[i] = new Vertex(i);
		}
		
		vertice[0].edges.add(new Edge(vertice[0], vertice[1], 3));
		vertice[0].edges.add(new Edge(vertice[0], vertice[2], 2));

		vertice[1].edges.add(new Edge(vertice[1], vertice[3], 1));

		vertice[2].edges.add(new Edge(vertice[2], vertice[1], -2));
		vertice[2].edges.add(new Edge(vertice[2], vertice[4], 1));

		vertice[3].edges.add(new Edge(vertice[3], vertice[4], 1));
		
		BellmanFordShortestPath2D s = new BellmanFordShortestPath2D(vertice);
		s.build2DTable();
		Util.dump(s.weight);
		Assert.assertTrue(s.weight[3][4] == 2);
	}	

	@Test
	public void test3() {
		Vertex [] vertice = gen();

		BellmanFordShortestPath2D shortest = new BellmanFordShortestPath2D(vertice);
		shortest.build2DTable();
		Util.dump(shortest.weight);
	}

	private Vertex[] gen() {
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
		
		return vertice;
	}
}
