package graph.dfs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import graph.GraphGenerator;
import graph.Vertex;

public class TestSCC {
	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		int len = new GraphGenerator().autoGenUnnsigned(r);
		Vertex [] vertice = GraphGenerator.autoGen(len);
		for (Vertex v : vertice) {
			// get rid of cycle
			ListIterator<Vertex> it = v.getConnected().listIterator();
			while (it.hasNext()) {
				Vertex s = it.next();
				if (s.getId() == v.getId()) {
					it.remove();
				}
			}
			// Get rid of duplications
			len = v.getConnected().size();
			for (int i = 0; i < len; i ++) {
				Vertex current = v.getConnected().get(i);
				ListIterator<Vertex> it1 = v.getConnected().listIterator(i + 1);
				while (it1.hasNext()) {
					Vertex s = it1.next();
					if (s.getId() == current.getId()) {
						it1.remove();
						len --;
					}
				}
			}
		}
		System.out.println(" ----------------------------------------");
		GraphGenerator.dump(vertice);
		List<Vertex> leadings = new SCC(vertice).search();
		System.out.println("leadings:");
		for (Vertex v : leadings) {
			System.out.print("[" + v.getId() + "]");
		}
		System.out.println("\ndone!");
		verify(leadings, vertice);
	}

	private void verify(List<Vertex> leadings, Vertex[] vertice) {
		// (1) for each leading vertex, we use DFS to browser and mark
		//     node visited.
		// (2) By browser late leading vertex in list, it should find visited 
		//     vertex that is not in earlier SCC.
		// (3) After all, all vertices have to be marked visited.
		
		// clean up
		for (Vertex v : vertice) {
			v.setVisited(false);
		}
		// Verify (1), (2)
		List<Vertex> list = new ArrayList<>();
		for (Vertex leader : leadings) {
			visit(leader, list);
		}
		// Verify (3) 
		for (Vertex v : vertice) {
			Assert.assertTrue(v.isVisited());
		}
	}
	
	public void visit(Vertex vertex, List<Vertex> list) {
		if (!list.contains(vertex)) {
			list.add(vertex);
		}
		vertex.setVisited(true);
		for (Vertex v : vertex.getConnected()) {
			if (v.isVisited()) {
				Assert.assertTrue(list.contains(v));
				continue;
			}
			visit(v, list);
		}
	}

}
