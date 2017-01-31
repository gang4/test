package dynamic.programming.interview;

public class MaxSqureMatrix {
	/**
	 * Give a matrix N x N
	 * each item v[i,j] in matrix, the value is either 0 or 1
	 * Find the max sub matrix that has all value items value 1.
	 * 
	 * item = min {v[i - 1, j - 1], v[i - 1, j], v[i, j - 1]}
	 * v[i, j] = squre((square-root(item) + 1)) 
	 * 
	 * Matrix
	 *  0 0 0 0 0 0
	 *  0 1 1 1 1 0
	 *  0 1 1 1 1 1
	 *  0 0 1 1 1 1
	 *  
	 * Value matrix
	 *  0 0 0 0 0 0  
	 *  0 1 1 1 1 0 
	 *  0 1 4 4 4 1
	 *  0 0 1 4 9 4
	 */
	int [][] matrix;
	int [][] table = null;
	int max = 0;
	public MaxSqureMatrix(int [][] matrix) {
		this.matrix = matrix;
	}
	
	private void init() {
		table = new int[this.matrix.length][this.matrix[0].length];
		for (int i = 0; i < this.matrix.length; i ++) {
			table[i][0] = matrix[i][0];
		}
		
		for (int i = 0; i < this.matrix[0].length; i ++) {
			table[0][i] = matrix[0][i];
		}
		this.max = 0;
	}
	
	public int[][] buildTable() {
		init();

		for (int i = 1; i < this.matrix.length; i ++) {
			for (int j = 1; j < this.matrix[0].length; j ++) {
				if (this.matrix[i][j] == 0) {
					table[i][j] = 0;
				}
				else {
					int value = Math.min(table[i - 1][j - 1], Math.min(table[i - 1][j], table[i][j - 1]));
					value = (int) Math.sqrt(value);
					table[i][j] = (value + 1) * (value + 1);
					if (max < table[i][j]) {
						max  = table[i][j];
					}
				}
			}			
		}
		return this.table;
	}
}
