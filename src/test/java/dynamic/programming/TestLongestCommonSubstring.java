package dynamic.programming;

import org.junit.Assert;
import org.junit.Test;

public class TestLongestCommonSubstring {
	@Test
	public void test1() {
		String s1 = "abcxyzay";
		String s2 = "xyzabcb";
		int [] set = {0, 1,2,3,4,5,6,7};
		LongestCommonSubstring l = new LongestCommonSubstring(s1, s2);
		boolean[][] bt = l.buildTable0();
		Util.dump(bt, set);
		int [][] table = l.buildTable();
		Util.dump(table);
		System.out.println("Max: " + l.max);
		Assert.assertTrue(l.max == 4);
	}
	
	@Test
	public void test2() {
		String s1 = "abab";
		String s2 = "baba";
		int [] set = {0, 1,2,3,4};
		LongestCommonSubstring l = new LongestCommonSubstring(s1, s2);
		boolean[][] bt = l.buildTable0();
		Util.dump(bt, set);
		int [][] table = l.buildTable();
		Util.dump(table);
		System.out.println("Max: " + l.max);
		Assert.assertTrue(l.max == 3);
	}
}
