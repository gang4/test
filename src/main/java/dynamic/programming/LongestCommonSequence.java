package dynamic.programming;

/**
 * 
 * http://www.cs.cmu.edu/afs/cs/academic/class/15451-s15/LectureNotes/lecture04.pdf
 * 
 * https://www.google.com/search?q=Longest+common+subsequence+problem&sa=X&rlz=1C1GCEA_enUS787US787&stick=H4sIAAAAAAAAAOOQUeLUz9U3MMyyKDYxUiupLEgtVshPU0ipzEvMzUxWKCjKTy9KzM3NzEtXSMxJzy_KLMnIjZLIyc9LTy0uUUjOz83Nz1MoLk0qLikCqjnFCDbNrKjcwATKBpt8ipELxDYyLTGpTINKmFcWxmefYuQAsS3N4OqNTDLMsmHqC7PMzXKhEqYW5cVpvxiJdOMiVpyOBACLSHpi9AAAAA&biw=1054&bih=634&tbm=isch&source=iu&ictx=1&fir=8K0T7uBaGKIauM%253A%252CA23-Q_GgFVEY3M%252C%252Fm%252F01j8s4&vet=1&usg=AI4_-kRIfEipuLSI-Q5wadA-RTPJSZmYdw&ved=2ahUKEwi1u6X3wuXhAhVM4VQKHeeNA50Q_B0wGHoECAoQCQ#imgdii=6WNBowm_2y57UM:&imgrc=8K0T7uBaGKIauM:&vet=1
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
	 * Case 1: what if S[i] != T[j], Then, the desired subsequence has to ignore one of 
	 * S[i] or T[j] so we have:
	 * 		LCS[i, j] = max(LCS[i − 1, j], LCS[i, j − 1]).
	 * Case 2: what if S[i] = T[j], Then the LCS of S[1..i] and T[1..j] might as well 
	 * match them up. For instance, if I gave you a common subsequence that matched 
	 * S[i] to an earlier location in T, for instance, you could always match it to 
	 * T[j] instead. So, in this case we have:
	 *  	LCS[i, j] = 1 + LCS[i − 1, j − 1].
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
					/**
					 * table[i - 1, j - 1] table[i - 1, j]
					 * table[i, j - 1]     table[i,  j]
					 */
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
	public String traceBack(int [][] table) {
		StringBuilder str = new StringBuilder();
		for (int j = table[0].length - 1, i = table.length - 1; 0 < j && 0 < i;) {
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
				if (table[savedi][savedj] > table[i][j]) {
					str.insert(0, this.s1.charAt(savedi));
					//System.out.print(this.s1.charAt(savedi));
				}
			}
			//table[savedi][savedj] = -1;
		}
		
		return str.toString();
	}
}
