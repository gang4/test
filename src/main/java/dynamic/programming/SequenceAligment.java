package dynamic.programming;

/**
 * (1) http://www.avatar.se/molbioinfo2001/dynprog/dynamic.html
 * (2) https://www.coursera.org/learn/algorithm-design-analysis-2/lecture/tNmae/a-dynamic-programming-algorithm
 * We use (1) since there is no detail calculation in (2) 
 * 
 */
public class SequenceAligment {
	final private String s1;
	final private String s2;
	public SequenceAligment(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	
	/**
	 * There is error in (1). 
	 * For table[6][5]:
	 * in (1),  table[6][5] = 3
	 * in here, table[6][5] = 4
	 * If look cells around table[6][5]
	 * table[5][4] = 3
	 * table[6][4] = 3
	 * table[5][5] = 3
	 * also, table[6][5] is match, both are character C. So, the value has to be 4, rather than 3.
	 *  
	 * A 0 1 2 3 3 3 4 5 5 5 5 6
	 * G 0 1 2 2 3 3 4 4 5 5 5 5
	 *
	 * C 0 1 2 2 3 3 4 4 4 4 4 4  5  correct one.
  	 *   0 1 2 2 3 3 3 4 4 4 4 4     <-- error in (1) 
	 *
	 * T 0 1 2 2 3 3 3 3 3 3 3 3  4
	 * A 0 1 2 2 2 2 2 2 2 2 2 3  3
     * G 0 1 1 1 1 1 1 1 2 2 2 2  2
     * G 0 1 1 1 1 1 1 1 1 1 1 1  1
     *   0 0 0 0 0 0 0 0 0 0 0 0
     *     G A A T T C A G T T A
     *     1 2 3 4 5 6 7 8 9 
     * 
	 * @return
	 */
	public int [][] buildTable() {
		int [][] table = new int[s1.length()][s2.length()];
		// match score = 1
		// mismatch, gap == 0
		//
		// a(i, j) = Max{a(i -1, j - 1) + match/mismatch, a(i - 1, j) + gap, a(i, j -1) + gap}
		// Fill in table. We do not need to initialize the first row and column of the table
		// by default, it is initialized.
		int gap = 0;
		for (int i = 1; i < s1.length(); i ++) {
			for (int j = 1; j < s2.length(); j ++) {
				int a1 = table[i - 1][j - 1] + (s1.charAt(i) == s2.charAt(j) ? 1 : 0);
				int a2 = table[i - 1][j] + gap;
				int a3 = table[i][j - 1] + gap;
				table[i][j] = Math.max(a1, Math.max(a2, a3));
			}
		}
		return table;
	}
	/**
	 * For (1), the trace back is also different. see reason in table build.
	 * We mark the trace as -1.
	 *  0  1  2  3  3  3  4  5  5  5  5 -1
 	 *  0  1  2  2  3  3  4  4  5 -1 -1  5
 	 * 	0  1  2  2  3  3  4 -1 -1  4  4  4
 	 * 	0  1  2  2  3 -1 -1  3  3  3  3  3
 	 * 	0  1  2 -1 -1  2  2  2  2  2  2  3
 	 *  0  1 -1  1  1  1  1  1  2  2  2  2
 	 *  0 -1  1  1  1  1  1  1  1  1  1  1
 	 *  0  0  0  0  0  0  0  0  0  0  0  0
	 * @param table
	 * @return
	 */
	public int [][] traceBack(int [][] table) {
		for (int i = s1.length() - 1; 1 <= i; i --) {
			for (int j = s2.length() - 1; 1 <= j; j --) {
				int a1 = table[i][j];
				int a2 = 0 < i ? table[i - 1][j] : 0;
				int a3 = 0 < j ? table[i][j - 1] : 0;
				// Shift to left
				if (a1 == a2) {
					int k = i;
					for (; 0 < k; k --) {
						if (table[k][j] == table[k - 1][j]) {
							table[k][j] = -1;
							continue;
						}
						break;
					}
					i = k;
				}
				// shift to upper
				else if (a1 == a3) {
					int k = j;
					for (; 0 < k; k --) {
						if (table[i][k] == table[i][k - 1]) {
							table[i][k] = -1;
							continue;
						}
						break;
					}
					j = k;
				}
				// shift both left and upper by 1
				else {
					table[i][j] = -1;
					i --; //
				}
			}
		}
		return table;
	}
}
