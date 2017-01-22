package dynamic.programming;

/**
 * 
 * http://www.cs.cmu.edu/afs/cs/academic/class/15451-s15/LectureNotes/lecture04.pdf
 *
 */
public class LongestCommonSequence {
	final String s1;
	final String s2;
	public LongestCommonSequence(String s1, String s2) {
		this.s1 = " " + s1;
		this.s2 = " " + s2;
	}

	/**
	 * Case 1: what if S[i] 6= T[j]? Then, the desired subsequence has to ignore one of 
	 * S[i] or T[j] so we have:
	 * 		LCS[i, j] = max(LCS[i − 1, j], LCS[i, j − 1]).
	 * Case 2: what if S[i] = T[j]? Then the LCS of S[1..i] and T[1..j] might as well 
	 * match them up. For instance, if I gave you a common subsequence that matched 
	 * S[i] to an earlier location in T, for instance, you could always match it to 
	 * T[j] instead. So, in this case we have:
	 *  	CS[i, j] = 1 + LCS[i − 1, j − 1].
	 * @return
	 */
	public int[][] buildTable() {
		int [][]table = new int[this.s1.length()][this.s2.length()];
		for (int i = 1; i < s1.length(); i ++) {
			for (int j= 1; j < this.s2.length(); j ++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					table[i][j] = table[i - 1][j - 1] + 1;
				}
				else {
					table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
				}
			}
		}

		return table;
	}
	
	/**
	 * To find the sequence, we just walk backwards through
	 * matrix starting the lower-right corner. If either the 
	 * cell directly above or directly to the right contains 
	 * a value equal to the value in the current cell, then 
	 * move to that cell (if both to, then chose either one). 
	 * If both such cells have values strictly less than the 
	 * value in the current cell, then move diagonally up-left 
	 * (this corresponds to applying Case 2), and output the 
	 * associated character
	 * @param table
	 * @return
	 */
	public int[][] traceBack(int [][] table) {
		for (int i = table.length - 1; 0 < i;) {
			for (int j = table[0].length - 1; 0 < j && 0 < i;) {
				int savedi = i;
				int savedj = j;
				if (table[i][j] == table[i - 1][j]) {
					i --;
				}
				else if (table[i][j] == table[i][j - 1]) {
					j --;
				}
				else {
					i --;
					j --;
				}
				table[savedi][savedj] = -1;
			}
		}
		return table;
	}
}
