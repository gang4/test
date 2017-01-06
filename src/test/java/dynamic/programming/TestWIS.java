package dynamic.programming;

import java.util.Date;
import java.util.Random;

import org.junit.Test;

import graph.GraphGenerator;

public class TestWIS {
	@Test
	public void testValue() {
		Random r = new Random(new Date().getTime());
		int len = new GraphGenerator().autoGenUnnsigned(r);
		Integer [] graph = new Integer[len];
		for (int i = 0; i < graph.length; i ++) {
			graph[i] = Math.abs(r.nextInt(len * 4));
 		}
		System.out.println("Input:");
		WIS.dump(graph);
		WIS s = new WIS(graph);
		int w = s.valueOfSet();
		System.out.println("Weight: " + w);
		Integer [] a = s.verextInSet();
		System.out.println("Set:");
		WIS.dump(a);
	}
}
