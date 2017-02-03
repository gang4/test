package dynamic.programming;

public class MaxSumContiguousSubsequence {
	final int [] s;
	private int count;
	public int getCount() {
		return count;
	}

	public MaxSumContiguousSubsequence(int [] s) {
		this.s = s;
	}
	
	public int [][] buildTable() {
		// Do we really need a table, No.
		// We only need an array
		int [][] table = new int [s.length][s.length];
		/**
		 * Given any sequence find out the max sum of contiguous sequence. 
		 * We have
		 * a(i, j) = a(i, j - 1) + a(j)
		 * where i < j;
		 */
		this.count = Integer.MIN_VALUE;
		for (int i = 1; i < table.length; i ++) {
			for (int j = i; j < table[0].length; j ++) {
				table[i][j] = table[i][j - 1] + s[j];
				if (this.count < table[i][j]) {
					this.count = table[i][j];
				}
			}
		}
		return table;
	}
}
