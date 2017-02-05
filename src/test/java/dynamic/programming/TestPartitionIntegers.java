package dynamic.programming;

import java.util.Date;
import java.util.Random;

import org.junit.Test;

import graph.GraphGenerator;

public class TestPartitionIntegers {
	@Test
	public void test() {
		Integer [] a = {0, 2, 4, 7, 5, 9};
		int [][] table = new PartitionIntegers(a).buildTable();
		int [] s = {0, 0, 2, 4, 7, 5, 9};
		if (table != null) {
			Util.dump(table, s, s);
		}
	}
	
	@Test
	public void test1() {
		Integer [] a = {0, 8, 4, 7, 5, 9, 45};
		int [][] table = new PartitionIntegers(a).buildTable();
		int [] s = {0, 0, 8, 4, 7, 5, 9, 45};
		Util.dump(table, s, s);
	}
	
	@Test
	public void test2() {
		Random r = new Random(new Date().getTime());
		GraphGenerator g = new GraphGenerator();
		int len = 0;
		while (len < 5) {
			len = g.autoGenUnnsigned(r);
		}
		Integer [] v = genIntegerArray(len, r);
		v[0] = 0;
		int [][] table = new PartitionIntegers(v).buildTable();
		int [] s = new int[v.length + 1];
		s[0] = 0;
		for (int i = 1; i < v.length + 1; i ++) {
			s[i] = v[i - 1];
		}
		Util.dump(table, s, s);
	}
	private Integer[] genIntegerArray(int len, Random r) {
		Integer [] a = new Integer[len];
		for (int i = 0; i < a.length; i ++) {
			a[i] = new Integer(Math.abs(r.nextInt(len)));
		}
		return a;
	}
}
