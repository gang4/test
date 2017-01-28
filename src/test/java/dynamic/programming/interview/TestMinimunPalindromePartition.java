package dynamic.programming.interview;

import org.junit.Assert;
import org.junit.Test;

public class TestMinimunPalindromePartition {
	@Test
	public void test() {
		MinimunPalindromePartition m = new MinimunPalindromePartition("abcbaab");
		String s = m.buildTable();
		System.out.println(s);
		Assert.assertTrue(s.compareTo("b,a,abcba,") == 0);
	}
}
