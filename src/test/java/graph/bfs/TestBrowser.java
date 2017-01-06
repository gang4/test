package graph.bfs;

import java.util.Date;
import java.util.Random;

import org.junit.Test;

import graph.GraphGenerator;
import graph.Vertex;

public class TestBrowser {
	@Test
	public void test() throws Exception {
		Random r = new Random(new Date().getTime());
		int len = 0;
		while (len == 0) {
			len = Math.abs(r.nextInt(100));
		}
		Vertex [] vertice = GraphGenerator.autoGen(len);
		Vertex end = new Vertex(vertice.length);
		int count = 0;
		for (int j = 0; j < vertice.length; j ++) {
			if (vertice[j].isVisited()) {
				count ++;
				continue;
			}
			MinDistance m = new MinDistance(vertice[j], end);
			m.search();
		}
		System.out.println("Visited: " + count + " of " + len);
		for (int j = 0; j < vertice.length; j ++) {
			if (!vertice[j].isVisited()) {
				throw new Exception("Not Visited: " + j);
			}
		}
	}
}
