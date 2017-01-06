package graph.dfs;

import java.util.Date;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import graph.GraphGenerator;
import graph.Vertex;

public class TestBrowser {
	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		Vertex [] vertice = GraphGenerator.autoGen(r.nextInt(100));
		new DFSBrowser(vertice).browser();
		for (Vertex v : vertice) {
			if (!v.isVisited()) {
				System.out.println("Not visited: [" + v.getId() + "]");
			}
			Assert.assertEquals(v.isVisited(), true);;
		}
	}
}
