package dynamic.programming;

import org.junit.Assert;
import org.junit.Test;

import graph.greedy.MakingChange;

public class TestMakingChange {
	@Test
	public void test() {
		int [] v = {1,5,10};
		int c = 26;
		int [][] rt = new MakingChange(v, c).build();
		Util.dump(rt);
		Assert.assertTrue(rt[c][v.length - 1] == 4);
	}
	
	@Test
	public void test1() {
		int [] v = {1,3,4};
		int c = 6;
		int [][] rt = new MakingChange(v, c).build();
		Util.dump(rt);
		Assert.assertTrue(rt[6][v.length - 1] == 2);
	}
}
