package graph.bfs;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import graph.GraphGenerator;
import graph.Vertex;

public class TestMinDistance {
	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		int len = 0;
		while (len == 0) {
			len = Math.abs(r.nextInt(100));
		}
		Vertex [] vertice = GraphGenerator.autoGen(len);
		int start = Math.abs(r.nextInt(len));
		int end = Math.abs(r.nextInt(len));
		MinDistance m = new MinDistance(vertice[start], vertice[end]);
		List<Vertex> path = m.search();
		System.out.println("Path from : " + vertice[start].getId() + " to : " + vertice[end].getId());
		if (path != null) {
			for (int j = 0; j < path.size(); j ++) {
				System.out.print("[" + path.get(j).getId() + "]");
			}
			System.out.println("\n");
		}
		else {
			System.out.println("No Path");
		}
	}
}
