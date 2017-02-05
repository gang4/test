package dynamic.programming;

/*
 * https://courses.csail.mit.edu/6.006/fall10/handouts/dpproblems-sol.pdf
 * 
 * http://algorithms.tutorialhorizon.com/dynamic-programming-box-stacking-problem/
 */
public class BoxStacking {
	// array is [3][n]
	final int [][] boxes;
	public BoxStacking(int [][] boxes) {
		this.boxes = boxes;
	}
	
	/**
	 * If we sort boxes by largest base, then we reduce this problem to 
	 * find out longest increasing sequence.
	 * (1) Expends all surface of a box. Then we have 3 * n bases
	 * (2) Sort from large to small.
	 * @return
	 */
	public int[][] build() {
		// For each boxes, l * w * h, we define 
		// base 1 as l * w
		// base 2 as w * h
		// base 3 as h * l
		// For each base of a box, we fill in all other bases of boxes 
		// that can stack on top of it. 
		//
		// Extend input array of boxes to array of bases.
		// Each box have 3 bases.
		//
		// base l box j can be stacked top base k of box i, then
		// 		a(i*3 + k, 3*j + l) = a((i - 1)*3 + k, 3*(j + 1) + l) + 1;
		// else 
		// 		a(i*3 + k, 3*j + l) = a((i - 1)*3 + k, 3*j + l);
		int [][] table = new int[(this.boxes.length + 1) * 3][(this.boxes.length + 1) * 3];
		// 	Runtime is O(n * n)
		for (int i = 1; i <= this.boxes.length; i ++) {
			for (int k = 0; k < 3; k ++) {
				for (int j = 1; j <= this.boxes.length; j ++) {
					for (int l = 0; l < 3; l ++) {
						if (i == j) {
							table[i + k][j * 3 + l] = table[(i - 1) * 3 + k][j * 3 + l];
						}
						else {
							if (compareBox(this.boxes[i - 1], k, this.boxes[j - 1], l)) {
								table[i * 3 + k][j * 3 + l] = table[(i - 1) * 3 + k][(j - 1) * 3 + l] + 1;
							}
							else {
								table[i * 3 + k][j * 3 + l] = table[(i - 1) * 3 + k][j * 3 + l];
							}
						}
					}
				}
			}
		}
		return table;
	}

	private boolean compareBox(int[] i1, int k, int[] i2, int l) {
		int [] b1 = {i1[0], i1[1], i1[2], i1[0]};
		int [] b2 = {i2[0], i2[1], i2[2], i2[0]};
		int m1 = Math.min(b1[k], b1[k + 1]);
		int m2 = Math.max(b2[l], b2[l + 1]);
		
		return (m1 >= m2);
	}
}
