package dynamic.programming;

import java.util.Date;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import dynamic.programming.OptimalBinarySearchTree.Callback;

public class TestOptimalBinarySearchTree {
	class MyCallback implements Callback {
		private int count = 1;
		private int len;
		private int saved;
		public MyCallback(int len) {
			this.len = len;
			this.saved = this.len - 1;
		}

		@Override
		public int getIntValue() {
			if (this.len == 0) {
				this.len = this.saved;
				this.saved --;
				count ++;
			}
			this.len --;
			return count;
		}
	}
	
	@Test
	public void test() {
		
		Random r = new Random(new Date().getTime());
		int len = 0;
		while (len < 5) {
			len = Math.abs(r.nextInt(32));
		}
		Integer [] keys = new Integer[len];
		OptimalBinarySearchTree tree = new OptimalBinarySearchTree(keys, null);
		int [][] table = tree.dumpPaThTable(new MyCallback(len));
		Util.dump(table);
		for (int i = 0; i < table.length - 1; i ++) {
			Assert.assertTrue(table[i][i] == table[i + 1][i + 1]);
		}
		for (int i = 0; i < table.length - 1; i ++) {
			Assert.assertTrue(table[i][i + 1] == 2);
		}
		for (int i = 0; i < table.length - 2; i ++) {
			Assert.assertTrue(table[i][i + 2] == 3);
		}
	}
	
	@Test
	public void test1() throws Exception {
		double [] p = {0.4,	0.05, 0.15,	0.05, 0.1, 0.25};
		Integer [] keys = {1,2,3,4,5,6};
		OptimalBinarySearchTree tree = new OptimalBinarySearchTree(keys, p);
		double [][] table = tree.buildTable();
		//		 0.40 0.00 0.00 0.00 0.00 0.00
		//		 0.50 0.05 0.00 0.00 0.00 0.00
		//		 0.85 0.25 0.15 0.00 0.00 0.00
		//		 1.00 0.35 0.25 0.05 0.00 0.00
		//		 1.35 0.60 0.50 0.20 0.10 0.00
		//		 2.10 1.20 1.05 0.60 0.45 0.25
		Util.dump(table);
		for (int i = 0; i < table.length - 1; i ++) {
			Assert.assertTrue(table[i][i] == p[i]);
		}
		double [] p1 = {0.5, 0.25, 0.25, 0.20,	0.45};
		for (int i = 0; i < table.length - 1; i ++) {
			Assert.assertTrue(Math.abs(table[i][i + 1] - p1[i]) < 0.01);
		}
		double [] p2 = {0.85, 0.35, 0.5, 0.60};
		for (int i = 0; i < table.length - 2; i ++) {
			Assert.assertTrue(Math.abs(table[i][i + 2] - p2[i]) < 0.01);
		}
		double [] p3 = {1.0, 0.6, 1.05};
		for (int i = 0; i < table.length - 3; i ++) {
			Assert.assertTrue(Math.abs(table[i][i + 3] - p3[i]) < 0.01);
		}
		double [] p4 = {1.35, 1.2};
		for (int i = 0; i < table.length - 4; i ++) {
			Assert.assertTrue(Math.abs(table[i][i + 4] - p4[i]) < 0.01);
		}
		Assert.assertTrue(Math.abs(table[0][5] - 2.1) < 0.01);
	}
}
