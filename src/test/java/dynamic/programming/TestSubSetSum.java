package dynamic.programming;

import org.junit.Assert;
import org.junit.Test;

import dynamic.programming.SubsetSum;

public class TestSubSetSum {
	@Test
	public void test1() {
		int [] set = {0, 12, 1, 3, 8, 2, 5};
		int total = 0;
		for (int i = 0; i < set.length; i ++) {
			total += set[i];
		}
		Util.dump(set);
		int max = 14;
		System.out.println("total : " + total + "; Sum : " + max);
		boolean [][] table = new SubsetSum(set, max).find();
		Util.dump(table, set);
		System.out.println();
		Assert.assertTrue(table[14][5]);
	}
	
	@Test
	public void test2() {
		int [] set = {0, 12, 1, 3, 8, 2, 5};
		int total = 0;
		for (int i = 0; i < set.length; i ++) {
			total += set[i];
		}
		Util.dump(set);
		int max = 6;
		System.out.println("total : " + total + "; Sum : " + max);
		boolean [][] table = new SubsetSum(set, max).find();
		Util.dump(table, set);
		System.out.println();
		Assert.assertTrue(table[6][5]);
		Assert.assertTrue(table[6][6]);
	}
	
	@Test
	public void test3() {
		int [] set = {0, 12, 1, 3, 8, 2, 5};
		int total = 0;
		for (int i = 0; i < set.length; i ++) {
			total += set[i];
		}
		Util.dump(set);
		int max = 15;
		System.out.println("total : " + total + "; Sum : " + max);
		boolean [][] table = new SubsetSum(set, max).find();
		Util.dump(table, set);
		System.out.println();
		Assert.assertTrue(table[15][6]);
	}
	
	@Test
	public void test4() {
		int [] set = {0, 12, 1, 3, 8, 2, 5};
		int total = 0;
		for (int i = 0; i < set.length; i ++) {
			total += set[i];
		}
		Util.dump(set);
		int max = 13;
		System.out.println("total : " + total + "; Sum : " + max);
		boolean [][] table = new SubsetSum(set, max).find();
		Util.dump(table, set);
		System.out.println();
		Assert.assertTrue(table[13][5]);
	}
}
