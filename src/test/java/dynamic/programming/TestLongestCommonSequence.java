package dynamic.programming;

import org.junit.Assert;
import org.junit.Test;

public class TestLongestCommonSequence {
	@Test
	public void test1() {
		String s1 = "bacbad";
		String s2 = "abazdc";
		LongestCommonSequence l = new LongestCommonSequence(s1, s2);
		int [][] table = l.buildTable();
		Util.dump(table);
		Assert.assertTrue(table[6][6] == 4);
		System.out.println("TraceBack");
		table = l.traceBack(table);
		Util.dump(table);
	}
}
