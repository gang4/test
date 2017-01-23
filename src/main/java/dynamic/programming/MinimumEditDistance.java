package dynamic.programming;

/**
 * The minimum edit distance between two string
 * Is the minimum number of editing operation
 *  Insertion
 *  Deletion
 *  Substitution
 *  Needed to transform one into the other
 * https://web.stanford.edu/class/cs124/lec/med.pdf
 */
public class MinimumEditDistance {
	final String s1;
	final String s2;
	static int costInsert = 1;
	static int costDelete = 1;
	public MinimumEditDistance(String s1, String s2) {
		this.s1 = " " + s1;
		this.s2 = " " + s2;
	}
	
	/**
	 * We give cost of delete and insert to 1. Here is how to 
	 * initialize table.
	 * D(0,0) = 0
	 * D(i,0) = D(i-1,0) + del[x(i)]; 1 < i ≤ N
	 * D(0,j) = D(0,j-1) + ins[y(j)]; 1 < j ≤ M
	 * 
	 * What this real means, for any two chars that adjacent,
	 * the cost of alignment is 1 if delete or insert apply
	 * 
	 * The initial only aligns itself, so no substitute.
	 * @param table
	 */
	private void init(int [][] table) {
		table[0][0] = 0;
		for (int i = 1; i < table.length; i ++) {
			table[i][0] = table[i - 1][0] + costInsert;
		}
		
		for (int j = 1; j < table[0].length; j ++) {
			table[0][j] = table[0][j - 1] + costDelete;
		}
	}
	
	/**
	 * We give cost of substitute to 2. 
	 * 
	 * We assume before D(i,j), all sub problem has been resolved
	 * That is D(i -1, j), D(i, j - 1) and D(i - 1, j - 1).
	 * For D(i, j), we have (A) delete or insert can apply
	 * to either D(i -1, j) or D(i, j - 1) with cost 1 or (B) we can do 
	 * nothing (cost 0) for D(i - 1, j - 1) if S(i) == S(j), or apply 
	 * substitute to either S(i) or S(j) because they are not the 
	 * same and cost 2.
	 *  
	 * @return
	 */
	public int [][] buildTable() {
		int [][] table = new int[s1.length()][s2.length()];
		init(table);
		for (int i = 1; i < s1.length(); i ++) {
			for (int j= 1; j < this.s2.length(); j ++) {
				int cost = s1.charAt(i) == s2.charAt(j) ? 0 : 2;
				table[i][j] = Math.min(table[i - 1][j] + costInsert, table[i][j - 1] + costDelete);
				table[i][j] = Math.min(table[i][j], table[i - 1][j - 1] + cost);
			}
		}
		return table;
	}
	
	public int[][] traceBack(int [][] table) {
		table[table.length - 1][table[0].length - 1] = -1;
		for (int i = table.length - 1; 0 < i;) {
			for (int j = table[0].length - 1; 0 < j && 0 < i;) {
				int d = table[i - 1][j - 1];
				int left = table[i - 1][j];
				int down = table[i][j - 1];
				if (d < left && d < down) {
					table[i - 1][j - 1] = -1; // same
					i --;
					j --;
				}
				else if (left < down) {
					table[i - 1][j] = -1;
					i --;
				}
				else {
					table[i][j - 1] = -1;
					j --;
				}
			}
		}
		return table;
	}
}
