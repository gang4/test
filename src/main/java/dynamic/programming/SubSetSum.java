package dynamic.programming;

/**
 * 
 * https://cseweb.ucsd.edu/classes/wi12/cse202-a/lecture7-final.pdf
 * t is sum of a subset
 * STEP 1: Define subtasks
 * 	For i=1..n, s=1..t,
 * 		S(i,s) 	= True, if some subset of a[1..i] adds to s
 *   	   		= False, ow
 *  Output = S(n, t)
 * STEP 2: Express recursively
 *  If a[i] <= s:
 *  	S(i,s) = S(i - 1, s - a[i]) OR S(i - 1, s)
 *  Else: 
 *  	S(i, s) = S(i - 1, s)
 * STEP 3: Order of subtasks
 *  Row by row, increasing column
 */
public class SubSetSum {
	final int [] set;
	int value;
	public SubSetSum(int [] set, int value) {
		this.set = set;
		this.value = value;
	}
	
	/**
	 * We want to find out there is a sub set that can sum up to "this.value"
	 * @return
	 */
	public boolean [][] find() {
		this.value ++;
		boolean [][]table = new boolean [this.value][set.length];
		// Initialize the table.
		// For the first column, since value is 0, it will any numbers subset
		for (int j = 0; j < set.length; j ++) {
			table[0][j] = true;
		}
		// First row, 0 is not anyone's subset.
		for (int i = 1; i < this.value; i ++) {
			table[i][0] = false;
		}
		// J = row, i = column
		for (int j = 1; j < set.length; j ++) {
			for (int i = 1; i < this.value; i ++) {
				if (set[j] > i) {
					// set[j] can not be in subset. Inherent the true or false of
					// 1 ... (j - 1).
					table[i][j] = table[i][j - 1];
				}
				else {
					// set[j] can be in subset, subtract set[j]
					// from current set to see whether subset is in or not.
					int index = i - set[j];
					if (index == 0) {
						// Any one's subset
						table[i][j] = true;
					}
					else {
						// Need to see whether we have 
						// subset of i - set[j] without set[j]
						table[i][j] = table[index][j -1];
					}
				}
			}
		}
		return table;
	}
}
