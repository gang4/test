package dynamic.programming;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import graph.GraphGenerator;

/**
 * Not verified
 *
 */
public class TestKnapsack {
	
	public void testNaiveBuild(Integer [] v, Integer [] w, int capacity) {
		Knapsack k = new Knapsack(v, w, capacity);
		int [][] table = k.buildNaive();
		Util.dump(table);
	}
	
	@Test
	public void testNaiveBuild1() {
		Integer [] v = {0,3,2,4,4};
		Integer [] w = {0,4,3,2,3};
		testNaiveBuild(v, w, 6);
	}

	@Test
	public void testNaiveBuild2() {
		Integer [] v = {0,4,2,4,3};
		Integer [] w = {0,2,3,3,4};
		testNaiveBuild(v, w, 13);
	}

	private void testgetOptimalSolution(Integer [] v, Integer [] w, int capacity) {
		Knapsack k = new Knapsack(v, w, capacity);
		int [][] table = k.buildNaive();
		System.out.println("Table");
		Util.dump(table);
		List<Integer> list = k.traceBack(table);
		System.out.println("List of Value item index");
		list.stream().forEach(i -> System.out.print(" " + i));
		System.out.println();
	}
	
	@Test
	public void testgetOptimalSolution1() {
		Integer [] v = {0,3,2,4,4};
		Integer [] w = {0,4,3,2,3};
		testgetOptimalSolution(v, w, 6);
	}
	
	@Test
	public void testgetOptimalSolution2() {
		Integer [] v = {0,3,2,4,4};
		Integer [] w = {0,4,3,2,3};
		testgetOptimalSolution(v, w, 13);
	}
	
	@Test
	public void testgetOptimalSolution3() {
		Integer [] v = {0,4,2,4,3};
		Integer [] w = {0,2,3,3,4};
		testgetOptimalSolution(v, w, 13);
	}
	
	@Test
	public void testgetOptimalSolution4() {
		Random r = new Random(new Date().getTime());
		GraphGenerator g = new GraphGenerator();
		int len = 0;
		while (len < 5) {
			len = g.autoGenUnnsigned(r);
		}
		Integer [] v = genIntegerArray(len, r);
		Integer [] w = genIntegerArray(len, r);
		int total = 0;
		for (int i = 0; i < w.length; i ++) {
			total += w[i];
		}
		
		while (len < 5) {
			len = Math.abs(r.nextInt(total));
		}
		System.out.println("Input: " + len);
		Util.dumpInput(v, w);
		
		testgetOptimalSolution(v, w, len);
	}
	
	private Integer[] genIntegerArray(int len, Random r) {
		Integer [] a = new Integer[len];
		for (int i = 0; i < a.length; i ++) {
			a[i] = new Integer(Math.abs(r.nextInt(len)));
		}
		return a;
	}
}
