package dynamic.programming;

import org.junit.Assert;
import org.junit.Test;

public class TestLongestIncreasingSubsequence {
	@Test
	public void test() {
		int [] s = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		int [] rt = new LongestIncreasingSubsequence(s).buildTable();
		int [] rtn = {0,4,6,9,11,15,0,0,0,0,0,0,0,0,0,0};
		for (int i = 0; i < rt.length; i ++) {
			Assert.assertTrue(rt[i] == rtn[i]);
			if (i != 0) {
				System.out.print(",");
			}
			System.out.print(rt[i]);
		}
		System.out.println();
	}
}
