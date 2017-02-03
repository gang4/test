package dynamic.programming;

import org.junit.Assert;
import org.junit.Test;

public class TestMaxSumContiguousSubsequence {
	@Test
	public void test() {
		int []  s = {0, -2, 11, -4, 13, -5, 2 };
		MaxSumContiguousSubsequence m = new MaxSumContiguousSubsequence(s);
		int [][] table = m.buildTable();
		Util.dump(table, s);
		System.out.println("Max: " + m.getCount());
		Assert.assertTrue(m.getCount() == 20);
	}
	@Test
	public void test1() {
		int [] s = {-15, 29, -36, 3, -22, 11, 19, -5};
		MaxSumContiguousSubsequence m = new MaxSumContiguousSubsequence(s);
		int [][] table = m.buildTable();
		Util.dump(table, s);
		System.out.println("Max: " + m.getCount());
		Assert.assertTrue(m.getCount() == 30);
	}
}
