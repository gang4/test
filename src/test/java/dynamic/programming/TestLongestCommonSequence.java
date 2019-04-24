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
		Util.dump(table, " " + s1, " " + s2);
		Assert.assertTrue(table[6][6] == 4);
		System.out.println("TraceBack");
		String lcs = l.traceBack(table);
		System.out.println("LCS: " + lcs);
		
		s1 = "AGGTAB";
		s2 = "GXTXAYB";
		l = new LongestCommonSequence(s1, s2);
		table = l.buildTable();
		Util.dump(table, " " + s1, " " + s2);
		System.out.println("TraceBack");
		lcs = l.traceBack(table);
		System.out.println("LCS: " + lcs);
	}
}
