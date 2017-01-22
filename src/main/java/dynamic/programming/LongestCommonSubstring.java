package dynamic.programming;

public class LongestCommonSubstring {
	final String s1;
	final String s2;
	int max = 0;
	public LongestCommonSubstring(String s1, String s2) {
		this.s1 = " " + s1;
		this.s2 = " " + s2;
	}

	/**
	 * For any D(i,j) if s1[i] == s2[j], we set D(i,j) = true, otherwise
	 * D(i,j) = false 
	 * 
	 * Then the longest substring, will be the continues True in diagnose
	 * @return
	 */
	public boolean[][] buildTable0() {
		this.max = 0;
		boolean [][]table = new boolean[this.s1.length()][this.s2.length()];
		for (int i = 1; i < s1.length(); i ++) {
			for (int j= 1; j < this.s2.length(); j ++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					table[i][j] = true;
				}
				else {
					table[i][j] = false;
				}
			}
		}

		return table;
	}
	
	/**
	 * For any D(i,j) if s1[i] == s2[j], then its value will be
	 * D(i - 1, j - 1) + 1, otherwise it is 0.
	 * 
	 * Think about common substring, always starts with any
	 * s1[i] == s2[j]. For any char newly added char in s2, such as
	 * s1[i] == x, we have to trace back recursively d(i - 1, x - 1) 
	 * d(i - 2, x - 2) ... to see matches. Those value has been 
	 * computed and cached in table.  
	 * @return
	 */
	public int[][] buildTable() {
		this.max = 0;
		int [][]table =new int[this.s1.length()][this.s2.length()];
		for (int i = 1; i < s1.length(); i ++) {
			for (int j= 1; j < this.s2.length(); j ++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					table[i][j] = table[i - 1][j - 1] + 1;
					if (max < table[i][j]) {
						max = table[i][j];
					}
				}
			}
		}

		return table;
	}
}
