package dynamic.programming;

import org.junit.Assert;
import org.junit.Test;

public class TestMinimumEditDistance {
	@Test
	public void test1() {
		String s1 = "intention";
		String s2 = "execution";
		EditDistance d = new EditDistance(s1, s2);
		int [][] table = d.buildTable();
		Util.dump(table);
		Assert.assertTrue(table[s1.length() - 1][s2.length() - 1] == 8);
		table = d.traceBack(table);
		System.out.println("Trace Back");
		Util.dump(table);
	}
	
	@Test
	public void test2() {
		String s1 = "Zeil";
		String s2 = "trials";
		EditDistance d = new EditDistance(s1, s2);
		int [][] table = d.buildTable();
		Util.dump(table);
		Assert.assertTrue(table[s1.length() - 1][s2.length() - 1] == 6);
		table = d.traceBack(table);
		System.out.println("Trace Back");
		Util.dump(table);
	}
}
