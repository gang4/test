package dynamic.programming;

import java.util.Date;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import graph.GraphGenerator;

public class TestWIS {
	
	private void testValue(Integer [] graph) {
		System.out.println("Input:");
		WIS.dump(graph);
		WIS s = new WIS(graph);
		int w = s.valueOfSet();
		System.out.println("Weight: " + w);
		Integer [] a = s.verextInSet();
		System.out.println("Set:");
		WIS.dump(a);
		int w1 = 0;
		for (int i = 0; i < graph.length; i++) {
			if (a[i] < 0) {
				continue;
			}
			w1 += graph[i];
		}
		Assert.assertTrue(w == w1);
	}
	
	@Test
	public void testValue() {
		Random r = new Random(new Date().getTime());
		GraphGenerator g = new GraphGenerator();
		int len = 2;
		while (len < 3) {
			len = g.autoGenUnnsigned(r);
		}
		Integer [] graph = new Integer[len];
		for (int i = 0; i < graph.length; i ++) {
			graph[i] = Math.abs(r.nextInt(len * 4));
 		}
		testValue(graph);
	}
	
	@Test
	public void testValue3() {
		Integer [] graph = {10, 67, 15, 4, 60, 34, 71, 1, 21, 53, 37, 3, 43, 29, 19, 18, 23, 29, 75};
		testValue(graph);
	}
	
	@Test
	public void testValue1() {
		Integer [] graph = {11, 16, 26, 7, 23, 31, 12, 30, 38, 18, 41, 17, 0};
		testValue(graph);
	}
	
	@Test
	public void testValue2() {
		Integer [] graph = {26, 15, 4, 20, 18, 20, 6, 27};
		testValue(graph);
	}
}
